package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.PingMsg;
import it.polimi.ingsw.messages.answers.AnswerMsg;
import it.polimi.ingsw.messages.answers.WinMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class InputView implements Runnable{
    private Socket socket;
    private ObjectInputStream input;
    private Client client;

    public InputView(Socket socket, Client client){
        this.socket = socket;
        this.client = client;
    }

    public void run(){
        //ObjectInputStream input = null;
        try {
           input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Object next = null;
            try {
                socket.setSoTimeout(20000);
                next = input.readObject();
            } catch (SocketTimeoutException e){
                System.out.println("Server died");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            AnswerMsg message = (AnswerMsg) next;
            message.processMessage(client.getLightModel());
            message.printMessage();

            if (next instanceof WinMsg){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
