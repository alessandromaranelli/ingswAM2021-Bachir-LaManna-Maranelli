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

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert server address: ");
        String hostname = scanner.nextLine();
        System.out.println("Insert server port: ");
        int port = scanner.nextInt();
        Client client = new Client(port, hostname);

        Socket socket;
        try {
            socket = new Socket(hostname, port);
        } catch (IOException e) {
            System.out.println("server unreachable");
            return;
        }
        System.out.println("Connected with server");

        InputView inputView = new InputView(socket, client);
        Thread input = new Thread(inputView);
        input.start();

        OutputView outputView = new OutputView(socket, scanner, client);
        Thread output = new Thread(outputView);
        output.start();

        PongThread pongThread = new PongThread(outputView);
        Thread pong = new Thread(pongThread);
        pong.start();
    }

    public LightModel getLightModel(){
        return lightModel;
    }
}