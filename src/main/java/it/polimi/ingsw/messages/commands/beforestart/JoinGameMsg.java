package it.polimi.ingsw.messages.commands.beforestart;

import Exceptions.ModelException;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;
import it.polimi.ingsw.server.Lobby;

import java.io.FileNotFoundException;

public class JoinGameMsg extends BeforeStartMsg {
    private String unicode;

    public JoinGameMsg(String unicode) {
        this.unicode = unicode;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Lobby lobby) throws FileNotFoundException, ModelException {
        lobby.reJoinMatch(clientHandler,unicode);
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws ModelException {

    }
}
