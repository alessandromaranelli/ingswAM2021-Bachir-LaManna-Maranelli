package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    Table table= new Table();

    public TableTest() throws FileNotFoundException {
    }

    @Test
    public void getMarket() {
        assertInstanceOf(Market.class,table.getMarket());
    }
    @Test
    public void getDevelopmentCardDeck() {
        DevelopmentCardDeck developmentCardDeck=table.getDevelopmentCardDeck(Color.GREEN,3);
        assertEquals(Color.GREEN, developmentCardDeck.getColor());
        assertEquals(3,developmentCardDeck.getLevel());
    }

    @Test
    public void viewDevelopmentCard() throws ModelException {
        DevelopmentCard developmentCard=table.viewDevelopmentCard(Color.GREEN,3);
        assertEquals(Color.GREEN, developmentCard.getColor());
        assertEquals(3,developmentCard.getLevel());
    }

    @Test
    public void removeDevelopmentCard() throws ModelException {
        table.removeDevelopmentCard(Color.GREEN,3);
        assertEquals(3,table.getDevelopmentCardDeck(Color.GREEN,3).getDevelopmentCards().size());
    }

    @Test
    public void getLeaderCardDeck(){
        assertEquals(16,table.getLeaderCardDeck().getDeck().size());
    }
}