package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.DevelopmentCard;

public class UpdateCardSlotMsg extends AnswerMsg{
    DevelopmentCard card;
    int slot;
    String message;

    public UpdateCardSlotMsg(DevelopmentCard card, int slot){
        this.card = card;
        this.slot = slot;
        message = "Added card to slot: " + slot;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setDevelopmentCard(card, slot);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
