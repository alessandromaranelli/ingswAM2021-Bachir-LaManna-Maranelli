package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.ResourcesToOrganizeMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ManageResourcesToOrganizeMsg extends CommandMsg {
    private Resource r;
    private int storageNumber;
    private int quantity;


    public ManageResourcesToOrganizeMsg(Resource r, int storageNumber, int quantity) {
        this.r = r;
        this.storageNumber = storageNumber;
        this.quantity = quantity;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().manageResourcesToOrganize(controller,r,storageNumber,quantity);

        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }

    }
}