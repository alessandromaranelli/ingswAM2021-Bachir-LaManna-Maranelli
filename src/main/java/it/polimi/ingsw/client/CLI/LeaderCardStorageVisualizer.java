package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.LeaderCardReduction;
import it.polimi.ingsw.model.LeaderCardStorage;

public class LeaderCardStorageVisualizer {
    private static final int MAX_VERT_TILES = 7; //rows.
    private static final int MAX_HORIZ_TILES = 40; //cols.

    String tiles[][] = new String[MAX_VERT_TILES][MAX_HORIZ_TILES];

    /*public void setStart(int start){
        this.start = start;
    }*/
    public LeaderCardStorageVisualizer() {
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

    public void showLeaderData(LeaderCardStorage leaderCard) {
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
        tiles[3][1] = "R";
        tiles[3][2] = "E";
        tiles[3][3] = "Q";
        tiles[3][4] = "1";
        tiles[3][5] = ":";
        switch (leaderCard.getRequirement()) {
            case SERVANT:
                tiles[3][6] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" + greenEscape;
                break;
            case SHIELD:
                tiles[3][6] = ColorVisualizer.ANSI_BLUE.escape() + "⛊" + greenEscape;
                break;
            case COIN:
                tiles[3][6] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀" + greenEscape;
                break;
            default:
                tiles[3][6] = ColorVisualizer.ANSI_GREY.escape() + "⛰" + greenEscape;
                break;
        }

        //Color2
        tiles[4][1] = "S";
        tiles[4][2] = "t";
        tiles[4][3] = "T";
        tiles[4][4] = "Y";
        tiles[4][5] = "P";
        tiles[4][6] = "E";
        tiles[4][7] = ":";
        switch (leaderCard.getType()) {
            case SERVANT:
                tiles[4][8] = ColorVisualizer.ANSI_PURPLE.escape() + "⚒" + greenEscape;
                break;
            case SHIELD:
                tiles[4][8] = ColorVisualizer.ANSI_BLUE.escape() + "⛊" + greenEscape;
                break;
            case COIN:
                tiles[4][8] = ColorVisualizer.ANSI_YELLOW.escape() + "⛀" + greenEscape;
                break;
            default:
                tiles[4][8] = ColorVisualizer.ANSI_GREY.escape() + "⛰" + greenEscape;
                break;
        }


    }
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

