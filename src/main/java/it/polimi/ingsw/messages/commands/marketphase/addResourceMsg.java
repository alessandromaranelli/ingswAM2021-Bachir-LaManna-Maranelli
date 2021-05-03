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

public class addResourceMsg extends CommandMsg {
    Resource r;
    int storageNumber;
    int quantity;

    public addResourceMsg(Resource r, int storageNumber, int quantity) {
        this.r = r;
        this.storageNumber = storageNumber;
        this.quantity = quantity;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        try {
            controller.getGame().getCurrentPlayer().addResources(r,storageNumber,quantity);
        } catch (ModelException e) {
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateStorageMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                            stream().map(Storage::getQuantity).toArray(Integer[]::new)));
            clientHandler.getOutput().writeObject(new UpdateResourcesToAddMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
