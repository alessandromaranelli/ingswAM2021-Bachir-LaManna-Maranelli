package it.polimi.ingsw.client;

import it.polimi.ingsw.server.PingThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int PORT;
    private final String hostname;
    private LightModel lightModel;


    public Client(int port, String hostname){
        this.PORT = port;
        this.hostname = hostname;
        lightModel = new LightModel();
    }

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Insert server address: ");
        String hostname = scanner.nextLine();
        System.out.println("Insert server port: ");
        int port = scanner.nextInt();*/
        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        System.out.println("Connected" + " " + hostName + " " + portNumber);
        Socket socket;

        Client client = new Client(portNumber, hostName);


        try {
            socket = new Socket(hostName, portNumber);
        } catch (IOException e) {
            System.out.println("server unreachable");
            return;
        }
        System.out.println("Connected with server");

        InputView inputView = new InputView(socket, client);
        Thread input = new Thread(inputView);
        input.start();

        Scanner scanner = new Scanner(System.in);
        OutputView outputView = new OutputView(socket, scanner, client);
        Thread output = new Thread(outputView);
        output.start();

        PongThread pongThread = new PongThread(outputView);
        pongThread.start();
    }

    public LightModel getLightModel(){
        return lightModel;
    }

}