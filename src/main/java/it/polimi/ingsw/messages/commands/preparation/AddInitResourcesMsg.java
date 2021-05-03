package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateFaithMarkerPositionMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class AddInitResourcesMsg extends CommandMsg {
    Resource r1;
    Resource r2;

    public AddInitResourcesMsg(Resource r1) {
        this.r1 = r1;
        this.r2=null;
    }

    public AddInitResourcesMsg(Resource r1, Resource r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        if(r2==null){
            try{
                controller.getGame().getCurrentPlayer().addInitResources(r1);
            }catch (ModelException e){
                clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
            }
        } else {
            try{
                controller.getGame().getCurrentPlayer().addInitResources(r1,r2);
            }catch (ModelException e){
                clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
            }
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateStorageMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                            stream().map(Storage::getQuantity).toArray(Integer[]::new)));
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
