package it.polimi.ingsw.model;

import Exceptions.ModelException;

import java.util.*;

/**
 * The type Personal board.
 */
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

    private Map<Resource, Integer> cardCost;

    /**
     * Instantiates a new Personal board.
     *
     * @param vaticanReportSections the vatican report sections
     */
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

    /**
     * Gets production.
     *
     * @return the production
     */
    public Production getProduction() {
        return production;
    }

    /**
     * Gets ware house.
     *
     * @return the ware house
     */
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    /**
     * Get card slot card slot.
     *
     * @return the card slot
     */
    public CardSlot getCardSlot(){
        return cardSlot;
    }

    /**
     * Gets faith track.
     *
     * @return the faith track
     */
    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    /**
     * Move faith marker.
     */
    public void moveFaithMarker(){
        faithTrack.movePositionForward();
    }

    /**
     * Get reduction array list.
     *
     * @return the array list
     */
    public ArrayList<Resource> getReduction(){
        return reduction;
    }

    /**
     * Get last reduction resource.
     *
     * @return the resource
     */
    public Resource getLastReduction(){
        return reduction.get(reduction.size()-1);
    }

    /**
     * Gets white marble.
     *
     * @return the white marble
     */
    public ArrayList<Resource> getWhiteMarble() {
        return whiteMarble;
    }

    /**
     * Get last white marble resource.
     *
     * @return the resource
     */
    public Resource getLastWhiteMarble(){
        return whiteMarble.get(whiteMarble.size()-1);
    }

    /**
     * Add white marble to manage.
     */
    public void addWhiteMarbleToManage(){
        manageWhiteMarbles++;
    }

    /**
     * Get manage white marbles int.
     *
     * @return the int
     */
    public int getManageWhiteMarbles(){
        return manageWhiteMarbles;
    }

    /**
     * Sets manage white marbles.
     *
     * @param manageWhiteMarbles the manage white marbles
     */
    public void setManageWhiteMarbles(int manageWhiteMarbles) {
        this.manageWhiteMarbles = manageWhiteMarbles;
    }

    /**
     * Manage white marbles.
     *
     * @param type the type
     * @throws ModelException the model exception
     */
    public void manageWhiteMarbles(Resource type) throws ModelException{
        if(manageWhiteMarbles == 0) throw new ModelException("No white marbles");
        if(whiteMarble.get(0) != type && whiteMarble.get(1) != type) throw new ModelException("Wrong type for white marble");
        manageWhiteMarbles--;
        wareHouse.addResource(type, 1);
    }

    /**
     * Gets leader cards in hand.
     *
     * @return the leader cards in hand
     */
    public ArrayList<LeaderCard> getLeaderCardsInHand() {
        return leaderCardsInHand;
    }

    /**
     * Gets leader cards played.
     *
     * @return the leader cards played
     */
    public ArrayList<LeaderCard> getLeaderCardsPlayed() {
        return leaderCardsPlayed;
    }

    /**
     * Gets desc leader cardin hand.
     *
     * @param i the
     * @return the desc leader cardin hand
     * @throws ModelException the model exception
     */
    public String getDescLeaderCardinHand(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");

        return leaderCardsInHand.get(i-1).getDescription();
    }

    /**
     * Gets desc leader card played.
     *
     * @param i the
     * @return the desc leader card played
     * @throws ModelException the model exception
     */
    public String getDescLeaderCardPlayed(int i) throws ModelException{
        if(i < 1 || i > leaderCardsPlayed.size()) throw new ModelException("Not existing leader card");

        return leaderCardsPlayed.get(i-1).getDescription();
    }

    /**
     * Verify requirements leader card boolean.
     *
     * @param i the
     * @return the boolean
     * @throws ModelException the model exception
     */
    public boolean verifyRequirementsLeaderCard(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");
        return leaderCardsInHand.get(i-1).verifyRequirements(this);
    }

    /**
     * Activate leader card.
     *
     * @param i the
     * @throws ModelException the model exception
     */
    public void activateLeaderCard(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");
        if(!leaderCardsInHand.get(i - 1).verifyRequirements(this)) throw new ModelException("Requirements not satisfied");
        leaderCardsInHand.get(i-1).activateEffect(this);
        leaderCardsPlayed.add(leaderCardsInHand.get(i-1));
        leaderCardsInHand.remove(i-1);
    }

    /**
     * Discard leader card.
     *
     * @param i the
     * @throws ModelException the model exception
     */
    public void discardLeaderCard(int i) throws ModelException{
        if(i < 1 || i > leaderCardsInHand.size()) throw new ModelException("Not existing leader card");
        leaderCardsInHand.get(i-1).discard(faithTrack);
        leaderCardsInHand.remove(i-1);
    }


    /**
     * Count victory points int.
     *
     * @return the int
     * @throws ModelException the model exception
     */
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

    /**
     * Add reduction.
     *
     * @param reduction the reduction
     */
//Adds a Resource to be discounted when buying DevCards
    public void addReduction(Resource reduction) {
        this.reduction.add(reduction);
    }

    /**
     * Add white marble.
     *
     * @param resource the resource
     */
//Adds a resource to be exchanged with drawn white marbles
    public void addWhiteMarble(Resource resource){
        if(whiteMarble.size() == 0)  whiteMarble.add(resource);
        else if(whiteMarble.get(0) != resource) {
            whiteMarble.add(resource);
        }
    }


    /**
     * Control card to buy boolean.
     *
     * @param card the card
     * @param i    the
     * @return the boolean
     * @throws ModelException the model exception
     */
    public boolean controlCardToBuy(DevelopmentCard card, int i) throws ModelException{
        if(wareHouse.controlRequirements(card) == false) return false;
        if(cardSlot.controlCardToAdd(card, i) == false) return false;
        else return true;
    }

    /**
     * Choose card to buy.
     *
     * @param card the card
     * @param i    the
     * @throws ModelException the model exception
     */
    public void chooseCardToBuy(DevelopmentCard card, int i) throws ModelException{
        if(controlCardToBuy(card, i) == false) throw new ModelException("Not possible to buy this card");
        cardSlot.addCardToSlot(card, i);
        cardCost.clear();                                       //cardCost è sempre vuoto quando sto scegliendo di comprare una carta. Questa istruzione serve solo per fare un test
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
                if(cardCost.get(x) != null && cardCost.get(x)-1 >= 0){
                    cardCost.put(x, cardCost.get(x)-1);
                }
            }
        }
    }

    /**
     * Get card cost map.
     *
     * @return the map
     */
    public Map<Resource, Integer> getCardCost(){
        return cardCost;
    }

    /**
     * Pay cardfrom storage.
     *
     * @param type the type
     * @param n    the n
     * @param i    the
     * @throws ModelException the model exception
     */
    public void payCardfromStorage(Resource type, int n, int i) throws ModelException{
        if(n > cardCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromStorage(type, n, i);
        cardCost.put(type, cardCost.get(type) - n);
    }

    /**
     * Pay cardfrom chest.
     *
     * @param type the type
     * @param n    the n
     * @throws ModelException the model exception
     */
    public void payCardfromChest(Resource type, int n) throws ModelException{
        if(n > cardCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromChest(type, n);
        cardCost.put(type, cardCost.get(type) - n);
    }

    /**
     * Pay allfrom chest.
     *
     * @throws ModelException the model exception
     */
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

    /**
     * Is card payed boolean.
     *
     * @return the boolean
     */
    public boolean isCardPayed(){
        Set<Resource> s = cardCost.keySet();
        for(Resource i : s){
            if(cardCost.get(i) != 0) return false;
        }
        return true;
    }
}