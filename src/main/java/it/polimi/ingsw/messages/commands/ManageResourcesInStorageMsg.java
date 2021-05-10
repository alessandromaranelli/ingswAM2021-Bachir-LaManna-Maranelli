package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.ResourcesToOrganizeMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ManageResourcesInStorageMsg extends CommandMsg {
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().manageResourcesInStorages();

            clientHandler.sendAnswerMessage(new ResourcesToOrganizeMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                    controller.getGame().getCurrentPlayer().getPhase()));

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " started to reorganize resources in storages");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
