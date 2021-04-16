package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopeFavourTest {

    @Test
    void activate() {
        PopeFavour popeFavour=new PopeFavour(5);
        popeFavour.Activate();
        assertEquals(true,popeFavour.isActivated());
    }

    @Test
    void isActivated() {
        PopeFavour popeFavour=new PopeFavour(5);
        assertEquals(false,popeFavour.isActivated());
    }

    @Test
    void getVictoryPoints() {
        PopeFavour popeFavour=new PopeFavour(5);
        assertEquals(5,popeFavour.getVictoryPoints());
    }
}