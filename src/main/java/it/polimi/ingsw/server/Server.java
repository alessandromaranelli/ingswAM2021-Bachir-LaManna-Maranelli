package it.polimi.ingsw.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;

public class Server {
    private Controller controller= new Controller();
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
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
                this.clientConnectionThreads.add(new ClientHandler(client, controller));
                System.out.println("Player "+(controller.getGame().getPlayers().size()+1)+" is now connected");

                if (clientConnectionThreads.size() == controller.getNumberOfPlayers()){
                    listening=false;
                }

            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

    public void waitReady(){
        while (true) {
            try {
                if (areAllReady() && this.clientConnectionThreads.size() == controller.getNumberOfPlayers()) {
                    System.out.println("Starting game...");
                    this.listening=false;
                    for (ClientHandler clientConnectionThread : clientConnectionThreads) {
                        clientConnectionThread.startGame();
                    }

                    return;
                }
                Thread.sleep(100);
            } catch (InterruptedException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean areAllReady() {
        return clientConnectionThreads.stream().allMatch(ClientHandler::isReady);
    }
}