package it.polimi.ingsw.model;

import Exceptions.*;
import java.util.*;

/**
 * The type Production. It has a map for the total cost of the production activated, a map for the total resources
 * produced and the faith points gained. Booleans memorize if the production are activated or not
 * There is a list for the Special Productions (there are at most 2 Special Productions activated)
 */
public class Production {
    private PersonalBoard personalBoard;
    private WareHouse wareHouse;
    private CardSlot cardSlot;
    private List<SpecialProduction> specialProduction;
    private Map<Resource, Integer> totalCost;
    private Map<Resource, Integer> totalGain;
    private int faithPoints;
    private boolean[] productionActivated;
    private boolean personalProductionActivated;

    /**
     * Instantiates a new Production.
     *
     * @param p the personal board
     * @param w the warehouse
     * @param c the card slots
     */
    public Production(PersonalBoard p, WareHouse w, CardSlot c){
        personalBoard = p;
        wareHouse = w;
        cardSlot = c;
        specialProduction = new ArrayList<>();
        totalCost = new HashMap<>();
        totalGain = new HashMap<>();
        faithPoints = 0;
        productionActivated = new boolean[3];
        personalProductionActivated = false;

        totalCost.put(Resource.COIN, 0);
        totalCost.put(Resource.STONE, 0);
        totalCost.put(Resource.SHIELD, 0);
        totalCost.put(Resource.SERVANT, 0);

        totalGain.put(Resource.COIN, 0);
        totalGain.put(Resource.STONE, 0);
        totalGain.put(Resource.SHIELD, 0);
        totalGain.put(Resource.SERVANT, 0);
    }

    /**
     * Gets then warehouse.
     *
     * @return the warehouse
     */
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    /**
     * Gets the card slots.
     *
     * @return the card slots
     */
    public CardSlot getCardSlot() {
        return cardSlot;
    }

    /**
     * Get total gain map.
     *
     * @return the map
     */
    public Map<Resource, Integer> getTotalGain(){
        return totalGain;
    }

    /**
     * Get total cost map.
     *
     * @return the map
     */
    public Map<Resource, Integer> getTotalCost(){
        return totalCost;
    }

    /**
     * Get faith points gained.
     *
     * @return the quantity
     */
    public int getFaithPoints(){
        return faithPoints;
    }

    /**
     * Sets faith points.
     *
     * @param faithPoints the faith points
     */
    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    /**
     * Count the number of Special Productions
     *
     * @return the quantity
     */
    public int numOfSpecialProduction(){
        if(specialProduction.isEmpty()) return 0;
        else if(specialProduction.size() == 1) return 1;
        else return 2;
    }

    /**
     * Gets the type of resource requested for a special production.
     *
     * @param i the special production
     * @return the type of the resource
     * @throws ModelException if the special production does not exist
     */
    public Resource getTypeOfSpecialProduction(int i) throws ModelException{
        if(i < 1 || i > numOfSpecialProduction()) throw new ModelException("Not existing special production, you have" + numOfSpecialProduction());
        return specialProduction.get(i-1).getCost();
    }

    /**
     * Is personal production activated
     *
     * @return the boolean
     */
    public boolean isPersonalProductionActivated(){
        return personalProductionActivated;
    }

    /**
     * Is production od a card activated
     *
     * @param i the slot of the card
     * @return the boolean
     */
    public boolean isProductionActivated(int i){
        return productionActivated[i-1];
    }

    /**
     * Is special production activated
     *
     * @param i the special production
     * @return the boolean
     * @throws ModelException if the special production does not exist
     */
    public boolean isSpecialProductionActivated(int i) throws ModelException{
        if(i < 1 || i > numOfSpecialProduction()) throw new ModelException("Not existing special production");
        return specialProduction.get(i-1).isActivated();
    }

    /**
     * Total gain is empty.
     *
     * @return the boolean
     */
    public boolean totalGainIsEmpty(){
        Set<Resource> s = totalGain.keySet();
        for(Resource i : s){
            if(totalGain.get(i) != 0) return false;
        }
        return true;
    }

    /**
     * Total cost is empty.
     *
     * @return the boolean
     */
    public boolean totalCostIsEmpty(){
        Set<Resource> s = totalCost.keySet();
        for(Resource i : s){
            if(totalCost.get(i) != 0) return false;
        }
        return true;
    }

    /**
     * Add special production.
     *
     * @param type the resource requested for the special production
     */
    public void addSpecialProduction(Resource type){
        specialProduction.add(new SpecialProduction(type));
    }

    /**
     * Gets the production requirements of a development card in a slot.
     *
     * @param i the slot
     * @return the production input of the card
     * @throws ModelException the model exception
     */
    public Map<Resource, Integer> getProductionInputOfSlot(int i) throws ModelException{
        Map<Resource, Integer> m = cardSlot.getTopCardofSlot(i).getProductionInput();
        return m;
    }

    /**
     * Gets the production output of a development card in a slot.
     *
     * @param i the slot
     * @return the production output of the card
     * @throws ModelException the model exception
     */
    public Map<Resource, Integer> getProductionOutputOfSlot(int i) throws ModelException{
        Map<Resource, Integer> m = cardSlot.getTopCardofSlot(i).getProductionOutput();
        return m;
    }

    /**
     * Gets faith points of a development card in a slot.
     *
     * @param i the slot
     * @return the faith points of the card
     * @throws ModelException the model exception
     */
    public int getFaithPointsOfSlot(int i) throws ModelException{
        return cardSlot.getTopCardofSlot(i).getFaithPoint();
    }

    /**
     * Control if the requirements of a Development Card are satisfied.
     *
     * @param i the slot of the card
     * @return the boolean
     * @throws ModelException the model exception
     */
    public boolean controlRequirementsOfSlot(int i) throws ModelException{
        Map<Resource, Integer> cardCost = cardSlot.getTopCardofSlot(i).getProductionInput();
        Map<Resource, Integer> warehouse = wareHouse.totalResources();
        Set<Resource> s = cardCost.keySet();

        for(Resource x : s){
            if(cardCost.get(x) + totalCost.get(x) > warehouse.get(x)) return false;
        }
        return true;
    }

    /**
     * Activate production of a Development Card in a slot.
     *
     * @param i the slot
     * @throws ModelException the model exception if the production can't be activated
     */
    public void activateProductionOfSlot(int i) throws ModelException{
        if(controlRequirementsOfSlot(i) == false) throw new ModelException("Not enough resources to activate production");
        if(productionActivated[i-1] == true) throw new ModelException("Production was already activated");
        productionActivated[i-1] = true;

        Map<Resource, Integer> inputCard = cardSlot.getTopCardofSlot(i).getProductionInput();
        Set<Resource> s = inputCard.keySet();
        for(Resource x : s){
            totalCost.put(x, totalCost.get(x) + inputCard.get(x));
        }

        Map<Resource, Integer> outputCard = cardSlot.getTopCardofSlot(i).getProductionOutput();
        Set<Resource> v = outputCard.keySet();
        for(Resource x : v){
            totalGain.put(x, totalGain.get(x) + outputCard.get(x));
        }

        faithPoints = faithPoints + cardSlot.getTopCardofSlot(i).getFaithPoint();
    }

    /**
     * Activate personal production.
     *
     * @param input1 the first resource
     * @param input2 the second resource
     * @param output the resource gained
     * @throws ModelException if the production can't be activated
     */
    public void activatePersonalProduction(Resource input1, Resource input2, Resource output) throws ModelException{
        Map<Resource, Integer> ware = wareHouse.totalResources();
        if(input1 == input2 && totalCost.get(input1) + 2 > ware.get(input1)) throw new ModelException("Not enough resources");
        else {
            if (totalCost.get(input1) + 1 > ware.get(input1)) throw new ModelException("Not enough resources");
            if (totalCost.get(input2) + 1 > ware.get(input2)) throw new ModelException("Not enough resources");
        }
        if(personalProductionActivated == true) throw new ModelException("Personal production already activated");

        personalProductionActivated = true;
        totalCost.put(input1, totalCost.get(input1) + 1);
        totalCost.put(input2, totalCost.get(input2) + 1);
        totalGain.put(output, totalGain.get(output) + 1);
    }

    /**
     * Activate special production.
     *
     * @param output the resource gained
     * @param i      the special production
     * @throws ModelException if the production can't be activated
     */
    public void activateSpecialProduction(Resource output, int i) throws ModelException{
        if(i < 1 || i > numOfSpecialProduction()) throw new ModelException("Not existing special production");
        if(specialProduction.get(i-1).isActivated() == true) throw new ModelException("Special production already activated");

        Map<Resource, Integer> warehouse = wareHouse.totalResources();
        if(totalCost.get(getTypeOfSpecialProduction(i)) + 1 > warehouse.get(getTypeOfSpecialProduction(i))) throw new ModelException("Not enough resources");

        specialProduction.get(i-1).activate();
        totalCost.put(getTypeOfSpecialProduction(i), totalCost.get(getTypeOfSpecialProduction(i)) + 1);
        totalGain.put(output, totalGain.get(output) + 1);
        faithPoints = faithPoints + 1;
    }

    /**
     * Pay costs of productions from storage.
     *
     * @param type the type of the resources
     * @param n    the quantity of the resources
     * @param i    the storage
     * @throws ModelException the model exception
     */
    public void payCostfromStorage(Resource type, int n, int i) throws ModelException{
        if(n > totalCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromStorage(type, n, i);
        totalCost.put(type, totalCost.get(type) - n);
    }

    /**
     * Pay costs of productions from from chest.
     *
     * @param type the type of the resources
     * @param n    the quantity of the resources
     * @throws ModelException the model exception
     */
    public void payCostfromChest(Resource type, int n) throws ModelException{
        if(n > totalCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromChest(type, n);
        totalCost.put(type, totalCost.get(type) - n);
    }

    /**
     * Pay all productions from chest.
     *
     * @throws ModelException if there aren't enough resources in chest
     */
    public void payAllfromChest() throws ModelException{
        Set<Resource> s = totalCost.keySet();

        for(Resource i : s){
            if(wareHouse.getFromChest(i) < totalCost.get(i)) throw new ModelException("Not enough resources in chest");
        }

        for(Resource i : s){
            wareHouse.subFromChest(i, totalCost.get(i));
            totalCost.put(i, 0);
        }
    }

    /**
     * Gain resources produced by all the productions and end the productions phase
     *
     * @throws ModelException if there is something left to pay
     */
    public void gainResourcesAndEndProduction() throws ModelException{
        if(totalCostIsEmpty() == false) throw new ModelException("Not payed all");

        Set<Resource> s = totalGain.keySet();
        for(Resource i : s){
            wareHouse.addToChest(i, totalGain.get(i));
            totalGain.put(i, 0);
        }

        for(int j = 0; j < faithPoints; j++){
            personalBoard.getFaithTrack().movePositionForward();
        }
        faithPoints = 0;

        for(int i=0; i < 3; i++){
            productionActivated[i] = false;
        }
        personalProductionActivated = false;

        for(SpecialProduction x : specialProduction){
            x.disactivate();
        }
    }

    /**
     * Disactivate all productions.
     */
    public void disactivateAllProductions(){
        for(int i=0; i < 3; i++){
            productionActivated[i] = false;
        }
        personalProductionActivated = false;

        for(SpecialProduction x : specialProduction){
            x.disactivate();
        }
    }
}
