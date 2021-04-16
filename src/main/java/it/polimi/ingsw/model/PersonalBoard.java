package it.polimi.ingsw.model;

import Exceptions.ModelException;

import java.util.*;

public class PersonalBoard {
    private ArrayList<LeaderCard> leaderCardsInHand;
    private ArrayList<LeaderCard> leaderCardsPlayed;
    private CardSlot cardSlot;
    private FaithTrack faithTrack;
    private WareHouse wareHouse;
    private Production production;

    private ArrayList<Resource> reduction;
    private ArrayList<Resource> whiteMarble;
    private int manageWhiteMarbles;

    private Map<Resource, Integer> cardCost= new HashMap<>();

    public PersonalBoard(ArrayList<VaticanReportSection> vaticanReportSections) {
        faithTrack = new FaithTrack(vaticanReportSections);
        wareHouse = new WareHouse();
        cardSlot = new CardSlot();
        production = new Production(this, wareHouse, cardSlot);
        leaderCardsInHand = new ArrayList<>();
        leaderCardsPlayed = new ArrayList<>();
        reduction = new ArrayList<>();
        whiteMarble = new ArrayList<>();
        manageWhiteMarbles= 0;
        cardCost = new HashMap<>();

        cardCost.put(Resource.COIN, 0);
        cardCost.put(Resource.STONE, 0);
        cardCost.put(Resource.SHIELD, 0);
        cardCost.put(Resource.SERVANT, 0);
    }

    public Production getProduction() {
        return production;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public CardSlot getCardSlot(){
        return cardSlot;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public void moveFaithMarker(){
        faithTrack.movePositionForward();
    }

    public ArrayList<Resource> getReduction(){
        return reduction;
    }

    public ArrayList<Resource> getWhiteMarble() {
        return whiteMarble;
    }

    public void addWhiteMarbleToManage(){
        manageWhiteMarbles++;
    }

    public int getManageWhiteMarbles(){
        return manageWhiteMarbles;
    }

    public void manageWhiteMarbles(Resource type) throws ModelException{
        if(manageWhiteMarbles == 0) throw new ModelException("No white marbles");
        if(whiteMarble.get(0) != type && whiteMarble.get(1) != type) throw new ModelException("Wrong type for white marble");
        manageWhiteMarbles--;
        wareHouse.addResource(type, 1);
    }

    public ArrayList<LeaderCard> getLeaderCardsInHand() {
        return leaderCardsInHand;
    }

    public ArrayList<LeaderCard> getLeaderCardsPlayed() {
        return leaderCardsPlayed;
    }

    public String getDescLeaderCardinHand(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");

        return leaderCardsInHand.get(i-1).getDescription();
    }

    public String getDescLeaderCardPlayed(int i) throws ModelException{
        if(i < 1 || i > leaderCardsPlayed.size()) throw new ModelException("Not existing leader card");

        return leaderCardsPlayed.get(i-1).getDescription();
    }

    public boolean verifyRequirementsLeaderCard(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");
        return leaderCardsInHand.get(i-1).verifyRequirements(this);
    }

    public void activateLeaderCard(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");
        if(!leaderCardsInHand.get(i - 1).verifyRequirements(this)) throw new ModelException("Requirements not satisfied");
        leaderCardsInHand.get(i-1).activateEffect(this);
        leaderCardsPlayed.add(leaderCardsInHand.get(i-1));
        leaderCardsInHand.remove(i-1);
    }

    public void discardLeaderCard(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");
        leaderCardsInHand.get(i-1).discard(faithTrack);
        leaderCardsInHand.remove(i-1);
    }


    public int countVictoryPoints() throws ModelException {
        int points=0;
        for (LeaderCard leaderCard : leaderCardsPlayed){
            points+=leaderCard.getVictoryPoints();
        }
        points+=cardSlot.countVictoryPoint();
        points+=wareHouse.totResources()/5;
        points+=faithTrack.getTrack().stream().filter(box -> faithTrack.getTrack().indexOf(box)<=faithTrack.getTrack().indexOf(faithTrack.checkPlayerPosition())).max(Comparator.comparingInt(Box::getVictoryPoints)).get().getVictoryPoints();
        points+=faithTrack.getPopeFavours().stream().filter(PopeFavour::isActivated).mapToInt(PopeFavour::getVictoryPoints).sum();
        return points;
    }

    //Adds a Resource to be discounted when buying DevCards
    public void addReduction(Resource reduction) {
        this.reduction.add(reduction);
    }

    //Adds a resource to be exchanged with drawn white marbles
    public void addWhiteMarble(Resource resource){
        whiteMarble.add(resource);
    }



    public boolean controlCardToBuy(DevelopmentCard card, int i) throws ModelException{
        if(wareHouse.controlRequirements(card) == false) return false;
        if(cardSlot.controlCardToAdd(card, i) == false) return false;
        else return true;
    }

    public void chooseCardToBuy(DevelopmentCard card, int i) throws ModelException{
        if(controlCardToBuy(card, i) == false) throw new ModelException("Not possible to buy this card");
        cardSlot.addCardToSlot(card, i);
        Set<Resource> s = card.getRequirements().keySet();
        for(Resource x : s){
            cardCost.put(x, card.getRequirements().get(x));
        }

        if(reduction.isEmpty() == false){
            Resource x = reduction.get(0);
            if(cardCost.get(x) != null){
                cardCost.put(x, cardCost.get(x)-1);
            }

            if(reduction.get(1) != null){
                x = reduction.get(1);
                if(cardCost.get(x) != null){
                    cardCost.put(x, cardCost.get(x)-1);
                }
            }
        }
    }

    public Map<Resource, Integer> getCardCost(){
        return cardCost;
    }

    public void payCardfromStorage(Resource type, int n, int i) throws ModelException{
        if(n > cardCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromStorage(type, n, i);
        cardCost.put(type, cardCost.get(type) - n);
    }

    public void payCardfromChest(Resource type, int n) throws ModelException{
        if(n > cardCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromChest(type, n);
        cardCost.put(type, cardCost.get(type) - n);
    }

    public void payAllfromChest() throws ModelException{
        Set<Resource> s = cardCost.keySet();

        for(Resource i : s){
            if(wareHouse.getFromChest(i) < cardCost.get(i)) throw new ModelException("Not enough resources in chest");
        }

        for(Resource i : s){
            wareHouse.subFromChest(i, cardCost.get(i));
            cardCost.put(i, 0);
        }
    }

    public boolean isCardPayed(){
        Set<Resource> s = cardCost.keySet();
        for(Resource i : s){
            if(cardCost.get(i) != 0) return false;
        }
        return true;
    }
}