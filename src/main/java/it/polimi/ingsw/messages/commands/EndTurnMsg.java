package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class EndTurnMsg extends CommandMsg{
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        controller.getGame().nextPlayer();
    }
}
