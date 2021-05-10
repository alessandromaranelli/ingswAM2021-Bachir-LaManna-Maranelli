package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateResourcesToAddMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class DiscardResourceMsg extends CommandMsg {
    private Resource r;
    private int quantity;

    public DiscardResourceMsg(Resource r, int quantity) {
        this.r = r;
        this.quantity = quantity;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().discardResources(r,quantity);

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " discarded a resource, your position increases by 1 box!");
            controller.sendAllExcept(stringMsg, clientHandler);
            controller.sendAllPos(clientHandler);

            clientHandler.sendAnswerMessage(new UpdateResourcesToAddMsg(
                    controller.getGame().getCurrentPlayer().getPhase(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }




    }
}
