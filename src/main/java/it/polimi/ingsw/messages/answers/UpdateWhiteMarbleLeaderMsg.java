package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

/**
 * The type UpdateWhiteMarbleLeaderMsg.
 */
public class UpdateWhiteMarbleLeaderMsg extends AnswerMsg{
    private Resource resource;
    private String message;

    /**
     * Instantiates a new Update white marble leader msg.
     *
     * @param resource the resource
     */
    public UpdateWhiteMarbleLeaderMsg(Resource resource){
         this.resource = resource;
         this.message = "Now you can transform a white marble in " + resource.toString();
     }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.addWhiteMarble(resource);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
