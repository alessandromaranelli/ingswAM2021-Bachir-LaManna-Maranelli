package it.polimi.ingsw.server;


import it.polimi.ingsw.messages.answers.AnswerMessage;
import it.polimi.ingsw.messages.commands.CommandMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Controller controller;

    public ClientHandler(Socket socket, Controller controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
    }

    public void run() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
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
            System.out.println("Message arrived");
            CommandMessage command = (CommandMessage)next;
            command.handleMessage(controller, this);
        }
    }

    public void sendAnswerMessage(AnswerMessage answerMessage){
        try {
            output.writeObject((Object)answerMessage);
            System.out.println("Answer send");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}