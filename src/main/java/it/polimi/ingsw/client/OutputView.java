package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.answers.UpdateLeaderCardsMsg;
import it.polimi.ingsw.messages.commands.*;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.BuyCardMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.BuyDevelopmentPhaseMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.PayCardFromChestMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.PayCardFromStorageMsg;
import it.polimi.ingsw.messages.commands.leadermanage.ActivateLeaderMsg;
import it.polimi.ingsw.messages.commands.leadermanage.DiscardLeaderMsg;
import it.polimi.ingsw.messages.commands.marketphase.*;
import it.polimi.ingsw.messages.commands.preparation.*;
import it.polimi.ingsw.messages.commands.productionphase.*;
import it.polimi.ingsw.model.Color;
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
            if(!client.getLightModel().getNickname().equals(client.getLightModel().getCurrentPlayer())){
                System.out.println("It's not your turn! It's player " + client.getLightModel().getCurrentPlayer() + "'s turn");
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

    public synchronized void sendCommandMessage(CommandMsg commandMessage){
        try {
            output.writeObject(commandMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CommandMsg createCommandMessage(String parts[]){
        if(type == TypeOfCommand.NICKNAME) {
            NickNameMsg nickNameMessage = new NickNameMsg(parts[1], Integer.parseInt(parts[3]));
            return nickNameMessage;
        }
        if(type == TypeOfCommand.DRAWLEADERCARDS){
            return new DrawLeadersMsg();
        }
        if(type == TypeOfCommand.DISCARDLEADERCARDS){
            return new DiscardLeadersAtTheStartMsg(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
        if(type == TypeOfCommand.SETINITSTORAGETYPES){
            return new SetInitStorageTypeMsg(Resource.valueOf(parts[1]), Resource.valueOf(parts[2]), Resource.valueOf(parts[3]));
        }
        if(type == TypeOfCommand.ADDINITRESOURCES){
            if(parts.length == 2)
            return new AddInitResourcesMsg(Resource.valueOf(parts[1]));
            else return new AddInitResourcesMsg(Resource.valueOf(parts[1]), Resource.valueOf(parts[2]));
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
        if(type == TypeOfCommand.SETSTORAGETYPES){
            return new SetStorageTypesMsg(Resource.valueOf(parts[1]),Resource.valueOf(parts[2]),Resource.valueOf(parts[3]));
        }
        if(type == TypeOfCommand.MANAGERESOURCESBYDEFAULT){
            return new DefaultManageResourcesToOrganizeMsg();
        }
        if(type == TypeOfCommand.MANAGERESOURCES){
            return new ManageResourcesToOrganizeMsg(Resource.valueOf(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
        }
        if(type == TypeOfCommand.STARTADDRESOURCES){
            return new StartAddResourcesMsg();
        }
        if(type == TypeOfCommand.ADDRESOURCES){
            return new AddResourceMsg(Resource.valueOf(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
        }
        if(type == TypeOfCommand.DISCARDRESOURCES){
            return new DiscardResourceMsg(Resource.valueOf(parts[1]),Integer.parseInt(parts[2]));
        }


        if(type == TypeOfCommand.REORGANIZERESOURCES){
            return new ManageResourcesInStorageMsg();
        }


        if(type == TypeOfCommand.SELECTBUYDEVELOPMENTCARDPHASE){
            return new BuyDevelopmentPhaseMsg();
        }
        if(type == TypeOfCommand.BUYDEVELOPMENTCARD){
            return new BuyCardMsg(Color.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        if(type == TypeOfCommand.PAYCARDFROMCHEST){
            return new PayCardFromChestMsg(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]));
        }
        if(type == TypeOfCommand.PAYCARDFROMSTORAGE){
            return new PayCardFromStorageMsg(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }


        if(type == TypeOfCommand.SELECTPRODUCTIONPHASE){
            return new SelectProductionPhaseMsg();
        }
        if(type == TypeOfCommand.ACTIVATEPERSONALPRODUCTION){
            return new ActivatePersonalProductionMsg(Resource.valueOf(parts[1]), Resource.valueOf(parts[2]), Resource.valueOf(parts[3]));
        }
        if(type == TypeOfCommand.ACTIVATEPRODUCTION){
            return new ActivateProductionMsg(Integer.parseInt(parts[1]));
        }
        if(type == TypeOfCommand.ACTIVATESPECIALPRODUCTION){
            return new ActivateSpecialProduction(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]));
        }
        if(type == TypeOfCommand.STARTPAYPRODUCTION){
            return new StartPayProductionMsg();
        }
        if(type == TypeOfCommand.PAYPRODUCTIONFROMSTORAGE){
            return new PayProductionFromStorage(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        if(type == TypeOfCommand.PAYPRODUCTIONFROMCHEST){
            return new PayProductionFromChest(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]));
        }


        if(type == TypeOfCommand.ACTIVATELEADERCARD){
            return new ActivateLeaderMsg(Integer.parseInt(parts[1]));
        }
        if(type == TypeOfCommand.DISCARDLEADERCARD){
            return new DiscardLeaderMsg(Integer.parseInt(parts[1]));
        }


        if(type == TypeOfCommand.ENDTURN){
            return new EndTurnMsg();
        }
        else return null;
    }


    public boolean parseEnum(String parts[]){
        if(parts[0].toLowerCase().equals("nickname") && parts[2].toLowerCase().equals("numberofplayers") && client.getLightModel().getPhase() == TurnState.BEFORESTART){
            this.type = TypeOfCommand.NICKNAME;
            return true;
        }
        if (parts[0].toLowerCase().equals("drawleadercards") && client.getLightModel().getPhase() == TurnState.PREPARATION){
            this.type = TypeOfCommand.DRAWLEADERCARDS;
            return true;
        }
        if (parts[0].toLowerCase().equals("discardleadercards") && client.getLightModel().getPhase() == TurnState.CHOOSELEADERCARDS){
            this.type = TypeOfCommand.DISCARDLEADERCARDS;
            return true;
        }
        if (parts[0].toLowerCase().equals("setinitialstoragetypes") && client.getLightModel().getPhase() == TurnState.CHOOSERESOURCES){
            this.type = TypeOfCommand.SETINITSTORAGETYPES;
            return true;
        }
        if (parts[0].toLowerCase().equals("addinitialresources") && client.getLightModel().getPhase() == TurnState.CHOOSERESOURCES){
            this.type = TypeOfCommand.ADDINITRESOURCES;
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
        if (parts[0].toLowerCase().equals("setstoragetypes") && client.getLightModel().getPhase() == TurnState.ORGANIZERESOURCES){
            this.type = TypeOfCommand.SETSTORAGETYPES;
            return true;
        }
        if (parts[0].toLowerCase().equals("defaultmanageresourcestoorganize") && client.getLightModel().getPhase() == TurnState.MANAGERESOURCES){
            this.type = TypeOfCommand.MANAGERESOURCESBYDEFAULT;
            return true;
        }
        if (parts[0].toLowerCase().equals("manageresourcestoorganize") && client.getLightModel().getPhase() == TurnState.MANAGERESOURCES){
            this.type = TypeOfCommand.MANAGERESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("startaddresources") && client.getLightModel().getPhase() == TurnState.CHOICE){
            this.type = TypeOfCommand.STARTADDRESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("addresources") && client.getLightModel().getPhase() == TurnState.ADDRESOURCES){
            this.type = TypeOfCommand.ADDRESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("discardresources") && client.getLightModel().getPhase() == TurnState.ADDRESOURCES) {
            this.type = TypeOfCommand.DISCARDRESOURCES;
            return true;
        }


        if (parts[0].toLowerCase().equals("reorganizeresources") && (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            this.type = TypeOfCommand.REORGANIZERESOURCES;
            return true;
        }


        if (parts[0].toLowerCase().equals("selectbuydevelopmentcardphase") && client.getLightModel().getPhase() == TurnState.START){
            this.type = TypeOfCommand.SELECTBUYDEVELOPMENTCARDPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("buydevelopmentcard") && client.getLightModel().getPhase() == TurnState.BUYDEVELOPMENTCARDPHASE){
            this.type = TypeOfCommand.BUYDEVELOPMENTCARD;
            return true;
        }
        if (parts[0].toLowerCase().equals("paycardfromchest") && client.getLightModel().getPhase() == TurnState.PAYDEVELOPMENTCARD){
            this.type = TypeOfCommand.PAYCARDFROMCHEST;
            return true;
        }
        if (parts[0].toLowerCase().equals("paycardfromstorage") && client.getLightModel().getPhase() == TurnState.PAYDEVELOPMENTCARD){
            this.type = TypeOfCommand.PAYCARDFROMSTORAGE;
            return true;
        }


        if (parts[0].toLowerCase().equals("selectproductionphase") && client.getLightModel().getPhase() == TurnState.START){
            this.type = TypeOfCommand.SELECTPRODUCTIONPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("activatepersonalproduction") && client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.ACTIVATEPERSONALPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("activateproduction") && client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.ACTIVATEPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("activatespecialproduction") && client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.ACTIVATESPECIALPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("startpayproduction") && client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.STARTPAYPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("payproductionfromchest") && client.getLightModel().getPhase() == TurnState.PAYPRODUCTIONS){
            this.type = TypeOfCommand.PAYPRODUCTIONFROMCHEST;
            return true;
        }
        if (parts[0].toLowerCase().equals("payproductionfromstorage") && client.getLightModel().getPhase() == TurnState.PAYPRODUCTIONS){
            this.type = TypeOfCommand.PAYPRODUCTIONFROMSTORAGE;
            return true;
        }


        if (parts[0].toLowerCase().equals("activateleadercard") && (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            this.type = TypeOfCommand.ACTIVATELEADERCARD;
            return true;
        }
        if (parts[0].toLowerCase().equals("discardleadercard") && (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            this.type = TypeOfCommand.DISCARDLEADERCARD;
            return true;
        }


        if (parts[0].toLowerCase().equals("endturn") && (client.getLightModel().getPhase() == TurnState.ENDTURN || client.getLightModel().getPhase() == TurnState.ENDPREPARATION)){
            this.type = TypeOfCommand.ENDTURN;
            return true;
        }
        else return false;
    }
}
