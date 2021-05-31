package it.polimi.ingsw.messages.commands.productionphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.util.Map;

public class PayProductionFromChest extends CommandMsg {
    private Resource r;
    private int i;

    public PayProductionFromChest(Resource r, int i) {
        this.r = r;
        this.i = i;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().payProductionFromChest(controller,r, i);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

