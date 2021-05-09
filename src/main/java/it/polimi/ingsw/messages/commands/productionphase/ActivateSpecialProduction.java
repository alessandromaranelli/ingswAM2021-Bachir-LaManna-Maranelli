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

public class ActivateSpecialProduction extends CommandMsg {
    private Resource r;
    private int i;

    public ActivateSpecialProduction(Resource r, int i) {
        this.r = r;
        this.i = i;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().activateSpecialProduction(r, i);

            clientHandler.sendAnswerMessage(new UpdateCostsGainsMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalCost(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalGain(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getFaithPoints()));

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " activated a special production");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
