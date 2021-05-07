package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.ResourcesToOrganizeMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ManageResourcesToOrganize extends CommandMsg {
    Resource r;
    int storageNumber;
    int quantity;
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        try {
            controller.getGame().getCurrentPlayer().manageResourcesToOrganize(r,storageNumber,quantity);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
        clientHandler.sendAnswerMessage(new UpdateStorageMsg(controller.getGame().getCurrentPlayer().getPhase(),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));
        clientHandler.sendAnswerMessage(new ResourcesToOrganizeMsg(
                controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToOrganize()));
    }
}