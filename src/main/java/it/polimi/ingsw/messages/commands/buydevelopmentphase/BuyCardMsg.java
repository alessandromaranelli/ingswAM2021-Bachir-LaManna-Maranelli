package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

/**
 * The type BuyCardMsg.
 */
public class BuyCardMsg extends CommandMsg {
    private Color c;
    private int level;
    private int slot;

    /**
     * Instantiates a new Buy card msg.
     *
     * @param c     the color
     * @param level the level
     * @param slot  the slot
     */
    public BuyCardMsg(Color c, int level, int slot) {
        this.c = c;
        this.level = level;
        this.slot = slot;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().chooseDevelopmentCard(controller, c, level, slot);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }

    }
}
