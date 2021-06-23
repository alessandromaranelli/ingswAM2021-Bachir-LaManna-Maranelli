package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.*;
import java.util.List;
import java.util.Map;

public class DevelCardsOfPlayerVisualizer {

    public DevelCardsOfPlayerVisualizer(){

    }
    public void plot(Map<Integer,DevelopmentCard> developmentCards){

        System.out.print( "\nYour card slot situation:  \n");
        DevelopmentCardVisualizer dcv = new DevelopmentCardVisualizer();
        for(DevelopmentCard d: developmentCards.values()){
            dcv.showDevelData(d);
            dcv.plot();
        }
    }
}
