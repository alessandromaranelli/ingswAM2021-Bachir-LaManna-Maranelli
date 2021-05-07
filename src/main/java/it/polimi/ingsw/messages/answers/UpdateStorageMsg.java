package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class UpdateStorageMsg extends AnswerMsg{
    Integer[] storages;
    String message;
    TurnState phase;

    public UpdateStorageMsg(TurnState phase, Integer[] storages) {
        this.storages = storages;
        this.message = "Now you have: " + storages[0] + " in first storage, " + storages[1] + " in second storage, " + storages[2] +" in third storage";
        this.phase = phase;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setStorageQuantity(storages[0], storages[1], storages[2]);
        lightModel.setPhase(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
