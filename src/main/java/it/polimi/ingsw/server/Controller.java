package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.AnswerMessage;
import it.polimi.ingsw.messages.answers.GameStartMessage;
import it.polimi.ingsw.messages.answers.StringMessage;
import it.polimi.ingsw.messages.answers.UpdateNicknameMessage;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Controller {
    private Game game;
    private Map<Integer, ClientHandler> playersConnection;
    private int numberOfPlayers;

    public Controller() throws FileNotFoundException {
        game = new Game();
        playersConnection = new HashMap<>();
        numberOfPlayers = 0;
    }

    public void setNickname(String nickname, int numberOfPlayers, ClientHandler clientHandler){
        if(this.numberOfPlayers == 0){
            this.numberOfPlayers = numberOfPlayers;
            game.createNewPlayer(new Player(nickname, 1, game));
            playersConnection.put(1, clientHandler);
            clientHandler.sendAnswerMessage(createUpdateNicknameMessage(nickname, 1, this.numberOfPlayers));
            return;
        }

        if(game.getPlayers().size() < this.numberOfPlayers){
            for(Player p : game.getPlayers()){
                if(p.getNickname().equals(nickname)){
                    AnswerMessage answerMessage = createStringMessage("Nickname already taken, choose another nickname");
                    clientHandler.sendAnswerMessage(answerMessage);
                }
                return;
            }

            game.createNewPlayer(new Player(nickname, game.getPlayers().size()+1, game));
            playersConnection.put(game.getPlayers().size(), clientHandler);
            clientHandler.sendAnswerMessage(createUpdateNicknameMessage(nickname, game.getPlayers().size(), this.numberOfPlayers));
            sendBroadcastMessage("Player " + nickname + " has joined the game");


            if(game.getPlayers().size() == this.numberOfPlayers){
                game.getPlayers().get(0).setAsCurrentPlayer();
                for(int i=0; i < this.numberOfPlayers; i++){
                    if(i == game.getCurrentPlayer().getPlayerID()){
                        playersConnection.get((i)).sendAnswerMessage(createGameStartMessage(game.getTable().getMarket(), game.getTable().getMarket().getMarbleInExcess(), ));
                    }
                }
            }
        }
    }



    public AnswerMessage createStringMessage(String message){
        StringMessage answerMessage = new StringMessage(message);
        return answerMessage;
    }

    public void sendBroadcastMessage(String message){
        for(int i=1; i <= playersConnection.size(); i++){
            if(i != game.getCurrentPlayer().getPlayerID()){
                AnswerMessage answerMessage = createStringMessage("message");
                playersConnection.get(i).sendAnswerMessage(answerMessage);
            }
        }
    }

    public AnswerMessage createUpdateNicknameMessage(String nickname, int playerID, int numberOfPlayers){
        UpdateNicknameMessage updateNicknameMessage = new UpdateNicknameMessage(nickname, playerID, numberOfPlayers);
        return updateNicknameMessage;
    }

    public AnswerMessage createGameStartMessage(Marble[][] m, Marble mx, DevelopmentCard[] d, PersonalBoard p, String c, TurnState phase){
        GameStartMessage gameStartMessage = new GameStartMessage(m, mx, d, p, c, phase);
        return gameStartMessage;
    }









    /*
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
    */
}
