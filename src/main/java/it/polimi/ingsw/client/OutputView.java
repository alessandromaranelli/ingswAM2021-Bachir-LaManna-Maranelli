package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.preparation.NickNameMsg;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class OutputView implements Runnable{
    private Socket socket;
    private ObjectOutputStream output;
    private Scanner scanner;
    private Client client;
    private int type;

    public OutputView(Socket socket, Scanner scanner, Client client){
        this.socket = socket;
        this.scanner = scanner;
        this.client = client;
    }

    public void run(){
        //ObjectOutputStream output= null;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            String s = scanner.nextLine();
            String parts[] = s.split(":");
            if(client.getLightModel().getPhase() == TurnState.WAIT){
                System.out.println("Waiting for other players to join the game");
            }
            if(client.getLightModel().getPhase() == TurnState.ENDTURN){
                System.out.println("It's not your turn! IT's player " + client.getLightModel().getCurrentPlayer() + " turn");
            }
            else if(parseInt(parts) == false){
                System.out.println("Wrong syntax!");
            }
            else {
                CommandMsg commandMessage = createCommandMessage(parts);
                sendCommandMessage(commandMessage);
                type = 0;
            }
        }
    }

    public void sendCommandMessage(CommandMsg commandMessage){
        try {
            output.writeObject((Object)commandMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CommandMsg createCommandMessage(String parts[]){
        if(type == 1) {
            NickNameMsg nickNameMessage = new NickNameMsg(parts[1], Integer.parseInt(parts[3]));
            return nickNameMessage;
        }
        else return null;
    }

    public boolean parseInt(String parts[]){
        if(parts[0].toLowerCase().equals("nickname") && parts[2].toLowerCase().equals("numberofplayers") && client.getLightModel().getPhase() == TurnState.BEFORESTART){
            this.type = 1;
            return true;
        }
        else return false;
    }
}
