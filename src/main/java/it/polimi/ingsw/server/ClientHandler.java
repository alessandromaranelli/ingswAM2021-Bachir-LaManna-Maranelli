package it.polimi.ingsw.server;


import Exceptions.ModelException;
import it.polimi.ingsw.messages.PongMsg;
import it.polimi.ingsw.messages.answers.AnswerMsg;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.commands.beforestart.BeforeStartMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * The type ClientHandler runs a thread that manages the exchange of messages between a client and the server.
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int playerID;
    private boolean ready;
    private Controller controller;
    private Lobby lobby;
    private Match match;
    private String unicode;
    private boolean connected;

    /**
     * Instantiates a new ClientHandler.
     *
     * @param socket the socket
     * @param lobby  the lobby
     * @throws IOException the io exception
     */
    public ClientHandler(Socket socket, Lobby lobby) throws IOException {
        this.socket = socket;
        this.lobby=lobby;
        controller=null;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        ready = false;
        connected=true;
        this.start();
    }

    /**
     * Sets player id.
     *
     * @param playerID the player id
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Gets player id.
     *
     * @return the player id
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Gets unicode.
     *
     * @return the unicode
     */
    public String getUnicode() {
        return unicode;
    }

    /**
     * Sets unicode.
     *
     * @param unicode the unicode
     */
    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    /**
     * Gets match.
     *
     * @return the match
     */
    public Match getMatch() {
        return match;
    }

    /**
     * Sets match.
     *
     * @param match the match
     */
    public void setMatch(Match match) {
        this.match = match;
    }

    /**
     * Is ready boolean.
     *
     * @return the boolean
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Sets ready.
     */
    public void setReady() {
        this.ready = true;
    }

    /**
     * Is connected boolean.
     *
     * @return the boolean
     */
    public boolean isConnected() {
        return connected&&socket!=null;
    }

    /**
     * Sets connected.
     *
     * @param connected the connected
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Method that reads and processes the messages received from the client and
     * starts a new thread that manages the 'ping'.
     */
    public void run() {
        PingThread pingThread = new PingThread(this);
        Thread ping = new Thread(pingThread);
        ping.start();
        Object next = null;
        while (!ready) {
            next=readObjectfromSocket();
            if(next!=null)processObject(next);
            else{
                System.out.println("Client died");
                closeClientSocket();
                removeAllReferencesOfClient1();
                return;
            }
        }
        try {
            handleClientConnection();
        } catch (IOException | ModelException e) {
            System.out.println("client " + socket.getInetAddress() + " connection dropped");
        }
    }

    /**
     * Processes a message from the client
     * @param next the message from the client
     */
    private void processObject(Object next) {
        if (next==null) return;
        CommandMsg command = (CommandMsg) next;
            if (controller == null && command instanceof BeforeStartMsg) {
                BeforeStartMsg beforeStartMsg = (BeforeStartMsg) command;
                try {
                    beforeStartMsg.processMessage(this, lobby);
                } catch (FileNotFoundException | ModelException exc) {
                    System.out.println("Error FIleNotFound | Model");
                }
            }else{
                try {
                    command.processMessage(this, controller);
                } catch (ModelException exc) {
                    System.out.println("Error Model");
                }
            }
    }

    /**
     * Processes a command from the client
     * @param next the command from the client
     */
    private void processCommand(Object next){
        if (next==null) return;
        CommandMsg command = (CommandMsg) next;
        if (controller==null&&command instanceof BeforeStartMsg) {
            BeforeStartMsg beforeStartMsg = (BeforeStartMsg) command;
            try {
                beforeStartMsg.processMessage(this, lobby);
            } catch (FileNotFoundException | ModelException exc) {
                System.out.println("Error FIleNotFound | Model");
            }
        }
        else if (controller.isCurrentPlayer(this)) {
            try {
                command.processMessage(this, controller);
            } catch (ModelException exc) {
                System.out.println("Error Model");
            }
        }
        else if(command instanceof PongMsg){
        }
        else {
            ErrorMsg errorMsg = new ErrorMsg("You are not the current player");
            sendAnswerMessage(errorMsg);
        }

    }

    /**
     * Reads an object from the socket input
     * @return
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
     * Removes all client references on the server when a disconnection happens
     */
    private void removeAllReferencesOfClient1(){
        if(!(match ==null))match.checkClientConnection(this);
        if (controller!=null){
            if (!ready) {
                match.getClientConnectionThreads().remove(this);
                if (match.isFull()) match.setFull(false);
            }
            else if (controller.getGame().getCurrentPlayer().getPlayerID()==playerID){
                try {
                    controller.manageClientDisconnectionWhilePlayingHisTurn(this);
                } catch (ModelException modelException) {
                    modelException.printStackTrace();
                }
            }
        }
    }
    /**
     * Removes all client references on the server when a disconnection happens
     */
    private void removeAllReferencesOfClient2() throws ModelException {
        if(!(match ==null))match.checkClientConnection(this);
        if (controller!=null){
            if (!ready) {
                match.getClientConnectionThreads().remove(this);
                if (match.isFull()) match.setFull(false);
            }
            else if (controller.isGameStarted()&&controller.getGame().getCurrentPlayer().getPlayerID()==playerID){
                controller.manageClientDisconnectionWhilePlayingHisTurn(this);
            }
        }
    }

    /**
     * Closes the socket
     */
    private void closeClientSocket(){
        if(socket==null){
            this.setConnected(false);
            return;
        }
        try {
            socket.close();
        } catch (IOException ioException) {
            System.out.println("Cannot close socket");
        }
        socket=null;
        this.setConnected(false);
    }

    /**
     * Reads and processes commands from the client
     * @throws IOException
     * @throws ModelException
     */
    private void handleClientConnection() throws IOException, ModelException {
        Object next=null;
        while (true) {
            next = readObjectfromSocket();
            if (next != null) processCommand(next);
            else {
                System.out.println("Client died");
                closeClientSocket();
                removeAllReferencesOfClient2();
                break;
            }
        }

    }

    /**
     * Get output object output stream.
     *
     * @return the object output stream
     */
    public ObjectOutputStream getOutput(){
        return output;
    }


    /**
     * Sends AnswerMessages to the client.
     *
     * @param answerMessage the answer message
     */
    public synchronized void sendAnswerMessage(AnswerMsg answerMessage){
        try {
            if (this.isConnected() && socket != null) {
                output.writeObject(answerMessage);
                output.flush();
                output.reset();
            }
        }catch (SocketException socketException){
            closeClientSocket();
            removeAllReferencesOfClient1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes all the socket streams.
     */
    public synchronized void closeSocketStreams(){
        try {
            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}