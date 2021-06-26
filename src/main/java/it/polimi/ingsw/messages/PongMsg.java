package it.polimi.ingsw.messages;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;


/**
 * The type PongMsg.
 */
public class PongMsg extends CommandMsg {
    private String pong="pong";

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {

    }
}
