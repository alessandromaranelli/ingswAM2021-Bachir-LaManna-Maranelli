package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.*;

public class RedMarbleTest {

    @Test
    public void whenDrawn() throws FileNotFoundException {
        Marble marble= new RedMarble();
        marble.toString();
        Game game= new Game();
        PersonalBoard personalBoard = new PersonalBoard(game.getVaticanReportSections());
        marble.whenDrawn(personalBoard);
        assertEquals(1,personalBoard.getFaithTrack().getTrack().indexOf(personalBoard.getFaithTrack().checkPlayerPosition()));

    }
}