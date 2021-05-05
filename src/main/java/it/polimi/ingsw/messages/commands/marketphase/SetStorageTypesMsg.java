package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateLeaderCardsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class SetStorageTypesMsg extends CommandMsg {
    Resource r1;
    Resource r2;
    Resource r3;
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException{
        try{
            controller.getGame().getCurrentPlayer().setStoragesTypes(r1,r2,r3);
        }catch (ModelException e){
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        /*try{
            clientHandler.getOutput().writeObject(new UpdateStorageTypesMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getStorages().
                            stream().map(Storage::getType).toArray(Integer[]::new)));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
