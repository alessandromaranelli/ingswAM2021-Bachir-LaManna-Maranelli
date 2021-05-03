package it.polimi.ingsw.messages.commands.leadermanage;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ActivateLeaderMsg extends CommandMsg {
    int i;

    public ActivateLeaderMsg(int i) {
        this.i = i;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        try {
            controller.getGame().getCurrentPlayer().activateLeaderCard(i);
        } catch (ModelException e) {
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        //aggiornare la roba giusta
    }
}
