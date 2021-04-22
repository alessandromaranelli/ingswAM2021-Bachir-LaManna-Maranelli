package it.polimi.ingsw.model;

/**
 * PopeSpace is a special space within the FaithTrack and there are three PopeSpace boxes
 * in every FaithTrack. When the faith marker of a player comes on a PopeSpace, method
 * report is called and if the corresponding VaticanReportSection is not already activated,
 * the position of every player is checked to determine whether to activate or not the
 * corresponding PopeFavour.
 */

public class PopeSpace extends Box {
    private VaticanReportSection vaticanReportSection;

    /**
     * Constructor PopeSpace creates a new PopeSpace instance
     * @param victoryPoints - the amount of victoryPoints that define the PopeSpace
     * @param vaticanReportSection - the corresponding VaticanReportSection
     */
    public PopeSpace(int victoryPoints, VaticanReportSection vaticanReportSection) {
        super(victoryPoints);
        this.vaticanReportSection = vaticanReportSection;
    }

    /**
     * Method report calls the checkEveryPlayer method of VaticanReportSection, if this has not
     * been already called
     */
    @Override
    public void report() {
        if (!vaticanReportSection.isPassed()){
            vaticanReportSection.checkEveryPlayer();
        }
    }
}