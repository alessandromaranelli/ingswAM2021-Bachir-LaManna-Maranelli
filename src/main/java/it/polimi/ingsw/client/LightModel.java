package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;
import java.util.*;

public class LightModel {
    private String nickname;
    private int playerID;
    private int numberOfPlayers;
    private String currentPlayer;
    private TurnState phase;
    private boolean[] popeFavours;

    private Marble[][] market;
    private Marble marbleInExcess;
    private LeaderCard[] leaderCardsInHand;
    private LeaderCard[] leaderCardsPlayed;
    private DevelopmentCard[] developmentCardsToBuy;

    private Resource[] storageType;
    private int[] storageQuantity;
    private Map<Resource, Integer> chest;
    private Map<Resource, Integer> resourcesToOrganize;
    private Map<Resource, Integer> resourcesToAdd;
    private Map<Resource, Integer> totalCost;
    private Map<Resource, Integer> totalGain;
    private int faithPoints;
    private DevelopmentCard[] developmentCard;
    private Map<Resource, Integer> cardCost;

    public LightModel(){
        phase = TurnState.BEFORESTART;
        popeFavours = new boolean[3];
        market = new Marble[3][4];
        leaderCardsInHand = new LeaderCard[4];
        developmentCardsToBuy = new DevelopmentCard[12];
        faithPoints = 0;
        developmentCard = new DevelopmentCard[3];
        storageType = new Resource[3];
        storageQuantity = new int[3];

        chest.put(Resource.COIN, 0);
        chest.put(Resource.SHIELD, 0);
        chest.put(Resource.SERVANT, 0);
        chest.put(Resource.STONE, 0);

        resourcesToOrganize.put(Resource.COIN, 0);
        resourcesToOrganize.put(Resource.STONE, 0);
        resourcesToOrganize.put(Resource.SERVANT, 0);
        resourcesToOrganize.put(Resource.SHIELD, 0);

        resourcesToAdd.put(Resource.COIN, 0);
        resourcesToAdd.put(Resource.STONE, 0);
        resourcesToAdd.put(Resource.SERVANT, 0);
        resourcesToAdd.put(Resource.SHIELD, 0);

        totalCost.put(Resource.COIN, 0);
        totalCost.put(Resource.STONE, 0);
        totalCost.put(Resource.SERVANT, 0);
        totalCost.put(Resource.SHIELD, 0);

        totalGain.put(Resource.COIN, 0);
        totalGain.put(Resource.STONE, 0);
        totalGain.put(Resource.SERVANT, 0);
        totalGain.put(Resource.SHIELD, 0);

        cardCost.put(Resource.COIN, 0);
        cardCost.put(Resource.STONE, 0);
        cardCost.put(Resource.SERVANT, 0);
        cardCost.put(Resource.SHIELD, 0);
    }

    public TurnState getPhase(){
        return phase;
    }

    public String getCurrentPlayer(){
        return currentPlayer;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setPhase(TurnState phase) {
        this.phase = phase;
    }

    public void setCurrentPlayer(String currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    public void setPopeFavours(boolean[] popeFavours) {
        this.popeFavours = popeFavours;
    }

    public void setMarket(Marble[][] market) {
        this.market = market;
    }

    public void setMarbleInExcess(Marble marbleInExcess) {
        this.marbleInExcess = marbleInExcess;
    }

    public void setLeaderCardsInHand(LeaderCard[] leaderCardsInHand) {
        this.leaderCardsInHand = leaderCardsInHand;
    }

    public void setLeaderCardsPlayed(LeaderCard[] leaderCardsPlayed) {
        this.leaderCardsPlayed = leaderCardsPlayed;
    }

    public void setDevelopmentCardsToBuy(DevelopmentCard[] developmentCardsToBuy) {
        this.developmentCardsToBuy = developmentCardsToBuy;
    }

    public void setStorageQuantity(int i1, int i2, int i3) {
        storageQuantity[0] = i1;
        storageQuantity[1] = i2;
        storageQuantity[2] = i3;
    }

    public void setStorageType(Resource i1, Resource i2, Resource i3){
        storageType[0] = i1;
        storageType[1] = i2;
        storageType[2] = i3;
    }

    public void setChest(Map<Resource, Integer> chest) {
        this.chest = chest;
    }

    public void setResourcesToOrganize(Map<Resource, Integer> resourcesToOrganize) {
        if(resourcesToOrganize.isEmpty()){
            resourcesToOrganize.put(Resource.COIN, 0);
            resourcesToOrganize.put(Resource.STONE, 0);
            resourcesToOrganize.put(Resource.SERVANT, 0);
            resourcesToOrganize.put(Resource.SHIELD, 0);
        }
        else this.resourcesToOrganize = resourcesToOrganize;
    }

    public void setResourcesToAdd(Map<Resource, Integer> resourcesToAdd) {
        if(resourcesToAdd.isEmpty()){
            resourcesToAdd.put(Resource.COIN, 0);
            resourcesToAdd.put(Resource.STONE, 0);
            resourcesToAdd.put(Resource.SERVANT, 0);
            resourcesToAdd.put(Resource.SHIELD, 0);
        }
        else this.resourcesToAdd = resourcesToAdd;
    }

    public void setTotalCost(Map<Resource, Integer> totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalGain(Map<Resource, Integer> totalGain) {
        this.totalGain = totalGain;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    public void setDevelopmentCard(DevelopmentCard[] developmentCard) {
        this.developmentCard = developmentCard;
    }

    public void setCardCost(Map<Resource, Integer> cardCost) {
        this.cardCost = cardCost;
    }
}
