package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.*;

import java.util.Map;

public class DevelopmentCardVisualizer {
    //private int start;
    private static final int MAX_VERT_TILES = 7; //rows.
    private static final int MAX_HORIZ_TILES = 15; //cols.

    String tiles[][] = new String[MAX_VERT_TILES][MAX_HORIZ_TILES];

    /*public void setStart(int start){
        this.start = start;
    }*/
    public DevelopmentCardVisualizer() {
       // start = 0;
    }


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
        tiles[0][0] = color + "┌"+ greenEscape;
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[0][c] = color + "─" + greenEscape;
        }
        tiles[0][MAX_HORIZ_TILES - 1] = color + "┐" + greenEscape;

        for (int r = 1; r < MAX_VERT_TILES - 1; r++) {
            tiles[r][0] = color + "│" + greenEscape;
            for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
                tiles[r][c] = color +" " + greenEscape;
            }
            tiles[r][MAX_HORIZ_TILES- 1] = color + " " + greenEscape;
        }

        tiles[MAX_VERT_TILES - 1][0] = color + "└" + greenEscape;
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[MAX_VERT_TILES - 1][c] = color + "─" + greenEscape;
        }

        tiles[MAX_VERT_TILES - 1][MAX_HORIZ_TILES - 1] = color + "┘" + greenEscape;

    }
    public void showDevelData(DevelopmentCard developmentCard){
        fillEmpty(developmentCard.getColor());
        String greenEscape = ColorVisualizer.ANSI_GREEN.escape();
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
                        tiles[1][i + 2] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" + greenEscape;
                        break;
                    case SHIELD:
                        tiles[1][i + 2] = ColorVisualizer.ANSI_BLUE.escape() + "⛊" + greenEscape;
                        break;
                    case COIN:
                        tiles[1][i + 2] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀" + greenEscape;
                        break;
                    default:
                        tiles[1][i + 2] = ColorVisualizer.ANSI_GREY.escape() + "⛰" + greenEscape;
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
                        tiles[4][i + 2] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" + greenEscape;
                        break;
                    case SHIELD:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_BLUE.escape() + "⛊" + greenEscape;
                        break;
                    case COIN:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀" + greenEscape;
                        break;
                    default:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_GREY.escape() + "⛰" + greenEscape;
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
                        tiles[4][i + 2] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" + greenEscape;
                        break;
                    case SHIELD:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_BLUE.escape() + "⛊" + greenEscape;
                        break;
                    case COIN:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀" + greenEscape;
                        break;
                    default:
                        tiles[4][i + 2] = ColorVisualizer.ANSI_GREY.escape() + "⛰" + greenEscape;
                        break;

                }
                tiles[4][i + 3] = " ";
                i = i + 3;
            }
        }
        //faithpoints ma non credo vada messo
        tiles[5][1]="F";tiles[5][2]="P";tiles[5][3]=":";
        tiles[5][4]=""+developmentCard.getFaithPoint();
    }





  /*  public void showMarbles(Marble[][] marbles){
        String greenEscape = ColorVisualizer.ANSI_GREEN.escape();
        if (marbles!=null) {
            for (int i = 0; i < MARKET_VERT_TILES; i++) {
                for (int j =0; j < MARKET_HORIZ_TILES; j++) {
                    if (marbles[i][j] instanceof WhiteMarble) {
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_WHITE.escape() +  "●" + greenEscape;
                    }
                    if (marbles[i][j] instanceof RedMarble) {
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_RED.escape() + "●" + greenEscape;
                    }
                    if (marbles[i][j] instanceof YellowMarble) {
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_YELLOW.escape() + "●" + greenEscape;
                    }
                    if (marbles[i][j] instanceof PurpleMarble) {
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_PURPLE.escape() + "●" + greenEscape;
                    }
                    if (marbles[i][j] instanceof GreyMarble) {
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_GREY.escape() + "●" + greenEscape;
                    }
                    if (marbles[i][j] instanceof BlueMarble) {
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_BLUE.escape() + "●" + greenEscape;
                    }
                }
            }
        }

    }
*/

    public final void plot() {
        System.out.print( ColorVisualizer.ANSI_GREEN.escape());
        for (int r = 0; r < MAX_VERT_TILES; r++) {
            System.out.println();
            for (int c = 0; c < MAX_HORIZ_TILES; c++) {
                System.out.print(tiles[r][c]);
            }
        }
    }
}
