package it.polimi.ingsw.model;

/**
 * The type Special Production. It has a cost decided by the leader card and a boolean to know if it has been activated
 * during this turn or not. The cost is a type of resource that has to be paid in order to activate the special production
 */
public class SpecialProduction {
    private Resource cost;
    private boolean isActivated;

    /**
     * Instantiates a new Special production.
     *
     * @param type the type of the resource
     */
    public SpecialProduction(Resource type){
        cost = type;
        isActivated = false;
    }

    /**
     * Get cost resource.
     *
     * @return the type of the resource
     */
    public Resource getCost(){
        return cost;
    }

    /**
     * Is activated boolean.
     *
     * @return the boolean
     */
    public boolean isActivated(){
        return isActivated;
    }

    /**
     * Activate.
     */
    public void activate(){
        isActivated = true;
    }

    /**
     * Disactivate at the end of the turn.
     */
    public void disactivate(){
        isActivated = false;
    }

}
