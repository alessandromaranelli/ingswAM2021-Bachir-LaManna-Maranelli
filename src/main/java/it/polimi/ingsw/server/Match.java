package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.GameStartMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.model.TurnState;

import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Match {
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
    private Controller controller = new Controller(clientConnectionThreads);
    private boolean isFull=false;


    public Match() throws FileNotFoundException {
    }


    public void createMatch() {
        new Thread(this::waitReady).start();
        runServer();
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

    public void runServer() {
        if(controller.getNumberOfPlayers() > 0 && clientConnectionThreads.size() == controller.getNumberOfPlayers()){
            isFull=true;
        }

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
                            GameStartMsg gameStartMsg = new GameStartMsg(controller.getGame().getTable().getMarket().getMarketTable(), controller.getGame().getTable().getMarket().getMarbleInExcess(), controller.getGame().getTable().getTopDevelopmentcards(), controller.getGame().getCurrentPlayer().getNickname(), TurnState.PREPARATION);
                            c.sendAnswerMessage(gameStartMsg);
                        } else {
                            GameStartMsg gameStartMsg = new GameStartMsg(controller.getGame().getTable().getMarket().getMarketTable(), controller.getGame().getTable().getMarket().getMarbleInExcess(), controller.getGame().getTable().getTopDevelopmentcards(), controller.getGame().getCurrentPlayer().getNickname(), TurnState.ENDTURN);
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
}
