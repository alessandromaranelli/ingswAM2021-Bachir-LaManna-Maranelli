package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class PayCardFromStorageMsg extends CommandMsg {
    private Resource r;
    private int n;
    private int i;      //storage number

    public PayCardFromStorageMsg(Resource r, int i, int n) {
        this.r = r;
        this.i = i;
        this.n = n;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().payCardFromStorage(r, n, i);

            CardPriceMsg cardPriceMsg = new CardPriceMsg(controller.getGame().getCurrentPlayer().getPhase(), controller.getGame().getCurrentPlayer().getPersonalBoard().getCardCost());
            clientHandler.sendAnswerMessage(cardPriceMsg);

            UpdateStorageMsg updateStorageMsg = new UpdateStorageMsg(controller.getGame().getCurrentPlayer().getPhase(), controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromAllStorages().values().toArray(new Integer[3]));
            clientHandler.sendAnswerMessage(updateStorageMsg);

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " paid card from storage");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

