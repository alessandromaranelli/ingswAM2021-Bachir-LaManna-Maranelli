package it.polimi.ingsw.model;

import java.util.ArrayList;

public class VaticanReportSection {
    private int positionToCheck;
    private boolean passed;
    private final int reportNumber;
    private ArrayList<Player> players;

    public VaticanReportSection (int position, int reportNumber, ArrayList<Player> players){
        this.positionToCheck=position;
        this.passed=false;
        this.reportNumber=reportNumber;
        this.players=players;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed() {
        this.passed =true;
    }

    public void checkEveryPlayer(){
        for (Player player : players){
            if(player.getPersonalBoard().getFaithTrack().getTrack().indexOf(player.getPersonalBoard().getFaithTrack().checkPlayerPosition())>=positionToCheck){
                player.getPersonalBoard().getFaithTrack().getPopeFavours().get(reportNumber-1).Activate();
            }
        }
        setPassed();
    }

}