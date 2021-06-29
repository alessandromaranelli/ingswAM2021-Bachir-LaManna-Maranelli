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
 * The type ActivatePersonalProductionMsg.
 */
public class ActivatePersonalProductionMsg extends CommandMsg {
    private Resource i1;
    private Resource i2;
    private Resource o;

    /**
     * Instantiates a new Activate personal production msg.
     *
     * @param i1 the input1
     * @param i2 the input2
     * @param o  the output
     */
    public ActivatePersonalProductionMsg(Resource i1, Resource i2, Resource o) {
        this.i1 = i1;
        this.i2 = i2;
        this.o = o;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().activatePersonalProduction(controller,i1, i2, o);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
