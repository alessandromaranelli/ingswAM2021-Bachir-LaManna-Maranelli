package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

/**
 * The type PayCardFromStorageMsg.
 */
public class PayCardFromStorageMsg extends CommandMsg {
    private Resource r;
    private int n;
    private int i;      //storage number

    /**
     * Instantiates a new Pay card from storage msg.
     *
     * @param r the resource
     * @param i the storage number
     * @param n the quantity
     */
    public PayCardFromStorageMsg(Resource r, int i, int n) {
        this.r = r;
        this.i = i;
        this.n = n;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().payCardFromStorage(controller,r, n, i);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

