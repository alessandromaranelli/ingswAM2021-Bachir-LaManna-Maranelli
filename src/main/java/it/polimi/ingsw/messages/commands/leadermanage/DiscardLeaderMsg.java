package it.polimi.ingsw.messages.commands.leadermanage;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateFaithMarkerPositionMsg;
import it.polimi.ingsw.messages.answers.UpdateLeaderCardsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class DiscardLeaderMsg extends CommandMsg {
    int i;

    public DiscardLeaderMsg(int i) {
        this.i = i;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().discardLeaderCard(i);

            clientHandler.sendAnswerMessage(new UpdateFaithMarkerPositionMsg(controller.getGame().getCurrentPlayer().getPhase(),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getTrack().indexOf(
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                        stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));

            clientHandler.sendAnswerMessage(new UpdateLeaderCardsMsg(controller.getGame().getCurrentPlayer().getPhase(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsInHand(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsPlayed(),
                    "LeaderCard successfully discarded"));

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " discarded a leader card");
            controller.sendAllExcept(stringMsg, clientHandler);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
