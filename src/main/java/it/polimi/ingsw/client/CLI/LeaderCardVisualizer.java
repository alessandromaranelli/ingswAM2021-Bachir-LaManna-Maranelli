package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.*;

/**
 * Takes care of invoking the correct type of leader card visualizer
 */
public class LeaderCardVisualizer {

    LeaderCardReductionVisualizer leaderCardReductionVisualizer=new LeaderCardReductionVisualizer();

    LeaderCardStorageVisualizer leaderCardStorageVisualizer=new LeaderCardStorageVisualizer();

    LeaderCardWhiteMarbleVisualizer leaderCardWhiteMarbleVisualizer=new LeaderCardWhiteMarbleVisualizer();

    LeaderCardSpecialProductionVisualizer leaderCardSpecialProductionVisualizer=new LeaderCardSpecialProductionVisualizer();

    /**
     * selects the correct leader card visualizer
     */
    public void showLeaderData(LeaderCard leaderCard){
        if (leaderCard instanceof LeaderCardReduction){
            leaderCardReductionVisualizer.fillEmpty(Color.BLUE);
            leaderCardReductionVisualizer.showLeaderData((LeaderCardReduction)leaderCard);
            leaderCardReductionVisualizer.plot();
        }
        if (leaderCard instanceof LeaderCardStorage){
            leaderCardStorageVisualizer.fillEmpty(Color.GREEN);
            leaderCardStorageVisualizer.showLeaderData((LeaderCardStorage)leaderCard);
            leaderCardStorageVisualizer.plot();
        }
        if (leaderCard instanceof LeaderCardWhiteMarble){
            leaderCardWhiteMarbleVisualizer.fillEmpty(Color.YELLOW);
            leaderCardWhiteMarbleVisualizer.showLeaderData((LeaderCardWhiteMarble)leaderCard);
            leaderCardWhiteMarbleVisualizer.plot();
        }
        if (leaderCard instanceof LeaderCardSpecialProduction){
            leaderCardSpecialProductionVisualizer.fillEmpty(Color.PURPLE);
            leaderCardSpecialProductionVisualizer.showLeaderData((LeaderCardSpecialProduction) leaderCard);
            leaderCardSpecialProductionVisualizer.plot();
        }
    }
}
