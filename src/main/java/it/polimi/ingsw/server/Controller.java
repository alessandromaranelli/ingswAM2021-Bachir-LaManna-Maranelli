package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.TurnState;

import java.io.FileNotFoundException;

public class Controller {
    private Game game=new Game();
    private boolean gameStarted=false;
    private int numberOfPlayers=-1;

    public Controller() throws FileNotFoundException {
    }

    public Game getGame() {
        return game;
    }

    public void startGame(){
        gameStarted=true;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public boolean isCurrentPlayer(ClientHandler clientHandler, CommandMsg commandMsg){
        if(clientHandler.getPlayerID()!=game.getCurrentPlayer().getPlayerID()){
            //Wrong player
            return false;
        }
        return true;
    }

}
