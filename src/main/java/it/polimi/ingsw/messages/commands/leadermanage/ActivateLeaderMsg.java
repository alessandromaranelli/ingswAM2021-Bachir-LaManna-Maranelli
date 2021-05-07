package it.polimi.ingsw.messages.commands.leadermanage;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
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
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException{
        char leaderAct = 0;
        try {
            leaderAct=controller.getGame().getCurrentPlayer().activateLeaderCard(i);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
        if (leaderAct=='s') clientHandler.sendAnswerMessage(new UpdateStorageLeaderMsg());
        else if (leaderAct=='r') clientHandler.sendAnswerMessage(new UpdateReductionLeaderMsg());
        else if (leaderAct=='w') clientHandler.sendAnswerMessage(new UpdateWhiteMarbleLeaderMsg());
        else if (leaderAct=='p') clientHandler.sendAnswerMessage(new UpdateSpecialProductionLeaderMsg());

        clientHandler.sendAnswerMessage(new UpdateLeaderCardsMsg(TurnState.CHOOSERESOURCES,
                controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsInHand(),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsPlayed(),
                "You activated 1 leader card"));
    }
}
