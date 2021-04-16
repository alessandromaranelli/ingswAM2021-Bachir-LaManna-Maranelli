package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RedMarbleTest {

    @Test
    void whenDrawn() throws FileNotFoundException {
        Marble marble= new RedMarble();

        Game game= new Game();
        PersonalBoard personalBoard = new PersonalBoard(game.getVaticanReportSections());
        marble.whenDrawn(personalBoard);
        assertEquals(1,personalBoard.getFaithTrack().getTrack().indexOf(personalBoard.getFaithTrack().checkPlayerPosition()));

    }
}