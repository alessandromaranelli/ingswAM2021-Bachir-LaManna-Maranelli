package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

/**
 * View of development cards available to buy
 */
public class DevelopmentCardToBuyVisualizer {

    /**
     * Prints on screen the development cards
     *
     * @param developmentCards list of the development cards
     */
    public void plot(List<DevelopmentCard> developmentCards){
        System.out.print( "\nCards you are able to buy from the table:  \n");
        DevelopmentCardVisualizer developmentCardVisualizer = new DevelopmentCardVisualizer();
        for(int i=0; i < developmentCards.size(); i++){
            developmentCardVisualizer.showDevelData(developmentCards.get(i));
            developmentCardVisualizer.plot();
        }
    }
}
