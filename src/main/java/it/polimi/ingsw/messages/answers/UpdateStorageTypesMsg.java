package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;


public class UpdateStorageTypesMsg extends AnswerMsg{
    Resource r1;
    Resource r2;
    Resource r3;
    TurnState phase;
    String message;

    public UpdateStorageTypesMsg(TurnState phase, Resource r1, Resource r2, Resource r3) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.phase = phase;
        this.message = "You changed your storages type to: " + r1.toString() + ", " + r2.toString() + ", " + r3.toString();
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setStorageType(r1, r2, r3);
        lightModel.setPhase(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
