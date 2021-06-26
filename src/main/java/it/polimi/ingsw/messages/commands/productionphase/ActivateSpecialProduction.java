package it.polimi.ingsw.messages.commands.productionphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateCostsGainsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

/**
 * The type ActivateSpecialProduction.
 */
public class ActivateSpecialProduction extends CommandMsg {
    private Resource r;
    private int i;

    /**
     * Instantiates a new Activate special production.
     *
     * @param r the resource
     * @param i the number
     */
    public ActivateSpecialProduction(Resource r, int i) {
        this.r = r;
        this.i = i;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().activateSpecialProduction(controller,r, i);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
