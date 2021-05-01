package it.polimi.ingsw.messages.commands;

import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class DrawLeadersMsg extends CommandMsg{
    public DrawLeadersMsg(TurnState turnState) {
        super(turnState);
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {

    }
}
