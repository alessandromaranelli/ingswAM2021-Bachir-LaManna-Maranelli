package it.polimi.ingsw.model;

import Exceptions.*;
import java.util.*;

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

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public CardSlot getCardSlot() {
        return cardSlot;
    }

    //metodi per sapere come stanno andando le produzioni
    public Map<Resource, Integer> getTotalGain(){
        return totalGain;
    }

    public Map<Resource, Integer> getTotalCost(){
        return totalCost;
    }

    public int getFaithPoints(){
        return faithPoints;
    }

    //conta quante specialProductions possiede il giocatore
    public int numOfSpecialProduction(){
        if(specialProduction.isEmpty()) return 0;
        else if(specialProduction.size() == 1) return 1;
        else return 2;
    }

    //ritorna la risorsa richiesta per attivare una specialProduction
    public Resource getTypeOfSpecialProduction(int i) throws ModelException{
        if(i < 1 || i > numOfSpecialProduction()) throw new ModelException("Not existing special production, you have" + numOfSpecialProduction());
        return specialProduction.get(i-1).getCost();
    }

    public boolean isPersonalProductionActivated(){
        return personalProductionActivated;
    }

    public boolean isProductionActivated(int i){
        return productionActivated[i-1];
    }

    public boolean isSpecialProductionActivated(int i) throws ModelException{
        if(i < 1 || i > numOfSpecialProduction()) throw new ModelException("Not existing special production");
        return specialProduction.get(i-1).isActivated();
    }

    public boolean totalGainIsEmpty(){
        Set<Resource> s = totalGain.keySet();
        for(Resource i : s){
            if(totalGain.get(i) != 0) return false;
        }
        return true;
    }

    public boolean totalCostIsEmpty(){
        Set<Resource> s = totalCost.keySet();
        for(Resource i : s){
            if(totalCost.get(i) != 0) return false;
        }
        return true;
    }

    //aggiungi una specialProduction
    public void addSpecialProduction(Resource type){
        specialProduction.add(new SpecialProduction(type));
    }

    //ritorna il costo di produzione della carta sviluppo in cima allo slot i
    public Map<Resource, Integer> getProductionInputOfSlot(int i) throws ModelException{
        Map<Resource, Integer> m = cardSlot.getTopCardofSlot(i).getProductionInput();
        return m;
    }

    //ritorna la produzione della carta sviluppo in cima allo slot i
    public Map<Resource, Integer> getProductionOutputOfSlot(int i) throws ModelException{
        Map<Resource, Integer> m = cardSlot.getTopCardofSlot(i).getProductionOutput();
        return m;
    }

    //ritorna i faithPoints della carta sviluppo in cima allo slot i
    public int getFaithPointsOfSlot(int i) throws ModelException{
        return cardSlot.getTopCardofSlot(i).getFaithPoint();
    }

    //controlla se può essere attivata la produzione dello slot i
    public boolean controlRequirementsOfSlot(int i) throws ModelException{
        Map<Resource, Integer> cardCost = cardSlot.getTopCardofSlot(i).getProductionInput();
        Map<Resource, Integer> warehouse = wareHouse.totalResources();
        Set<Resource> s = cardCost.keySet();

        for(Resource x : s){
            if(cardCost.get(x) + totalCost.get(x) > warehouse.get(x)) return false;
        }
        return true;
    }

    //attiva produzione dello slot i
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

    public void activatePersonalProduction(Resource input1, Resource input2, Resource output) throws ModelException{
        Map<Resource, Integer> warehouse = wareHouse.totalResources();
        if(totalCost.get(input1) + 1 > warehouse.get(input1)) throw new ModelException("Not enough resources");
        if(totalCost.get(input2) + 1 > warehouse.get(input2)) throw new ModelException("Not enough resources");
        if(personalProductionActivated == true) throw new ModelException("Personal production already activated");

        personalProductionActivated = true;
        totalCost.put(input1, totalCost.get(input1) + 1);
        totalCost.put(input2, totalCost.get(input2) + 1);
        totalGain.put(output, totalGain.get(output) + 1);
    }

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

    public void payCostfromStorage(Resource type, int n, int i) throws ModelException{
        if(n > totalCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromStorage(type, n, i);
        totalCost.put(type, totalCost.get(type) - n);
    }

    public void payCostfromChest(Resource type, int n) throws ModelException{
        if(n > totalCost.get(type)) throw new ModelException("Paying too much");
        wareHouse.subFromChest(type, n);
        totalCost.put(type, totalCost.get(type) - n);
    }

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

    public void gainResourcesAndEndProduction() throws ModelException{
        if(totalCostIsEmpty() == false) throw new ModelException("Not payed all");

        Set<Resource> s = totalGain.keySet();
        for(Resource i : s){
            wareHouse.addToChest(i, totalGain.get(i));
            totalGain.put(i, 0);
        }

        //personalBoard.getFaithTrack().movePositionForward(); devi mandare avanti il player di tanto quanti sono i faithPoints accumulati
        faithPoints = 0;

        for(int i=0; i < 3; i++){
            productionActivated[i] = false;
        }
        personalProductionActivated = false;

        for(SpecialProduction x : specialProduction){
            x.disactivate();
        }
    }
}
