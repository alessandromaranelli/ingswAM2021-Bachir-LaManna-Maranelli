package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class WhiteMarbleTest {
    Game game= new Game();
    PersonalBoard personalBoard= new PersonalBoard(game.getVaticanReportSections());

    WhiteMarbleTest() throws FileNotFoundException {
    }

    @Test
    void whenDrawn1() {
        Marble marble= new WhiteMarble();
        marble.whenDrawn(personalBoard);
    }

    @Test
    void whenDrawn2() {
        Marble marble= new WhiteMarble();
        personalBoard.getWhiteMarble().add(Resource.COIN);
        marble.whenDrawn(personalBoard);
        assertEquals (1,personalBoard.getWareHouse().getResourcesToAdd().get(Resource.COIN));
    }

    @Test
    void whenDrawn3() {
        Marble marble= new WhiteMarble();
        personalBoard.getWhiteMarble().add(Resource.COIN);
        personalBoard.getWhiteMarble().add(Resource.STONE);
        marble.whenDrawn(personalBoard);
        assertEquals (1,personalBoard.getManageWhiteMarbles());
    }
}