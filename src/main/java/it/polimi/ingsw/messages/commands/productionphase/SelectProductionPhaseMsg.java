package it.polimi.ingsw.messages.commands.productionphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdatePhaseMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class SelectProductionPhaseMsg extends CommandMsg {
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().selectProductionPhase();

            UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.PRODUCTIONPHASE, "You can activate productions");
            clientHandler.sendAnswerMessage(updatePhaseMsg);

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " started Production phase");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
