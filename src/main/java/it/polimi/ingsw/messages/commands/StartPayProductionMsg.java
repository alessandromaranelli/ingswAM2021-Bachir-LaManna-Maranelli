package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class StartPayProductionMsg extends CommandMsg{
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        try {
            controller.getGame().getCurrentPlayer().startPayProduction();
        } catch (ModelException e) {
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
    }
}
