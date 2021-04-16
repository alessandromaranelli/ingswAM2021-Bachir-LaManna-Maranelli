package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LeaderCardDeckTest {
    Table table= new Table();

    LeaderCardDeckTest() throws FileNotFoundException {
    }

    @Test
    void getDeck() {
        assertEquals(16,table.getLeaderCardDeck().getDeck().size());
    }

    @Test
    void draw() throws ModelException {
        LeaderCard leaderCard= table.getLeaderCardDeck().draw();
        assertEquals(15,table.getLeaderCardDeck().getQuantity());
        assertInstanceOf(LeaderCard.class,leaderCard);

    }

}