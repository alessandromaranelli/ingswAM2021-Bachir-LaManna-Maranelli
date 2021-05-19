package it.polimi.ingsw.view;

import it.polimi.ingsw.client.CLI.DevelopmentCardVisualizer;
import it.polimi.ingsw.client.CLI.FaithTrackVisualizer;
import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.Game;
import org.junit.Test;

import java.io.FileNotFoundException;

public class FaithTrackTest {
    public FaithTrackTest() throws FileNotFoundException {
    }
    @Test
    public void view() throws FileNotFoundException {
        FaithTrackVisualizer f = new FaithTrackVisualizer();
        f.plot(13);
        assert(true);
    }
}
