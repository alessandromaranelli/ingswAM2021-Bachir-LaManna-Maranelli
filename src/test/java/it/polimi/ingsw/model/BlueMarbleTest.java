package it.polimi.ingsw.model;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BlueMarbleTest {

    @Test
    public void testWhenDrawn() throws FileNotFoundException {
        Marble marble= new BlueMarble();
        Game game= new Game();
        marble.toString();
        PersonalBoard personalBoard = new PersonalBoard(game.getVaticanReportSections());
        marble.whenDrawn(personalBoard);
        Map<Resource, Integer> m= new HashMap<>();
        m.put(Resource.SHIELD, 1);
        assertEquals(m,personalBoard.getWareHouse().getResourcesToAdd());
    }
}