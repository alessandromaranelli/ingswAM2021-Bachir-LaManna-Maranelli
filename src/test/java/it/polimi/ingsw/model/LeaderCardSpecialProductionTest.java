package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardSpecialProductionTest {
    LeaderCardSpecialProduction leaderCardSpecialProduction = new LeaderCardSpecialProduction(4,"This is a SpecialProduction Leader 2",Color.PURPLE,Resource.STONE, "hello");
    @Test
    public void getDescription() {
        assertEquals("This is a SpecialProduction Leader 2",leaderCardSpecialProduction.getDescription());
        assertEquals(Color.PURPLE,leaderCardSpecialProduction.getColor());
        assertEquals(Resource.STONE,leaderCardSpecialProduction.getCost());
    }

    @Test
    public void getVictoryPoints() {
        assertEquals(4,leaderCardSpecialProduction.getVictoryPoints());
    }

    @Test
    public void discard() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardSpecialProduction);
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
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardSpecialProduction);
        try {
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.GREEN, 1, 1, m, m, m, 0), 2);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.BLUE, 2, 1, m, m, m, 0), 1);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.PURPLE, 2, 1, m, m, m, 0), 2);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        assertTrue(leaderCardSpecialProduction.verifyRequirements(player.getPersonalBoard()));
    }

    @Test
    public void activateEffect() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        Map<Resource, Integer> m = new HashMap<>();
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardSpecialProduction);
        player.getPersonalBoard().getLeaderCardsInHand().get(0).activateEffect(player.getPersonalBoard());
        try {
            assertEquals(Resource.STONE,player.getPersonalBoard().getProduction().getTypeOfSpecialProduction(1));
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
}