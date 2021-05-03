package it.polimi.ingsw.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private Controller controller;
    private final int PORT;

    public Server(int port) throws FileNotFoundException {
        controller = new Controller();
        PORT = port;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert server port: ");
        int port = scanner.nextInt();
        Server server = new Server(port);
        server.runServer();
    }

    public void runServer(){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Server on");

        while(true){
            Socket socket;
            try {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, controller);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






   /*
    public void waitReady(){
        while (true) {
            try {
                if (areAllReady() && this.clientConnectionThreads.size() == controller.getNumberOfPlayers()) {
                    System.out.println("Starting game...");
                    this.listening=false;
                    controller.startGame();
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
    }   */
}