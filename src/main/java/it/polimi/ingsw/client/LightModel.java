package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.*;
import it.polimi.ingsw.client.GUI.*;
import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.util.*;
import java.util.prefs.Preferences;

/**
 * The type Light model. It is a copy of the model on the server
 */
public abstract class LightModel {
    private Client client;


    private final String PREF_CODE="Unicode";
    private String unicode=null;
    Preferences prefs = Preferences.userNodeForPackage(it.polimi.ingsw.client.LightModel.class);

    private String nickname;
    private List<Player> players;
    private int playerID;
    private int numberOfPlayers;
    private TurnState phase;
    private String currentPlayer;
    private Boolean[] popeFavours;
    private int position;
    private int faithPoints;
    private boolean isSoloGame;

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


    /**
     * Instantiates a new Light model.
     *
     * @param client the client
     */
    public LightModel(Client client){
        this.client = client;

        nickname = new String();
        phase = TurnState.STARTCONNECTION;
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
        storageQuantity.add(0);storageQuantity.add(0);storageQuantity.add(0);
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


    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets unicode.
     *
     * @param unicode the unicode
     */
    public void setUnicode(String unicode) {
        this.unicode = unicode;
        prefs.put(PREF_CODE, this.unicode);
    }

    /**
     * Gets unicode.
     *
     * @return the unicode
     */
    public String getUnicode() {
        if (unicode==null){
            String defaultValue = "default string";
            String propertyValue = prefs.get(PREF_CODE, defaultValue); // "a string"
            return propertyValue;
        }
        return unicode;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Sets players.
     *
     * @param players the players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Is solo game boolean.
     *
     * @return the boolean
     */
    public boolean isSoloGame() {
        return isSoloGame;
    }

    /**
     * Sets solo game.
     *
     * @param soloGame the solo game
     */
    public void setSoloGame(boolean soloGame) {
        isSoloGame = soloGame;
    }

    /**
     * Get player id int.
     *
     * @return the int
     */
    public int getPlayerID(){
        return playerID;
    }

    /**
     * Gets storage type.
     *
     * @return the storage type
     */
    public List<Resource> getStorageType() {
        return storageType;
    }

    /**
     * Gets storage quantity.
     *
     * @return the storage quantity
     */
    public List<Integer> getStorageQuantity() {
        return storageQuantity;
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public Map<Resource, Integer> getChest() {
        return chest;
    }

    /**
     * Gets development card.
     *
     * @return the development card
     */
    public List<DevelopmentCard> getDevelopmentCard() {
        return developmentCard;
    }

    /**
     * Gets development card 2.
     *
     * @return the development card 2
     */
    public Map<Integer, DevelopmentCard> getDevelopmentCard2() {
        return developmentCard2;
    }

    /**
     * Get development cards to buy list.
     *
     * @return the list
     */
    public List<DevelopmentCard> getDevelopmentCardsToBuy(){
        return developmentCardsToBuy;
    }

    /**
     * Gets card cost.
     *
     * @return the card cost
     */
    public Map<Resource, Integer> getCardCost() {
        return cardCost;
    }

    /**
     * Gets faith points.
     *
     * @return the faith points
     */
    public int getFaithPoints() {
        return faithPoints;
    }

    /**
     * Gets total gain.
     *
     * @return the total gain
     */
    public Map<Resource, Integer> getTotalGain() {
        return totalGain;
    }

    /**
     * Gets total cost.
     *
     * @return the total cost
     */
    public Map<Resource, Integer> getTotalCost() {
        return totalCost;
    }

    /**
     * Get special production list.
     *
     * @return the list
     */
    public List<Resource> getSpecialProduction(){
        return specialProduction;
    }

    /**
     * Get phase turn state.
     *
     * @return the turn state
     */
    public TurnState getPhase(){
        return phase;
    }

    /**
     * Get current player string.
     *
     * @return the string
     */
    public String getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Get nickname string.
     *
     * @return the string
     */
    public String getNickname(){
        return nickname;
    }


    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Sets player id.
     *
     * @param playerID the player id
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Sets number of players.
     *
     * @param numberOfPlayers the number of players
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Sets phase.
     *
     * @param phase the phase
     */
    public void setPhase(TurnState phase) {
        this.phase = phase;
    }

    /**
     * Set current player.
     *
     * @param currentPlayer the current player
     */
    public void setCurrentPlayer(String currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets pope favours.
     *
     * @param popeFavours the pope favours
     */
    public void setPopeFavours(Boolean[] popeFavours) {
        this.popeFavours = popeFavours;
    }

    /**
     * Get pope favours boolean [ ].
     *
     * @return the boolean [ ]
     */
    public Boolean[] getPopeFavours() {
        return popeFavours;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Gets leader cards in hand.
     *
     * @return the leader cards in hand
     */
    public List<LeaderCard> getLeaderCardsInHand() {
        return leaderCardsInHand;
    }

    /**
     * Gets leader cards played.
     *
     * @return the leader cards played
     */
    public List<LeaderCard> getLeaderCardsPlayed() {
        return leaderCardsPlayed;
    }

    /**
     * Sets market.
     *
     * @param market the market
     */
    public void setMarket(Marble[][] market) {
        this.market = market;

    }

    /**
     * Get market marble [ ] [ ].
     *
     * @return the marble [ ] [ ]
     */
    public Marble[][] getMarket() {
        return market;
    }

    /**
     * Get marble in excess marble.
     *
     * @return the marble
     */
    public Marble getMarbleInExcess(){
        return marbleInExcess;
    }

    /**
     * Sets marble in excess.
     *
     * @param marbleInExcess the marble in excess
     */
    public void setMarbleInExcess(Marble marbleInExcess) {
        this.marbleInExcess = marbleInExcess;
    }

    /**
     * Sets leader cards in hand.
     *
     * @param leaderCardsInHand the leader cards in hand
     */
    public void setLeaderCardsInHand(List<LeaderCard> leaderCardsInHand) {
        this.leaderCardsInHand = leaderCardsInHand;
    }

    /**
     * Sets leader cards played.
     *
     * @param leaderCardsPlayed the leader cards played
     */
    public void setLeaderCardsPlayed(List<LeaderCard> leaderCardsPlayed) {
        this.leaderCardsPlayed = leaderCardsPlayed;
    }

    /**
     * Sets development cards to buy.
     *
     * @param developmentCardsToBuy the development cards to buy
     */
    public void setDevelopmentCardsToBuy(List<DevelopmentCard> developmentCardsToBuy) {
        this.developmentCardsToBuy = developmentCardsToBuy;
    }

    /**
     * Sets storage quantity.
     *
     * @param i1 the 1
     * @param i2 the 2
     * @param i3 the 3
     */
    public void setStorageQuantity(int i1, int i2, int i3) {
        storageQuantity.set(0, i1);
        storageQuantity.set(1, i2);
        storageQuantity.set(2, i3);
    }

    /**
     * Sets storage quantity.
     *
     * @param i1 the 1
     * @param i2 the 2
     * @param i3 the 3
     * @param i4 the 4
     */
    public void setStorageQuantity(int i1, int i2, int i3, int i4) {
        storageQuantity.set(0, i1);
        storageQuantity.set(1, i2);
        storageQuantity.set(2, i3);
        storageQuantity.set(3, i4);
    }

    /**
     * Sets storage quantity.
     *
     * @param i1 the 1
     * @param i2 the 2
     * @param i3 the 3
     * @param i4 the 4
     * @param i5 the 5
     */
    public void setStorageQuantity(int i1, int i2, int i3, int i4, int i5) {
        storageQuantity.set(0, i1);
        storageQuantity.set(1, i2);
        storageQuantity.set(2, i3);
        storageQuantity.set(3, i4);
        storageQuantity.set(4, i5);
    }

    /**
     * Set storage type.
     *
     * @param i1 the 1
     * @param i2 the 2
     * @param i3 the 3
     */
    public void setStorageType(Resource i1, Resource i2, Resource i3){
        storageType.set(0, i1);
        storageType.set(1, i2);
        storageType.set(2, i3);
    }

    /**
     * Sets chest.
     *
     * @param chest the chest
     */
    public void setChest(Map<Resource, Integer> chest) {
        this.chest = chest;
    }

    /**
     * Sets resources to organize.
     *
     * @param resourcesToOrganize the resources to organize
     */
    public void setResourcesToOrganize(Map<Resource, Integer> resourcesToOrganize) {
        this.resourcesToOrganize = resourcesToOrganize;
    }

    /**
     * Sets resources to add.
     *
     * @param resourcesToAdd the resources to add
     */
    public void setResourcesToAdd(Map<Resource, Integer> resourcesToAdd) {
         this.resourcesToAdd = resourcesToAdd;
    }

    /**
     * Gets resources to organize.
     *
     * @return the resources to organize
     */
    public Map<Resource, Integer> getResourcesToOrganize() {
        return resourcesToOrganize;
    }

    /**
     * Gets resources to add.
     *
     * @return the resources to add
     */
    public Map<Resource, Integer> getResourcesToAdd() {
        return resourcesToAdd;
    }

    /**
     * Gets white marbles to manage.
     *
     * @return the white marbles to manage
     */
    public int getWhiteMarblesToManage() {
        return whiteMarblesToManage;
    }

    /**
     * Gets white marble.
     *
     * @return the white marble
     */
    public List<Resource> getWhiteMarble() {
        return whiteMarble;
    }

    /**
     * Sets total cost.
     *
     * @param totalCost the total cost
     */
    public void setTotalCost(Map<Resource, Integer> totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Sets total gain.
     *
     * @param totalGain the total gain
     */
    public void setTotalGain(Map<Resource, Integer> totalGain) {
        this.totalGain = totalGain;
    }

    /**
     * Sets position.
     *
     * @param faithPoints the faith points
     */
    public void setPosition(int faithPoints) {
        this.position = faithPoints;
    }

    /**
     * Set faith points.
     *
     * @param faithPoints the faith points
     */
    public void setFaithPoints(int faithPoints){
        this.faithPoints = faithPoints;
    }

    /**
     * Sets development card.
     *
     * @param card the card
     * @param slot the slot
     */
    public void setDevelopmentCard(DevelopmentCard card, int slot) {
       developmentCard.set(slot-1, card);
    }

    /**
     * Sets development card 2.
     *
     * @param card the card
     * @param slot the slot
     */
    public void setDevelopmentCard2(DevelopmentCard card, int slot) {       //la lista dà eccezione all'inizio perchè è vuota
        developmentCard2.put(slot, card);
    }

    /**
     * Sets card cost.
     *
     * @param cardCost the card cost
     */
    public void setCardCost(Map<Resource,Integer> cardCost) {
        this.cardCost = cardCost;
    }

    /**
     * Sets white marbles to manage.
     *
     * @param whiteMarblesToManage the white marbles to manage
     */
    public void setWhiteMarblesToManage(int whiteMarblesToManage) {
        this.whiteMarblesToManage = whiteMarblesToManage;
    }

    /**
     * Add leader storage.
     *
     * @param resource the resource
     */
    public void addLeaderStorage(Resource resource){
        storageQuantity.add(0);
        storageType.add(resource);
    }

    /**
     * Add reduction.
     *
     * @param resource the resource
     */
    public void addReduction(Resource resource){
        reduction.add(resource);
    }

    /**
     * Add white marble.
     *
     * @param resource the resource
     */
    public void addWhiteMarble(Resource resource){
        whiteMarble.add(resource);
    }

    /**
     * Add special production.
     *
     * @param resource the resource
     */
    public void addSpecialProduction(Resource resource){
        specialProduction.add(resource);
    }


    /**
     * Gets market view.
     *
     * @return the market view
     */
    public MarketVisualizer getMarketView() {
        return marketView;
    }

    /**
     * Gets development card view.
     *
     * @return the development card view
     */
    public DevelopmentCardVisualizer getDevelopmentCardView() {
        return developmentCardView;
    }

    /**
     * Gets leader card visualizer.
     *
     * @return the leader card visualizer
     */
    public LeaderCardVisualizer getLeaderCardVisualizer() {
        return leaderCardVisualizer;
    }

    /**
     * Gets faith track visualizer.
     *
     * @return the faith track visualizer
     */
    public FaithTrackVisualizer getFaithTrackVisualizer() {
        return faithTrackVisualizer;
    }

    /**
     * Get development card to buy visualizer development card to buy visualizer.
     *
     * @return the development card to buy visualizer
     */
    public DevelopmentCardToBuyVisualizer getDevelopmentCardToBuyVisualizer(){
        return developmentCardToBuyVisualizer;
    }

    /**
     * Gets production visualizer.
     *
     * @return the production visualizer
     */
    public ProductionVisualizer getProductionVisualizer() {
        return productionVisualizer;
    }

    /**
     * Gets devel cards of player visualizer.
     *
     * @return the devel cards of player visualizer
     */
    public DevelCardsOfPlayerVisualizer getDevelCardsOfPlayerVisualizer() {
        return develCardsOfPlayerVisualizer;
    }

    /**
     * Gets chest visualizer.
     *
     * @return the chest visualizer
     */
    public ChestVisualizer getChestVisualizer() {
        return chestVisualizer;
    }

    /**
     * Gets storages visualizer.
     *
     * @return the storages visualizer
     */
    public StoragesVisualizer getStoragesVisualizer() {
        return storagesVisualizer;
    }

    /**
     * Update.
     */
    public void update(){};

    /**
     * Update.
     *
     * @param nickname        the nickname
     * @param playerID        the player id
     * @param numberOfPlayers the number of players
     * @param phase           the phase
     */
    public void update(String nickname, int playerID, int numberOfPlayers, TurnState phase){            //UpdateNicknameMsg

    }

    /**
     * Update.
     *
     * @param market           the market
     * @param marbleInExcess   the marble in excess
     * @param developmentCards the development cards
     * @param currentPlayer    the current player
     * @param phase            the phase
     * @param isSoloGame       the is solo game
     */
    public void update(Marble[][] market, Marble marbleInExcess, List<DevelopmentCard> developmentCards, String currentPlayer, TurnState phase, boolean isSoloGame){    //GameStartMsg

    }

    /**
     * Update.
     *
     * @param leaderCardInHand  the leader card in hand
     * @param leaderCardsPlayed the leader cards played
     * @param phase             the phase
     */
    public void update(List<LeaderCard> leaderCardInHand, List<LeaderCard> leaderCardsPlayed, TurnState phase){     //UpdateLeaderCardsMsg

    }

    /**
     * Update.
     *
     * @param phase the phase
     * @param r1    the r 1
     * @param r2    the r 2
     * @param r3    the r 3
     */
    public void update(TurnState phase, Resource r1, Resource r2, Resource r3){     //UpdateStorageTypesMsg

    }

    /**
     * Update.
     *
     * @param phase    the phase
     * @param storages the storages
     */
    public void update(TurnState phase, Integer[] storages){                        //UpdateStorageMsg

    }

    /**
     * Update.
     *
     * @param phase       the phase
     * @param position    the position
     * @param popeFavours the pope favours
     */
    public void update(TurnState phase, int position, Boolean[] popeFavours){       //UpdateFaithMarkerPositionMsg

    }

    /**
     * Update.
     *
     * @param phase the phase
     */
    public void update(TurnState phase){                            //UpdatePhaseMsg

    }

    /**
     * Update.
     *
     * @param market the market
     */
    public void update(Market market){                          //UpdateMarketMsg

    }

    /**
     * Update.
     *
     * @param phase the phase
     * @param map   the map
     */
    public void update(TurnState phase, Map<Resource, Integer> map){     //UpdateResourcesToAddMsg

    }

    /**
     * Update.
     *
     * @param whiteMarbles the white marbles
     */
    public void update(int whiteMarbles){               //UpdateWhiteMarblesToManageMsg
    }

    /**
     * Update.
     *
     * @param map   the map
     * @param phase the phase
     */
    public void update(Map<Resource, Integer> map, TurnState phase){     //ResourcesToOrganizeMsg

    }

    /**
     * Update.
     *
     * @param card the card
     * @param slot the slot
     */
    public void update(DevelopmentCard card, int slot){             //Update CardSlotMsg

    }

    /**
     * Update.
     *
     * @param cards the cards
     */
    public void update(List<DevelopmentCard> cards){                //UpdateDecksMsg

    }

    /**
     * Update card price.
     *
     * @param phase the phase
     * @param price the price
     */
    public void updateCardPrice(TurnState phase, Map<Resource, Integer> price){     //CardPriceMsg

    }

    /**
     * Update chest.
     *
     * @param phase        the phase
     * @param mapFromChest the map from chest
     */
    public void updateChest(TurnState phase, Map<Resource, Integer> mapFromChest){      //ChestMsg

    }

    /**
     * Update.
     *
     * @param productionInput  the production input
     * @param productionOutput the production output
     * @param faithPoint       the faith point
     */
    public void update(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint){        //UpdateCostGainsMsg

    }

    /**
     * Update.
     *
     * @param totalCost the total cost
     */
    public void update(Map<Resource, Integer> totalCost){       //UpdateProductionCostMsg

    }

    /**
     * Update.
     *
     * @param phase       the phase
     * @param chest       the chest
     * @param position    the position
     * @param popeFavours the pope favours
     */
    public void update(TurnState phase, Map<Resource, Integer> chest, int position, Boolean[] popeFavours){     //EndProductionMsg

    }

    /**
     * Update.
     *
     * @param phase         the phase
     * @param currentPlayer the current player
     */
    public void update(TurnState phase, String currentPlayer){          //StartTurnMsg

    }

    /**
     * Update.
     *
     * @param message the message
     */
    public void update(String message){                                //ErrorMsg

    }

    /**
     * Update.
     *
     * @param message the message
     * @param bool    the bool
     */
    public void update(String message, Boolean bool){

    }

    /**
     * Update.
     *
     * @param players the players
     */
    public void update(ArrayList<Player> players) {

    }

    /**
     * Show.
     *
     * @param phase             the phase
     * @param nickname          the nickname
     * @param mapFromChest      the map from chest
     * @param storages          the storages
     * @param resourceList      the resource list
     * @param position          the position
     * @param popeFavours       the pope favours
     * @param leaderCardsPlayed the leader cards played
     */
    public void show(TurnState phase, String nickname, Map<Resource, Integer> mapFromChest, Integer[] storages, List<Resource> resourceList, int position, Boolean[] popeFavours, List<LeaderCard> leaderCardsPlayed){

    }
}
