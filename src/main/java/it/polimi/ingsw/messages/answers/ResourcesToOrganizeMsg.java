package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;
import java.util.Map;

/**
 * The type ResourcesToOrganizeMsg.
 */
public class ResourcesToOrganizeMsg extends AnswerMsg{
    private Map<Resource,Integer> map;
    private String message;
    private TurnState phase;

    /**
     * Instantiates a new Resources to organize msg.
     *
     * @param map   the map
     * @param phase the phase
     */
    public ResourcesToOrganizeMsg(Map<Resource, Integer> map, TurnState phase) {
        this.map = map;
        this.phase=phase;
        this.message="Resources to reput in storages: "+map.toString();
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(map, phase);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}