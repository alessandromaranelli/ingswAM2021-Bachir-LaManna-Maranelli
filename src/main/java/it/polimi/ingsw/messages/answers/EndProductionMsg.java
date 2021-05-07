package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.util.Map;

public class EndProductionMsg extends AnswerMsg {
    Map<Resource, Integer> gains; int faithP; Boolean[] popeF;

    public EndProductionMsg(Map<Resource, Integer> gains, int faithP, Boolean[] popeF) {
        this.gains = gains;
        this.faithP = faithP;
        this.popeF = popeF;
    }

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}
