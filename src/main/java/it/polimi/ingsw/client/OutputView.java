package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.commands.*;
import it.polimi.ingsw.messages.commands.beforestart.JoinGameMsg;
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
    private InputParser inputParser;

    public OutputView(Socket socket, Scanner scanner, Client client){
        this.socket = socket;
        this.scanner = scanner;
        this.client = client;
        inputParser=new InputParser();
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
            else if(!inputParser.parseEnum(parts, client, this)){
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

    public void setType(TypeOfCommand type) {
        this.type = type;
    }

    public synchronized void sendCommandMessage(CommandMsg commandMessage){
        try {
            output.writeObject(commandMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CommandMsg createCommandMessage(String[] parts) {
        if (type == TypeOfCommand.QUICKSTART) {
            return new QuickStartMsg();
        }
        if (type == TypeOfCommand.NEWMATCH) {
            return new NewGameMsg();
        }
        if (type == TypeOfCommand.REJOIN) {
            return new JoinGameMsg(client.getLightModel().getUnicode());
        }
        if (type == TypeOfCommand.NICKNAME) {
            return new NickNameMsg(parts[1], Integer.parseInt(parts[3]));
        }
        if (type == TypeOfCommand.DRAWLEADERCARDS) {
            return new DrawLeadersMsg();
        }
        if (type == TypeOfCommand.DISCARDLEADERCARDS) {
            return new DiscardLeadersAtTheStartMsg(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
        if (type == TypeOfCommand.SETINITSTORAGETYPES) {
            return new SetInitStorageTypeMsg(Resource.valueOf(parts[1]), Resource.valueOf(parts[2]), Resource.valueOf(parts[3]));
        }
        if (type == TypeOfCommand.ADDINITRESOURCES) {
            if (parts.length == 2)
                return new AddInitResourcesMsg(Resource.valueOf(parts[1]));
            else return new AddInitResourcesMsg(Resource.valueOf(parts[1]), Resource.valueOf(parts[2]));
        }


        if (type == TypeOfCommand.SELECTMARKETPHASE) {
            return new SelectMarketPhaseMsg();
        }
        if (type == TypeOfCommand.STARTMARKETPHASE) {
            return new StartMarketPhaseMsg(Integer.parseInt(parts[1]), Boolean.valueOf(parts[2]));
        }
        if (type == TypeOfCommand.WHITEMARBLES) {
            return new ManageWhiteMarbleMsg(Resource.valueOf(parts[1]));
        }
        if (type == TypeOfCommand.ORGANIZERESOURCES) {
            return new StartOrganizeResourcesMsg();
        }
        if (type == TypeOfCommand.SETSTORAGETYPES) {
            return new SetStorageTypesMsg(Resource.valueOf(parts[1]), Resource.valueOf(parts[2]), Resource.valueOf(parts[3]));
        }
        if (type == TypeOfCommand.MANAGERESOURCESBYDEFAULT) {
            return new DefaultManageResourcesToOrganizeMsg();
        }
        if (type == TypeOfCommand.MANAGERESOURCES) {
            return new ManageResourcesToOrganizeMsg(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        if (type == TypeOfCommand.STARTADDRESOURCES) {
            return new StartAddResourcesMsg();
        }
        if (type == TypeOfCommand.ADDRESOURCES) {
            return new AddResourceMsg(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        if (type == TypeOfCommand.DISCARDRESOURCES) {
            return new DiscardResourceMsg(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]));
        }


        if (type == TypeOfCommand.REORGANIZERESOURCES) {
            return new ManageResourcesInStorageMsg();
        }


        if (type == TypeOfCommand.SELECTBUYDEVELOPMENTCARDPHASE) {
            return new BuyDevelopmentPhaseMsg();
        }
        if (type == TypeOfCommand.BUYDEVELOPMENTCARD) {
            return new BuyCardMsg(Color.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        if (type == TypeOfCommand.PAYCARDFROMCHEST) {
            return new PayCardFromChestMsg(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]));
        }
        if (type == TypeOfCommand.PAYCARDFROMSTORAGE) {
            return new PayCardFromStorageMsg(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }


        if (type == TypeOfCommand.SELECTPRODUCTIONPHASE) {
            return new SelectProductionPhaseMsg();
        }
        if (type == TypeOfCommand.ACTIVATEPERSONALPRODUCTION) {
            return new ActivatePersonalProductionMsg(Resource.valueOf(parts[1]), Resource.valueOf(parts[2]), Resource.valueOf(parts[3]));
        }
        if (type == TypeOfCommand.ACTIVATEPRODUCTION) {
            return new ActivateProductionMsg(Integer.parseInt(parts[1]));
        }
        if (type == TypeOfCommand.ACTIVATESPECIALPRODUCTION) {
            return new ActivateSpecialProduction(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]));
        }
        if (type == TypeOfCommand.STARTPAYPRODUCTION) {
            return new StartPayProductionMsg();
        }
        if (type == TypeOfCommand.PAYPRODUCTIONFROMSTORAGE) {
            return new PayProductionFromStorage(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        if (type == TypeOfCommand.PAYPRODUCTIONFROMCHEST) {
            return new PayProductionFromChest(Resource.valueOf(parts[1]), Integer.parseInt(parts[2]));
        }


        if (type == TypeOfCommand.ACTIVATELEADERCARD) {
            return new ActivateLeaderMsg(Integer.parseInt(parts[1]));
        }
        if (type == TypeOfCommand.DISCARDLEADERCARD) {
            return new DiscardLeaderMsg(Integer.parseInt(parts[1]));
        }


        if (type == TypeOfCommand.ENDTURN) {
            return new EndTurnMsg();
        }

        if (type == TypeOfCommand.VIEWOTHERPLAYERS) {
            return new ViewOtherPlayersMsg();
        }
        if (type == TypeOfCommand.VIEWOTHERPLAYERNUMBER) {
            return new ViewOtherPlayerNumberMsg(Integer.parseInt(parts[0]));
        }

        if (type == TypeOfCommand.VIEWMARKET) {
            client.getLightModel().getMarketView().showMarbles(client.getLightModel().getMarket());
            client.getLightModel().getMarketView().plot();
            return null;
        }
        if (type == TypeOfCommand.VIEWDEVELOPMENTCARD) {
            client.getLightModel().getDevelCardsOfPlayerVisualizer().plot(client.getLightModel().getDevelopmentCard2());
            return null;
        }
        if (type == TypeOfCommand.VIEWLEADERS) {
            if (client.getLightModel().getLeaderCardsInHand().size() > 0) {
                System.out.println("\nHere are your LeadersInHand: ");
                for (LeaderCard leaderCard : client.getLightModel().getLeaderCardsInHand())
                    client.getLightModel().getLeaderCardVisualizer().showLeaderData(leaderCard);
            }

            if (client.getLightModel().getLeaderCardsPlayed().size() > 0) {
                System.out.println("\nHere are your LeadersPlayed: ");
                for (LeaderCard leaderCard : client.getLightModel().getLeaderCardsPlayed())
                    client.getLightModel().getLeaderCardVisualizer().showLeaderData(leaderCard);
            }
            return null;
        }
        if (type == TypeOfCommand.VIEWFAITHTRACK) {
            client.getLightModel().getFaithTrackVisualizer().plot(client.getLightModel().getPosition(), client.getLightModel().getPopeFavours());
            return null;
        }

        if (type == TypeOfCommand.VIEWDEVELOPMENTCARDSTOBUY) {
            client.getLightModel().getDevelopmentCardToBuyVisualizer().plot(client.getLightModel().getDevelopmentCardsToBuy());
            return null;
        }

        if (type == TypeOfCommand.VIEWPRODUCTIONS) {
            client.getLightModel().getProductionVisualizer().plot(client.getLightModel().getTotalCost(), client.getLightModel().getTotalGain(), client.getLightModel().getFaithPoints());
            return null;
        }

        if (type == TypeOfCommand.VIEWRESOURCES) {
            client.getLightModel().getChestVisualizer().plot(client.getLightModel().getChest(), "This is the chest");
            client.getLightModel().getStoragesVisualizer().plot(client.getLightModel().getStorageType(), client.getLightModel().getStorageQuantity());
            return null;
        } else return null;
    }


}
