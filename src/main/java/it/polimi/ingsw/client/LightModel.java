package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.*;
import it.polimi.ingsw.client.GUI.*;
import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.util.*;
import java.util.prefs.Preferences;

public abstract class LightModel {
    private Client client;


    private final String PREF_CODE="Unicode";
    private String unicode=null;
    Preferences prefs = Preferences.userNodeForPackage(it.polimi.ingsw.client.LightModel.class);

    private String nickname;
    private List<Player> players;
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
    private Map<Integer, DevelopmentCard> developmentCard2;
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

        nickname = new String();
        phase = TurnState.BEFORESTART;
        players = new ArrayList<>();
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
        developmentCard = new ArrayList<>(3);
        developmentCard2 = new HashMap<>();
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

        marketView = new MarketVisualizer();
        developmentCardView = new DevelopmentCardVisualizer();
        leaderCardVisualizer = new LeaderCardVisualizer();
        faithTrackVisualizer = new FaithTrackVisualizer();
        developmentCardToBuyVisualizer = new DevelopmentCardToBuyVisualizer();
        productionVisualizer = new ProductionVisualizer();
        chestVisualizer = new ChestVisualizer();
        storagesVisualizer = new StoragesVisualizer();
        develCardsOfPlayerVisualizer = new DevelCardsOfPlayerVisualizer();

    }


    public Client getClient() {
        return client;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
        prefs.put(PREF_CODE, this.unicode);
    }

    public String getUnicode() {
        if (unicode==null){
            String defaultValue = "default string";
            String propertyValue = prefs.get(PREF_CODE, defaultValue); // "a string"
            return propertyValue;
        }
        return unicode;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getPlayerID(){
        return playerID;
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

    public Map<Integer, DevelopmentCard> getDevelopmentCard2() {
        return developmentCard2;
    }

    public List<DevelopmentCard> getDevelopmentCardsToBuy(){
        return developmentCardsToBuy;
    }

    public Map<Resource, Integer> getCardCost() {
        return cardCost;
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

    public List<Resource> getSpecialProduction(){
        return specialProduction;
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
        this.resourcesToOrganize = resourcesToOrganize;
    }

    public void setResourcesToAdd(Map<Resource, Integer> resourcesToAdd) {
         this.resourcesToAdd = resourcesToAdd;
    }

    public Map<Resource, Integer> getResourcesToOrganize() {
        return resourcesToOrganize;
    }

    public Map<Resource, Integer> getResourcesToAdd() {
        return resourcesToAdd;
    }

    public int getWhiteMarblesToManage() {
        return whiteMarblesToManage;
    }

    public List<Resource> getWhiteMarble() {
        return whiteMarble;
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
       developmentCard.set(slot-1, card);
    }

    public void setDevelopmentCard2(DevelopmentCard card, int slot) {       //la lista dà eccezione all'inizio perchè è vuota
        developmentCard2.put(slot, card);
    }

    public void setCardCost(Map<Resource,Integer> cardCost) {
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

    public void update(){};

    public void update(String nickname, int playerID, int numberOfPlayers, TurnState phase){            //UpdateNicknameMsg

    }

    public void update(Marble[][] market, Marble marbleInExcess, List<DevelopmentCard> developmentCards, String currentPlayer, TurnState phase){    //GameStartMsg

    }

    public void update(List<LeaderCard> leaderCardInHand, List<LeaderCard> leaderCardsPlayed, TurnState phase){     //UpdateLeaderCardsMsg

    }

    public void update(TurnState phase, Resource r1, Resource r2, Resource r3){     //UpdateStorageTypesMsg

    }

    public void update(TurnState phase, Integer[] storages){                        //UpdateStorageMsg

    }

    public void update(TurnState phase, int position, Boolean[] popeFavours){       //UpdateFaithMarkerPositionMsg

    }

    public void update(TurnState phase){                            //UpdatePhaseMsg

    }

    public void update(Market market){                          //UpdateMarketMsg

    }

    public void update(TurnState phase, Map<Resource, Integer> map){     //UpdateResourcesToAddMsg

    }

    public void update(int whiteMarbles){               //UpdateWhiteMarblesToManageMsg
    }

    public void update(Map<Resource, Integer> map, TurnState phase){     //ResourcesToOrganizeMsg

    }

    public void update(DevelopmentCard card, int slot){             //Update CardSlotMsg

    }

    public void update(List<DevelopmentCard> cards){                //UpdateDecksMsg

    }

    public void updateCardPrice(TurnState phase, Map<Resource, Integer> price){     //CardPriceMsg

    }

    public void updateChest(TurnState phase, Map<Resource, Integer> mapFromChest){      //ChestMsg

    }

    public void update(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint){        //UpdateCostGainsMsg

    }

    public void update(Map<Resource, Integer> totalCost){       //UpdateProductionCostMsg

    }

    public void update(TurnState phase, Map<Resource, Integer> chest, int position, Boolean[] popeFavours){     //EndProductionMsg

    }

    public void update(TurnState phase, String currentPlayer){          //StartTurnMsg

    }

    public void update(String message){                                //ErrorMsg

    }

    public void update(ArrayList<Player> players) {

    }

    public void show(TurnState phase, String nickname, Map<Resource, Integer> mapFromChest, Integer[] storages, List<Resource> resourceList, int position, Boolean[] popeFavours, List<LeaderCard> leaderCardsPlayed){

    }
}
