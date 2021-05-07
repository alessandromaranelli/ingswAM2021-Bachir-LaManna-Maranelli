package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageTypesMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.util.ArrayList;

public class SetInitStorageTypeMsg extends CommandMsg {
    private Resource r1;
    private Resource r2;
    private Resource r3;

    public SetInitStorageTypeMsg(Resource r1, Resource r2, Resource r3) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().setInitStorageTypes(r1, r2, r3);

            UpdateStorageTypesMsg updateStorageTypesMsg = new UpdateStorageTypesMsg(TurnState.CHOOSERESOURCES, r1, r2, r3);
            clientHandler.sendAnswerMessage(updateStorageTypesMsg);

            StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " changed his storages type");
            controller.sendAllExcept(stringMsg, clientHandler);
        } catch (ModelException e) {
            ErrorMsg errorMsg = new ErrorMsg(e.getMessage());
            clientHandler.sendAnswerMessage(errorMsg);
        }
    }
}
