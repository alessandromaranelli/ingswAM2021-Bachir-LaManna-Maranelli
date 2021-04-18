package it.polimi.ingsw.model;

public abstract class LeaderCard {
    private final int victoryPoints;
    private final String description;

    public LeaderCard(int victoryPoints, String description) {
        this.victoryPoints = victoryPoints;
        this.description = description;
    }

    public abstract void activateEffect (PersonalBoard personalboard);

    public String getDescription() {
        return description;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void discard(FaithTrack faithTrack){
        faithTrack.movePositionForward();
    }

    public abstract boolean verifyRequirements(PersonalBoard personalBoard);

}
