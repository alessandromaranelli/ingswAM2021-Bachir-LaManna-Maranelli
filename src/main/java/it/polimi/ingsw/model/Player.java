package it.polimi.ingsw.model;

import Exceptions.ModelException;

import java.util.*;

public class Player {
    private String nickname;
    private int playerID;
    private PersonalBoard personalBoard;
    private Game game;
    private TurnState phase;
    private boolean leaderAction;

    public Player(String nickname, int playerID, Game game) {
        this.nickname = nickname;
        this.playerID = playerID;
        this.game = game;
        this.personalBoard = new PersonalBoard(game.getVaticanReportSections());
        this.phase = TurnState.PREPARATION;
        this.leaderAction = false;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        nickname = nickname;
    }

    public void setPlayerID(int playerID) {
        playerID = playerID;
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public void setAsCurrentPlayer(){
        phase = TurnState.START;
        leaderAction = false;
    }

    public TurnState getPhase(){
        return phase;
    }

    public boolean getLeaderAction(){
        return leaderAction;
    }

    public void drawLeaderCards() throws ModelException{
        if(phase != TurnState.PREPARATION) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        for(int i = 0; i < 4; i++) {
            personalBoard.getLeaderCardsInHand().add(game.getTable().getLeaderCardDeck().draw());
        }
        phase = TurnState.CHOOSELEADERCARDS;
    }

    public void chooseLeaderCardsToDiscard(int i1, int i2) throws ModelException{
        if(phase != TurnState.CHOOSELEADERCARDS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getLeaderCardsInHand().remove(i1);
        personalBoard.getLeaderCardsInHand().remove(i2);
        phase = TurnState.CHOOSERESOURCES;
    }

    public void setInitStorageTypes(Resource i1, Resource i2, Resource i3) throws ModelException{
        if(phase != TurnState.CHOOSERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        Resource resource1 = personalBoard.getWareHouse().getTypeStorage(1);
        Resource resource2 = personalBoard.getWareHouse().getTypeStorage(2);
        Resource resource3 = personalBoard.getWareHouse().getTypeStorage(3);

        personalBoard.getWareHouse().setTypeOfStorage(1, i1);
        personalBoard.getWareHouse().setTypeOfStorage(2, i2);
        personalBoard.getWareHouse().setTypeOfStorage(3, i3);
        if(personalBoard.getWareHouse().controlStoragesType() == false){
            personalBoard.getWareHouse().setTypeOfStorage(1, resource1);
            personalBoard.getWareHouse().setTypeOfStorage(2, resource2);
            personalBoard.getWareHouse().setTypeOfStorage(3, resource3);
            throw new ModelException("Wrong choice for storage types");
        }
    }

    public void addInitResources(Resource i) throws ModelException{
        if(phase != TurnState.CHOOSERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(playerID == 0) phase = TurnState.ENDPREPARATION;
        if(playerID == 1){
            personalBoard.getWareHouse().addInitResources(i);
            phase = TurnState.ENDPREPARATION;
        }
        if(playerID == 2){
            personalBoard.getWareHouse().addInitResources(i);
            personalBoard.getFaithTrack().movePositionForward();
            phase = TurnState.ENDPREPARATION;
        }
        if(playerID == 3) throw new ModelException("Player 4 must choose 2 resources to start");
    }

    public void addInitResources(Resource i1, Resource i2) throws ModelException{
        if(phase != TurnState.CHOOSERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(playerID != 3) throw new ModelException("Player " + playerID+1 + " must choose only 1 resource");
        personalBoard.getWareHouse().addInitResources(i1, i2);
        personalBoard.getFaithTrack().movePositionForward();
        personalBoard.getFaithTrack().movePositionForward();
        phase = TurnState.ENDPREPARATION;
    }


    public void selectMarketPhase() throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.MARKETPHASE;
    }

    public void startMarketPhase(int dim, boolean row) throws ModelException{
        if(phase != TurnState.MARKETPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        ArrayList<Marble> marbles;
        if(row == true){
            marbles = game.getTable().getMarket().chooseRow(dim);
        }
        else marbles = game.getTable().getMarket().chooseColumn(dim);

        for(Marble i : marbles){
            i.whenDrawn(personalBoard);
        }
        game.getTable().getMarket().reorganize(marbles, dim);
        if(personalBoard.getWhiteMarble().size() == 2 && personalBoard.getManageWhiteMarbles() > 0) phase = TurnState.WHITEMARBLES;
        else phase = TurnState.CHOICE;
    }

    public void manageWhiteMarbles(Resource type) throws ModelException{
        if(phase != TurnState.WHITEMARBLES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.manageWhiteMarbles(type);
        if(personalBoard.getManageWhiteMarbles() == 0) phase = TurnState.CHOICE;
    }

    public void startOrganizeResources() throws ModelException{
        if(phase != TurnState.CHOICE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.ORGANIZERESOURCES;
        personalBoard.getWareHouse().getFromStorages();
    }

    public void setStoragesTypes(Resource i1, Resource i2, Resource i3) throws ModelException{
        if(phase != TurnState.ORGANIZERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        Resource resource1 = personalBoard.getWareHouse().getTypeStorage(1);
        Resource resource2 = personalBoard.getWareHouse().getTypeStorage(2);
        Resource resource3 = personalBoard.getWareHouse().getTypeStorage(3);

        personalBoard.getWareHouse().setTypeOfStorage(1, i1);
        personalBoard.getWareHouse().setTypeOfStorage(2, i2);
        personalBoard.getWareHouse().setTypeOfStorage(3, i3);
        if(personalBoard.getWareHouse().controlStoragesType() == false){
            personalBoard.getWareHouse().setTypeOfStorage(1, resource1);
            personalBoard.getWareHouse().setTypeOfStorage(2, resource2);
            personalBoard.getWareHouse().setTypeOfStorage(3, resource3);
            throw new ModelException("Wrong choice for storage types");
        }
        else if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty() && personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        else if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty()) phase = TurnState.ADDRESOURCES;
        else phase = TurnState.MANAGERESOURCES;
    }

    public void defualtManageResourcesToOrganize() throws ModelException{
        if(phase != TurnState.MANAGERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().defaultAddResourcesToOrganize();
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        phase = TurnState.ADDRESOURCES;
    }

    public void manageResourcesToOrganize(Resource type, int i, int n) throws ModelException{
        if(phase != TurnState.MANAGERESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().addResourcestoOrganize(type, i, n);
        if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty() && personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        if(personalBoard.getWareHouse().resourcesToOrganizeIsEmpty()) phase = TurnState.ADDRESOURCES;
    }

    public void startAddResources() throws ModelException{
        if(phase != TurnState.CHOICE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
        phase = TurnState.ADDRESOURCES;
    }

    public void addResources(Resource type, int i, int n) throws ModelException{
        if(phase != TurnState.ADDRESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().addResourcestoAdd(type, i, n);
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
    }

    public void discardResources(Resource type, int n) throws ModelException{
        if(phase != TurnState.ADDRESOURCES) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getWareHouse().discardResourcesToAdd(type, n);
        for(int i = 0; i < n; i++){
            for(Player x : game.getPlayers()){
                if(!x.equals(game.getCurrentPlayer())) {
                    x.personalBoard.getFaithTrack().movePositionForward();
                }
            }
        }
        if(personalBoard.getWareHouse().resourcesToAddIsEmpty()) phase = TurnState.ENDTURN;
    }

    public void manageResourcesInStorages() throws ModelException{
        phase = TurnState.CHOICE;
        startOrganizeResources();
    }


    public void selectBuyDevelopmentCardPhase() throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.BUYDEVELOPMENTCARDPHASE;
    }

    public void chooseDevelopmentCard(Color color, int level, int slot) throws ModelException{
        if(phase != TurnState.BUYDEVELOPMENTCARDPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        DevelopmentCard card = game.getTable().viewDevelopmentCard(color, level);
        personalBoard.chooseCardToBuy(card, slot);
        game.getTable().removeDevelopmentCard(color, level);
        phase = TurnState.PAYDEVELOPMENTCARD;
    }

    public void payCardAllFromChest() throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payAllfromChest();
        phase = TurnState.ENDTURN;
    }

    public void payCardFromChest(Resource type, int n) throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payCardfromChest(type, n);
        if(personalBoard.isCardPayed() == true) phase = TurnState.ENDTURN;
    }

    public void payCardFromStorage(Resource type, int n, int i) throws ModelException{
        if(phase != TurnState.PAYDEVELOPMENTCARD) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.payCardfromStorage(type, n, i);
        if(personalBoard.isCardPayed() == true) phase = TurnState.ENDTURN;
    }


    public void selectProductionPhase() throws ModelException{
        if(phase != TurnState.START) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.PRODUCTIONPHASE;
    }

    public void activateProductionOfSlot(int i) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activateProductionOfSlot(i);
    }

    public void activatePersonalProduction(Resource input1, Resource input2, Resource output) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activatePersonalProduction(input1, input2, output);
    }

    public void activateSpecialProduction(Resource output, int i) throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().activateSpecialProduction(output, i);
    }

    public void startPayProduction() throws ModelException{
        if(phase != TurnState.PRODUCTIONPHASE) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        phase = TurnState.PAYPRODUCTIONS;
    }

    public void payProductionAllFromChest() throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payAllfromChest();
        personalBoard.getProduction().gainResourcesAndEndProduction();
        phase = TurnState.ENDTURN;
    }

    public void payProductionFromChest(Resource type, int n) throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payCostfromChest(type, n);
        if(personalBoard.getProduction().totalCostIsEmpty()){
            personalBoard.getProduction().gainResourcesAndEndProduction();
            phase = TurnState.ENDTURN;
        }
    }

    public void payProductionFromStorage(Resource type, int n, int i) throws ModelException{
        if(phase != TurnState.PAYPRODUCTIONS) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.getProduction().payCostfromStorage(type, n, i);
        if(personalBoard.getProduction().totalCostIsEmpty()){
            personalBoard.getProduction().gainResourcesAndEndProduction();
            phase = TurnState.ENDTURN;
        }
    }


    public void activateLeaderCard(int i) throws ModelException{
        if(phase != TurnState.START && phase != TurnState.ENDTURN) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.activateLeaderCard(i);
        leaderAction = true;
    }

    public void discardLeaderCard(int i) throws ModelException{
        if(phase != TurnState.START && phase != TurnState.ENDTURN) throw new ModelException("Wrong phase, player " + playerID + " is in phase: " + phase.toString());
        personalBoard.discardLeaderCard(i);
        leaderAction = true;
    }








    /*

    public ArrayList<Marble> getResources (Table table, char rowCol, int dim){
        ArrayList<Marble> resources;
        if (rowCol=='c') resources= table.getMarket().chooseColumn(dim);
        else resources= table.getMarket().chooseRow(dim);
        table.getMarket().reorganize(resources,dim);
        for (Marble marble : resources){
            marble.whenDrawn(personalBoard);
        }
        manageResources();
        return resources;  //serve ritornare l'array? anche qua per la view
    }

    public void manageResources (){
        Map<Resource, Integer> resourcesToAdd = personalBoard.getWareHouse().getResourcesToAdd(); //forse serve per la view??
        personalBoard.getWareHouse().getFromStorages();
        Map<Resource, Integer> resourcesToOrganize = personalBoard.getWareHouse().getResourcesToOrganize(); //forse serve per la view??
        while (!personalBoard.getWareHouse().resourcesToOrganizeIsEmpty()){
            //l'utente sistema le risorse
        }
        if (!personalBoard.getWareHouse().resourcesToAddIsEmpty()){
            //somma il numero di risorse che vengono scartate
            int totResourcesToDiscard = personalBoard.getWareHouse().getResourcesToAdd().values().stream().reduce(0,Integer::sum);
            for (int i=0;i<totResourcesToDiscard;i++){
                discardResource();
            }
            personalBoard.getWareHouse().getResourcesToAdd().clear();
        }
    }

    public void discardResource () {
        for (Player player : game.getActivePlayers()){
            if (!player.equals(game.getCurrentPlayer())){
                player.getPersonalBoard().getFaithTrack().movePositionForward();
            }
        }
    }

    public void buyDevelopmentCard (Table table, Color color, int level) throws ModelException {
        Map<Resource,Integer> requirements= new HashMap<>();
        requirements.putAll(table.getDevelopmentCardDeck(color,level).verifyRequirement());
        if (!personalBoard.getWareHouse().controlRequirements(table.getDevelopmentCardDeck(color,level).getDevelopmentCards().peek())){
            throw new ModelException("Not enough resources");
        }
        requirements.forEach((Resource,Integer)->{
            while (Integer>0){
                //da aspettare per il prendere le risorse dai depositi
            }
        });
    }

    public void drawLeaderCard(Table table){
        ArrayList<LeaderCard> leaderCards= new ArrayList<>();
        for (int i=0 ; i<4 ; i++)
        leaderCards.add(table.getLeaderCardDeck().draw());
        chooseLeaderCards(leaderCards);
    }

    public void chooseLeaderCards(ArrayList<LeaderCard> leaderCards){

    }

    public void playLeaderCard(LeaderCard leaderCard) throws ModelException {
        if (!leaderCard.verifyRequirements(personalBoard)) throw new ModelException("Not having the requirements");
        personalBoard.getLeaderCardsPlayed().add(leaderCard);
    }

    public void discardLeaderCard(LeaderCard leaderCard){
        leaderCard.discard(personalBoard.getFaithTrack());
    }

    public void updateFaithMarkerAtTheStart (int pos){
        while (pos>0){
            personalBoard.getFaithTrack().movePositionForward();
            pos--;
        }
    }

    public void ActivateProduction(){}


     */
}