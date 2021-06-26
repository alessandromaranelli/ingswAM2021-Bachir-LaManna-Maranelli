package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;
import java.util.Map;

/**
 * The type UpdateResourcesToAddMsg.
 */
public class UpdateResourcesToAddMsg extends AnswerMsg{
    private Map<Resource,Integer> map;
    private String message;
    private TurnState phase;

    /**
     * Instantiates a new Update resources to add msg.
     *
     * @param phase the phase
     * @param map   the map
     */
    public UpdateResourcesToAddMsg(TurnState phase, Map<Resource, Integer> map) {
        this.phase = phase;
        this.map = map;
        this.message="Resources to add to storages from the market: "+map.toString();
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase, map);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
