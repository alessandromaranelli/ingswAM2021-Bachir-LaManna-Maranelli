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
    Resource resource;

    public ManageWhiteMarbleMsg(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException{
        try{
            controller.getGame().getCurrentPlayer().manageWhiteMarbles(resource);
        }catch (ModelException e){
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateResourcesToAddMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));
            clientHandler.getOutput().writeObject(new UpdateFaithMarkerPositionMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getTrack().indexOf(
                            controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                            stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
            if (controller.getGame().getCurrentPlayer().getPhase()== TurnState.WHITEMARBLES){
                clientHandler.getOutput().writeObject(new UpdateWhiteMarblesToManageMsg(
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getManageWhiteMarbles()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
