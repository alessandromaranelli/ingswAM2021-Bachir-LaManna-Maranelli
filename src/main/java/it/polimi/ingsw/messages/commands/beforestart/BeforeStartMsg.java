package it.polimi.ingsw.messages.commands.beforestart;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Lobby;

import java.io.FileNotFoundException;


/**
 * The type BeforeStartMsg.
 */
public abstract class BeforeStartMsg extends CommandMsg {

        /**
         * Process message.
         *
         * @param clientHandler the client handler
         * @param lobby         the lobby
         * @throws FileNotFoundException the file not found exception
         * @throws ModelException        the model exception
         */
        public abstract void processMessage(ClientHandler clientHandler, Lobby lobby) throws FileNotFoundException, ModelException;
}
