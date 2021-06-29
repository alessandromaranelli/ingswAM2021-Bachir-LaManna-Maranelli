package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.*;
import java.util.List;
import java.util.Map;

/**
 * View of a player's development cards state
 */
public class DevelCardsOfPlayerVisualizer {

    /**
     * Instantiates a new Devel cards of player visualizer.
     */
    public DevelCardsOfPlayerVisualizer(){

    }

    /**
     * Plots on screen the view
     *
     * @param developmentCards map between slot number and card
     */
    public void plot(Map<Integer,DevelopmentCard> developmentCards){

        System.out.print( "\nYour card slot situation:  \n");
        DevelopmentCardVisualizer dcv = new DevelopmentCardVisualizer();
        for(DevelopmentCard d: developmentCards.values()){
            dcv.showDevelData(d);
            dcv.plot();
        }
    }
}
