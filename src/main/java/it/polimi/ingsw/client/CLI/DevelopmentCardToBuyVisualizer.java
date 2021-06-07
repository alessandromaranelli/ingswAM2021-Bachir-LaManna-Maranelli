package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

public class DevelopmentCardToBuyVisualizer {

    public void plot(List<DevelopmentCard> developmentCards){
        DevelopmentCardVisualizer developmentCardVisualizer = new DevelopmentCardVisualizer();
        System.out.print( "\nCards you are able to buy from the table:  \n");
        for(int i=0; i < developmentCards.size(); i++){
            developmentCardVisualizer.showDevelData(developmentCards.get(i));
            developmentCardVisualizer.plot();
        }
    }
}
