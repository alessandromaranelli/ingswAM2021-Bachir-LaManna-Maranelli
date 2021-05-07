package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateStorageTypesMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class SetStorageTypesMsg extends CommandMsg {
    Resource r1;
    Resource r2;
    Resource r3;

    public SetStorageTypesMsg(Resource r1, Resource r2, Resource r3) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException{
        try{
            controller.getGame().getCurrentPlayer().setStoragesTypes(r1,r2,r3);
        }catch (ModelException e){
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
        try{
            clientHandler.sendAnswerMessage(new UpdateStorageTypesMsg(controller.getGame().getCurrentPlayer().getPhase(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getTypeStorage(1),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getTypeStorage(2),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getTypeStorage(3)));
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
}
