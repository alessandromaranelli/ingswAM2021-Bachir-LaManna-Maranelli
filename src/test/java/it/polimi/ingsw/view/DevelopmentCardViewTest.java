package it.polimi.ingsw.view;
import it.polimi.ingsw.*;
import it.polimi.ingsw.client.CLI.DevelopmentCardVisualizer;
import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.Game;
import org.junit.Test;

import java.io.FileNotFoundException;

public class DevelopmentCardViewTest {
    public DevelopmentCardViewTest() throws FileNotFoundException {
    }
    @Test
    public void view() throws FileNotFoundException {
        Game game = new Game();
        DevelopmentCard dev = game.getTable().getTopDevelopmentcards().get(11);
        DevelopmentCardVisualizer d = new DevelopmentCardVisualizer();
        d.showDevelData(dev);
        d.plot();
        assert(true);
    }
}
