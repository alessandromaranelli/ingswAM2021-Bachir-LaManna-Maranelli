package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class GameTest {

    @Test
    public void createCPU() {
        Game game = null;
        try {
            game = new Game ();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Player player= new Player("Aldo",1, game);
        game.createNewPlayer(player);
        game.start();
        assertTrue(game.isSoloMatch());
        game.getCpu();

    }

    /*@Test
    void getVaticanReportSections() {
    }*/

    @Test
    public void createNewPlayer() {
        Game game = null;
        try {
            game = new Game ();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Player player= new Player("Aldo",1, game);
        game.createNewPlayer(player);
        assertEquals(game.getPlayers().get(0),player);
        assertEquals(player,game.getPlayerById(1));
    }
/*
    @Test
    void getPlayers() {
    }

    @Test
    void getTable() {
    }
*/
    @Test
    public void setCurrentPlayer() {
        Game game = null;
        try {
            game = new Game ();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Player player= new Player("Aldo",1, game);
        game.createNewPlayer(player);
        game.setCurrentPlayer(player);
        Player player2= new Player("Marcellino",2, game);
        game.createNewPlayer(player2);
        assertEquals(player,game.getCurrentPlayer());
        game.setCurrentPlayer(player2);
        assertEquals(player2,game.getCurrentPlayer());

    }
/*
    @Test
    void getCurrentPlayer() {
    }
    */
    @Test
    public void nextPlayer1() {
        Game game = null;
        try {
            game = new Game ();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Player player= new Player("Aldo",1, game);
        game.createNewPlayer(player);
        game.setCurrentPlayer(player);
        Player player2= new Player("Marcellino",2, game);
        game.createNewPlayer(player2);
        game.nextPlayer();
        assertEquals(player2, game.getCurrentPlayer());
        assertEquals(false,game.isGameAboutToFinish());
        assertEquals(false,game.hasLorenzoWon());
    }

    @Test
    public void nextPlayer2() {
        Game game = null;
        try {
            game = new Game ();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Player player= new Player("Aldo",1, game);
        game.createNewPlayer(player);
        game.setCurrentPlayer(player);
        game.start();
        game.nextPlayer();
        assertEquals(player, game.getCurrentPlayer());
    }


}