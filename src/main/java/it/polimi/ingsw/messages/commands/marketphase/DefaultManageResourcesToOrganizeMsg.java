package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class DefaultManageResourcesToOrganizeMsg extends CommandMsg {
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        try{
            controller.getGame().getCurrentPlayer().defaultManageResourcesToOrganize();
        }catch (ModelException e){
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateStorageMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                            stream().map(Storage::getType).toArray(Integer[]::new)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
