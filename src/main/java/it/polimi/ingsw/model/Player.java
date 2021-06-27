package it.polimi.ingsw.model;

import Exceptions.ModelException;
import it.polimi.ingsw.server.Controller;

import java.io.Serializable;
import java.util.*;

/**
 * The type Player. Contains methods to manage all the possible moves that a player could make during his turn
 */
public class Player implements Serializable {
    private String nickname;
    private int playerID;
    private PersonalBoard personalBoard;
    private Game game;
    private TurnState phase;
    private boolean leaderAction;
    private boolean initPhaseDone;
    private boolean manageResources;
    private TurnState lastState;

    /**
     * Instantiates a new Player.
     *
     * @param nickname the nickname
     * @param playerID the player id
     * @param game     the game
     */
    public Player(String nickname, int playerID, Game game) {
        this.nickname = nickname;
        this.playerID = playerID;
        this.game = game;
        this.personalBoard = new PersonalBoard(game.getVaticanReportSections());
        this.phase = TurnState.PREPARATION;
        this.leaderAction = false;
        this.initPhaseDone = false;
        this.manageResources = false;
    }

    /**
     * Gets player id.
     *
     * @return the player id
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets nickname.
     *
     * @param name the nickname
     */
    public void setNickname(String name) {
        nickname = name;
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
     * Gets personal board.
     *
     * @return the personal board of the player
     */
    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    /**
     * Set player as the current player.
     */
    public void setAsCurrentPlayer(){
        if (!initPhaseDone) {
            phase = TurnState.PREPARATION;
        }
        else phase = TurnState.START;
        leaderAction = false;
    }

    /**
     * Is init phase done boolean. Init phase is the phase when the player draws the leader cards, discards the leader
     * cards and chooses the initial resources
     *
     * @return the boolean
     */
    public boolean isInitPhaseDone() {
        return initPhaseDone;
    }

    /**
     * Get phase of the player.
     *
     * @return the phase
     */
    public TurnState getPhase(){
        return phase;
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
     * Get if the leader action has been done during this turn or not
     *
     * @return the boolean
     */
    public boolean getLeaderAction(){
        return leaderAction;
    }

    /**
     * Draw 4 leader cards.
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void drawLeaderCards(Controller controller) throws ModelException{
        if(phase != TurnState.PREPARATION) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        for(int i = 0; i < 4; i++) {
            personalBoard.getLeaderCardsInHand().add(game.getTable().getLeaderCardDeck().draw());
        }
        phase = TurnState.CHOOSELEADERCARDS;
        controller.sendUpdateDrawLeaders(this);
    }

    /**
     * Choose 2 leader cards to discard.
     *
     * @param i1         the first leader card
     * @param i2         the second leader card
     * @param controller the controller
     * @throws ModelException if the player is in another phase or wants to discard a non existing card
     */
    public void chooseLeaderCardsToDiscard(int i1, int i2, Controller controller) throws ModelException{
        if(phase != TurnState.CHOOSELEADERCARDS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(i1<1||i1>4||i2<1||i2>4) throw new ModelException("Invalid position of the leadercard");
        if(i1 == 12) throw new ModelException("You must choose two different leader cards");
        personalBoard.getLeaderCardsInHand().remove(i1-1);
        personalBoard.getLeaderCardsInHand().remove(i2-2);
        phase = TurnState.CHOOSERESOURCES;
        controller.sendUpdateDiscardLeaders(this);
    }

    /**
     * Sets initial storage types.
     *
     * @param controller the controller
     * @param i1         the first type
     * @param i2         the second type
     * @param i3         the third type
     * @throws ModelException if the player is in another phase or chooses wrong types
     */
    public void setInitStorageTypes(Controller controller,Resource i1, Resource i2, Resource i3) throws ModelException{
        if(phase != TurnState.CHOOSERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        Resource resource1 = personalBoard.getWareHouse().getTypeStorage(1);
        Resource resource2 = personalBoard.getWareHouse().getTypeStorage(2);
        Resource resource3 = personalBoard.getWareHouse().getTypeStorage(3);

        personalBoard.getWareHouse().setTypeOfStorage(1, i1);
        personalBoard.getWareHouse().setTypeOfStorage(2, i2);
        personalBoard.getWareHouse().setTypeOfStorage(3, i3);
        if(!personalBoard.getWareHouse().controlStoragesType()){
            personalBoard.getWareHouse().setTypeOfStorage(1, resource1);
            personalBoard.getWareHouse().setTypeOfStorage(2, resource2);
            personalBoard.getWareHouse().setTypeOfStorage(3, resource3);
            throw new ModelException("Wrong choice for storage types");
        }
        if(playerID == 1) {
            phase = TurnState.ENDPREPARATION;
            initPhaseDone = true;
        }
        if(playerID == 1&&game.isSoloMatch()) {
            phase = TurnState.START;
            initPhaseDone = true;
        }
        controller.sendUpdateInitStorageTypes(this, personalBoard.getWareHouse().getTypeStorage(1), personalBoard.getWareHouse().getTypeStorage(2), personalBoard.getWareHouse().getTypeStorage(3));
    }

    /**
     * Add initial resource to storages.
     *
     * @param controller the controller
     * @param i          the resource
     * @throws ModelException if the player is in another phase or needs to choose 2 resources
     */
    public void addInitResources(Controller controller, Resource i) throws ModelException{
        if(phase != TurnState.CHOOSERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(playerID == 1) {
            phase = TurnState.ENDPREPARATION;
            initPhaseDone = true;
        }
        if(playerID == 2){
            personalBoard.getWareHouse().addInitResources(i);
            phase = TurnState.ENDPREPARATION;
            initPhaseDone = true;
        }
        if(playerID == 3){
            personalBoard.getWareHouse().addInitResources(i);
            personalBoard.getFaithTrack().movePositionForward();
            phase = TurnState.ENDPREPARATION;
            initPhaseDone = true;
        }
        if(playerID == 4) throw new ModelException("Player 4 must choose 2 resources to start");
        controller.sendUpdateAddInitResources(this);
    }

    /**
     * Add 2 initial resources.
     *
     * @param controller the controller
     * @param i1         the first resource
     * @param i2         the second resource
     * @throws ModelException if the player is in another phase
     */
    public void addInitResources(Controller controller, Resource i1, Resource i2) throws ModelException{
        if(phase != TurnState.CHOOSERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(playerID != 4) throw new ModelException("Player " + playerID + " must choose only 1 resource");
        personalBoard.getWareHouse().addInitResources(i1, i2);
        personalBoard.getFaithTrack().movePositionForward();
        phase = TurnState.ENDPREPARATION;
        initPhaseDone = true;
        controller.sendUpdateAddInitResources(this);
    }


    /**
     * Select market phase.
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void selectMarketPhase(Controller controller) throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.MARKETPHASE;
        controller.sendUpdateSelectMarketPhase(this);
    }

    /**
     * Select row or column of the market
     *
     * @param controller the controller
     * @param dim        the dimension
     * @param row        is true for rows
     * @throws ModelException if the player is in another phase
     */
    public void startMarketPhase(Controller controller, int dim, boolean row) throws ModelException{
        if(phase != TurnState.MARKETPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        ArrayList<Marble> marbles;
        if(row){
            marbles = game.getTable().getMarket().chooseRow(dim-1);
        }
        else marbles = game.getTable().getMarket().chooseColumn(dim-1);

        for(Marble i : marbles){
            i.whenDrawn(personalBoard);
        }
        game.getTable().getMarket().reorganize(marbles, dim-1);
        if(personalBoard.getWhiteMarble().size() == 2 && personalBoard.getManageWhiteMarbles() > 0) phase = TurnState.WHITEMARBLES;
        else phase = TurnState.CHOICE;
        controller.sendUpdateStartMarketPhase(this,marbles);
    }

    /**
     * Manage white marbles. Each white marble is substituted whit another resource based on the leader cards activated
     *
     * @param controller the controller
     * @param type       the resource
     * @throws ModelException if the player is in another phase
     */
    public void manageWhiteMarbles(Controller controller,Resource type) throws ModelException{
        if(phase != TurnState.WHITEMARBLES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.manageWhiteMarbles(type);
        if(personalBoard.getManageWhiteMarbles() == 0) phase = TurnState.CHOICE;
        controller.sendUpdateManageWhiteMarble(this);
    }

    /**
     * Start organize resources in storages.
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void startOrganizeResources(Controller controller) throws ModelException{
        if(phase != TurnState.CHOICE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.ORGANIZERESOURCES;
        personalBoard.getWareHouse().getFromStorages();
        controller.sendUpdateStartOrganizeResources(this);
    }

    /**
     * Set storages types.
     *
     * @param controller the controller
     * @param i1         the first type
     * @param i2         the second type
     * @param i3         the third type
     * @throws ModelException if the player is in another phase
     */
    public void setStoragesTypes(Controller controller,Resource i1, Resource i2, Resource i3) throws ModelException{
        if(phase != TurnState.ORGANIZERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        Resource resource1 = personalBoard.getWareHouse().getTypeStorage(1);
        Resource resource2 = personalBoard.getWareHouse().getTypeStorage(2);
        Resource resource3 = personalBoard.getWareHouse().getTypeStorage(3);

        personalBoard.getWareHouse().setTypeOfStorage(1, i1);
        personalBoard.getWareHouse().setTypeOfStorage(2, i2);
        personalBoard.getWareHouse().setTypeOfStorage(3, i3);
        if(!personalBoard.getWareHouse().controlStoragesType()){
            personalBoard.getWareHouse().setTypeOfStorage(1, resource1);
            personalBoard.getWareHouse().setTypeOfStorage(2, resource2);
            personalBoard.getWareHouse().setTypeOfStorage(3, resource3);
            throw new ModelException("Wrong choice for storage types");
        }
        else if(manageResources) phase = TurnState.MANAGERESOURCES;
        else if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty() && personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        else if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty()) phase = TurnState.ADDRESOURCES;
        else phase = TurnState.MANAGERESOURCES;
        controller.sendUpdateSetStorageTypes(this);
    }

    /**
     * Put the resources to organize in the right storage
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void defaultManageResourcesToOrganize(Controller controller) throws ModelException{
        if(phase != TurnState.MANAGERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().defaultAddResourcesToOrganize();
        if(manageResources){
            phase = lastState;
            manageResources = false;
        }
        else if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        else phase = TurnState.ADDRESOURCES;
        controller.sendUpdateDefaultManageResourcesToOrganize(this);
    }

    /**
     * Put resources in a storage
     *
     * @param controller the controller
     * @param type       the type of the resources
     * @param i          the storage
     * @param n          the quantity of the resources
     * @throws ModelException if the player is in another phase
     */
    public void manageResourcesToOrganize(Controller controller, Resource type, int i, int n) throws ModelException{
        if(phase != TurnState.MANAGERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().addResourcestoOrganize(type, i, n);
        if(manageResources && personalBoard.getWareHouse().resourcesToOrganizeIsEmpty()){
            phase = lastState;
            manageResources = false;
        }
        else if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty() && personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        else if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty()) phase = TurnState.ADDRESOURCES;
        controller.sendUpdateManageResourcesToOrganize(this);
    }

    /**
     * Start add resources to storages (these resources were gained from the market)
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void startAddResources(Controller controller) throws ModelException{
        if(phase != TurnState.CHOICE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        phase = TurnState.ADDRESOURCES;
        controller.sendUpdateStartAddResources(this);
    }

    /**
     * Add resources gained from the market to a storage
     *
     * @param controller the controller
     * @param type       the type of the resources
     * @param i          the storage
     * @param n          the quantity of the resources
     * @throws ModelException if the player is in another phase
     */
    public void addResources(Controller controller,Resource type, int i, int n) throws ModelException{
        if(phase != TurnState.ADDRESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().addResourcestoAdd(type, i, n);
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        controller.sendUpdateAddResources(this);
    }

    /**
     * Discard resources.
     *
     * @param controller the controller
     * @param type       the type of the resources
     * @param n          the quantity of the resources
     * @throws ModelException if the player is in another phase
     */
    public void discardResources(Controller controller,Resource type, int n) throws ModelException{
        if(phase != TurnState.ADDRESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().discardResourcesToAdd(type, n);
        for(int i = 0; i < n; i++){
            if(game.isSoloMatch()) {
                game.getCpu().getFaithTrack().movePositionForward();
            }else{
                for(Player x : game.getPlayers()){
                    if(!x.equals(game.getCurrentPlayer())) {
                        x.personalBoard.getFaithTrack().movePositionForward();
                    }
                }
            }
        }
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        controller.sendUpdateDiscardResources(this);
    }

    /**
     * Start reorganizing resources in the storage even if it is not the market phase
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void manageResourcesInStorages(Controller controller) throws ModelException{
        if(phase != TurnState.START && phase != TurnState.ENDTURN) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        lastState = phase;
        phase = TurnState.CHOICE;
        manageResources = true;
        startOrganizeResources(controller);
    }


    /**
     * Start buy DevelopmentCard phase.
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase or he can't buy any card
     */
    public void selectBuyDevelopmentCardPhase(Controller controller) throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        boolean ok = false;
        for(DevelopmentCard card : game.getTable().getTopDevelopmentcards()){
            if(getPersonalBoard().controlCardToBuy(card, 1) == true || getPersonalBoard().controlCardToBuy(card, 2) == true || getPersonalBoard().controlCardToBuy(card, 3) == true){
                ok = true;
            }
        }
        if(ok == false) throw new ModelException("You can't buy any card from table");
        phase = TurnState.BUYDEVELOPMENTCARDPHASE;
        controller.sendUpdateBuydevelopmentPhase(this);
    }

    /**
     * Choose development card to buy.
     *
     * @param controller the controller
     * @param color      the color
     * @param level      the level
     * @param slot       the slot for the card
     * @throws ModelException if the player is in another phase
     */
    public void chooseDevelopmentCard(Controller controller,Color color, int level, int slot) throws ModelException{
        if(phase != TurnState.BUYDEVELOPMENTCARDPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        DevelopmentCard card = game.getTable().viewDevelopmentCard(color, level);
        personalBoard.chooseCardToBuy(card, slot);
        game.getTable().removeDevelopmentCard(color, level);
        phase = TurnState.PAYDEVELOPMENTCARD;
        controller.sendUpdateBuyCard(this,slot);
    }

    /**
     * Pay card all from chest.
     *
     * @throws ModelException if the player is in another phase
     */
    public void payCardAllFromChest() throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payAllfromChest();
        phase = TurnState.ENDTURN;
    }

    /**
     * Pay card from chest.
     *
     * @param controller the controller
     * @param type       the type of the resources
     * @param n          the quantity of the resources
     * @throws ModelException if the player is in another phase
     */
    public void payCardFromChest(Controller controller,Resource type, int n) throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payCardfromChest(type, n);
        if(personalBoard.isCardPayed()) phase = TurnState.ENDTURN;
        controller.sendUpdatePayCardFromChest(this);
    }

    /**
     * Pay card from storage.
     *
     * @param controller the controller
     * @param type       the type of the resources
     * @param n          the quantity of the resources
     * @param i          the storage
     * @throws ModelException if the player is in another phase
     */
    public void payCardFromStorage(Controller controller,Resource type, int n, int i) throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payCardfromStorage(type, n, i);
        if(personalBoard.isCardPayed()) phase = TurnState.ENDTURN;
        controller.sendUpdatePayCardFromStorage(this);
    }


    /**
     * Start production phase.
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void selectProductionPhase(Controller controller) throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.PRODUCTIONPHASE;
        controller.sendUpdateSelectProductionPhase(this);
    }

    /**
     * Activate production of a DevelopmentCard.
     *
     * @param controller the controller
     * @param i          the slot of the card
     * @throws ModelException if the player is in another phase
     */
    public void activateProductionOfSlot(Controller controller,int i) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activateProductionOfSlot(i);
        controller.sendUpdateActivateProduction(this);
    }

    /**
     * Activate personal production. It pays 2 resources to produce a resource
     *
     * @param controller the controller
     * @param input1     the first resource
     * @param input2     the second resource
     * @param output     the resource produced
     * @throws ModelException if the player is in another phase
     */
    public void activatePersonalProduction(Controller controller,Resource input1, Resource input2, Resource output) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activatePersonalProduction(input1, input2, output);
        controller.sendUpdateActivatePersonalProduction(this);
    }

    /**
     * Activate a special production of a leader card
     *
     * @param controller the controller
     * @param output     the resource produced
     * @param i          the special production
     * @throws ModelException if the player is in another phase
     */
    public void activateSpecialProduction(Controller controller,Resource output, int i) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activateSpecialProduction(output, i);
        controller.sendUpdateActivateSpecialProduction(this);
    }

    /**
     * Start paying productions.
     *
     * @param controller the controller
     * @throws ModelException if the player is in another phase
     */
    public void startPayProduction(Controller controller) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(getPersonalBoard().getProduction().totalCostIsEmpty()) phase = TurnState.ENDTURN;
        else phase = TurnState.PAYPRODUCTIONS;
        controller.sendUpdateStartPayProduction(this);
    }

    /**
     * Pay production all from chest.
     *
     * @throws ModelException if the player is in another phase
     */
    public void payProductionAllFromChest() throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payAllfromChest();
        personalBoard.getProduction().gainResourcesAndEndProduction();
        phase = TurnState.ENDTURN;
    }

    /**
     * Pay production from chest.
     *
     * @param controller the controller
     * @param type       the type of the resources
     * @param n          the quantity of the resources
     * @throws ModelException if the player is in another phase
     */
    public void payProductionFromChest(Controller controller,Resource type, int n) throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payCostfromChest(type, n);
        if(personalBoard.getProduction().totalCostIsEmpty()){
            personalBoard.getProduction().gainResourcesAndEndProduction();
            phase = TurnState.ENDTURN;
        }
        controller.sendUpdatePayProductionFromChest(this);
    }

    /**
     * Pay production from storage.
     *
     * @param controller the controller
     * @param type       the type of the resources
     * @param n          the quantity of the resources
     * @param i          the storage
     * @throws ModelException if the player is in another phase
     */
    public void payProductionFromStorage(Controller controller,Resource type, int n, int i) throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payCostfromStorage(type, n, i);
        if(personalBoard.getProduction().totalCostIsEmpty()){
            personalBoard.getProduction().gainResourcesAndEndProduction();
            phase = TurnState.ENDTURN;
        }
        controller.sendUpdatePayProductionFromStorage(this);
    }


    /**
     * Activate a leader card.
     *
     * @param controller the controller
     * @param i          the leader card
     * @throws ModelException if the player is in another phase or the leader action was already done this turn
     */
    public void activateLeaderCard(Controller controller,int i) throws ModelException{
        if(phase != TurnState.START && phase != TurnState.ENDTURN) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(leaderAction == true) throw new ModelException("Leader action already done for this turn");
        int storageLeader=personalBoard.getWareHouse().getStorages().size();
        int reduction=personalBoard.getReduction().size();
        int whiteMarbles=personalBoard.getWhiteMarble().size();
        int specialProd=personalBoard.getProduction().numOfSpecialProduction();
        personalBoard.activateLeaderCard(i);
        leaderAction = true;
        char leaderAct;
        if (storageLeader<personalBoard.getWareHouse().getStorages().size()) leaderAct='s';
        else if (reduction<personalBoard.getReduction().size()) leaderAct='r';
            else if (whiteMarbles<personalBoard.getWhiteMarble().size()) leaderAct='w';
                else leaderAct='p';
        controller.sendUpdateActivateLeader(this,leaderAct);
    }

    /**
     * Discard a leader card.
     *
     * @param controller the controller
     * @param i          the leader card
     * @throws ModelException if the player is in another phase or the leader action was already done this turn
     */
    public void discardLeaderCard(Controller controller,int i) throws ModelException{
        if(phase != TurnState.START && phase != TurnState.ENDTURN) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(leaderAction == true) throw new ModelException("Leader action already done for this turn");
        personalBoard.discardLeaderCard(i);
        leaderAction = true;
        controller.sendUpdateDiscardLeader(this);
    }
}