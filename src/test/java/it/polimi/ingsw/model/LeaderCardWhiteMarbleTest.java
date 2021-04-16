package it.polimi.ingsw.model;//devo sistemare una roba
import Exceptions.ModelException;
import org.junit.Test;
///import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardWhiteMarbleTest {


    @Test
    public void getDescription() {
        Map<Color, Integer> req = new HashMap<>();
        req.put(Color.PURPLE,2);
        req.put(Color.GREEN,1);
        LeaderCardWhiteMarble leaderCardWhiteMarble = new LeaderCardWhiteMarble(3,"This is a WhiteMarble Leader 1",req,Resource.COIN);

        assertEquals("This is a WhiteMarble Leader 1",leaderCardWhiteMarble.getDescription());
    }

    @Test
    public void getVictoryPoints() {
        Map<Color, Integer> req = new HashMap<>();
        req.put(Color.PURPLE,2);
        req.put(Color.GREEN,1);
        LeaderCardWhiteMarble leaderCardWhiteMarble = new LeaderCardWhiteMarble(3,"This is a WhiteMarble Leader 1",req,Resource.COIN);
        assertEquals(3,leaderCardWhiteMarble.getVictoryPoints());
    }

    @Test
    public void discard() throws FileNotFoundException {
        Map<Color, Integer> req = new HashMap<>();
        req.put(Color.PURPLE,2);
        req.put(Color.GREEN,1);
        LeaderCardWhiteMarble leaderCardWhiteMarble = new LeaderCardWhiteMarble(3,"This is a WhiteMarble Leader 1",req,Resource.COIN);
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardWhiteMarble);
        try {
            player.getPersonalBoard().discardLeaderCard(1);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        assertEquals(1, player.getPersonalBoard().getFaithTrack().getTrack().indexOf(player.getPersonalBoard().getFaithTrack().checkPlayerPosition()));
    }
    @Test
    public void verifyRequirements() throws FileNotFoundException {
        Map<Color, Integer> req = new HashMap<>();
        req.put(Color.PURPLE,2);
        req.put(Color.GREEN,1);
        LeaderCardWhiteMarble leaderCardWhiteMarble = new LeaderCardWhiteMarble(3,"This is a WhiteMarble Leader 1",req,Resource.COIN);
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        Map<Resource, Integer> m = new HashMap<>();
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardWhiteMarble);
        try {
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.GREEN, 1, 1, m, m, m, 0), 2);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.PURPLE, 2, 1, m, m, m, 0), 1);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.PURPLE, 2, 1, m, m, m, 0), 2);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        assertTrue(leaderCardWhiteMarble.verifyRequirements(player.getPersonalBoard()));
    }

    @Test
    public void activateEffect() throws FileNotFoundException {
        Map<Color, Integer> req = new HashMap<>();
        req.put(Color.PURPLE,2);
        req.put(Color.GREEN,1);
        LeaderCardWhiteMarble leaderCardWhiteMarble = new LeaderCardWhiteMarble(3,"This is a WhiteMarble Leader 1",req,Resource.COIN);
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        Map<Resource, Integer> m = new HashMap<>();
        players.add(player);
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardWhiteMarble);
        player.getPersonalBoard().getLeaderCardsInHand().get(0).activateEffect(player.getPersonalBoard());
        assertEquals(Resource.COIN,player.getPersonalBoard().getWhiteMarble().get(0));
    }
}