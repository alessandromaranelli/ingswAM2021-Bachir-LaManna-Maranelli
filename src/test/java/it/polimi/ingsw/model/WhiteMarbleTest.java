package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class WhiteMarbleTest {
    Game game= new Game();
    PersonalBoard personalBoard= new PersonalBoard(game.getVaticanReportSections());

    public WhiteMarbleTest() throws FileNotFoundException {
    }

    @Test
    public void whenDrawn1() {
        Marble marble= new WhiteMarble();
        marble.toString();
        marble.whenDrawn(personalBoard);
    }

    @Test
    public void whenDrawn2() {
        Marble marble= new WhiteMarble();
        personalBoard.getWhiteMarble().add(Resource.COIN);
        marble.whenDrawn(personalBoard);
        assertEquals (1,personalBoard.getWareHouse().getResourcesToAdd().get(Resource.COIN));
    }

    @Test
    public void whenDrawn3() {
        Marble marble= new WhiteMarble();
        personalBoard.getWhiteMarble().add(Resource.COIN);
        personalBoard.getWhiteMarble().add(Resource.STONE);
        marble.whenDrawn(personalBoard);
        assertEquals (1,personalBoard.getManageWhiteMarbles());
    }
}