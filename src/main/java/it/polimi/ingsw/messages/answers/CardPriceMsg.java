package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;
import java.util.Map;

public class CardPriceMsg extends AnswerMsg {
    private Map<Resource,Integer> price;
    private TurnState phase;

    public CardPriceMsg(TurnState phase, Map<Resource, Integer> price) {
        this.phase = phase;
        this.price = price;
    }

    @Override
    public void processMessage(LightModel lightModel){
        lightModel.updateCardPrice(phase, price);
        /*
        lightModel.setPhase(phase);
        lightModel.setCardCost(price);

         */
    }

    @Override
    public void printMessage() {
        System.out.println("It remains to pay: " + price.get(Resource.STONE) + " stones, " + price.get(Resource.SHIELD) + " shields, " + price.get(Resource.COIN) + " coins, " + price.get(Resource.SERVANT) + " servants");

    }
}
