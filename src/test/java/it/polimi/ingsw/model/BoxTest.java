package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    @Test
    public void getVictoryPoints() {
        Box box= new Box(6);
        assertEquals(6,box.getVictoryPoints());
    }
}