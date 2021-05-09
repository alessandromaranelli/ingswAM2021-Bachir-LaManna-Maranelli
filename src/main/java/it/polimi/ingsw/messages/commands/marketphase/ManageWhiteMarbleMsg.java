package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateFaithMarkerPositionMsg;
import it.polimi.ingsw.messages.answers.UpdateResourcesToAddMsg;
import it.polimi.ingsw.messages.answers.UpdateWhiteMarblesToManageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ManageWhiteMarbleMsg extends CommandMsg {
    private Resource resource;

    public ManageWhiteMarbleMsg(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException{
        try{
            controller.getGame().getCurrentPlayer().manageWhiteMarbles(resource);

            clientHandler.sendAnswerMessage(new UpdateResourcesToAddMsg(
                    controller.getGame().getCurrentPlayer().getPhase(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));

            if (controller.getGame().getCurrentPlayer().getPhase() == TurnState.WHITEMARBLES){
                clientHandler.sendAnswerMessage(new UpdateWhiteMarblesToManageMsg(
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getManageWhiteMarbles()
                ));
            }
            }catch (ModelException e){
                clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
            }
    }
}
