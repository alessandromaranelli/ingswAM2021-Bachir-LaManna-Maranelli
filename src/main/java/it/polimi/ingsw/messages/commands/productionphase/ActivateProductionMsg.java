package it.polimi.ingsw.messages.commands.productionphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateCostsGainsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ActivateProductionMsg extends CommandMsg {
    private int slot;

    public ActivateProductionMsg(int slot) {
        this.slot = slot;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().activateProductionOfSlot(slot);

            clientHandler.sendAnswerMessage(new UpdateCostsGainsMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalCost(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalGain(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getFaithPoints()));

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " activated a production");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
