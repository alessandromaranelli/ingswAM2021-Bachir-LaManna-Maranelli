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

/**
 * The type DiscardResourceMsg.
 */
public class DiscardResourceMsg extends CommandMsg {
    private Resource r;
    private int quantity;

    /**
     * Instantiates a new Discard resource msg.
     *
     * @param r        the resource
     * @param quantity the quantity
     */
    public DiscardResourceMsg(Resource r, int quantity) {
        this.r = r;
        this.quantity = quantity;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().discardResources(controller,r,quantity);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }




    }
}
