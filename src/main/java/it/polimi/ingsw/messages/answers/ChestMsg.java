package it.polimi.ingsw.messages.answers;


import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.util.*;

/**
 * The type ChestMsg.
 */
public class ChestMsg extends AnswerMsg{
    private Map<Resource,Integer> m;
    private TurnState phase;

    /**
     * Instantiates a new Chest msg.
     *
     * @param phase        the phase
     * @param mapFromChest the map from chest
     */
    public ChestMsg(TurnState phase, Map<Resource, Integer> mapFromChest) {
        this.phase = phase;
        this.m = mapFromChest;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.updateChest(phase, m);

    }

    @Override
    public void printMessage() {
        System.out.println("Now you have: " + m.get(Resource.STONE) + " stones, " + m.get(Resource.SHIELD) + " shields, " + m.get(Resource.COIN) + " coins, " + m.get(Resource.SERVANT) + " servants");
    }
}
