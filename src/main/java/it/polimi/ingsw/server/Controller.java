package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.AnswerMsg;
import it.polimi.ingsw.messages.answers.UpdateFaithMarkerPositionMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.TurnState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Controller {
    private Game game=new Game();
    private final Set<ClientHandler> clientConnectionThreads;
    private boolean gameStarted=false;
    private int numberOfPlayers=-1;

    public Controller(Set<ClientHandler> clientConnectionThreads) throws FileNotFoundException {
        this.clientConnectionThreads = clientConnectionThreads;
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

    public void sendAll(AnswerMsg answer){
        for(ClientHandler clientHandler : clientConnectionThreads){
            try{
                clientHandler.getOutput().writeObject(answer);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void sendAllPos(ClientHandler cH){
        for(ClientHandler clientHandler : clientConnectionThreads){
            if(clientHandler.equals(cH)) return;
            try{
                clientHandler.getOutput().writeObject(new UpdateFaithMarkerPositionMsg(
                        this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getTrack().indexOf(
                        this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                        this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getPopeFavours().
                                stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}
