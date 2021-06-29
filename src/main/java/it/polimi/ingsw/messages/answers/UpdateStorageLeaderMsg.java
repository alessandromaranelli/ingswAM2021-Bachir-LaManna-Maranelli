package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

/**
 * The type UpdateStorageLeaderMsg.
 */
public class UpdateStorageLeaderMsg extends AnswerMsg{
    private Resource resource;
    private String message;

    /**
     * Instantiates a new Update storage leader msg.
     *
     * @param resource the resource
     */
    public UpdateStorageLeaderMsg(Resource resource){
      this.resource = resource;
      this.message = "You added a leader storage with type: " + resource.toString();
    }
    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.addLeaderStorage(resource);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
