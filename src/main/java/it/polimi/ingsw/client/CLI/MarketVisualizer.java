package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.*;

/**
 * The market visualizer for cli
 */
public class MarketVisualizer {
    private static final int MAX_VERT_TILES = 5; //rows.
    private static final int MAX_HORIZ_TILES = 14; //cols.
    private static final int MARKET_VERT_TILES = 3; //rows.
    private static final int MARKET_HORIZ_TILES = 4; //cols.

    String tiles[][] = new String[MAX_VERT_TILES][MAX_HORIZ_TILES];

    public MarketVisualizer() {
        fillEmpty();
    }

    /**
     * Creates the frame
     */
    public void fillEmpty() {

        tiles[0][0] = "╔";
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[0][c] = "═";
        }

        tiles[0][MAX_HORIZ_TILES -1] = "╗";

        for (int r = 1; r < MAX_VERT_TILES - 1; r++) {
            tiles[r][0] = " ";
            for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
                tiles[r][c] = " ";
            }
            tiles[r][MAX_HORIZ_TILES-1] = " ";
        }

        tiles[MAX_VERT_TILES - 1][0] = "╚";
        for (int c = 1; c < MAX_HORIZ_TILES - 1; c++) {
            tiles[MAX_VERT_TILES - 1][c] = "═";
        }

        tiles[MAX_VERT_TILES - 1][MAX_HORIZ_TILES-1 ] = "╝";

    }


    /**
     * Inserts the marble in its correspondent position
     *
     * @param marbles matrix of marbles
     */
    public void showMarbles(Marble [][] marbles){
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
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_GREY.escape() + "●" + ColorVisualizer.RESET+ greenEscape;
                    }
                    if (marbles[i][j] instanceof BlueMarble) {
                        tiles[i+1][j+3] = ColorVisualizer.ANSI_BLUE.escape() + "●" + greenEscape;
                    }
                }
            }
        }

    }


    /**
     * Prints on screen the table
     */
    public final void plot() {
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        String greenEscape = ColorVisualizer.ANSI_GREEN.escape();
        System.out.print( greenEscape);
        for (int r = 0; r < MAX_VERT_TILES; r++) {
            System.out.println();
            for (int c = 0; c < MAX_HORIZ_TILES; c++) {
                System.out.print(tiles[r][c]);
            }
        }
        System.out.print( whiteEscape);
        System.out.print(ColorVisualizer.RESET);
    }

}

