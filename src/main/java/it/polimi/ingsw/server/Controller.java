package it.polimi.ingsw.server;

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

    public Controller(Set<ClientHandler> clientConnectionThreads) throws FileNotFoundException {
        game = new Game();
        this.clientConnectionThreads = clientConnectionThreads;
        gameStarted = false;
        numberOfPlayers = 0;
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

    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
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


    //metodo chiamato dal primo messaggio di un client in cui setta nickname e giocatori che vorrebbe nella partita
    //messo nel controller perchè serve synchronized
    public synchronized void setNickname(String nickname, int numberOfPlayers, ClientHandler clientHandler) {
        if (this.numberOfPlayers == 0) {
            this.numberOfPlayers = numberOfPlayers;
            game.createNewPlayer(new Player(nickname, 1, game));
            //clientConnectionThreads.add(clientHandler);               sarebbe meglio aggiungere i clienthandler al set solo dopo aver controllato che hanno un nickname giusto. E' come aggiungerli al set sapendo che sono già ready
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
                }
                return;
            }

            game.createNewPlayer(new Player(nickname, game.getPlayers().size() + 1, game));
            //clientConnectionThreads.add(clientHandler);               sarebbe meglio aggiungere i clienthandler al set solo dopo aver controllato che hanno un nickname giusto. E' come aggiungerli al set sapendo che sono già ready
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
}
