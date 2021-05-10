package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateResourcesToAddMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class AddResourceMsg extends CommandMsg {
    private Resource r;
    private int storageNumber;
    private int quantity;

    public AddResourceMsg(Resource r, int storageNumber, int quantity) {
        this.r = r;
        this.storageNumber = storageNumber;
        this.quantity = quantity;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().addResources(r,storageNumber,quantity);

            clientHandler.sendAnswerMessage(new UpdateStorageMsg(controller.getGame().getCurrentPlayer().getPhase(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                            stream().map(Storage::getQuantity).toArray(Integer[]::new)));
            clientHandler.sendAnswerMessage(new UpdateResourcesToAddMsg(
                    controller.getGame().getCurrentPlayer().getPhase(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

