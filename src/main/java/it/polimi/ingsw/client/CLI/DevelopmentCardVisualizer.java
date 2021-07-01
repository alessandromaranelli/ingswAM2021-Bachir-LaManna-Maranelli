package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.*;

import java.util.Map;

/**
 * The view of a single development card with all its features
 */
public class DevelopmentCardVisualizer {
    //private int start;
    private static final int MAX_VERT_TILES = 7; //rows.
    private static final int MAX_HORIZ_TILES = 15; //cols.


    String tiles[][] = new String[MAX_VERT_TILES][MAX_HORIZ_TILES];


    public DevelopmentCardVisualizer() {
       // start = 0;
    }


    /**
     * Creates the border
     *
     * @param col width
     */
    public void fillEmpty(Color col) {
        String greenEscape = ColorVisualizer.ANSI_GREEN.escape();
        String color;
        switch(col){
            case GREEN:
                color = ColorVisualizer.ANSI_GREEN.escape();
                break;
            case BLUE:
                color = ColorVisualizer.ANSI_BLUE.escape();
                break;
            case YELLOW:
                color = ColorVisualizer.ANSI_YELLOW.escape();
                break;
            default:
                color = ColorVisualizer.ANSI_PURPLE.escape();;

        }
        tiles[0][0] = color + "┌";
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[0][c] = color + "─" ;
        }
        tiles[0][MAX_HORIZ_TILES - 1] = color + "┐" ;

        for (int r = 1; r < MAX_VERT_TILES - 1; r++) {
            tiles[r][0] = color + "│" ;
            for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
                tiles[r][c] = color +" " ;
            }
            tiles[r][MAX_HORIZ_TILES- 1] = color + " " ;
        }

        tiles[MAX_VERT_TILES - 1][0] = color + "└" ;
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[MAX_VERT_TILES - 1][c] = color + "─" ;
        }

        tiles[MAX_VERT_TILES - 1][MAX_HORIZ_TILES - 1] = color + "┘" ;

    }

    /**
     * Inserts data in the frame
     *
     * @param developmentCard the development card
     * @return the string [ ] [ ]
     */
    public String[][] showDevelData(DevelopmentCard developmentCard){
        fillEmpty(developmentCard.getColor());
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        //requirements
        tiles[1][1] = "R";
        tiles[1][2] = "E";
        tiles[1][3] = "Q";
        tiles[1][4] = ":";
        int i=4;
        for(Resource r: developmentCard.getRequirements().keySet()){
            if(developmentCard.getRequirements().get(r)>0) {
                tiles[1][i + 1] = developmentCard.getRequirements().get(r).toString();
                switch (r) {
                    case SERVANT:
                        tiles[1][i + 2] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" ;
                        break;
                    case SHIELD:
                        tiles[1][i + 2] = ColorVisualizer.ANSI_BLUE.escape() + "⛊" ;
                        break;
                    case COIN:
                        tiles[1][i + 2] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀" ;
                        break;
                    default:
                        tiles[1][i + 2] = ColorVisualizer.ANSI_GREY.escape() + "⛰" ;
                        break;
                }
                tiles[1][i + 3] = " ";
                i=i+3;
            }
        }
        //level
        tiles[2][1] = "L";
        tiles[2][2] = "E";
        tiles[2][3] = "V";
        tiles[2][4] = ":";
        tiles[2][5] = "" + developmentCard.getLevel();
        //Victory points
        tiles[3][1] = "V";
        tiles[3][2] = "P";
        tiles[3][3] = ":";
        tiles[3][4] = ""+developmentCard.getVictoryPoints();
        //prod input
        i=0;
        for(Resource r: developmentCard.getProductionInput().keySet()) {
            if (developmentCard.getProductionInput().get(r) > 0) {
                tiles[4][i + 1] = developmentCard.getProductionInput().get(r).toString();
                switch (r) {
                    case SERVANT:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒";
                        break;
                    case SHIELD:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_BLUE.escape() + "⛊";
                        break;
                    case COIN:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀";
                        break;
                    default:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_GREY.escape() + "⛰";
                        break;

                }
                tiles[4][i + 3] = " ";
                i = i + 3;
            }
        }
        tiles[4][i+1]= "≫ ";
        i++;
        for(Resource r: developmentCard.getProductionOutput().keySet()) {
            if (developmentCard.getProductionOutput().get(r) > 0) {
                tiles[4][i + 1] = developmentCard.getProductionOutput().get(r).toString();
                switch (r) {
                    case SERVANT:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" ;
                        break;
                    case SHIELD:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_BLUE.escape() + "⛊";
                        break;
                    case COIN:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀";
                        break;
                    default:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_GREY.escape() + "⛰";
                        break;

                }
                tiles[4][i + 3] = " ";
                i = i + 3;
            }
        }
        //faithpoints ma non credo vada messo
        tiles[5][1]="F";tiles[5][2]="P";tiles[5][3]=":";
        tiles[5][4]=""+developmentCard.getFaithPoint();


        return tiles;
    }






    /**
     * Plot.
     */
    public final void plot() {
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        System.out.print( whiteEscape);
        for (int r = 0; r < MAX_VERT_TILES; r++) {
            System.out.println();
            for (int c = 0; c < MAX_HORIZ_TILES; c++) {
                System.out.print(tiles[r][c]);
            }
        }
        System.out.print( whiteEscape);
    }

    /**
     * Prints on screen
     */
    public final void plot(int size,String[][] tiles) {
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        System.out.print( whiteEscape);
        for (int r = 0; r < MAX_VERT_TILES; r++) {
            System.out.println();
            for (int c = 0; c < size*MAX_HORIZ_TILES; c++) {
                System.out.print(tiles[r][c]);
            }
        }
        System.out.print( whiteEscape);
        System.out.print(ColorVisualizer.RESET);
    }
}
