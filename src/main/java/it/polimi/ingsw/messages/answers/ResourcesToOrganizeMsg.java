package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;
import java.util.Map;

public class ResourcesToOrganizeMsg extends AnswerMsg{
    private Map<Resource,Integer> map;
    private String message="Resources to reput in storages: "+map.toString();
    private TurnState phase;

    public ResourcesToOrganizeMsg(Map<Resource, Integer> map, TurnState phase) {
        this.map = map;
        this.phase=phase;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setResourcesToOrganize(map);
        lightModel.setPhase(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}