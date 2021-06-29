package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardReductionTest {
    LeaderCardReduction cardReduction = new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
    @Test
    public void getDescription() {
        assertEquals("leader red 1",cardReduction.getDescription());
    }

    @Test
    public void getVictoryPoints() {
        assertEquals(2,cardReduction.getVictoryPoints());
    }

    @Test
    public void getters(){
        cardReduction.getPath();
        assertEquals(Color.BLUE,cardReduction.getColor1());
        assertEquals(Color.GREEN,cardReduction.getColor2());
        assertEquals(Resource.COIN,cardReduction.getReduction());
    }
    @Test
    public void discard() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(cardReduction);
        try {
            player.getPersonalBoard().discardLeaderCard(1);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        assertEquals(1, player.getPersonalBoard().getFaithTrack().getTrack().indexOf(player.getPersonalBoard().getFaithTrack().checkPlayerPosition()));
    }
    @Test
    public void verifyRequirements() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        Map<Resource, Integer> m = new HashMap<>();
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(cardReduction);
        try {
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.GREEN, 1, 1, m, m, m, 0), 2);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        assertTrue(cardReduction.verifyRequirements(player.getPersonalBoard()));
    }

    @Test
    public void activateEffect() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        Map<Resource, Integer> m = new HashMap<>();
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(cardReduction);
        player.getPersonalBoard().getLeaderCardsInHand().get(0).activateEffect(player.getPersonalBoard());
        assertEquals(Resource.COIN,player.getPersonalBoard().getReduction().get(0));
    }
}