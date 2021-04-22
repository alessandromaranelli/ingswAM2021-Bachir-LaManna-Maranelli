package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * In the game there are three VaticanReportSection instances that are shared by all the players.
 * When a VaticanReportSection is called, the position of every player is compared with the attribute
 * positionToCheck: if >, the PopeFavour corresponding to attribute reportNumber is activated, otherwise
 * it remains inactive.
 */

public class VaticanReportSection {
    private int positionToCheck;
    private boolean passed;
    private final int reportNumber;
    private ArrayList<Player> players;

    /**
     * Constructor VaticanReportSection creates a new VaticanReportSection instance
     * @param position - int, the position to check in every FaithTrack
     * @param reportNumber - int, this number associates a particular PopeFavour for every player to this object
     * @param players - ArrayList<Player>, a list that contains tha players who are playing
     */
    public VaticanReportSection (int position, int reportNumber, ArrayList<Player> players){
        this.positionToCheck=position;
        this.passed=false;
        this.reportNumber=reportNumber;
        this.players=players;
    }

    /**
     * Getter method to check attribute passed
     * @return boolean passed
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * Method setPassed sets attribute passed to true
     */
    public void setPassed() {
        this.passed =true;
    }

    /**
     * Method CheckEveryPlayer compares attribute positionToCheck with the position of every player.
     * If the player' s FaithMarker is in a higher position, then the PopeFavour corresponding to this
     * VaticanReportSection's reportNumber is activated, otherwise it is not
     * At the end of the method, attribute passed is set to true, so that this method is called once for
     * every VaticanReportSection instance
     */
    public void checkEveryPlayer(){
        for (Player player : players){
            if(player.getPersonalBoard().getFaithTrack().getTrack().indexOf(player.getPersonalBoard().getFaithTrack().checkPlayerPosition())>=positionToCheck){
                player.getPersonalBoard().getFaithTrack().getPopeFavours().get(reportNumber-1).activate();
            }
        }
        setPassed();
    }

}