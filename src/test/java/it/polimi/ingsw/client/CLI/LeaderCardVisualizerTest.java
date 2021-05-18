package it.polimi.ingsw.client.CLI;

import Exceptions.ModelException;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Player;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardVisualizerTest {
    @Test
    public void view() throws FileNotFoundException, ModelException {
        Game game = new Game();
        Player player=new Player("gino",1,game);
        player.drawLeaderCards();
        LeaderCardVisualizer leaderCardVisualizer=new LeaderCardVisualizer();
        for(LeaderCard leaderCard : player.getPersonalBoard().getLeaderCardsInHand()){
            leaderCardVisualizer.showLeaderData(leaderCard);
        }
        assert(true);
    }

}