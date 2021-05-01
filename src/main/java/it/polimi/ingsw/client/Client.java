package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.NickNameMsg;
import it.polimi.ingsw.messages.NumberOfPlayersMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream output;
        ObjectInputStream input;
        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        System.out.println("Connected" + " " + hostName + " " + portNumber);
        Socket socket = new Socket(hostName, portNumber);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);
        String name =(scanner.nextLine());
        System.out.println(name);
        output.writeObject(new NickNameMsg(name));

        try {
            boolean stop = false;
            int x=0;
            while (!stop) {
                /* read commands from the server and process them */
                try {
                    Object next = input.readObject();
                    System.out.println(next.toString());
                    x++;
                    if(x==3){
                        scanner = new Scanner(System.in);
                        int players = Integer.parseInt(scanner.nextLine());
                        System.out.println(players);
                        output.writeObject(new NumberOfPlayersMsg(players));
                    }

                } catch (IOException e) {
                    /* Check if we were interrupted because another thread has asked us to stop */

                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}