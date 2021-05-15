package it.polimi.ingsw.server;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class Controller {
    private Game game;
    private final Set<ClientHandler> clientConnectionThreads;   //magari è meglio usare una mappa che associa ad ogni ID il relativo clientHandler così da evitare ogni volte di scorrere tutto il set alla ricerca del player giusto
    private boolean gameStarted;                                //non so se serve
    private int numberOfPlayers;
    private boolean lastTurn;
    private int turnsToPlay;

    public Controller(Set<ClientHandler> clientConnectionThreads) throws FileNotFoundException {
        game = new Game();
        this.clientConnectionThreads = clientConnectionThreads;
        gameStarted = false;
        numberOfPlayers = 0;
        lastTurn=false;
    }

    public Game getGame() {
        return game;
    }

    public void startGame(){
        game.start();
        gameStarted=true;
    }

    public void setLastTurn(boolean lastTurn) {
        this.lastTurn = lastTurn;
    }

    public boolean isLastTurn() {
        return lastTurn;
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

    public int getTurnsToPlay() {
        return turnsToPlay;
    }

    public void setTurnsToPlay(int turnsToPlay) {
        this.turnsToPlay = turnsToPlay;
    }

    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }

    public boolean isCurrentPlayer(ClientHandler clientHandler, CommandMsg commandMsg){
        if(!gameStarted)return true;
        if(clientHandler.getPlayerID()!=game.getCurrentPlayer().getPlayerID()){
            //Wrong player
            return false;
        }
        return true;
    }

    public void sendAll(AnswerMsg answer){
        for(ClientHandler clientHandler : clientConnectionThreads){
            clientHandler.sendAnswerMessage(answer);
        }
    }

    public void sendAllExcept(AnswerMsg answer, ClientHandler cH){
        for(ClientHandler clientHandler : clientConnectionThreads){
            if(!clientHandler.equals(cH)) {
                clientHandler.sendAnswerMessage(answer);
            }
        }
    }

    public void sendAllPos(ClientHandler cH){
        for(ClientHandler clientHandler : clientConnectionThreads){
            if(!clientHandler.equals(cH)) {
            clientHandler.sendAnswerMessage(new UpdateFaithMarkerPositionMsg(getGame().getPlayerById(clientHandler.getPlayerID()).getPhase(),
                    this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getTrack().indexOf(
                            this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                    this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getPopeFavours().
                            stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
            }
        }
    }


    //metodo chiamato dal primo messaggio di un client in cui setta nickname e giocatori che vorrebbe nella partita
    //messo nel controller perchè serve synchronized
    public synchronized void setNickname(String nickname, int numberOfPlayers, ClientHandler clientHandler) {
        if (this.numberOfPlayers == 0) {
            if(numberOfPlayers<1 || numberOfPlayers>4) {
                StringMsg stringMsg = new StringMsg("Impossible number of players man");
                clientHandler.sendAnswerMessage(stringMsg);
            }
            this.numberOfPlayers = numberOfPlayers;
            game.createNewPlayer(new Player(nickname, 1, game));
            clientConnectionThreads.add(clientHandler);               //sarebbe meglio aggiungere i clienthandler al set solo dopo aver controllato che hanno un nickname giusto. E' come aggiungerli al set sapendo che sono già ready
            UpdateNicknameMsg updateNicknameMsg = new UpdateNicknameMsg(nickname, 1, this.numberOfPlayers);
            clientHandler.sendAnswerMessage(updateNicknameMsg);
            clientHandler.setReady();                                   //serve a qualcosa il boolean ready?
            clientHandler.setPlayerID(1);
            return;
        }

        if (game.getPlayers().size() < this.numberOfPlayers) {
            for (Player p : game.getPlayers()) {
                if (p.getNickname().equals(nickname)) {
                    StringMsg stringMsg = new StringMsg("Nickname already taken, choose another nickname");
                    clientHandler.sendAnswerMessage(stringMsg);
                    return;
                }
            }

            game.createNewPlayer(new Player(nickname, game.getPlayers().size()+1, game));
            clientConnectionThreads.add(clientHandler);               //sarebbe meglio aggiungere i clienthandler al set solo dopo aver controllato che hanno un nickname giusto. E' come aggiungerli al set sapendo che sono già ready
            UpdateNicknameMsg updateNicknameMsg = new UpdateNicknameMsg(nickname, game.getPlayers().size(), this.numberOfPlayers);
            clientHandler.sendAnswerMessage(updateNicknameMsg);
            clientHandler.setReady();                                   //serve a qualcosa il boolean ready?
            clientHandler.setPlayerID(game.getPlayers().size());
            for(ClientHandler c : clientConnectionThreads){             //esempio nel quale se ho aggiunto tutti i clienthandler indistintamente, va male
                if(!c.equals(clientHandler)){
                    StringMsg stringMsg = new StringMsg("Player " + nickname + " has joined the game");
                    c.sendAnswerMessage(stringMsg);
                }
            }

            /*  Penso sia meglio aggiungere al set i clientHandler solo se hanno inserito un nickname giusto, come se fossero gia ready. Così facendo si evitano
                problemi come avere 700 client nel set, di cui solo 4 sono ready. A questo punto tutta la parte nel server con listening è evitabile. Basta controllare
                quando si aggiunge un giocatore al set, se i posti sono finiti. A quel punto parte il codice che manda i messaggi per inizio gioco:

                if (game.getPlayers().size() == this.numberOfPlayers) {
                controller.getGame().getPlayers().get(0).setAsCurrentPlayer();
                for (ClientHandler c : clientConnectionThreads) {
                    if (c.getPlayerID() == 1) {
                        GameStartMsg gameStartMsg = new GameStartMsg(game.getTable().getMarket().getMarketTable(), game.getTable().getMarket().getMarbleInExcess(), game.getTable().getTopDevelopmentcards(), game.getCurrentPlayer().getPersonalBoard(), game.getCurrentPlayer().getNickname(), TurnState.PREPARATION);
                        c.sendAnswerMessage(gameStartMsg);
                    }
                    else{
                        GameStartMsg gameStartMsg = new GameStartMsg(controller.getGame().getTable().getMarket().getMarketTable(), controller.getGame().getTable().getMarket().getMarbleInExcess(), controller.getGame().getTable().getTopDevelopmentcards(), controller.getGame().getPlayers().get(c.getPlayerID()).getPersonalBoard(), controller.getGame().getCurrentPlayer().getNickname(), TurnState.ENDTURN);
                        c.sendAnswerMessage(gameStartMsg);
                    }
                }
            } */
        }
    }

    public void startLastTurn(){
        setLastTurn(true);
        setTurnsToPlay(game.getPlayers().size()-1);
    }

    public void endGame(boolean Lorenzo) throws ModelException {
        if(Lorenzo){
            //end connection
            return;
        }
        Player player=game.getPlayerById(0);
        int winPoints=player.getPersonalBoard().countVictoryPoints();
        for(Player p: game.getPlayers()){
            if(p.getPersonalBoard().countVictoryPoints()>winPoints){
                player=p;
                winPoints=p.getPersonalBoard().countVictoryPoints();
            }
        }
        sendAll(new WinMsg("Player "+ player.getNickname()+" won the game with "+winPoints+" victoryPoints"));
        //end connections
    }

}
