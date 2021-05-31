package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageTypesMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class SetStorageTypesMsg extends CommandMsg {
    private Resource r1;
    private Resource r2;
    private Resource r3;

    public SetStorageTypesMsg(Resource r1, Resource r2, Resource r3) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try{
            controller.getGame().getCurrentPlayer().setStoragesTypes(controller,r1,r2,r3);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
