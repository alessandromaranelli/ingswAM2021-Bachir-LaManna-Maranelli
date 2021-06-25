package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.GameReJoinMsg;
import it.polimi.ingsw.messages.answers.GameStartMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TurnState;

import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Match {
    private Server server;
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
    private final Set<ClientHandler> clientConnectionThreadsNotConnected = new LinkedHashSet<>();
    private Controller controller = new Controller(clientConnectionThreads,this);
    private boolean isFull=true;
    private Thread x;


    public Match(Server server) throws FileNotFoundException {
        this.server=server;
    }


    public void createMatch() {
        new Thread(this::waitReady).start();
    }

    public Controller getController() {
        return controller;
    }

    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

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

    public boolean areAllReady() {
        return clientConnectionThreads.stream().allMatch(ClientHandler::isReady);
    }


    public void checkClientConnection(ClientHandler clientHandler){
        if (!clientHandler.isConnected()){
            server.getLobby().getUnicodeList().put(clientHandler.getUnicode(),this);
            clientConnectionThreads.remove(clientHandler);
            clientConnectionThreadsNotConnected.add(clientHandler);
        }
    }


    public void reAdd(ClientHandler c){
        for (ClientHandler clientHandler:clientConnectionThreadsNotConnected){
            if(c.getUnicode().equals(clientHandler.getUnicode())){
                c.setConnected(true);
                for(Player p: controller.getPlayerClientHandlerMap().keySet()){
                    if(controller.getPlayerClientHandlerMap().get(p).equals(clientHandler)){
                        controller.getPlayerClientHandlerMap().put(p,c);
                        controller.getClientConnectionThreads().remove(clientHandler);
                        controller.getClientConnectionThreads().add(c);
                        System.out.println("Client reconnected");
                        break;
                    }
                }
                clientConnectionThreadsNotConnected.remove(clientHandler);
                clientConnectionThreads.add(c);
                c.setController(controller);
                c.sendAnswerMessage(new StringMsg("You reconnected successfully!!!\n\n"));
                c.sendAnswerMessage(new GameReJoinMsg(controller.getGame().getPlayerById(clientHandler.getPlayerID()).getNickname(),
                        controller.getGame().getTable().getMarket().getMarketTable(),
                        controller.getGame().getTable().getMarket().getMarbleInExcess(),
                        controller.getGame().getTable().getTopDevelopmentcards(),
                        controller.getGame().getCurrentPlayer().getNickname(),
                        controller.getGame().getPlayerById(clientHandler.getPlayerID()).getPhase(),
                        controller.getGame().isSoloMatch()));

            }
        }
    }

    public void closeMatch(){
        server.removeGame(this);
    }
}
