package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * Abstract class for the 4 types of leader cards. Getter methods for VP and description and discard method are already implemented.
 * The effect activation and requirements analyzes are left abstract.
 */
public abstract class LeaderCard implements Serializable {
    private final int victoryPoints;
    private final String description;
    private final String path;

    /**
     * When creating a new LeaderCard assigns victoryPoints and description
     * @param victoryPoints points given by the leaderCard for the final points count
     * @param description Brief sentence about leaderCad's requirements (resources or developmentCards) to be activated and its special power.
     */
    public LeaderCard(int victoryPoints, String description, String path) {
        this.victoryPoints = victoryPoints;
        this.description = description;
        this.path = path;
    }

    /**
     * When a player has what his leaderCard requires, he can activate the card's feature, that will be available for the rest of the game.
     * The card will be moved from the players hand to his personalBoard face up.
     * @param personalboard
     */
    public abstract void activateEffect (PersonalBoard personalboard);

    /**
     * Getter method for description attribute
     * @return String containing the leaderCard's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for victoryPoints attribute, called at the end of a match
     * @return int representing victory points
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String getPath(){
        return path;
    }

    /**
     * When called moves the player's pawn a head of one box
     * @param faithTrack calling player's faithtrack
     */
    public void discard(FaithTrack faithTrack){
        faithTrack.movePositionForward();
    }

    /**
     * inspects the player's possessions to verify if he meets the requirements
     * @param personalBoard
     * @return true if requirements are met
     */
    public abstract boolean verifyRequirements(PersonalBoard personalBoard);

}
