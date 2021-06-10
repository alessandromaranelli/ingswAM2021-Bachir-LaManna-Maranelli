package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.GameStartMsg;
import it.polimi.ingsw.model.TurnState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Server {
    public static final int PORT = 1235;
    private boolean listening = true;
    public ArrayList<LobbyServer> serverList = new ArrayList<>();

    public Server() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {

        Server s = new Server();
        s.runServer();
    }


    public void runServer() {
        LobbyServer lobbyServer = null;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server waiting for connections...");
            while (listening) {
                if (serverList.isEmpty() || serverList.stream().filter(p -> !p.isFull()).count() == 0) {
                    lobbyServer = new LobbyServer();
                    lobbyServer.createLobby();
                    serverList.add(lobbyServer);
                } else {
                    for (LobbyServer l : serverList) {
                        if (!l.isFull()) {
                            lobbyServer = l;
                            break;
                        }
                    }
                }
                Socket client = serverSocket.accept();
                new ClientHandler(client, lobbyServer.getController());
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

}