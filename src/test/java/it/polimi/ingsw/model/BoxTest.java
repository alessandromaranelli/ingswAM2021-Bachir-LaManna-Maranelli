package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void getVictoryPoints() {
        Box box= new Box(6);
        assertEquals(6,box.getVictoryPoints());
    }
}