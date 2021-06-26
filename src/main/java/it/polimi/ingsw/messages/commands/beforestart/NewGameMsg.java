package it.polimi.ingsw.messages.commands.beforestart;

import Exceptions.ModelException;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;
import it.polimi.ingsw.server.Lobby;

import java.io.FileNotFoundException;

/**
 * The type NewGameMsg.
 */
public class NewGameMsg extends BeforeStartMsg {

    @Override
    public void processMessage(ClientHandler clientHandler, Lobby lobby) throws FileNotFoundException {
        lobby.createMatch(clientHandler);
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws ModelException {

    }
}
