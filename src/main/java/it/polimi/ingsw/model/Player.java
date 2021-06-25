package it.polimi.ingsw.model;

import Exceptions.ModelException;
import it.polimi.ingsw.server.Controller;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private String nickname;
    private int playerID;
    private PersonalBoard personalBoard;
    private Game game;
    private TurnState phase;
    private boolean leaderAction;
    private boolean initPhaseDone;
    private boolean manageResources;
    private TurnState lastState;       //serve solo per la fase in cui il giocatore decide di riorganizzare le risorse

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

    public int getPlayerID() {
        return playerID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        nickname = name;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public void setAsCurrentPlayer(){
        if (!initPhaseDone) {
            phase = TurnState.PREPARATION;
        }
        else phase = TurnState.START;
        leaderAction = false;
    }

    public boolean isInitPhaseDone() {
        return initPhaseDone;
    }

    public TurnState getPhase(){
        return phase;
    }

    public boolean getLeaderAction(){
        return leaderAction;
    }

    public void drawLeaderCards(Controller controller) throws ModelException{
        if(phase != TurnState.PREPARATION) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        for(int i = 0; i < 4; i++) {
            personalBoard.getLeaderCardsInHand().add(game.getTable().getLeaderCardDeck().draw());
        }
        phase = TurnState.CHOOSELEADERCARDS;
        controller.sendUpdateDrawLeaders(this);
    }

    public void chooseLeaderCardsToDiscard(int i1, int i2, Controller controller) throws ModelException{
        if(phase != TurnState.CHOOSELEADERCARDS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(i1<1||i1>4||i2<1||i2>4) throw new ModelException("Invalid position of the leadercard");
        if(i1 == 12) throw new ModelException("You must choose two different leader cards");
        personalBoard.getLeaderCardsInHand().remove(i1-1);
        personalBoard.getLeaderCardsInHand().remove(i2-2);
        phase = TurnState.CHOOSERESOURCES;
        controller.sendUpdateDiscardLeaders(this);
    }

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

    public void addInitResources(Controller controller, Resource i1, Resource i2) throws ModelException{
        if(phase != TurnState.CHOOSERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(playerID != 4) throw new ModelException("Player " + playerID + " must choose only 1 resource");
        personalBoard.getWareHouse().addInitResources(i1, i2);
        personalBoard.getFaithTrack().movePositionForward();
        personalBoard.getFaithTrack().movePositionForward();
        phase = TurnState.ENDPREPARATION;
        initPhaseDone = true;
        controller.sendUpdateAddInitResources(this);
    }


    public void selectMarketPhase(Controller controller) throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.MARKETPHASE;
        controller.sendUpdateSelectMarketPhase(this);
    }

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

    public void manageWhiteMarbles(Controller controller,Resource type) throws ModelException{
        if(phase != TurnState.WHITEMARBLES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.manageWhiteMarbles(type);
        if(personalBoard.getManageWhiteMarbles() == 0) phase = TurnState.CHOICE;
        controller.sendUpdateManageWhiteMarble(this);
    }

    public void startOrganizeResources(Controller controller) throws ModelException{
        if(phase != TurnState.CHOICE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.ORGANIZERESOURCES;
        personalBoard.getWareHouse().getFromStorages();
        controller.sendUpdateStartOrganizeResources(this);
    }

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

    public void startAddResources(Controller controller) throws ModelException{
        if(phase != TurnState.CHOICE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        phase = TurnState.ADDRESOURCES;
        controller.sendUpdateStartAddResources(this);
    }

    public void addResources(Controller controller,Resource type, int i, int n) throws ModelException{
        if(phase != TurnState.ADDRESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().addResourcestoAdd(type, i, n);
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        controller.sendUpdateAddResources(this);
    }

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

    public void manageResourcesInStorages(Controller controller) throws ModelException{
        if(phase != TurnState.START && phase != TurnState.ENDTURN) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        lastState = phase;
        phase = TurnState.CHOICE;
        manageResources = true;
        startOrganizeResources(controller);
    }


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

    public void chooseDevelopmentCard(Controller controller,Color color, int level, int slot) throws ModelException{
        if(phase != TurnState.BUYDEVELOPMENTCARDPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        DevelopmentCard card = game.getTable().viewDevelopmentCard(color, level);
        personalBoard.chooseCardToBuy(card, slot);
        game.getTable().removeDevelopmentCard(color, level);
        phase = TurnState.PAYDEVELOPMENTCARD;
        controller.sendUpdateBuyCard(this,slot);
    }

    public void payCardAllFromChest() throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payAllfromChest();
        phase = TurnState.ENDTURN;
    }

    public void payCardFromChest(Controller controller,Resource type, int n) throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payCardfromChest(type, n);
        if(personalBoard.isCardPayed()) phase = TurnState.ENDTURN;
        controller.sendUpdatePayCardFromChest(this);
    }

    public void payCardFromStorage(Controller controller,Resource type, int n, int i) throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payCardfromStorage(type, n, i);
        if(personalBoard.isCardPayed()) phase = TurnState.ENDTURN;
        controller.sendUpdatePayCardFromStorage(this);
    }


    public void selectProductionPhase(Controller controller) throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.PRODUCTIONPHASE;
        controller.sendUpdateSelectProductionPhase(this);
    }

    public void activateProductionOfSlot(Controller controller,int i) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activateProductionOfSlot(i);
        controller.sendUpdateActivateProduction(this);
    }

    public void activatePersonalProduction(Controller controller,Resource input1, Resource input2, Resource output) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activatePersonalProduction(input1, input2, output);
        controller.sendUpdateActivatePersonalProduction(this);
    }

    public void activateSpecialProduction(Controller controller,Resource output, int i) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activateSpecialProduction(output, i);
        controller.sendUpdateActivateSpecialProduction(this);
    }

    public void startPayProduction(Controller controller) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(getPersonalBoard().getProduction().totalCostIsEmpty()) phase = TurnState.ENDTURN;
        else phase = TurnState.PAYPRODUCTIONS;
        controller.sendUpdateStartPayProduction(this);
    }

    public void payProductionAllFromChest() throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payAllfromChest();
        personalBoard.getProduction().gainResourcesAndEndProduction();
        phase = TurnState.ENDTURN;
    }

    public void payProductionFromChest(Controller controller,Resource type, int n) throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payCostfromChest(type, n);
        if(personalBoard.getProduction().totalCostIsEmpty()){
            personalBoard.getProduction().gainResourcesAndEndProduction();
            phase = TurnState.ENDTURN;
        }
        controller.sendUpdatePayProductionFromChest(this);
    }

    public void payProductionFromStorage(Controller controller,Resource type, int n, int i) throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payCostfromStorage(type, n, i);
        if(personalBoard.getProduction().totalCostIsEmpty()){
            personalBoard.getProduction().gainResourcesAndEndProduction();
            phase = TurnState.ENDTURN;
        }
        controller.sendUpdatePayProductionFromStorage(this);
    }


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
        if (reduction<personalBoard.getReduction().size()) leaderAct='r';
        if (whiteMarbles<personalBoard.getWhiteMarble().size()) leaderAct='w';
        else leaderAct='p';
        controller.sendUpdateActivateLeader(this,leaderAct);
    }

    public void discardLeaderCard(Controller controller,int i) throws ModelException{
        if(phase != TurnState.START && phase != TurnState.ENDTURN) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(leaderAction == true) throw new ModelException("Leader action already done for this turn");
        personalBoard.discardLeaderCard(i);
        leaderAction = true;
        controller.sendUpdateDiscardLeader(this);
    }
}