package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateFaithMarkerPositionMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class AddInitResourcesMsg extends CommandMsg {
    Resource r1;
    Resource r2;

    public AddInitResourcesMsg(Resource r1) {
        this.r1 = r1;
        this.r2 = null;
    }

    public AddInitResourcesMsg(Resource r1, Resource r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException{
        if(r2 == null){
            try{
                controller.getGame().getCurrentPlayer().addInitResources(r1);
            }catch (ModelException e){
                clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
            }
        } else {
            try{
                controller.getGame().getCurrentPlayer().addInitResources(r1, r2);
            }catch (ModelException e){
                clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
            }
        }

        clientHandler.sendAnswerMessage(new UpdateStorageMsg(TurnState.ENDPREPARATION,
                controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));

        clientHandler.sendAnswerMessage(new UpdateFaithMarkerPositionMsg(TurnState.ENDPREPARATION,
                controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getTrack().indexOf(
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                        stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));

        StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " added some initial resources");
        controller.sendAllExcept(stringMsg, clientHandler);
    }
}
