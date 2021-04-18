package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PopeFavourTest {

    @Test
    public void activate() {
        PopeFavour popeFavour=new PopeFavour(5);
        popeFavour.Activate();
        assertEquals(true,popeFavour.isActivated());
    }

    @Test
    public void isActivated() {
        PopeFavour popeFavour=new PopeFavour(5);
        assertEquals(false,popeFavour.isActivated());
    }

    @Test
    public void getVictoryPoints() {
        PopeFavour popeFavour=new PopeFavour(5);
        assertEquals(5,popeFavour.getVictoryPoints());
    }
}