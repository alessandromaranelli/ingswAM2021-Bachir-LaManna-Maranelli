package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.DevelopmentCard;

/**
 * The type UpdateCardSlotMsg.
 */
public class UpdateCardSlotMsg extends AnswerMsg{
    private DevelopmentCard card;
    private int slot;
    private String message;

    /**
     * Instantiates a new UpdateCardSlotMsg.
     *
     * @param card the card
     * @param slot the slot
     */
    public UpdateCardSlotMsg(DevelopmentCard card, int slot){
        this.card = card;
        this.slot = slot;
        message = "\nAdded card to slot: " + slot;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(card, slot);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
