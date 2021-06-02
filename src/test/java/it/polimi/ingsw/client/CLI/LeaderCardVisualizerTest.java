package it.polimi.ingsw.client.CLI;

import Exceptions.ModelException;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderCardVisualizerTest {
    Set<ClientHandler> clientHandlers=new LinkedHashSet<>();
    Controller controller=new Controller(clientHandlers);

    public LeaderCardVisualizerTest() throws FileNotFoundException {
    }

    @Test
    public void view() throws FileNotFoundException, ModelException {
        Game game = new Game();
        Player player=new Player("gino",1,game);
        player.drawLeaderCards(controller);
        LeaderCardVisualizer leaderCardVisualizer=new LeaderCardVisualizer();
        for(LeaderCard leaderCard : player.getPersonalBoard().getLeaderCardsInHand()){
            leaderCardVisualizer.showLeaderData(leaderCard);
        }
        assert(true);
    }

}