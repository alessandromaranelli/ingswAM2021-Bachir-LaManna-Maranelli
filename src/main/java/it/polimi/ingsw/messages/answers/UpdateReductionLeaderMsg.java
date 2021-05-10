package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

public class UpdateReductionLeaderMsg extends AnswerMsg{
    private Resource resource;
    private String message;

    public UpdateReductionLeaderMsg(Resource resource){
        this.resource = resource;
        this.message = "Now you have a reduction of 1 " + resource.toString() + " when you buy a development card";
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.addReduction(resource);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
