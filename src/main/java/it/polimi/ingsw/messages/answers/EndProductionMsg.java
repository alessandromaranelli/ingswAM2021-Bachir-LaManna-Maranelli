package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.util.HashMap;
import java.util.Map;

public class EndProductionMsg extends AnswerMsg {
    private TurnState phase;
    private Map<Resource, Integer> chest;
    private int position;
    private Boolean[] popeFavours;
    private String message;

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
        /*
        lightModel.setPhase(phase);
        lightModel.setChest(chest);
        lightModel.setPosition(position);
        lightModel.setPopeFavours(popeFavours);
        lightModel.setFaithPoints(0);

        Map<Resource, Integer> totalGain = new HashMap<>();
        totalGain.put(Resource.SERVANT, 0);
        totalGain.put(Resource.COIN, 0);
        totalGain.put(Resource.SHIELD, 0);
        totalGain.put(Resource.STONE, 0);
        lightModel.setTotalGain(totalGain);

         */
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
