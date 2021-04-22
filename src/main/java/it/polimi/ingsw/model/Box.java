package it.polimi.ingsw.model;

/**
 * Box is a single space within the FaithTrack. Every Box is characterized by a specific
 * amount of victoryPoints. When the faith marker of a player comes on a Box, method
 * report is called.
 */

public class Box {
    private int victoryPoints;

    /**
     * Constructor Box creates a new Box instance
     * @param victoryPoints - the amount of victoryPoints that define the Box
     */
    public Box(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Method report doesn't do anything. It is overrided in the subclass PopeSpace
     */
    public void report(){
    }

    /**
     * Method getVictoryPoints returns this object's victoryPoints
     * @return int representing victory points
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

}