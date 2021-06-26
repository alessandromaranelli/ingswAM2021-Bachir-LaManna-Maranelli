package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateFaithMarkerPositionMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

/**
 * The type AddInitResourcesMsg.
 */
public class AddInitResourcesMsg extends CommandMsg {
    Resource r1;
    Resource r2;

    /**
     * Instantiates a new Add init resources msg.
     *
     * @param r1 the resource 1
     */
    public AddInitResourcesMsg(Resource r1) {
        this.r1 = r1;
        this.r2 = null;
    }

    /**
     * Instantiates a new Add init resources msg.
     *
     * @param r1 the resource 1
     * @param r2 the resource 2
     */
    public AddInitResourcesMsg(Resource r1, Resource r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        if(r2 == null){
            try{
                controller.getGame().getCurrentPlayer().addInitResources(controller,r1);
            }catch (ModelException e){
                clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
            }
        } else {
            try{
                controller.getGame().getCurrentPlayer().addInitResources(controller,r1, r2);
            }catch (ModelException e){
                clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
            }
        }

    }
}
