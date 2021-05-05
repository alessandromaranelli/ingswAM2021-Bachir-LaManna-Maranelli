package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.CardPriceMsg;
import it.polimi.ingsw.messages.answers.ChestMsg;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StorageMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class PayFromChestMsg extends CommandMsg{
    private Resource r;
    private int i;

    public PayFromChestMsg(Resource r, int i) {
        this.r = r;
        this.i = i;
    }

    @Override

    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        try {
            controller.getGame().getCurrentPlayer().payCardFromChest(r, i);
            clientHandler.sendAnswerMessage(new CardPriceMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getCardCost()));
            clientHandler.sendAnswerMessage(new StorageMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromAllStorages()));
            clientHandler.sendAnswerMessage(new ChestMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromChest()));
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

