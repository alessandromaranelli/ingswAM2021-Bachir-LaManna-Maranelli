package it.polimi.ingsw.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    public static final int PORT = 1235;
    private boolean listening = true;
    private Set<Match> matches = new HashSet<>();
    private Lobby lobby;

    public Server() throws FileNotFoundException {
        lobby=new Lobby(this,matches);

    }

    public static void main(String[] args) throws IOException {

        Server s = new Server();
        s.runServer();
    }


    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server waiting for connections...");
            while (listening) {
                /*if (serverList.isEmpty() || serverList.stream().filter(p -> !p.isFull()).count() == 0) {
                    lobbyServer = new Lobby();
                    lobbyServer.createLobby();
                    serverList.add(lobbyServer);
                } else {
                    for (Lobby l : serverList) {
                        if (!l.isFull()) {
                            lobbyServer = l;
                            break;
                        }
                    }
                }*/
                Socket client = serverSocket.accept();
                lobby.add(new ClientHandler(client,lobby));
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

}