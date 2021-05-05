package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;
import java.util.Map;

public class ResourcesToOrganizeMsg extends AnswerMsg{
    Map<Resource,Integer> map;

    public ResourcesToOrganizeMsg(Map<Resource, Integer> map) {
        this.map = map;
    }

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}