package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;
import java.util.Map;

public class UpdateCostsGainsMsg extends AnswerMsg{
    private Map<Resource, Integer> productionInput;
    private Map<Resource, Integer> productionOutput;
    private int faithPoint;

    public UpdateCostsGainsMsg(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint) {
        this.productionInput = productionInput;
        this.productionOutput = productionOutput;
        this.faithPoint = faithPoint;
    }

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
