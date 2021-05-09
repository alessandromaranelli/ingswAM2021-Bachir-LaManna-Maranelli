package it.polimi.ingsw.messages.commands.buydevelopmentphase;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.TurnState;
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
    public void processMessage(ClientHandler clientHandler, Controller controller) {
        try {
            controller.getGame().getCurrentPlayer().chooseDevelopmentCard(c, level, slot);

            UpdateDecksMsg updateDecksMsg = new UpdateDecksMsg("You bought the card", controller.getGame().getTable().getTopDevelopmentcards());
            clientHandler.sendAnswerMessage(updateDecksMsg);

            UpdateCardSlotMsg updateCardSlotMsg = new UpdateCardSlotMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getCardSlot().getTopCardofSlot(slot), slot);
            clientHandler.sendAnswerMessage(updateCardSlotMsg);

            CardPriceMsg cp = new CardPriceMsg(TurnState.PAYDEVELOPMENTCARD, controller.getGame().getCurrentPlayer().getPersonalBoard().getCardCost());
            clientHandler.sendAnswerMessage(cp);

            UpdateDecksMsg updateDecksMsg1 = new UpdateDecksMsg(controller.getGame().getCurrentPlayer().getNickname() + " bought a card", controller.getGame().getTable().getTopDevelopmentcards());
            controller.sendAllExcept(updateDecksMsg1, clientHandler);
        } catch (ModelException e) {
            clientHandler.sendAnswerMessage(new ErrorMsg(e.getMessage()));
        }

    }
}
