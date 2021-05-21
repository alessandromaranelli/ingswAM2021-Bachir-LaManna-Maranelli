package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.DevelopmentCard;

import java.util.List;

public class UpdateDecksMsg extends AnswerMsg{
    private List<DevelopmentCard> developmentCards;
    private String message;

    public UpdateDecksMsg(String message, List<DevelopmentCard> cards){
        this.developmentCards = cards;
        this.message = message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(developmentCards);
        /*
        lightModel.setDevelopmentCardsToBuy(developmentCards);
        lightModel.getDevelopmentCardToBuyVisualizer().plot(developmentCards);

         */
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
