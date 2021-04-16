package it.polimi.ingsw.model;

public class PopeFavour {
    private int victoryPoints;
    private boolean isActivated;

    public  PopeFavour (int victoryPoints){
        this.victoryPoints=victoryPoints;
        this.isActivated=false;
    }

    public void Activate (){
        isActivated = true;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
}