package it.polimi.ingsw.messages.commands.productionphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.util.Map;

public class PayProductionFromStorage extends CommandMsg {
    private Resource r;
    private int n; //storage number
    private int i;

    public PayProductionFromStorage(Resource r, int i, int n) {
        this.r = r;
        this.i = i;
        this.n = n;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        if(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().totalCostIsEmpty()){
            ErrorMsg error = new ErrorMsg("You've already payed for this production");
            clientHandler.sendAnswerMessage(error);
        }else {
            try {
                Map<Resource,Integer> gains = controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalGain();
                int faithP = controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getFaithPoints();
                Boolean [] popeF = controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                        stream().map(PopeFavour::isActivated).toArray(Boolean[]::new);
                controller.getGame().getCurrentPlayer().payProductionFromStorage(r,i,n);
                clientHandler.sendAnswerMessage(new CardPriceMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getCardCost()));
                clientHandler.sendAnswerMessage(new UpdateStorageMsg(
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                                stream().map(Storage::getQuantity).toArray(Integer[]::new)));
                clientHandler.sendAnswerMessage(new ChestMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromChest()));
                if(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().totalCostIsEmpty()){
                    clientHandler.sendAnswerMessage(new EndProductionMsg(gains,faithP, popeF));
                }
            } catch (ModelException e) {
                clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
            }
        }
    }
}
