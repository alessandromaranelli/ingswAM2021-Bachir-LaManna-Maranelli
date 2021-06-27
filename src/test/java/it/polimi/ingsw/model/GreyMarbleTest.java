package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GreyMarbleTest {
    @Test
    public void whenDrawn() throws FileNotFoundException {
        Marble marble= new GreyMarble();
        marble.toString();
        Game game= new Game();
        PersonalBoard personalBoard = new PersonalBoard(game.getVaticanReportSections());
        marble.whenDrawn(personalBoard);
        Map<Resource, Integer> m= new HashMap<>();
        m.put(Resource.STONE, 1);
        assertEquals(m,personalBoard.getWareHouse().getResourcesToAdd());
    }


}