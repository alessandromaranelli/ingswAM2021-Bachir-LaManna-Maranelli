package it.polimi.ingsw.model;

/**
 * A PopeFavour, if activated gives some extra victoryPoints at the end of the game.
 */
public class PopeFavour {
    private int victoryPoints;
    private boolean isActivated;

    /**
     * Constructor PopeFavour creates a new PopeFavour instance
     * @param victoryPoints - the amount of victoryPoints that define the Box
     */
    public  PopeFavour (int victoryPoints){
        this.victoryPoints=victoryPoints;
        this.isActivated=false;
    }

    /**
     * Method activate sets attribute isActivated to true
     */
    public void activate (){
        isActivated = true;
    }

    /**
     * Getter method to check attribute isActivated
     * @return boolean isActivated
     */
    public boolean isActivated() {
        return isActivated;
    }

    /**
     * Method getVictoryPoints returns this object's victoryPoints
     * @return int representing victory points
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }
}