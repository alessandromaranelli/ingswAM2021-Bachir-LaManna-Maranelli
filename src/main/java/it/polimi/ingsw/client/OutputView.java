package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.commands.*;
import it.polimi.ingsw.messages.commands.beforestart.NewGameMsg;
import it.polimi.ingsw.messages.commands.beforestart.QuickStartMsg;
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
import it.polimi.ingsw.model.LeaderCard;
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
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        //ObjectOutputStream output= null;

        while (true){
            String s = scanner.nextLine();
            String[] parts = s.split(":");
            if(client.getLightModel().getPhase() == TurnState.WAIT){
                System.out.println("Waiting for other players to join the game");
            }
            if((client.getLightModel().getCurrentPlayer()!=null&&(!client.getLightModel().getNickname().equals(client.getLightModel().getCurrentPlayer())))){
                System.out.println("It's not your turn! It's player " + client.getLightModel().getCurrentPlayer() + "'s turn");
            }
            else if(!parseEnum(parts)){
                System.out.println("Wrong syntax!  You wrote: "+s+" your phase is "+client.getLightModel().getPhase());
            }
            else {
                CommandMsg commandMessage = createCommandMessage(parts);
                if(type != TypeOfCommand.VIEWMARKET && type != TypeOfCommand.VIEWDEVELOPMENTCARD && type != TypeOfCommand.VIEWLEADERS && type!=TypeOfCommand.VIEWDEVELOPMENTCARDSTOBUY && type!=TypeOfCommand.VIEWFAITHTRACK && type!=TypeOfCommand.VIEWPRODUCTIONS && type!=TypeOfCommand.VIEWRESOURCES) {
                    sendCommandMessage(commandMessage);
                }
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

    public CommandMsg createCommandMessage(String[] parts){
        if(type == TypeOfCommand.QUICKSTART) {
            return new QuickStartMsg();
        }
        if(type == TypeOfCommand.NEWMATCH) {
            return new NewGameMsg();
        }
        if(type == TypeOfCommand.NICKNAME) {
            return new NickNameMsg(parts[1], Integer.parseInt(parts[3]));
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
            return new StartMarketPhaseMsg(Integer.parseInt(parts[1]),Boolean.valueOf(parts[2]));
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

        if(type == TypeOfCommand.VIEWOTHERPLAYERS){
            return new ViewOtherPlayersMsg();
        }
        if(type == TypeOfCommand.VIEWOTHERPLAYERNUMBER){
            return new ViewOtherPlayerNumberMsg(Integer.parseInt(parts[0]));
        }

        if(type == TypeOfCommand.VIEWMARKET){
            client.getLightModel().getMarketView().showMarbles(client.getLightModel().getMarket());
            client.getLightModel().getMarketView().plot();
            return null;
        }
        if(type ==TypeOfCommand.VIEWDEVELOPMENTCARD){
            client.getLightModel().getDevelCardsOfPlayerVisualizer().plot(client.getLightModel().getDevelopmentCard2());
            return null;
        }
        if(type == TypeOfCommand.VIEWLEADERS){
            if(client.getLightModel().getLeaderCardsInHand().size()>0){
                System.out.println("\nHere are your LeadersInHand: ");
                for(LeaderCard leaderCard:client.getLightModel().getLeaderCardsInHand())client.getLightModel().getLeaderCardVisualizer().showLeaderData(leaderCard);
            }

            if(client.getLightModel().getLeaderCardsPlayed().size()>0){
                System.out.println("\nHere are your LeadersPlayed: ");
                for(LeaderCard leaderCard:client.getLightModel().getLeaderCardsPlayed())client.getLightModel().getLeaderCardVisualizer().showLeaderData(leaderCard);
            }
            return null;
        }
        if(type == TypeOfCommand.VIEWFAITHTRACK){
            client.getLightModel().getFaithTrackVisualizer().plot(client.getLightModel().getPosition(),client.getLightModel().getPopeFavours());
            return null;
        }

        if(type == TypeOfCommand.VIEWDEVELOPMENTCARDSTOBUY){
            client.getLightModel().getDevelopmentCardToBuyVisualizer().plot(client.getLightModel().getDevelopmentCardsToBuy());
            return null;
        }

        if(type == TypeOfCommand.VIEWPRODUCTIONS){
            client.getLightModel().getProductionVisualizer().plot(client.getLightModel().getTotalCost(), client.getLightModel().getTotalGain(), client.getLightModel().getFaithPoints());
            return null;
        }

        if(type == TypeOfCommand.VIEWRESOURCES){
            client.getLightModel().getChestVisualizer().plot(client.getLightModel().getChest(),"This is the chest");
            client.getLightModel().getStoragesVisualizer().plot(client.getLightModel().getStorageType(), client.getLightModel().getStorageQuantity());
            return null;
        }
        else return null;
    }



    public boolean parseEnum(String parts[]){
        if(parts[0].equals("QUICKSTART")&& parts.length==1 && client.getLightModel().getPhase() == TurnState.BEFORESTART){
            this.type =TypeOfCommand.QUICKSTART;
            return true;
        }
        if(parts[0].equals("NEW GAME")&& parts.length==1 && client.getLightModel().getPhase() == TurnState.BEFORESTART){
            this.type =TypeOfCommand.NEWMATCH;
            return true;
        }
        if(parts[0].toLowerCase().equals("nickname") && parts[2].toLowerCase().equals("numberofplayers") &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                parts.length==4 && client.getLightModel().getPhase() == TurnState.BEFORESTART){
            this.type = TypeOfCommand.NICKNAME;
            return true;
        }
        if (parts[0].toLowerCase().equals("drawleadercards") && parts.length==1 && client.getLightModel().getPhase() == TurnState.PREPARATION){
            this.type = TypeOfCommand.DRAWLEADERCARDS;
            return true;
        }
        if (parts[0].toLowerCase().equals("discardleadercards") && parts.length==3 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.CHOOSELEADERCARDS){
            this.type = TypeOfCommand.DISCARDLEADERCARDS;
            return true;
        }
        if (parts[0].toLowerCase().equals("setinitialstoragetypes") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2].equals("STONE")) &&
                (parts[3].equals("COIN") || parts[3].equals("SERVANT") || parts[3].equals("SHIELD") || parts[3].equals("STONE")) &&
                client.getLightModel().getPhase() == TurnState.CHOOSERESOURCES){
            this.type = TypeOfCommand.SETINITSTORAGETYPES;
            return true;
        }
        if (parts[0].toLowerCase().equals("addinitialresources") &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts.length==2 || (parts.length==3 && (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2]=="STONE"))) &&
                client.getLightModel().getPhase() == TurnState.CHOOSERESOURCES){
            this.type = TypeOfCommand.ADDINITRESOURCES;
            return true;
        }


        if (parts[0].toLowerCase().equals("selectmarketphase") && parts.length==1 && client.getLightModel().getPhase() == TurnState.START){
            this.type = TypeOfCommand.SELECTMARKETPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("startmarketphase") && parts.length==3 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (parts[2].equals("true")||parts[2].equals("false")) &&
                client.getLightModel().getPhase() == TurnState.MARKETPHASE){
            this.type = TypeOfCommand.STARTMARKETPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("managewhitemarbles") && parts.length==1 && client.getLightModel().getPhase() == TurnState.WHITEMARBLES){
            this.type = TypeOfCommand.WHITEMARBLES;
            return true;
        }
        if (parts[0].toLowerCase().equals("startorganizeresources") && parts.length==1 && client.getLightModel().getPhase() == TurnState.CHOICE){
            this.type = TypeOfCommand.ORGANIZERESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("setstoragetypes") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2].equals("STONE")) &&
                (parts[3].equals("COIN") || parts[3].equals("SERVANT") || parts[3].equals("SHIELD") || parts[3].equals("STONE")) &&
                client.getLightModel().getPhase() == TurnState.ORGANIZERESOURCES){
            this.type = TypeOfCommand.SETSTORAGETYPES;
            return true;
        }
        if (parts[0].toLowerCase().equals("defaultmanageresourcestoorganize") && parts.length==1 && client.getLightModel().getPhase() == TurnState.MANAGERESOURCES){
            this.type = TypeOfCommand.MANAGERESOURCESBYDEFAULT;
            return true;
        }
        if (parts[0].toLowerCase().equals("manageresourcestoorganize") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.MANAGERESOURCES){
            this.type = TypeOfCommand.MANAGERESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("startaddresources") && parts.length==1 && client.getLightModel().getPhase() == TurnState.CHOICE){
            this.type = TypeOfCommand.STARTADDRESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("addresources") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.ADDRESOURCES){
            this.type = TypeOfCommand.ADDRESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("discardresources") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.ADDRESOURCES) {
            this.type = TypeOfCommand.DISCARDRESOURCES;
            return true;
        }


        if (parts[0].toLowerCase().equals("reorganizeresources") && (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            this.type = TypeOfCommand.REORGANIZERESOURCES;
            return true;
        }


        if (parts[0].toLowerCase().equals("selectbuydevelopmentcardphase") && parts.length==1 &&client.getLightModel().getPhase() == TurnState.START){
            this.type = TypeOfCommand.SELECTBUYDEVELOPMENTCARDPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("buydevelopmentcard") && parts.length==4 &&
                (parts[1].equals("GREEN") || parts[1].equals("BLUE") || parts[1].equals("PURPLE") || parts[1].equals("YELLOW")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.BUYDEVELOPMENTCARDPHASE){
            this.type = TypeOfCommand.BUYDEVELOPMENTCARD;
            return true;
        }
        if (parts[0].toLowerCase().equals("paycardfromchest") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYDEVELOPMENTCARD){
            this.type = TypeOfCommand.PAYCARDFROMCHEST;
            return true;
        }
        if (parts[0].toLowerCase().equals("paycardfromstorage") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYDEVELOPMENTCARD){
            this.type = TypeOfCommand.PAYCARDFROMSTORAGE;
            return true;
        }


        if (parts[0].toLowerCase().equals("selectproductionphase") && parts.length==1 && client.getLightModel().getPhase() == TurnState.START){
            this.type = TypeOfCommand.SELECTPRODUCTIONPHASE;
            return true;
        }
        if (parts[0].toLowerCase().equals("activatepersonalproduction") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2].equals("STONE")) &&
                (parts[3].equals("COIN") || parts[3].equals("SERVANT") || parts[3].equals("SHIELD") || parts[3].equals("STONE")) &&
                client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.ACTIVATEPERSONALPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("activateproduction") && parts.length==2 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.ACTIVATEPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("activatespecialproduction") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.ACTIVATESPECIALPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("startpayproduction") && parts.length==1 &&client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            this.type = TypeOfCommand.STARTPAYPRODUCTION;
            return true;
        }
        if (parts[0].toLowerCase().equals("payproductionfromchest") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYPRODUCTIONS){
            this.type = TypeOfCommand.PAYPRODUCTIONFROMCHEST;
            return true;
        }
        if (parts[0].toLowerCase().equals("payproductionfromstorage") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYPRODUCTIONS){
            this.type = TypeOfCommand.PAYPRODUCTIONFROMSTORAGE;
            return true;
        }


        if (parts[0].toLowerCase().equals("activateleadercard") && parts.length==2 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            this.type = TypeOfCommand.ACTIVATELEADERCARD;
            return true;
        }
        if (parts[0].toLowerCase().equals("discardleadercard") && parts.length==2 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            this.type = TypeOfCommand.DISCARDLEADERCARD;
            return true;
        }


        if (parts[0].toLowerCase().equals("endturn") && parts.length==1 &&(client.getLightModel().getPhase() == TurnState.ENDTURN || client.getLightModel().getPhase() == TurnState.ENDPREPARATION)){
            client.getLightModel().setPhase(TurnState.START);
            this.type = TypeOfCommand.ENDTURN;
            return true;
        }

        if (parts[0].toLowerCase().equals("viewmarket")) {
            this.type = TypeOfCommand.VIEWMARKET;
            return true;
        }
        if (parts[0].toLowerCase().equals("viewdevelopmentcard")){
            this.type = TypeOfCommand.VIEWDEVELOPMENTCARD;
            return true;
        }
        if (parts[0].toLowerCase().equals("viewleaders")) {
            this.type = TypeOfCommand.VIEWLEADERS;
            return true;
        }
        if (parts[0].toLowerCase().equals("viewfaithtrack")) {
            this.type = TypeOfCommand.VIEWFAITHTRACK;
            return true;
        }
        if (parts[0].toLowerCase().equals("viewdevelopmentcardstobuy")) {
            this.type = TypeOfCommand.VIEWDEVELOPMENTCARDSTOBUY;
            return true;
        }
        if (parts[0].toLowerCase().equals("viewproduction")) {
            this.type = TypeOfCommand.VIEWPRODUCTIONS;
            return true;
        }
        if (parts[0].toLowerCase().equals("viewresources")) {
            this.type = TypeOfCommand.VIEWRESOURCES;
            return true;
        }
        if (parts[0].toLowerCase().equals("viewotherplayers")) {
            this.type = TypeOfCommand.VIEWOTHERPLAYERS;
            return true;
        }
        if ((parts[0].equals("0") || parts[0].equals("1") || parts[0].equals("2") || parts[0].equals("3") || parts[0].equals("4"))  && parts.length==1 &&(client.getLightModel().getPhase() == TurnState.VIEWOTHERPLAYERS )) {
            this.type = TypeOfCommand.VIEWOTHERPLAYERNUMBER;
            return true;
        }
        else return false;
    }
}
