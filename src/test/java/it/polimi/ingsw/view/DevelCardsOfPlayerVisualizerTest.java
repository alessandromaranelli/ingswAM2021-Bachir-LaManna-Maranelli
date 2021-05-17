package it.polimi.ingsw.view;
import it.polimi.ingsw.*;
import it.polimi.ingsw.client.CLI.DevelCardsOfPlayerVisualizer;
import it.polimi.ingsw.client.CLI.DevelopmentCardVisualizer;
import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.Game;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DevelCardsOfPlayerVisualizerTest {
    public DevelCardsOfPlayerVisualizerTest() throws FileNotFoundException {
    }
    @Test
    public void view() throws FileNotFoundException {
        Game game = new Game();
        List<DevelopmentCard> dev = new ArrayList<DevelopmentCard>();
        dev.add(game.getTable().getTopDevelopmentcards().get(11));
        dev.add(game.getTable().getTopDevelopmentcards().get(9));
        dev.add(game.getTable().getTopDevelopmentcards().get(3));
        DevelCardsOfPlayerVisualizer d = new DevelCardsOfPlayerVisualizer();
        d.plot(dev);
        assert(true);
    }
}
