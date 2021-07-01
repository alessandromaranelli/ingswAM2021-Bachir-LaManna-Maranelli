package it.polimi.ingsw.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Server is the class with the main method on the server side. It opens a socket and listens to all the clients
 * who connect to it.
 */
public class Server {
    public static final int PORT = 8080;
    private boolean listening = true;
    private Set<Match> matches = new HashSet<>();
    private Lobby lobby;

    /**
     * Instantiates a new Server.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public Server() throws FileNotFoundException {
        lobby=new Lobby(this,matches);

    }

    /**
     * The entry point of application, instantiates a server and runs it.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        Server s = new Server();
        s.runServer();
    }


    /**
     * RunServer method opens a socket and waits for clients to connect.
     */
    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server waiting for connections...");
            while (listening) {
                Socket client = serverSocket.accept();
                client.setSoTimeout(20000);
                lobby.add(new ClientHandler(client,lobby));
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

    /**
     * Gets lobby.
     *
     * @return the lobby
     */
    public Lobby getLobby() {
        return lobby;
    }

    /**
     * RemoveGame method removes the game from the set of matches when that specific game has ended.
     *
     * @param match the match
     */
    public void removeGame(Match match){
        matches.remove(match);
    }

}