package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateLeaderCardsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

/**
 * The type DiscardLeadersAtTheStartMsg.
 */
public class DiscardLeadersAtTheStartMsg extends CommandMsg {
    int leader1;
    int leader2;

    /**
     * Instantiates a new Discard leaders at the start msg.
     *
     * @param leader1 the leader 1
     * @param leader2 the leader 2
     */
    public DiscardLeadersAtTheStartMsg(int leader1, int leader2) {
        this.leader1 = leader1;
        this.leader2 = leader2;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller){
        try {
            controller.getGame().getCurrentPlayer().chooseLeaderCardsToDiscard(leader1, leader2, controller);
        } catch (ModelException e) {
            ErrorMsg errorMsg = new ErrorMsg(e.getMessage());
            clientHandler.sendAnswerMessage(errorMsg);
        }

    }
}
