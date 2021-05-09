package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdatePhaseMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class BuyDevelopmentPhaseMsg extends CommandMsg {

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller){
        try {
            controller.getGame().getCurrentPlayer().selectBuyDevelopmentCardPhase();

            UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.BUYDEVELOPMENTCARDPHASE, "You can select a card");
            clientHandler.sendAnswerMessage(updatePhaseMsg);

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " started BuyDevelopmentCard phase");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
