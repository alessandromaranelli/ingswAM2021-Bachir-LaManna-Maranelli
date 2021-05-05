package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class StartMarketPhaseMsg extends CommandMsg {
    int dim;
    boolean row;

    public StartMarketPhaseMsg(int dim, boolean row) {
        this.dim = dim;
        this.row = row;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException{
        try{
            controller.getGame().getCurrentPlayer().startMarketPhase(dim,row);
        }catch (ModelException e){
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
        try{
            controller.sendAll(new UpdateMarketMsg(controller.getGame().getTable().getMarket()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientHandler.sendAnswerMessage(new UpdateResourcesToAddMsg(
                controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));
        clientHandler.sendAnswerMessage(new UpdateFaithMarkerPositionMsg(
                controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getTrack().indexOf(
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                        stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
        if (controller.getGame().getCurrentPlayer().getPhase()== TurnState.WHITEMARBLES){
            clientHandler.sendAnswerMessage(new UpdateWhiteMarblesToManageMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getManageWhiteMarbles()
            ));
        }


    }
}
