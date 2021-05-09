package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.DevelopmentCard;

import java.util.List;

public class UpdateDecksMsg extends AnswerMsg{
    private List<DevelopmentCard> developmentCards;
    String message;

    public UpdateDecksMsg(String message, List<DevelopmentCard> cards){
        this.developmentCards = cards;
        this.message = message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setDevelopmentCardsToBuy(developmentCards);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
