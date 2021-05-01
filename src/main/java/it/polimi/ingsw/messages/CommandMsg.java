package it.polimi.ingsw.messages;



import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.util.UUID;


/**
 * A command message sent to the server.
 */
public abstract class CommandMsg extends NetworkMessage
{
    UUID identifier = UUID.randomUUID();


    /**
     * Unique identifier for the message.
     * Allows matching a message with its response.
     * @return The identifier
     */
    public UUID getIdentifier()
    {
        return identifier;
    }
    public abstract void processMessage(ClientHandler clientHandler, Controller controller) throws IOException;
}