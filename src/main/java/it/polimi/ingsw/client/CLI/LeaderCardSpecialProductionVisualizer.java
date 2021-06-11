package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.LeaderCardReduction;
import it.polimi.ingsw.model.LeaderCardSpecialProduction;

public class LeaderCardSpecialProductionVisualizer {
    private static final int MAX_VERT_TILES = 7; //rows.
    private static final int MAX_HORIZ_TILES = 40;  //cols.

    String tiles[][] = new String[MAX_VERT_TILES][MAX_HORIZ_TILES];

    /*public void setStart(int start){
        this.start = start;
    }*/
    public LeaderCardSpecialProductionVisualizer() {
        // start = 0;
    }


    public void fillEmpty(Color col) {
        String blueEscape = ColorVisualizer.ANSI_BLUE.escape();
        String color;
        switch (col) {
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
                color = ColorVisualizer.ANSI_PURPLE.escape();
                ;

        }
        tiles[0][0] = color + "┌" + blueEscape;
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[0][c] = color + "─" + blueEscape;
        }
        tiles[0][MAX_HORIZ_TILES - 1] = color + "┐" + blueEscape;

        for (int r = 1; r < MAX_VERT_TILES - 1; r++) {
            tiles[r][0] = color + "│" + blueEscape;
            for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
                tiles[r][c] = color + " " + blueEscape;
            }
            tiles[r][MAX_HORIZ_TILES - 1] = color + " " + blueEscape;
        }

        tiles[MAX_VERT_TILES - 1][0] = color + "└" + blueEscape;
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[MAX_VERT_TILES - 1][c] = color + "─" + blueEscape;
        }

        tiles[MAX_VERT_TILES - 1][MAX_HORIZ_TILES - 1] = color + "┘" + blueEscape;

    }

    public void showLeaderData(LeaderCardSpecialProduction leaderCard) {
        fillEmpty(Color.YELLOW);
        String greenEscape = ColorVisualizer.ANSI_GREEN.escape();
        //description
        tiles[1][1] = "D";
        tiles[1][2] = "E";
        tiles[1][3] = "S";
        tiles[1][4] = "C";
        tiles[1][5] = ":";
        tiles[1][6] = leaderCard.getDescription();

        //Victory points
        tiles[2][1] = "V";
        tiles[2][2] = "P";
        tiles[2][3] = ":";
        tiles[2][4] = "" + leaderCard.getVictoryPoints();

        //Color1
        tiles[3][1] = "C";
        tiles[3][2] = "O";
        tiles[3][3] = "L";
        tiles[3][4] = ":";
        switch (leaderCard.getColor()) {
            case GREEN:
                tiles[3][5] = ColorVisualizer.ANSI_GREEN.escape() + "●" + greenEscape;
                break;
            case PURPLE:
                tiles[3][5] = ColorVisualizer.ANSI_PURPLE.escape() + "●" + greenEscape;
                break;
            case YELLOW:
                tiles[3][5] = ColorVisualizer.ANSI_YELLOW.escape() + "●" + greenEscape;
                break;
            case BLUE:
                tiles[3][5] = ColorVisualizer.ANSI_BLUE.escape() + "●" + greenEscape;
                break;
        }

        //Cost
        tiles[4][1] = "C";
        tiles[4][2] = "O";
        tiles[4][3] = "S";
        tiles[4][4] = "T";
        tiles[4][5] = ":";
        switch (leaderCard.getCost()) {
            case SERVANT:
                tiles[4][6] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" + greenEscape;
                break;
            case SHIELD:
                tiles[4][6] = ColorVisualizer.ANSI_BLUE.escape() + "⛊" + greenEscape;
                break;
            case COIN:
                tiles[4][6] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀" + greenEscape;
                break;
            default:
                tiles[4][6] = ColorVisualizer.ANSI_GREY.escape() + "⛰" + greenEscape;
                break;
        }


    }
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
}
