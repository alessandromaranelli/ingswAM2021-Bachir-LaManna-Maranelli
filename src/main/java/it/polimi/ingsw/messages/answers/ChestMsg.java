package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;
import java.util.Map;

public class ChestMsg extends AnswerMsg{
    private Map<Resource,Integer> m;
    public ChestMsg(Map<Resource, Integer> mapFromChest) {
        m = mapFromChest;
    }

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}
