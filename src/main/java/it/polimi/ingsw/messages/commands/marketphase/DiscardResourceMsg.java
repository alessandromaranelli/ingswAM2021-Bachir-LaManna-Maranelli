package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateResourcesToAddMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class DiscardResourceMsg extends CommandMsg {
    Resource r;
    int quantity;

    public DiscardResourceMsg(Resource r, int quantity) {
        this.r = r;
        this.quantity = quantity;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        try {
            controller.getGame().getCurrentPlayer().discardResources(r,quantity);
        } catch (ModelException e) {
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        try{
            controller.sendAllPos(clientHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateResourcesToAddMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
