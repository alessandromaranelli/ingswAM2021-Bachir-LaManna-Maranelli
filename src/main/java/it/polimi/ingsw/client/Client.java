package it.polimi.ingsw.client;

import it.polimi.ingsw.client.GUI.CustomFrame;
import it.polimi.ingsw.client.GUI.Gui;
import it.polimi.ingsw.server.PingThread;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;

/**
 * The type Client.
 */
public class Client {
    private final int PORT;
    private final String hostname;
    private LightModel lightModel;
    private static Gui gui;

    /**
     * Instantiates a new Client.
     *
     * @param port     the port
     * @param hostname the hostname
     * @param gui      the gui
     */
    public Client(int port, String hostname,boolean gui){
        this.PORT = port;
        this.hostname = hostname;
        if(gui){
            this.lightModel=new LightModelGUI(this);
        }
        else this.lightModel=new LightModelCLI(this);
    }

    /**
     * Start the thread for communication with the server
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number> <CLI/GUI>");
            System.exit(1);
        }
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        System.out.println("Connected" + " " + hostName + " " + portNumber);
        Socket socket;
        Client client=null;
        if (args[2].equals("GUI")){
            client = new Client(portNumber, hostName,true);

        }else if (args[2].equals("CLI")){
            client = new Client(portNumber, hostName,false);
        }else{
            System.err.println(
                    "Usage: java EchoClient <host name> <port number> <CLI/GUI>");
            System.exit(1);
        }

        try {
            socket = new Socket(hostName, portNumber);
        } catch (IOException e) {
            System.out.println("server unreachable");
            return;
        }
        System.out.println("Connected with server");

        Scanner scanner = new Scanner(System.in);
        OutputView outputView = new OutputView(socket, scanner, client);
        Thread output = new Thread(outputView);
        output.start();

        InputView inputView = new InputView(socket, client);
        Thread input = new Thread(inputView);
        input.start();

        PongThread pongThread = new PongThread(outputView);
        pongThread.start();
        if (args[2].equals("GUI")) {
            gui = new Gui(outputView, client.getLightModel());
            Thread g = new Thread(gui);
            SwingUtilities.invokeLater(g);
        }
    }

    /**
     * Get light model
     *
     * @return the light model
     */
    public LightModel getLightModel(){
        return lightModel;
    }

    /**
     * Get gui
     *
     * @return the gui
     */
    public Gui getGui(){
        return gui;
    }
}