package it.polimi.ingsw.view;
import it.polimi.ingsw.*;
import it.polimi.ingsw.client.CLI.DevelCardsOfPlayerVisualizer;
import it.polimi.ingsw.client.CLI.DevelopmentCardVisualizer;
import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.Game;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DevelCardsOfPlayerVisualizerTest {
    public DevelCardsOfPlayerVisualizerTest() throws FileNotFoundException {
    }
    @Test
    public void view() throws FileNotFoundException {
        Game game = new Game();
        Map<Integer,DevelopmentCard> dev = new HashMap<>();
        dev.put(1,game.getTable().getTopDevelopmentcards().get(11));
        dev.put(2,game.getTable().getTopDevelopmentcards().get(9));
        dev.put(3,game.getTable().getTopDevelopmentcards().get(3));
        DevelCardsOfPlayerVisualizer d = new DevelCardsOfPlayerVisualizer();
        d.plot(dev);
        assert(true);
    }
}
