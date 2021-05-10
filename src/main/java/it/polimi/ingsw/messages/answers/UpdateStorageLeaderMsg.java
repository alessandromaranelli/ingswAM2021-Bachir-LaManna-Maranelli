package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

public class UpdateStorageLeaderMsg extends AnswerMsg{
    private Resource resource;
    private String message;

    public UpdateStorageLeaderMsg(Resource resource){
      this.resource = resource;
      this.message = "You added a leader storage whith type: " + resource.toString();
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
