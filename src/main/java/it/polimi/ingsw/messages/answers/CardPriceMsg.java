package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;
import java.util.Map;

public class CardPriceMsg extends AnswerMsg
{
    private Map<Resource,Integer> price;

    public CardPriceMsg(Map<Resource, Integer> price) {
        this.price = price;
    }

    @Override
    public void processMessage(LightModel lightModel){
        //rispondi nella view
    }

    @Override
    public void printMessage() {

    }
}
