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

public class DiscardLeadersAtTheStartMsg extends CommandMsg {
    int leader1;
    int leader2;

    public DiscardLeadersAtTheStartMsg(int leader1, int leader2) {
        this.leader1 = leader1;
        this.leader2 = leader2;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller){
        try {
            controller.getGame().getCurrentPlayer().chooseLeaderCardsToDiscard(leader1, leader2);

            UpdateLeaderCardsMsg updateLeaderCardsMsg = new UpdateLeaderCardsMsg(TurnState.CHOOSERESOURCES,
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsInHand(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsPlayed(),
                    "You discarded 2 leader cards");
            clientHandler.sendAnswerMessage(updateLeaderCardsMsg);

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " discarded 2 leader cards");
            controller.sendAllExcept(stringMsg,clientHandler);
        } catch (ModelException e) {
            ErrorMsg errorMsg = new ErrorMsg(e.getMessage());
            clientHandler.sendAnswerMessage(errorMsg);
        }

    }
}
