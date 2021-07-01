package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.PingMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * The type Pong thread. It sends to the client a message every 10000 second to know if it is alive
 */
public class PingThread extends Thread{
    private ClientHandler clientHandler;

    /**
     * Instantiates a new Ping thread.
     *
     * @param clientHandler the client handler
     */
    public PingThread(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void run(){
        while(true) {
            boolean isConnected = clientHandler.isConnected();
            if(!isConnected)
                break;
            PingMsg pingMsg = new PingMsg();
            clientHandler.sendAnswerMessage(pingMsg);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
