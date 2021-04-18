package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopeSpaceTest {

    @Test
    public void report1() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        players.add(player);
        game.createNewPlayer(player);
        player= new Player("Giovanni",2, game);
        players.add(player);
        game.createNewPlayer(player);
        player= new Player("Giacomo",3, game);
        players.add(player);
        game.createNewPlayer(player);
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        assertFalse(players.get(0).getPersonalBoard().getFaithTrack().getPopeFavours().get(0).isActivated());
        assertTrue(players.get(1).getPersonalBoard().getFaithTrack().getPopeFavours().get(0).isActivated());
        assertTrue(players.get(2).getPersonalBoard().getFaithTrack().getPopeFavours().get(0).isActivated());
        assertTrue(game.getVaticanReportSections().get(0).isPassed());
        assertFalse(game.getVaticanReportSections().get(1).isPassed());
        assertFalse(game.getVaticanReportSections().get(2).isPassed());
    }

    @Test
    public void report2() throws FileNotFoundException {
        Game game= new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player= new Player("Aldo",1, game);
        players.add(player);
        game.createNewPlayer(player);
        player= new Player("Giovanni",2, game);
        players.add(player);
        game.createNewPlayer(player);
        player= new Player("Giacomo",3, game);
        players.add(player);
        game.createNewPlayer(player);
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(2).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(0).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        players.get(1).getPersonalBoard().getFaithTrack().movePositionForward();
        assertFalse(players.get(0).getPersonalBoard().getFaithTrack().getPopeFavours().get(0).isActivated());
        assertTrue(players.get(1).getPersonalBoard().getFaithTrack().getPopeFavours().get(0).isActivated());
        assertTrue(players.get(2).getPersonalBoard().getFaithTrack().getPopeFavours().get(0).isActivated());
        assertTrue(game.getVaticanReportSections().get(0).isPassed());
        assertFalse(game.getVaticanReportSections().get(1).isPassed());
        assertFalse(game.getVaticanReportSections().get(2).isPassed());
    }

}