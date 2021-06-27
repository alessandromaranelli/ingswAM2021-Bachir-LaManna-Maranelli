package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardStorageTest {
    LeaderCardStorage leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 1",Resource.COIN,Resource.STONE, "hello");
    @Test
    public void getDescription() {
        assertEquals("This is a Storage Leader 1",leaderCardStorage.getDescription());
    }

    @Test
    public void getVictoryPoints() {
        assertEquals(3,leaderCardStorage.getVictoryPoints());
    }

    @Test
    public void discard() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        players.add(player);
        leaderCardStorage.getType();
        leaderCardStorage.getRequirement();
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardStorage);
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
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardStorage);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN,5);
        assertTrue(leaderCardStorage.verifyRequirements(player.getPersonalBoard()));
    }

    @Test
    public void activateEffect() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        Map<Resource, Integer> m = new HashMap<>();
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardStorage);
        player.getPersonalBoard().getLeaderCardsInHand().get(0).activateEffect(player.getPersonalBoard());
        try {
            assertEquals(Resource.STONE,player.getPersonalBoard().getWareHouse().getTypeStorage(4));
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
}