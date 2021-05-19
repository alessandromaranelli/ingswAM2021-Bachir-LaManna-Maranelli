package it.polimi.ingsw.messages;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

//sono solo messaggi di comodo che servono al server per sapere che il client è ancora vivo
public class PongMsg extends CommandMsg {
    private String pong="pong";

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {

    }
}
