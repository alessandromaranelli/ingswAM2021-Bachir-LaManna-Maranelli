package it.polimi.ingsw.server;

import it.polimi.ingsw.model.Game;

import java.io.FileNotFoundException;

public class Controller {
    private Game game=new Game();
    int numberOfPlayers=-1;

    public Controller() throws FileNotFoundException {
    }

    public Game getGame() {
        return game;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
