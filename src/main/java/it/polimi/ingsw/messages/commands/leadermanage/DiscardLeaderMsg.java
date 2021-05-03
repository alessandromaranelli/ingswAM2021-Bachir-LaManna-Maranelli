package it.polimi.ingsw.messages.commands.leadermanage;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateFaithMarkerPositionMsg;
import it.polimi.ingsw.messages.answers.UpdateResourcesToAddMsg;
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
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        try {
            controller.getGame().getCurrentPlayer().activateLeaderCard(i);
        } catch (ModelException e) {
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateFaithMarkerPositionMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getTrack().indexOf(
                            controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                            stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
