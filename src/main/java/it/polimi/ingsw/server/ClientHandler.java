package it.polimi.ingsw.server;


import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.AnswerMsg;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.NickNameMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int playerID;
    private boolean ready;
    private Controller controller;
    public ClientHandler(Socket socket, Controller controller) throws IOException {

        this.socket = socket;
        this.controller = controller;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        ready = false;
        this.start();
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady() {
        this.ready = true;
    }

    public void run(){
        try{
            while(!ready) {
                Object next = input.readObject();
                CommandMsg command = (NickNameMsg) next;
                command.processMessage(this, controller);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            handleClientConnection();
        } catch (IOException e) {
            System.out.println("client " + socket.getInetAddress() + " connection dropped");
        }
    }
    private void handleClientConnection() throws IOException
    {
        try {
            while (true) {
                /* read commands from the client, process them, and send replies */
                System.out.println("input");
                Object next = input.readObject();
                CommandMsg command = (CommandMsg)next;
                if (controller.isCurrentPlayer(this, command)) {
                    command.processMessage(this, controller);
                }
                else{
                    ErrorMsg errorMsg = new ErrorMsg("You are not the current player");
                    sendAnswerMessage(errorMsg);
                }
            }
        } catch (ClassNotFoundException | ClassCastException | IOException e) {
            System.out.println("invalid stream from client");
        }
    }
    public ObjectOutputStream getOutput(){
        return output;
    }

    /* Viene fatto nel GameStartMsg
    public void startGame() throws IOException {
        output.writeObject("Game is starting");
    } */

    public void sendAnswerMessage(AnswerMsg answerMessage){
        try {
            output.writeObject((Object)answerMessage);
            System.out.println("Answer send");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}