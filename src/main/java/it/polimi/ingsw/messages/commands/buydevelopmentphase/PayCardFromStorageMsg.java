package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.CardPriceMsg;
import it.polimi.ingsw.messages.answers.ChestMsg;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class PayCardFromStorageMsg extends CommandMsg {
    private Resource r;
    private int n; //storage number
    private int i;

    public PayCardFromStorageMsg(Resource r, int i, int n) {
        this.r = r;
        this.i = i;
        this.n = n;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        if(controller.getGame().getCurrentPlayer().getPersonalBoard().isCardPayed()){
            ErrorMsg error = new ErrorMsg("You've already payed for this card");
            clientHandler.sendAnswerMessage(error);
        }else {
            try {
                controller.getGame().getCurrentPlayer().payCardFromStorage(r, n, i);
                clientHandler.sendAnswerMessage(new CardPriceMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getCardCost()));
                clientHandler.sendAnswerMessage(new StorageMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromAllStorages()));
                clientHandler.sendAnswerMessage(new ChestMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromChest()));
            } catch (ModelException e) {
                clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
            }
        }
    }
}
