package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    Table table= new Table();

    TableTest() throws FileNotFoundException {
    }

    @Test
    void getMarket() {
        assertInstanceOf(Market.class,table.getMarket());
    }
    @Test
    void getDevelopmentCardDeck() {
        DevelopmentCardDeck developmentCardDeck=table.getDevelopmentCardDeck(Color.GREEN,3);
        assertEquals(Color.GREEN, developmentCardDeck.getColor());
        assertEquals(3,developmentCardDeck.getLevel());
    }

    @Test
    void viewDevelopmentCard() throws ModelException {
        DevelopmentCard developmentCard=table.viewDevelopmentCard(Color.GREEN,3);
        assertEquals(Color.GREEN, developmentCard.getColor());
        assertEquals(3,developmentCard.getLevel());
    }

    @Test
    void removeDevelopmentCard() throws ModelException {
        table.removeDevelopmentCard(Color.GREEN,3);
        assertEquals(3,table.getDevelopmentCardDeck(Color.GREEN,3).getDevelopmentCards().size());
    }

    @Test
    void getLeaderCardDeck(){
        assertEquals(16,table.getLeaderCardDeck().getDeck().size());
    }
}