package it.polimi.ingsw.messages.commands.marketphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.PopeFavour;
import it.polimi.ingsw.model.RedMarble;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;
import java.util.ArrayList;

public class StartMarketPhaseMsg extends CommandMsg {
    private int dim;
    private boolean row;

    public StartMarketPhaseMsg(int dim, boolean row) {
        this.dim = dim;
        this.row = row;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        ArrayList<Marble> marbles=new ArrayList<>();
        try{
            marbles= controller.getGame().getCurrentPlayer().startMarketPhase(dim,row);


            clientHandler.sendAnswerMessage(new UpdateResourcesToAddMsg(
                    controller.getGame().getCurrentPlayer().getPhase(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getWareHouse().getResourcesToAdd()));
            for(Marble m: marbles){
                if(m instanceof RedMarble) {
                    clientHandler.sendAnswerMessage(new UpdateFaithMarkerPositionMsg(controller.getGame().getCurrentPlayer().getPhase(),
                            controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getTrack().indexOf(
                                    controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                            controller.getGame().getCurrentPlayer().getPersonalBoard().getFaithTrack().getPopeFavours().
                                    stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
                    break;
                }
            }
            if (controller.getGame().getCurrentPlayer().getPhase()== TurnState.WHITEMARBLES){
                clientHandler.sendAnswerMessage(new UpdateWhiteMarblesToManageMsg(
                        controller.getGame().getCurrentPlayer().getPersonalBoard().getManageWhiteMarbles()
                ));

                StringMsg stringMsg = new StringMsg(controller.getGame().getCurrentPlayer().getNickname() + " picked "+marbles.size()+" from the market");
                controller.sendAllExcept(stringMsg, clientHandler);

                controller.sendAll(new UpdateMarketMsg(controller.getGame().getTable().getMarket()));
            }
        }catch (ModelException e){
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
