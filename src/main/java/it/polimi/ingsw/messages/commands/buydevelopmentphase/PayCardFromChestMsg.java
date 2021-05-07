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

    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        try {
            controller.getGame().getCurrentPlayer().payCardFromChest(r, i);
            clientHandler.sendAnswerMessage(new CardPriceMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getCardCost()));
            clientHandler.sendAnswerMessage(new UpdateStorageMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                            stream().map(Storage::getQuantity).toArray(Integer[]::new)));
            clientHandler.sendAnswerMessage(new ChestMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromChest()));
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

