package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateLeaderCardsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

/**
 * The type DrawLeadersMsg.
 */
public class DrawLeadersMsg extends CommandMsg {

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller){
        try {
            controller.getGame().getCurrentPlayer().drawLeaderCards(controller);
        } catch (ModelException e) {
            ErrorMsg errorMsg = new ErrorMsg(e.getMessage());
            clientHandler.sendAnswerMessage(errorMsg);
        }
    }
}
