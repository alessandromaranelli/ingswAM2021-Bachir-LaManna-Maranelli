package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PurpleMarbleTest {

    @Test
    public void whenDrawn() throws FileNotFoundException {
        Marble marble= new PurpleMarble();Game game= new Game();
        marble.toString();
        PersonalBoard personalBoard = new PersonalBoard(game.getVaticanReportSections());
        marble.whenDrawn(personalBoard);
        Map<Resource, Integer> m= new HashMap<>();
        m.put(Resource.SERVANT, 1);
        assertEquals(m,personalBoard.getWareHouse().getResourcesToAdd());
    }

}