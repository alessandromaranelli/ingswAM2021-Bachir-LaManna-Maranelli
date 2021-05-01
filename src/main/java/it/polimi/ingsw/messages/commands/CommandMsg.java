package it.polimi.ingsw.messages.commands;



import it.polimi.ingsw.messages.NetworkMessage;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;


/**
 * A command message sent to the server.
 */
public abstract class CommandMsg extends NetworkMessage
{
    private TurnState turnState;

    public CommandMsg(TurnState turnState) {
        this.turnState = turnState;
    }


    public TurnState getTurnState() {
        return turnState;
    }

    public abstract void processMessage(ClientHandler clientHandler, Controller controller) throws IOException;
}