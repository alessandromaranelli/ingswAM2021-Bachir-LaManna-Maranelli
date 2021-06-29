package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardDeckTest {
    Table table= new Table();

    public LeaderCardDeckTest() throws FileNotFoundException {
    }

    @Test
    public void getDeck() {
        assertEquals(16,table.getLeaderCardDeck().getDeck().size());
    }

    @Test
    public void draw() throws ModelException {
        LeaderCard leaderCard= table.getLeaderCardDeck().draw();
        assertEquals(15,table.getLeaderCardDeck().getQuantity());
        assertInstanceOf(LeaderCard.class,leaderCard);

    }

}