package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.*;
import java.util.List;

public class DevelCardsOfPlayerVisualizer {
    private static final int MAX_HORIZ_TILES = 25; //cols
    public DevelCardsOfPlayerVisualizer(){

    }
    public void plot (List<DevelopmentCard> developmentCardList){
        DevelopmentCardVisualizer dcv = new DevelopmentCardVisualizer();
        for(DevelopmentCard d:developmentCardList){
            dcv.showDevelData(d);
            dcv.plot();
        }

    }
}
