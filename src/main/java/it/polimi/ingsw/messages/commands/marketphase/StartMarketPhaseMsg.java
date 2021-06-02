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
        try{
            controller.getGame().getCurrentPlayer().startMarketPhase(controller,dim,row);

        }catch (ModelException e){
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }
    }
}
