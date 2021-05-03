package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateCostsGainsMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class ActivateSpecialProduction extends CommandMsg{
    private Resource r;
    private int i;
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        try {
            controller.getGame().getCurrentPlayer().activateSpecialProduction(r,i);
            clientHandler.getOutput().writeObject(new UpdateCostsGainsMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalCost(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getTotalGain(),
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getProduction().getFaithPoints()));
        } catch (ModelException e) {
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
    }
}
