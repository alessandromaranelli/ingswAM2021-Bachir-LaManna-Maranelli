package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.GameStartMsg;
import it.polimi.ingsw.model.TurnState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;

public class Server {
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
    private Controller controller= new Controller(clientConnectionThreads);
    public static final int PORT = 1235;
    private boolean listening = true;

    public Server() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {

        Server s = new Server();
        s.createLobby();
    }

    public void createLobby() {
        new Thread(this::waitReady).start();
        runServer();
    }

    public void runServer(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server waiting for connections...");
            while(listening){
                Socket client = serverSocket.accept();
                new ClientHandler(client, controller);
                if (clientConnectionThreads.size()==controller.getNumberOfPlayers()) listening=false;
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

    public void waitReady(){
        while (true) {
            try {
                if (areAllReady() && this.clientConnectionThreads.size() == controller.getNumberOfPlayers()) {     //vedi controller
                    System.out.println("Starting game...");
                    listening=false;
                    controller.startGame();   //non so se serve
                    controller.getGame().getPlayers().get(0).setAsCurrentPlayer();
                    for (ClientHandler c: clientConnectionThreads) {
                        if (c.getPlayerID() == 1) {
                            GameStartMsg gameStartMsg = new GameStartMsg(controller.getGame().getTable().getMarket().getMarketTable(), controller.getGame().getTable().getMarket().getMarbleInExcess(), controller.getGame().getTable().getTopDevelopmentcards(), controller.getGame().getCurrentPlayer().getPersonalBoard(), controller.getGame().getCurrentPlayer().getNickname(), TurnState.PREPARATION);
                            c.sendAnswerMessage(gameStartMsg);
                        }
                        else{
                            GameStartMsg gameStartMsg = new GameStartMsg(controller.getGame().getTable().getMarket().getMarketTable(), controller.getGame().getTable().getMarket().getMarbleInExcess(), controller.getGame().getTable().getTopDevelopmentcards(), controller.getGame().getPlayers().get(c.getPlayerID()-1).getPersonalBoard(), controller.getGame().getCurrentPlayer().getNickname(), TurnState.ENDTURN);
                            c.sendAnswerMessage(gameStartMsg);
                        }
                    }

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