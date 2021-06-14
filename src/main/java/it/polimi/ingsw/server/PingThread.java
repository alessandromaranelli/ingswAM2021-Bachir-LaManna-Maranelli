package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.PingMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class PingThread extends Thread{
    private ClientHandler clientHandler;

    public PingThread(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void run(){
        while(true) {
            if (clientHandler.isConnected()) {
                PingMsg pingMsg = new PingMsg();
                clientHandler.sendAnswerMessage(pingMsg);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
