package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

/**
 * The type UpdateSpecialProductionLeaderMsg.
 */
public class UpdateSpecialProductionLeaderMsg extends AnswerMsg{
    private Resource resource;
    private String message;

    /**
     * Instantiates a new Update special production leader msg.
     *
     * @param resource the resource
     */
    public UpdateSpecialProductionLeaderMsg(Resource resource){
        this.resource = resource;
        this.message = "Now you have a special production that costs 1 " + resource.toString();
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.addSpecialProduction(resource);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
