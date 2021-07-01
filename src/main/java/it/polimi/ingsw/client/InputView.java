package it.polimi.ingsw.client;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.PingMsg;
import it.polimi.ingsw.messages.PongMsg;
import it.polimi.ingsw.messages.answers.AnswerMsg;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.WinMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.beforestart.BeforeStartMsg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * The type Input view. It is a thread that receives messages from the server
 */
public class InputView implements Runnable{
    private Socket socket;
    private ObjectInputStream input;
    private Client client;

    /**
     * Instantiates a new Input view.
     *
     * @param socket the socket
     * @param client the client
     */
    public InputView(Socket socket, Client client){
        this.socket = socket;
        this.client = client;
    }

    /**
     * method run of inputView thread
     */
    public void run(){
        try {
           input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Object next = null;
            next = readObjectfromSocket();
            if(next!=null)processAnswer(next);
            else{
                System.out.println("Server died");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            if (next instanceof WinMsg){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads an object from the inputstream
     * @return the message from the server
     */
    private Object readObjectfromSocket() {
        try {
            Object next = input.readObject();
            return next;
        } catch (ClassNotFoundException ioException) {
            ioException.printStackTrace();
            return null;
        }
        catch (IOException ioException) {
            return null;
        }
    }

    /**
     * Processes the answer from the server
     * @param next Message received from the server
     */
    private void processAnswer(Object next){
        if (next==null) return;
        AnswerMsg message = (AnswerMsg) next;
        message.processMessage(client.getLightModel());
        message.printMessage();

    }
}
