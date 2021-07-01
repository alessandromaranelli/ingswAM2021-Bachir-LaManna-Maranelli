package it.polimi.ingsw.server;

import Exceptions.ModelException;

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
    private static int port = 8080;
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
        if(args.length == 1){
            try{
            port = Integer.parseInt(args[0]);}
            catch (NumberFormatException e){
                System.out.println("Port must be a number");
            }
            if(port<1000 || port>9999) {
                System.out.println("Port number must be between 1000 and 9999");
                System.out.println("Used default port 8080");
            }
        }
        Server s = new Server();
        s.runServer();
    }


    /**
     * RunServer method opens a socket and waits for clients to connect.
     */
    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server waiting for connections...on port:" + port);
            while (listening) {
                Socket client = serverSocket.accept();
                client.setSoTimeout(20000);
                lobby.add(new ClientHandler(client,lobby));
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
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