package it.polimi.ingsw.messages.commands.leadermanage;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ActivateLeaderMsg extends CommandMsg {
    int i;

    public ActivateLeaderMsg(int i) {
        this.i = i;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            char leaderAct = controller.getGame().getCurrentPlayer().activateLeaderCard(i);

            if (leaderAct=='s') clientHandler.sendAnswerMessage(new UpdateStorageLeaderMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getTypeStorage(4)));
            else if (leaderAct=='r') clientHandler.sendAnswerMessage(new UpdateReductionLeaderMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getLastReduction()));
            else if (leaderAct=='w') clientHandler.sendAnswerMessage(new UpdateWhiteMarbleLeaderMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getLastWhiteMarble()));
            else if (leaderAct=='p') clientHandler.sendAnswerMessage(new UpdateSpecialProductionLeaderMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTypeOfSpecialProduction(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().numOfSpecialProduction())));

            clientHandler.sendAnswerMessage(new UpdateLeaderCardsMsg(controller.getGame().getCurrentPlayer().getPhase(),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsInHand(),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsPlayed(),
                "LeaderCard successfully activated"));

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " activated a leader card");
            controller.sendAllExcept(stringMsg, clientHandler);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
