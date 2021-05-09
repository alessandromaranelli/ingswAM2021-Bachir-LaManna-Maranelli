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
            controller.getGame().getCurrentPlayer().payProductionFromChest(r, i);

            UpdateProductionCostMsg updateProductionCostMsg = new UpdateProductionCostMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalCost());
            clientHandler.sendAnswerMessage(updateProductionCostMsg);

            if(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().totalCostIsEmpty()){
                EndProductionMsg endProductionMsg = new EndProductionMsg(TurnState.ENDTURN, controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromChest(),
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getTrack().indexOf(
                                controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                                stream().map(PopeFavour::isActivated).toArray(Boolean[]::new));
                clientHandler.sendAnswerMessage(endProductionMsg);

                StringMsg stringMsg1 = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " ended production phase");
                controller.sendAllExcept(stringMsg1, clientHandler);
            }
            else{
                ChestMsg chestMsg = new ChestMsg(TurnState.PAYPRODUCTIONS, controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getMapfromChest());
                clientHandler.sendAnswerMessage(chestMsg);

                StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " paid productions from chest");
                controller.sendAllExcept(stringMsg, clientHandler);
            }
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}

