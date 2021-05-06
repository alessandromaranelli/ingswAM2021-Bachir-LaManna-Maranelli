package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.commands.*;
import it.polimi.ingsw.messages.commands.marketphase.*;
import it.polimi.ingsw.messages.commands.preparation.*;
import it.polimi.ingsw.model.Resource;
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
    private TypeOfCommand type;

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
            else if(!parseEnum(parts)){
                System.out.println("Wrong syntax!");
            }
            else {
                CommandMsg commandMessage = createCommandMessage(parts);
                sendCommandMessage(commandMessage);
                type = TypeOfCommand.FOLD;
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
        if(type == TypeOfCommand.NICKNAME) {
            NickNameMsg nickNameMessage = new NickNameMsg(parts[1], Integer.parseInt(parts[3]));
            return nickNameMessage;
        }
        if(type == TypeOfCommand.SELECTMARKETPHASE){
            return new SelectMarketPhaseMsg();
        }
        if(type == TypeOfCommand.STARTMARKETPHASE){
            return new StartMarketPhaseMsg(Integer.parseInt(parts[1]),Boolean.getBoolean(parts[2]));
        }
        if(type == TypeOfCommand.WHITEMARBLES){
            return new ManageWhiteMarbleMsg(Resource.valueOf(parts[1]));
        }
        if(type == TypeOfCommand.ORGANIZERESOURCES){
            return new StartOrganizeResourcesMsg();
        }
        else return null;
    }

    public boolean parseEnum(String parts[]){
        if(parts[0].toLowerCase().equals("nickname") && parts[2].toLowerCase().equals("numberofplayers") && client.getLightModel().getPhase() == TurnState.BEFORESTART){
            this.type = TypeOfCommand.NICKNAME;
            return true;
        }
        if (parts[0].toLowerCase().equals("selectmarketphase") && client.getLightModel().getPhase() == TurnState.START){
            this.type = TypeOfCommand.SELECTMARKETPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("startmarketphase") && client.getLightModel().getPhase() == TurnState.MARKETPHASE){
            this.type = TypeOfCommand.STARTMARKETPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("managewhitemarbles") && client.getLightModel().getPhase() == TurnState.WHITEMARBLES){
            this.type = TypeOfCommand.WHITEMARBLES;
            return true;
        }
        if (parts[0].toLowerCase().equals("startorganizeresources") && client.getLightModel().getPhase() == TurnState.CHOICE){
            this.type = TypeOfCommand.ORGANIZERESOURCES;
            return true;
        }
        else return false;
    }
}
