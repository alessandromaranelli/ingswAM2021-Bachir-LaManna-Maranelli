package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;
import java.util.Map;

/**
 * The type UpdateCostsGainsMsg.
 */
public class UpdateCostsGainsMsg extends AnswerMsg{
    private Map<Resource, Integer> productionInput;
    private Map<Resource, Integer> productionOutput;
    private int faithPoint;
    private String message;

    /**
     * Instantiates a new Update costs gains msg.
     *
     * @param productionInput  the production input
     * @param productionOutput the production output
     * @param faithPoint       the faith point
     */
    public UpdateCostsGainsMsg(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint) {
        this.productionInput = productionInput;
        this.productionOutput = productionOutput;
        this.faithPoint = faithPoint;
        this.message = "Production successfully activated";
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(productionInput, productionOutput, faithPoint);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
