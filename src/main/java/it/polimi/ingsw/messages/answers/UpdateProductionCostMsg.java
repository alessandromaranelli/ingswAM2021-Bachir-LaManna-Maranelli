package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.util.Map;

/**
 * The type UpdateProductionCostMsg.
 */
public class UpdateProductionCostMsg extends AnswerMsg{
    private Map<Resource, Integer> totalCost;
    private String message;

    /**
     * Instantiates a new Update production cost msg.
     *
     * @param totalCost the total cost
     */
    public UpdateProductionCostMsg(Map<Resource, Integer> totalCost){
        this.totalCost = totalCost;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(totalCost);

    }

    @Override
    public void printMessage() {
        System.out.println("It remains to pay: " + totalCost.get(Resource.STONE) + " stones, " + totalCost.get(Resource.SHIELD) + " shields, " + totalCost.get(Resource.COIN) + " coins, " + totalCost.get(Resource.SERVANT) + " servants");
    }
}
