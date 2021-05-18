package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.DevelopmentCardVisualizer;
import it.polimi.ingsw.client.CLI.LeaderCardVisualizer;
import it.polimi.ingsw.client.CLI.MarketVisualizer;
import it.polimi.ingsw.model.*;
import java.util.*;

public class LightModel {
    private String nickname;
    private int playerID;
    private int numberOfPlayers;
    private TurnState phase;                //ricordarsi che ogni messaggio di update aggiorna la fase del player
    private String currentPlayer;
    private Boolean[] popeFavours;
    private int position;                   //posizione sul faithTrack
    private int faithPoints;                //faithPoints guadagnati in fase di produzione

    private Marble[][] market;
    private Marble marbleInExcess;
    private List<LeaderCard> leaderCardsInHand;
    private List<LeaderCard> leaderCardsPlayed;
    private List<DevelopmentCard> developmentCardsToBuy;

    private List<Resource> storageType;
    private List<Integer> storageQuantity;
    private Map<Resource, Integer> chest;
    private Map<Resource, Integer> resourcesToOrganize;
    private Map<Resource, Integer> resourcesToAdd;
    private Map<Resource, Integer> totalCost;
    private Map<Resource, Integer> totalGain;



    private List<DevelopmentCard> developmentCard;
    private Map<Resource, Integer> cardCost;

    private int whiteMarblesToManage;
    private List<Resource> whiteMarble;
    private List<Resource> reduction;
    private List<Resource> specialProduction;


    private MarketVisualizer marketView=new MarketVisualizer();
    private DevelopmentCardVisualizer developmentCardView = new DevelopmentCardVisualizer();
    private LeaderCardVisualizer leaderCardVisualizer=new LeaderCardVisualizer();


    public LightModel(){
        nickname = new String();
        phase = TurnState.BEFORESTART;
        popeFavours = new Boolean[3];
        market = new Marble[3][4];
        leaderCardsInHand = new ArrayList<>();
        leaderCardsPlayed = new ArrayList<>();
        developmentCardsToBuy = new ArrayList<>();
        position = 0;
        faithPoints = 0;
        developmentCard = new ArrayList<>();
        storageType = new ArrayList<>(3);
        storageQuantity = new ArrayList<>(3);
        storageType.add(Resource.COIN);storageType.add(Resource.COIN);storageType.add(Resource.COIN);
        storageQuantity.add(2);storageQuantity.add(2);storageQuantity.add(2);
        chest = new HashMap<>();
        resourcesToOrganize = new HashMap<>();
        resourcesToAdd = new HashMap<>();
        totalCost = new HashMap<>();
        totalGain = new HashMap<>();
        cardCost = new HashMap<>();

        whiteMarblesToManage=0;
        whiteMarble = new ArrayList<>();
        reduction = new ArrayList<>();
        specialProduction = new ArrayList<>();


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

    //mi serve per provare la DevelView
    public DevelopmentCard getDevelopmentCard() {
        return developmentCardsToBuy.get(1);
    }



    public TurnState getPhase(){
        return phase;
    }

    public String getCurrentPlayer(){
        return currentPlayer;
    }

    public String getNickname(){
        return nickname;
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

    public void setPopeFavours(Boolean[] popeFavours) {
        this.popeFavours = popeFavours;
    }

    public List<LeaderCard> getLeaderCardsInHand() {
        return leaderCardsInHand;
    }

    public List<LeaderCard> getLeaderCardsPlayed() {
        return leaderCardsPlayed;
    }

    public void setMarket(Marble[][] market) {
        this.market = market;

    }

    public Marble[][] getMarket() {
        return market;
    }

    public void setMarbleInExcess(Marble marbleInExcess) {
        this.marbleInExcess = marbleInExcess;
    }

    public void setLeaderCardsInHand(List<LeaderCard> leaderCardsInHand) {
        this.leaderCardsInHand = leaderCardsInHand;
    }

    public void setLeaderCardsPlayed(List<LeaderCard> leaderCardsPlayed) {
        this.leaderCardsPlayed = leaderCardsPlayed;
    }

    public void setDevelopmentCardsToBuy(List<DevelopmentCard> developmentCardsToBuy) {
        this.developmentCardsToBuy = developmentCardsToBuy;
    }

    public void setStorageQuantity(int i1, int i2, int i3) {
        storageQuantity.set(0, i1);
        storageQuantity.set(1, i2);
        storageQuantity.set(2, i3);
    }

    public void setStorageQuantity(int i1, int i2, int i3, int i4) {
        storageQuantity.set(0, i1);
        storageQuantity.set(1, i2);
        storageQuantity.set(2, i3);
        storageQuantity.set(3, 14);
    }

    public void setStorageQuantity(int i1, int i2, int i3, int i4, int i5) {
        storageQuantity.set(0, i1);
        storageQuantity.set(1, i2);
        storageQuantity.set(2, i3);
        storageQuantity.set(3, 14);
        storageQuantity.set(4, i5);
    }

    public void setStorageType(Resource i1, Resource i2, Resource i3){
        storageType.set(0, i1);
        storageType.set(1, i2);
        storageType.set(2, i3);
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

    public void setPosition(int faithPoints) {
        this.position = faithPoints;
    }

    public void setFaithPoints(int faithPoints){
        this.faithPoints = faithPoints;
    }

    public void setDevelopmentCard(DevelopmentCard card, int slot) {
        if(developmentCard.size()>=slot)developmentCard.set(slot-1, card);
        else developmentCard.add(card);
    }

    public void setCardCost(Map<Resource, Integer> cardCost) {
        this.cardCost = cardCost;
    }

    public void setWhiteMarblesToManage(int whiteMarblesToManage) {
        this.whiteMarblesToManage = whiteMarblesToManage;
    }

    public void addLeaderStorage(Resource resource){
        storageQuantity.add(0);
        storageType.add(resource);
    }

    public void addReduction(Resource resource){
        reduction.add(resource);
    }

    public void addWhiteMarble(Resource resource){
        whiteMarble.add(resource);
    }

    public void addSpecialProduction(Resource resource){
        specialProduction.add(resource);
    }

    public MarketVisualizer getMarketView() {
        return marketView;
    }

    public DevelopmentCardVisualizer getDevelopmentCardView() {
        return developmentCardView;
    }

    public LeaderCardVisualizer getLeaderCardVisualizer() {
        return leaderCardVisualizer;
    }
}
