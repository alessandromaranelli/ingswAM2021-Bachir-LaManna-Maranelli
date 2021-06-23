package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.*;
import it.polimi.ingsw.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightModelCLI extends LightModel {


    public LightModelCLI(Client client) {
        super(client);

    }


    public void update(String nickname, int playerID, int numberOfPlayers, TurnState phase){            //UpdateNicknameMsg
        this.setNickname(nickname);
        this.setPhase(phase);
        this.setPlayerID(playerID);
        this.setNumberOfPlayers(numberOfPlayers);

    }

    public void update(Marble[][] market, Marble marbleInExcess, List<DevelopmentCard> developmentCards, String currentPlayer, TurnState phase){    //GameStartMsg
        this.setMarket(market);
        this.setMarbleInExcess(marbleInExcess);
        this.setDevelopmentCardsToBuy(developmentCards);
        this.setCurrentPlayer(currentPlayer);
        this.setPhase(phase);

        this.setStorageType(Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        this.setStorageQuantity(0, 0, 0);

        System.out.println("Your Situation: ->");
        getFaithTrackVisualizer().plot(getPosition(), getPopeFavours());
        getChestVisualizer().plot(getChest(),"This is the chest");
        getStoragesVisualizer().plot(getStorageType(), getStorageQuantity());

    }

    public void update(List<LeaderCard> leaderCardInHand, List<LeaderCard> leaderCardsPlayed, TurnState phase){     //UpdateLeaderCardsMsg
        this.setLeaderCardsInHand(leaderCardInHand);
        this.setLeaderCardsPlayed(leaderCardsPlayed);
        this.setPhase(phase);

        if(getLeaderCardsInHand().size()>0){
            System.out.println("\nHere are your LeadersInHand: ");
            for(LeaderCard leaderCard:getLeaderCardsInHand()) getLeaderCardVisualizer().showLeaderData(leaderCard);
        }

        if(getLeaderCardsPlayed().size()>0){
            System.out.println("\nHere are your LeadersPlayed: ");
            for(LeaderCard leaderCard:getLeaderCardsPlayed()) getLeaderCardVisualizer().showLeaderData(leaderCard);
        }
    }

    public void update(TurnState phase, Resource r1, Resource r2, Resource r3){     //UpdateStorageTypesMsg
        this.setStorageType(r1, r2, r3);
        this.setPhase(phase);

        //storagesVisualizer.plot(storageType, storageQuantity);

    }

    public void update(TurnState phase, Integer[] storages){                        //UpdateStorageMsg
        this.setPhase(phase);
        if(storages.length == 3){
            this.setStorageQuantity(storages[0], storages[1], storages[2]);
        }
        else if(storages.length == 4){
            this.setStorageQuantity(storages[0], storages[1], storages[2], storages[3]);
        }
        else this.setStorageQuantity(storages[0], storages[1], storages[2], storages[3], storages[4]);

        getStoragesVisualizer().plot(getStorageType(), getStorageQuantity());

    }

    public void update(TurnState phase, int position, Boolean[] popeFavours){       //UpdateFaithMarkerPositionMsg
        this.setPhase(phase);
        this.setPosition(position);
        this.setPopeFavours(popeFavours);

        getFaithTrackVisualizer().plot(position,popeFavours);

    }

    public void update(TurnState phase){                            //UpdatePhaseMsg
        this.setPhase(phase);

    }

    public void update(Market market){                          //UpdateMarketMsg
        this.setMarket(market.getMarketTable());
        this.setMarbleInExcess(market.getMarbleInExcess());

        getMarketView().showMarbles(market.getMarketTable());
        getMarketView().plot();

    }

    public void update(TurnState phase, Map<Resource, Integer> map){     //UpdateResourcesToAddMsg
        this.setResourcesToAdd(map);
        this.setPhase(phase);

        getChestVisualizer().plot(getResourcesToAdd(),"These are the resources to add");

    }

    public void update(int whiteMarbles){               //UpdateWhiteMarblesToManageMsg
        this.setWhiteMarblesToManage(whiteMarbles);

    }

    public void update(Map<Resource, Integer> map, TurnState phase){     //ResourcesToOrganizeMsg
        this.setResourcesToOrganize(map);
        this.setPhase(phase);

        getChestVisualizer().plot(getResourcesToOrganize(),"These are the resources to organize");
    }

    public void update(DevelopmentCard card, int slot){             //Update CardSlotMsg
        this.setDevelopmentCard2(card, slot);

        getDevelCardsOfPlayerVisualizer().plot(getDevelopmentCard2());

    }

    public void update(List<DevelopmentCard> cards){                //UpdateDecksMsg
        this.setDevelopmentCardsToBuy(cards);

        getDevelopmentCardToBuyVisualizer().plot(getDevelopmentCardsToBuy());


    }

    public void updateCardPrice(TurnState phase, Map<Resource, Integer> price){     //CardPriceMsg
        this.setPhase(phase);
        this.setCardCost(price);

        getChestVisualizer().plot(getCardCost(),"This is the card cost");

    }

    public void updateChest(TurnState phase, Map<Resource, Integer> mapFromChest){      //ChestMsg
        this.setChest(mapFromChest);
        this.setPhase(phase);

        getChestVisualizer().plot(getCardCost(),"This is the card cost");

    }

    public void update(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint){        //UpdateCostGainsMsg
        this.setTotalCost(productionInput);
        this.setTotalGain(productionOutput);
        this.setFaithPoints(faithPoint);

        getProductionVisualizer().plot(getTotalCost(), getTotalGain(), getFaithPoints());

    }

    public void update(Map<Resource, Integer> totalCost){       //UpdateProductionCostMsg
        this.setTotalCost(totalCost);

        getProductionVisualizer().plot(getTotalCost(), getTotalGain(), getFaithPoints());

    }

    public void update(TurnState phase, Map<Resource, Integer> chest, int position, Boolean[] popeFavours){     //EndProductionMsg
        this.setPhase(phase);
        this.setChest(chest);
        this.setPosition(position);
        this.setPopeFavours(popeFavours);
        this.setFaithPoints(0);

        Map<Resource, Integer> totalGains = new HashMap<>();
        totalGains.put(Resource.SERVANT, 0);
        totalGains.put(Resource.COIN, 0);
        totalGains.put(Resource.SHIELD, 0);
        totalGains.put(Resource.STONE, 0);
        this.setTotalGain(totalGains);


        getFaithTrackVisualizer().plot(getPosition(), getPopeFavours());
        getChestVisualizer().plot(getChest(),"This is the chest");
        getStoragesVisualizer().plot(getStorageType(), getStorageQuantity());

    }

    public void update(TurnState phase, String currentPlayer){          //StartTurnMsg
        this.setPhase(phase);
        this.setCurrentPlayer(currentPlayer);

        getFaithTrackVisualizer().plot(getPosition(), getPopeFavours());
        getChestVisualizer().plot(getChest(),"This is the chest");
        getStoragesVisualizer().plot(getStorageType(), getStorageQuantity());

    }

    public void update(String message){                                //ErrorMsg

    }

    public void show(TurnState phase, String nickname, Map<Resource, Integer> mapFromChest, Integer[] storages, List<Resource> resourceList, int position, Boolean[] popeFavours, List<LeaderCard> leaderCardsPlayed){
        setPhase(phase);

        System.out.println("\nThis is player "+nickname);
        getFaithTrackVisualizer().plot(position, popeFavours);
        getChestVisualizer().plot(mapFromChest,"This is his chest");
        getStoragesVisualizer().plot(resourceList, Arrays.asList(storages.clone()));
        if(leaderCardsPlayed.size()>0){
            System.out.println("\nHere are his LeadersPlayed: ");
            for(LeaderCard leaderCard:getLeaderCardsPlayed()) getLeaderCardVisualizer().showLeaderData(leaderCard);
        }

    }


}