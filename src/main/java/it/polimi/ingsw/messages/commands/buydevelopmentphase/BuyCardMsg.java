package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.CardPriceMsg;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class BuyCardMsg extends CommandMsg {
    private Color c;
    private int level;
    private int slot;

    public BuyCardMsg(Color c, int level, int slot) {
        this.c = c;
        this.level = level;
        this.slot = slot;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        try {
            controller.getGame().getCurrentPlayer().chooseDevelopmentCard(c,level,slot);
            CardPriceMsg cp = new CardPriceMsg(controller.getGame().getTable().viewDevelopmentCard(c,level).getRequirements());
            clientHandler.sendAnswerMessage(cp);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }

    }
}
