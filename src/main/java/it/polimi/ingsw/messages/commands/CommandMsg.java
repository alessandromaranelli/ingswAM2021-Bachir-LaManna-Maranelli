package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.io.Serializable;

/**
 * The type CommandMsg is the abstract class extended by all the messages that the client sends to the server.
 */
public abstract class CommandMsg implements Serializable {
    /**
     * ProcessMessage is the method invoked by the clientHandler when a message arrives to the server.
     *
     * @param clientHandler the client handler
     * @param controller    the controller
     * @throws ModelException the model exception
     */
    public abstract void processMessage(ClientHandler clientHandler, Controller controller) throws ModelException;
}
