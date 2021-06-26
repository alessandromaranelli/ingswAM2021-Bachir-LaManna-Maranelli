package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.util.HashMap;
import java.util.Map;

/**
 * The type EndProductionMsg.
 */
public class EndProductionMsg extends AnswerMsg {
    private TurnState phase;
    private Map<Resource, Integer> chest;
    private int position;
    private Boolean[] popeFavours;
    private String message;

    /**
     * Instantiates a new End production msg.
     *
     * @param phase       the phase
     * @param chest       the chest
     * @param position    the position
     * @param popeFavours the pope favours
     */
    public EndProductionMsg(TurnState phase, Map<Resource, Integer> chest, int position, Boolean[] popeFavours) {
        this.phase = phase;
        this.chest = chest;
        this.position = position;
        this.popeFavours = popeFavours;
        this.message = "You paid all productions";
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase, chest, position, popeFavours);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
