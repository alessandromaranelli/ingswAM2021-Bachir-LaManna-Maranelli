package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageTypesMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The type SetInitStorageTypeMsg.
 */
public class SetInitStorageTypeMsg extends CommandMsg {
    private Resource r1;
    private Resource r2;
    private Resource r3;

    /**
     * Instantiates a new Set init storage type msg.
     *
     * @param r1 the resource 1
     * @param r2 the resource 2
     * @param r3 the resource 3
     */
    public SetInitStorageTypeMsg(Resource r1, Resource r2, Resource r3) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().setInitStorageTypes(controller, r1, r2, r3);

            if (controller.getGame().getPlayers().stream().allMatch(Player::isInitPhaseDone)) {
                controller.getGame().setCurrentPlayer(controller.getGame().getPlayers().get(0));
            }

        } catch (ModelException e) {
            ErrorMsg errorMsg = new ErrorMsg(e.getMessage());
            clientHandler.sendAnswerMessage(errorMsg);
        }
    }
}
