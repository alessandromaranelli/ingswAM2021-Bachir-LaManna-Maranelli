package it.polimi.ingsw.messages.commands.beforestart;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Lobby;

import java.io.FileNotFoundException;


public abstract class BeforeStartMsg extends CommandMsg {

        public abstract void processMessage(ClientHandler clientHandler, Lobby lobby) throws FileNotFoundException;
}
