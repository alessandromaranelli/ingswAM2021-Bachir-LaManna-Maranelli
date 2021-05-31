package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.ResourcesToOrganizeMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class StartOrganizeResourcesMsg extends CommandMsg {
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().startOrganizeResources(controller);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
