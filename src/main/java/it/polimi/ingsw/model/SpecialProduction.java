package it.polimi.ingsw.model;

public class SpecialProduction {
    private Resource cost;
    private boolean isActivated;

    public SpecialProduction(Resource type){
        cost = type;
        isActivated = false;
    }

    public Resource getCost(){
        return cost;
    }

    public boolean isActivated(){
        return isActivated;
    }

    public void activate(){
        isActivated = true;
    }

    public void disactivate(){
        isActivated = false;
    }

}
