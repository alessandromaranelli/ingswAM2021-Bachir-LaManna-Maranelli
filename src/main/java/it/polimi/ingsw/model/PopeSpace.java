package it.polimi.ingsw.model;

public class PopeSpace extends Box {
    private VaticanReportSection vaticanReportSection;

    public PopeSpace(int victoryPoints, VaticanReportSection vaticanReportSection) {
        super(victoryPoints);
        this.vaticanReportSection = vaticanReportSection;
    }

    @Override
    public void report() {
        if (!vaticanReportSection.isPassed()){
            vaticanReportSection.checkEveryPlayer();
        }
    }
}