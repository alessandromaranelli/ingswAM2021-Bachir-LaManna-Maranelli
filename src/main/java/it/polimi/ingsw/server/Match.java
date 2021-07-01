package it.polimi.ingsw.server;

import Exceptions.ModelException;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.model.TurnState;

import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Match.
 */
public class Match {
    private Server server;
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
    private final Set<ClientHandler> clientConnectionThreadsNotConnected = new LinkedHashSet<>();
    private Controller controller = new Controller(clientConnectionThreads,this);
    private boolean isFull=true;


    /**
     * Instantiates a new Match.
     *
     * @param server the server
     * @throws FileNotFoundException the file not found exception
     */
    public Match(Server server) throws FileNotFoundException {
        this.server=server;
    }


    /**
     * CreateMatch starts a thread that will wait for every player to connect before starting the game.
     */
    public void createMatch() {
        new Thread(this::waitReady).start();
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Gets client connection threads.
     *
     * @return the client connection threads
     */
    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return isFull;
    }

    /**
     * Sets full.
     *
     * @param full the full
     */
    public void setFull(boolean full) {
        isFull = full;
    }

    /**
     * WaitReady method is executed by a thread that first waits for every client to be ready. When all the clients are ready
     * it starts the game and sends every client the GameStartMessage.
     */
    public void waitReady() {
        while (true) {
            try {
                if (areAllReady() && controller.getNumberOfPlayers() > 0 && this.clientConnectionThreads.size() == controller.getNumberOfPlayers()) {     //vedi controller
                    System.out.println("Starting game...");
                    controller.startGame();
                    controller.getGame().getPlayers().get(0).setAsCurrentPlayer();
                    for (ClientHandler c : clientConnectionThreads) {
                        c.sendAnswerMessage(new StringMsg("\n======  WELCOME TO MASTER OF RENAISSANCE  ======"));
                        c.sendAnswerMessage(new StringMsg("\n\nGame is starting now\n\n"));
                        if (c.getPlayerID() == 1) {
                            GameStartMsg gameStartMsg = new GameStartMsg(controller.getGame().getTable().getMarket().getMarketTable(), controller.getGame().getTable().getMarket().getMarbleInExcess(), controller.getGame().getTable().getTopDevelopmentcards(), controller.getGame().getCurrentPlayer().getNickname(), TurnState.PREPARATION, controller.getGame().isSoloMatch());
                            c.sendAnswerMessage(gameStartMsg);
                        } else {
                            GameStartMsg gameStartMsg = new GameStartMsg(controller.getGame().getTable().getMarket().getMarketTable(), controller.getGame().getTable().getMarket().getMarbleInExcess(), controller.getGame().getTable().getTopDevelopmentcards(), controller.getGame().getCurrentPlayer().getNickname(), TurnState.ENDTURN, controller.getGame().isSoloMatch());
                            c.sendAnswerMessage(gameStartMsg);
                        }
                    }
                    return;

                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Are all ready boolean.
     *
     * @return the boolean
     */
    public boolean areAllReady() {
        return clientConnectionThreads.stream().allMatch(ClientHandler::isReady);
    }


    /**
     * CheckClientConnection removes the clientHandler from the Match when a client disconnects.
     *
     * @param clientHandler the client handler
     */
    public void checkClientConnection(ClientHandler clientHandler){
        if (!clientHandler.isConnected()){
            server.getLobby().getUnicodeList().put(clientHandler.getUnicode(),this);
            clientConnectionThreads.remove(clientHandler);
            controller.getClientConnectionThreads().remove(clientHandler);
            clientConnectionThreadsNotConnected.add(clientHandler);
        }
    }


    /**
     * Method that reconnects a client to the match.
     *
     * @param c the clientHandler
     * @throws ModelException the model exception
     */
    public void reAdd(ClientHandler c) throws ModelException {
        ClientHandler disconnectedOne=null;
        for (ClientHandler clientHandler:clientConnectionThreadsNotConnected){
            if(c.getUnicode().equals(clientHandler.getUnicode())){
                disconnectedOne=clientHandler;
                c.setConnected(true);
                for(Player p: controller.getPlayerClientHandlerMap().keySet()){
                    if(controller.getPlayerClientHandlerMap().get(p).equals(clientHandler)){
                        c.setPlayerID(p.getPlayerID());
                        controller.getPlayerClientHandlerMap().put(p,c);
                        controller.getClientConnectionThreads().add(c);
                        System.out.println("Client reconnected");
                        break;
                    }
                }
                c.setController(controller);
                c.setReady();
                c.sendAnswerMessage(new UpdatePhaseMsg(TurnState.BEFORESTART,"You reconnected successfully!!!\n\n"));
                if(clientHandler.getPlayerID()>0&&controller.isGameStarted()) {
                    c.sendAnswerMessage(new GameReJoinMsg(controller.getGame().getPlayerById(clientHandler.getPlayerID()).getNickname(),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getTrack().indexOf(
                                    controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getPopeFavours().
                                    stream().map(PopeFavour::isActivated).toArray(Boolean[]::new),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getWareHouse().getTypeStorage(1),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getWareHouse().getTypeStorage(2),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getWareHouse().getTypeStorage(3),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getWareHouse().getStorages().
                                    stream().map(Storage::getQuantity).toArray(Integer[]::new),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getWareHouse().getMapfromChest(),
                            controller.getGame().getTable().getMarket().getMarketTable(),
                            controller.getGame().getTable().getMarket().getMarbleInExcess(),
                            controller.getGame().getTable().getTopDevelopmentcards(),
                            (controller.getGame().getCurrentPlayer()!=null)? controller.getGame().getCurrentPlayer().getNickname() : "not started yet",
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPhase(),
                            controller.getGame().isSoloMatch()));
                    c.sendAnswerMessage(new UpdateLeaderCardsMsg(controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPhase(),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getLeaderCardsInHand(),
                            controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getLeaderCardsPlayed(),
                            "\n"));
                }
                else if(clientHandler.getPlayerID()>0){
                    UpdateNicknameMsg updateNicknameMsg = new UpdateNicknameMsg(controller.getGame().getPlayerById(clientHandler.getPlayerID()).getNickname(),
                            c.getPlayerID(), controller.getNumberOfPlayers());
                    c.sendAnswerMessage(updateNicknameMsg);
                }

            }
        }
        if(disconnectedOne!=null) clientConnectionThreadsNotConnected.remove(disconnectedOne);
    }

    /**
     * CloseMatch removes the match from the list of active matches when a game has ended.
     */
    public void closeMatch(){
        server.removeGame(this);
        server.getLobby().getUnicodeList().forEach((s, match) -> {if(match.equals(this))server.getLobby().getUnicodeList().remove(s,match);});
    }
}
