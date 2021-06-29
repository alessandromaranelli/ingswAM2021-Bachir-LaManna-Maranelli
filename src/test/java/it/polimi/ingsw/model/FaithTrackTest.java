package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.*;

public class FaithTrackTest {

    @Test
    public void checkPlayerPosition() throws FileNotFoundException {
        Game game= new Game();
        FaithTrack faithTrack= new FaithTrack(game.getVaticanReportSections());
        assertEquals(0, faithTrack.getTrack().indexOf(faithTrack.checkPlayerPosition()));
    }


    @Test
    public void movePositionForward() throws FileNotFoundException {
        Game game = new Game();
        FaithTrack faithTrack = new FaithTrack(game.getVaticanReportSections());
        faithTrack.movePositionForward();
        faithTrack.movePositionForward();
        faithTrack.movePositionForward();
        assertEquals(3, faithTrack.getTrack().indexOf(faithTrack.checkPlayerPosition()));
    }
}