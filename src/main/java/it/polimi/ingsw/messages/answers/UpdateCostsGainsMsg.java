package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;
import java.util.Map;

public class UpdateCostsGainsMsg extends AnswerMsg{
    private Map<Resource, Integer> productionInput;
    private Map<Resource, Integer> productionOutput;
    private int faithPoint;
    private String message;

    public UpdateCostsGainsMsg(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint) {
        this.productionInput = productionInput;
        this.productionOutput = productionOutput;
        this.faithPoint = faithPoint;
        this.message = "Production successfully activated";
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(productionInput, productionOutput, faithPoint);
        /*
        lightModel.setTotalCost(productionInput);
        lightModel.setTotalGain(productionOutput);
        lightModel.setFaithPoints(faithPoint);

         */
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
