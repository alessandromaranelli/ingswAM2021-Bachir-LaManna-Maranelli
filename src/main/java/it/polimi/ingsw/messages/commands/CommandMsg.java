package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.io.Serializable;

public abstract class CommandMsg implements Serializable {
    public abstract void processMessage(ClientHandler clientHandler, Controller controller);
}
