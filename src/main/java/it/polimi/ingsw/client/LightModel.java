package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.*;
import it.polimi.ingsw.client.GUI.Gui;
import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.util.*;

public class LightModel {
    private Client client;
    private boolean CLI;
    private boolean GUI;                     //true se viene scelta la GUI

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


    private MarketVisualizer marketView;
    private DevelopmentCardVisualizer developmentCardView;
    private LeaderCardVisualizer leaderCardVisualizer;
    private FaithTrackVisualizer faithTrackVisualizer;
    private DevelopmentCardToBuyVisualizer developmentCardToBuyVisualizer;
    private DevelCardsOfPlayerVisualizer develCardsOfPlayerVisualizer;
    private ProductionVisualizer productionVisualizer;
    private ChestVisualizer chestVisualizer;
    private StoragesVisualizer storagesVisualizer;


    public LightModel(Client client){
        this.client = client;
        CLI = false;                                 //deve essere messa a true solo se il player sceglie di giocare con la CLI
        GUI = true;

        nickname = new String();
        phase = TurnState.BEFORESTART;
        popeFavours = new Boolean[3];
        popeFavours[0] = false;
        popeFavours[1] = false;
        popeFavours[2] = false;
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


        marketView = new MarketVisualizer();
        developmentCardView = new DevelopmentCardVisualizer();
        leaderCardVisualizer = new LeaderCardVisualizer();
        faithTrackVisualizer = new FaithTrackVisualizer();
        developmentCardToBuyVisualizer = new DevelopmentCardToBuyVisualizer();
        productionVisualizer = new ProductionVisualizer();
        chestVisualizer = new ChestVisualizer();
        storagesVisualizer = new StoragesVisualizer();
        develCardsOfPlayerVisualizer = new DevelCardsOfPlayerVisualizer();


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

    public List<Resource> getStorageType() {
        return storageType;
    }

    public List<Integer> getStorageQuantity() {
        return storageQuantity;
    }

    public Map<Resource, Integer> getChest() {
        return chest;
    }

    public List<DevelopmentCard> getDevelopmentCard() {
        return developmentCard;
    }

    public List<DevelopmentCard> getDevelopmentCardsToBuy(){
        return developmentCardsToBuy;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public Map<Resource, Integer> getTotalGain() {
        return totalGain;
    }

    public Map<Resource, Integer> getTotalCost() {
        return totalCost;
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

    public Boolean[] getPopeFavours() {
        return popeFavours;
    }

    public int getPosition() {
        return position;
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

    public Marble getMarbleInExcess(){
        return marbleInExcess;
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

    public FaithTrackVisualizer getFaithTrackVisualizer() {
        return faithTrackVisualizer;
    }

    public DevelopmentCardToBuyVisualizer getDevelopmentCardToBuyVisualizer(){
        return developmentCardToBuyVisualizer;
    }

    public ProductionVisualizer getProductionVisualizer() {
        return productionVisualizer;
    }

    public DevelCardsOfPlayerVisualizer getDevelCardsOfPlayerVisualizer() {
        return develCardsOfPlayerVisualizer;
    }

    public ChestVisualizer getChestVisualizer() {
        return chestVisualizer;
    }

    public StoragesVisualizer getStoragesVisualizer() {
        return storagesVisualizer;
    }




    public void update(String nickname, int playerID, int numberOfPlayers, TurnState phase){            //UpdateNicknameMsg
        this.setNickname(nickname);
        this.setPhase(phase);
        this.setPlayerID(playerID);
        this.setNumberOfPlayers(numberOfPlayers);

        if(CLI == true){

        }
        else if(GUI == true){
            client.getGui().waitScene();
        }
    }

    public void update(Marble[][] market, Marble marbleInExcess, List<DevelopmentCard> developmentCards, String currentPlayer, TurnState phase){    //GameStartMsg
        this.setMarket(market);
        this.setMarbleInExcess(marbleInExcess);
        this.setDevelopmentCardsToBuy(developmentCards);
        this.setCurrentPlayer(currentPlayer);
        this.setPhase(phase);

        this.setStorageType(Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        this.setStorageQuantity(0, 0, 0);

        if(CLI == true){
            faithTrackVisualizer.plot(position, popeFavours);
            chestVisualizer.plot(chest);
            storagesVisualizer.plot(storageType, storageQuantity);
        }
        else if(GUI == true){
            client.getGui().updatePersonalBoard(this);
        }
    }

    public void update(List<LeaderCard> leaderCardInHand, List<LeaderCard> leaderCardsPlayed, TurnState phase){     //UpdateLeaderCardsMsg
        this.setLeaderCardsInHand(leaderCardInHand);
        this.setLeaderCardsPlayed(leaderCardsInHand);
        this.setPhase(phase);

        if(CLI == true){
            if(getLeaderCardsInHand().size()>0){
                System.out.println("\nHere are your LeadersInHand: ");
                for(LeaderCard leaderCard:getLeaderCardsInHand()) getLeaderCardVisualizer().showLeaderData(leaderCard);
            }

            if(getLeaderCardsPlayed().size()>0){
                System.out.println("\nHere are your LeadersPlayed: ");
                for(LeaderCard leaderCard:getLeaderCardsPlayed()) getLeaderCardVisualizer().showLeaderData(leaderCard);
            }
        }
        else if(GUI == true){
            client.getGui().updatePersonalBoard(this);
        }
    }

    public void update(TurnState phase, Resource r1, Resource r2, Resource r3){     //UpdateStorageTypesMsg
        this.setStorageType(r1, r2, r3);
        this.setPhase(phase);

        if(CLI == true){
            storagesVisualizer.plot(storageType, storageQuantity);
        }
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

        if(CLI == true){
            storagesVisualizer.plot(storageType, storageQuantity);
        }
    }

    public void update(TurnState phase, int position, Boolean[] popeFavours){       //UpdateFaithMarkerPositionMsg
        this.setPhase(phase);
        this.setPosition(position);
        this.setPopeFavours(popeFavours);

        if(CLI == true){
            faithTrackVisualizer.plot(position,popeFavours);
        }
    }

    public void update(Market market){                          //UpdateMarketMsg
        this.setMarket(market.getMarketTable());
        this.setMarbleInExcess(market.getMarbleInExcess());

        if(CLI == true){
            marketView.showMarbles(market.getMarketTable());
            marketView.plot();
        }
    }

    public void update(TurnState phase, Map<Resource, Integer> map){     //UpdateResourcesToAddMsg
        this.setResourcesToAdd(map);
        this.setPhase(phase);

        if(CLI == true){
            chestVisualizer.plot(resourcesToAdd);
        }
    }

    public void update(int whiteMarbles){               //UpdateWhiteMarblesToManageMsg
        this.setWhiteMarblesToManage(whiteMarbles);

        if(CLI == true){

        }
    }

    public void update(Map<Resource, Integer> map, TurnState phase){     //ResourcesToOrganizeMsg
        this.setResourcesToOrganize(map);
        this.setPhase(phase);

        if(CLI == true){
            chestVisualizer.plot(resourcesToOrganize);
        }
    }

    public void update(DevelopmentCard card, int slot){             //Update CardSlotMsg
        this.setDevelopmentCard(card, slot);

        if(CLI == true){
            develCardsOfPlayerVisualizer.plot(developmentCard);
        }
    }

    public void update(List<DevelopmentCard> cards){                //UpdateDecksMsg
        this.setDevelopmentCardsToBuy(cards);

        if(CLI == true){
            developmentCardToBuyVisualizer.plot(developmentCardsToBuy);
        }
    }

    public void updateCardPrice(TurnState phase, Map<Resource, Integer> price){     //CardPriceMsg
        this.setPhase(phase);
        this.setCardCost(price);

        if(CLI == true){
            chestVisualizer.plot(cardCost);
        }
    }

    public void updateChest(TurnState phase, Map<Resource, Integer> mapFromChest){      //ChestMsg
        this.setChest(mapFromChest);
        this.setPhase(phase);

        if(CLI == true){
            chestVisualizer.plot(cardCost);
        }
    }

    public void update(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint){        //UpdateCostGainsMsg
        this.setTotalCost(productionInput);
        this.setTotalGain(productionOutput);
        this.setFaithPoints(faithPoint);

        if(CLI == true){
            productionVisualizer.plot(totalCost, totalGain, faithPoints);
        }
    }

    public void update(Map<Resource, Integer> totalCost){       //UpdateProductionCostMsg
        this.setTotalCost(totalCost);

        if(CLI == true){
            productionVisualizer.plot(this.totalCost, this.totalGain, faithPoints);
        }
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

        if(CLI == true){
            faithTrackVisualizer.plot(position, popeFavours);
            chestVisualizer.plot(chest);
            storagesVisualizer.plot(storageType, storageQuantity);
        }
    }

    public void update(TurnState phase, String currentPlayer){          //StartTurnMsg
        this.setPhase(phase);
        this.setCurrentPlayer(currentPlayer);

        if(CLI == true){
            faithTrackVisualizer.plot(position, popeFavours);
            chestVisualizer.plot(chest);
            storagesVisualizer.plot(storageType, storageQuantity);
        }
    }

    public Map<Resource, Integer> getCardCost() {
        return cardCost;
    }
}
