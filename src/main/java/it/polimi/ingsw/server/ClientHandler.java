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

public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int playerID;
    private boolean ready;
    private Controller controller;
    private Lobby lobby;
    private String unicode;
    private boolean connected;

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

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady() {
        this.ready = true;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void run() {
        PingThread pingThread = new PingThread(this);
        Thread ping = new Thread(pingThread);
        ping.start();

        try {
            while (!ready) {
                //socket.setSoTimeout(20000);
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

            //System.exit(0);

        }

        catch (SocketTimeoutException e) {
            e.printStackTrace();    //gestire la disconnessione del client
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch (ModelException e) {
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
                //socket.setSoTimeout(20000);
                //System.out.println("input");
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
                    //do nothing
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

            //System.exit(0);

        } catch (ClassNotFoundException | ClassCastException | IOException e) {
            System.out.println("invalid stream from client");
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
    public ObjectOutputStream getOutput(){
        return output;
    }


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