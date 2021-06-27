package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class CardSlotTest {

    @Test
    public void testgetSlots(){
        CardSlot cardSlot = new CardSlot();
        assertTrue(cardSlot.getSlots().size()==3);
    }

    @Test
    public void testgetSlot(){
        CardSlot cardSlot = new CardSlot();
        assertTrue(cardSlot.getSlot(1).isEmpty());
    }

    @Test(expected = ModelException.class)
    public void testgetTopCardofSlot1() throws ModelException{
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = cardSlot.getTopCardofSlot(1);
    }

    @Test
    public void testgetTopCardofSlot2() throws ModelException{
        CardSlot cardSlot = new CardSlot();
        Map<Resource, Integer> m = new HashMap<>();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        cardSlot.addCardToSlot(card, 1);
        assertEquals(card, cardSlot.getTopCardofSlot(1));
    }

    @Test
    public void testcontrolCardToAdd1() throws ModelException{
        CardSlot cardSlot = new CardSlot();
        Map<Resource, Integer> m = new HashMap<>();
        cardSlot.addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);
        cardSlot.addCardToSlot(new DevelopmentCard(Color.BLUE, 2, 1, m, m, m, 0), 1);
        cardSlot.addCardToSlot(new DevelopmentCard(Color.BLUE, 3, 1, m, m, m, 0), 1);
        cardSlot.countCards();
        assertFalse(cardSlot.controlCardToAdd(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1));
    }

    @Test
    public void testcontrolCardToAdd2() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        assertTrue(cardSlot.controlCardToAdd(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1));
    }

    @Test
    public void testcontrolCardToAdd3() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        assertFalse(cardSlot.controlCardToAdd(new DevelopmentCard(Color.BLUE, 3, 1, m, m, m, 0), 1));
    }

    @Test
    public void testcontrolCardToAdd4() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        cardSlot.addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);

        assertTrue(cardSlot.controlCardToAdd(new DevelopmentCard(Color.BLUE, 2, 1, m, m, m, 0), 1));
    }

    @Test
    public void testcontrolCardToAdd5() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        cardSlot.addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);

        assertFalse(cardSlot.controlCardToAdd(new DevelopmentCard(Color.BLUE, 3, 1, m, m, m, 0), 1));
    }

    @Test(expected = ModelException.class)
    public void testaddCardToSlot1() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        cardSlot.addCardToSlot(new DevelopmentCard(Color.BLUE, 3, 1, m, m, m, 0), 1);
    }

    @Test
    public void testaddCardToSlot2() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        cardSlot.addCardToSlot(card, 1);
        assertEquals(card, cardSlot.getTopCardofSlot(1));
    }

    @Test
    public void testcountVictoryPoint() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        DevelopmentCard card2 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        DevelopmentCard card3 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);

        cardSlot.addCardToSlot(card1, 1);
        cardSlot.addCardToSlot(card2, 2);
        cardSlot.addCardToSlot(card3, 3);
        assertEquals(3, cardSlot.countVictoryPoint());
    }

    @Test
    public void testcontrolForReduction1() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        DevelopmentCard card2 = new DevelopmentCard(Color.YELLOW, 2, 1, m, m, m, 0);
        DevelopmentCard card3 = new DevelopmentCard(Color.GREEN, 1, 1, m, m, m, 0);

        cardSlot.addCardToSlot(card1, 1);
        cardSlot.addCardToSlot(card2, 1);
        cardSlot.addCardToSlot(card3, 3);
        assertTrue(cardSlot.controlForReduction(Color.BLUE, Color.YELLOW));
    }

    @Test
    public void testcontrolForReduction2() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);

        cardSlot.addCardToSlot(card1, 1);
        assertFalse(cardSlot.controlForReduction(Color.BLUE, Color.YELLOW));
    }

    @Test
    public void testcontrolForWhiteMarble1() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        DevelopmentCard card2 = new DevelopmentCard(Color.YELLOW, 2, 1, m, m, m, 0);
        DevelopmentCard card3 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);

        cardSlot.addCardToSlot(card1, 1);
        cardSlot.addCardToSlot(card2, 1);
        cardSlot.addCardToSlot(card3, 3);
        Map<Color,Integer> map=new HashMap<>();
        map.put(Color.BLUE,2);
        map.put(Color.YELLOW,1);
        assertTrue(cardSlot.controlForWhiteMarble(map));
    }

    @Test
    public void testcontrolForWhiteMarble2() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);

        cardSlot.addCardToSlot(card1, 1);
        Map<Color,Integer> map=new HashMap<>();
        map.put(Color.BLUE,2);
        map.put(Color.YELLOW,1);
        assertFalse(cardSlot.controlForWhiteMarble(map));
    }

    @Test
    public void testcontrolForSpecialProduction1() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        DevelopmentCard card2 = new DevelopmentCard(Color.YELLOW, 2, 1, m, m, m, 0);
        DevelopmentCard card3 = new DevelopmentCard(Color.GREEN, 1, 1, m, m, m, 0);

        cardSlot.addCardToSlot(card1, 1);
        cardSlot.addCardToSlot(card2, 1);
        cardSlot.addCardToSlot(card3, 3);
        assertTrue(cardSlot.controlForSpecialProduction(Color.YELLOW, 2));
    }

    @Test
    public void testcontrolForSpecialProduction2() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);

        cardSlot.addCardToSlot(card1, 1);

        assertFalse(cardSlot.controlForSpecialProduction(Color.YELLOW, 2));
    }
}