package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

public class DevelopmentCardToBuyVisualizer {

    public void plot(List<DevelopmentCard> developmentCards){
        DevelopmentCardVisualizer developmentCardVisualizer = new DevelopmentCardVisualizer();
        for(int i=1; i <= developmentCards.size(); i++){
            developmentCardVisualizer.showDevelData(developmentCards.get(i));
            developmentCardVisualizer.plot();
        }
    }
}
