package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;

public class DevelopmentCardToBuyVisualizer {
    private static final int MAX_VERT_TILES = 7; //rows.
    private static final int MAX_HORIZ_TILES = 15; //cols.

    public void plot(List<DevelopmentCard> developmentCards){
        String tiles[][]=new String[MAX_VERT_TILES][developmentCards.size()*MAX_HORIZ_TILES];
        String temp[][]=new String[MAX_VERT_TILES][MAX_HORIZ_TILES];
        DevelopmentCardVisualizer developmentCardVisualizer = new DevelopmentCardVisualizer();
        System.out.print( "\nCards you are able to buy from the table:  \n");
        for(int i=0; i < developmentCards.size(); i++){
            temp=developmentCardVisualizer.showDevelData(developmentCards.get(i));
            for (int r = 0; r < MAX_VERT_TILES; r++) {
                for (int c = 0; c < MAX_HORIZ_TILES; c++) {
                    tiles[r][(i*MAX_HORIZ_TILES)+c]=temp[r][c];
                }
            }
        }
        developmentCardVisualizer.plot(developmentCards.size(),tiles);
    }
}
