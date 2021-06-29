package it.polimi.ingsw.server;


import Exceptions.ModelException;
import it.polimi.ingsw.messages.PongMsg;
import it.polimi.ingsw.messages.answers.AnswerMsg;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.commands.beforestart.BeforeStartMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        return connected;
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

        try {
            while (!ready) {
                socket.setSoTimeout(20000);
                Object next = input.readObject();
                CommandMsg command = (CommandMsg) next;
                if (controller==null&&command instanceof BeforeStartMsg){
                    BeforeStartMsg beforeStartMsg=(BeforeStartMsg) command;
                    beforeStartMsg.processMessage(this,lobby);
                }
                else command.processMessage(this, controller);
            }

        } catch (SocketException e){
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.setConnected(false);
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

        catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch (ModelException e) {
            e.printStackTrace();
        }
        try {
            handleClientConnection();
        } catch (IOException | ModelException e) {
            System.out.println("client " + socket.getInetAddress() + " connection dropped");
        }
    }

    private void handleClientConnection() throws IOException, ModelException {
        try {
            while (true) {
                socket.setSoTimeout(20000);
                Object next = input.readObject();
                CommandMsg command = (CommandMsg) next;
                if (controller==null&&command instanceof BeforeStartMsg){
                    BeforeStartMsg beforeStartMsg=(BeforeStartMsg) command;
                    beforeStartMsg.processMessage(this,lobby);
                }
                else if (controller.isCurrentPlayer(this)) {
                    command.processMessage(this, controller);
                }
                else if(command instanceof PongMsg){
                }
                else {
                    ErrorMsg errorMsg = new ErrorMsg("You are not the current player");
                    sendAnswerMessage(errorMsg);
                }
            }
        } catch (SocketException e){
            System.out.println("Client died");
            socket.close();
            this.setConnected(false);
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


        } catch (ClassNotFoundException | ClassCastException | IOException e) {
            System.out.println("invalid stream from client");
        } catch (ModelException e) {
            e.printStackTrace();
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
            if (this.isConnected()) {
                output.writeObject(answerMessage);
                output.flush();
                output.reset();
            }
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