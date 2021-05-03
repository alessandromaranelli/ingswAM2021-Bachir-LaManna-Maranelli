package it.polimi.ingsw.messages.commands;

import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.Serializable;

public abstract class CommandMessage implements Serializable {
    public abstract void handleMessage(Controller controller, ClientHandler clientHandler);
}
