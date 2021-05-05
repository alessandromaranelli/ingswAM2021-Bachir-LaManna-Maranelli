package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.answers.AnswerMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
                next = input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            AnswerMsg message = (AnswerMsg) next;
            message.processMessage(client.getLightModel());
            message.printMessage();
        }
    }
}
