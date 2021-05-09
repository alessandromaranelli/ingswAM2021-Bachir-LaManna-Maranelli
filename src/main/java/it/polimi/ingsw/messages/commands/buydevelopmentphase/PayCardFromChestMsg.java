package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class PayCardFromChestMsg extends CommandMsg {
    private Resource r;
    private int i;

    public PayCardFromChestMsg(Resource r, int i) {
        this.r = r;
        this.i = i;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().payCardFromChest(r, i);

            CardPriceMsg cardPriceMsg = new CardPriceMsg(controller.getGame().getCurrentPlayer().getPhase(), controller.getGame().getCurrentPlayer().getPersonalBoard().getCardCost());
            clientHandler.sendAnswerMessage(cardPriceMsg);

            ChestMsg chestMsg = new ChestMsg(controller.getGame().getCurrentPlayer().getPhase(), controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromChest());
            clientHandler.sendAnswerMessage(chestMsg);

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " paid card from chest");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

