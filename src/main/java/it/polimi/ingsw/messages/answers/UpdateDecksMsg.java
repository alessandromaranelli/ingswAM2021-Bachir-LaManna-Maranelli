package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.DevelopmentCard;

import java.util.List;

/**
 * The type UpdateDecksMsg.
 */
public class UpdateDecksMsg extends AnswerMsg{
    private List<DevelopmentCard> developmentCards;
    private String message;

    /**
     * Instantiates a new Update decks msg.
     *
     * @param message the message
     * @param cards   the cards
     */
    public UpdateDecksMsg(String message, List<DevelopmentCard> cards){
        this.developmentCards = cards;
        this.message = message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(developmentCards);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
