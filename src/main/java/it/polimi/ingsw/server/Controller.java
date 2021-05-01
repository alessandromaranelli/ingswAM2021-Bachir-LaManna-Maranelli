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

    public boolean checkCommandValidity(ClientHandler clientHandler, CommandMsg commandMsg){
        if (!gameStarted) return checkCommandValidityBeforeStart(clientHandler,commandMsg);
        else return checkCommandValidityAfterStart(clientHandler,commandMsg);
    }

    public boolean checkCommandValidityBeforeStart(ClientHandler clientHandler, CommandMsg commandMsg){
        if (commandMsg.getTurnState()!= TurnState.BEFORESTART || commandMsg.getTurnState()!=TurnState.PREPARATION){
            //Command not valid
            return false;
        }
        return true;
    }
    public boolean checkCommandValidityAfterStart(ClientHandler clientHandler, CommandMsg commandMsg){
        if(clientHandler.getPlayerID()!=game.getCurrentPlayer().getPlayerID()){
            //Wrong player
            return false;
        }
        if(commandMsg.getTurnState()!=game.getCurrentPlayer().getPhase()){
            //Wrong Phase
            return false;
        }
        return true;
    }

}
