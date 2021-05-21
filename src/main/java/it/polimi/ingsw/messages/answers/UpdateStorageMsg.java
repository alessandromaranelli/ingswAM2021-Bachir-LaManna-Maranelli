package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class UpdateStorageMsg extends AnswerMsg{
    private Integer[] storages;
    private TurnState phase;

    public UpdateStorageMsg(TurnState phase, Integer[] storages) {
        this.storages = storages;
        this.phase = phase;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase, storages);
        /*
        lightModel.setPhase(phase);
        if(storages.length == 3){
            lightModel.setStorageQuantity(storages[0], storages[1], storages[2]);
        }
        else if(storages.length == 4){
            lightModel.setStorageQuantity(storages[0], storages[1], storages[2], storages[3]);
        }
        else lightModel.setStorageQuantity(storages[0], storages[1], storages[2], storages[3], storages[4]);
         */
    }

    @Override
    public void printMessage() {
        if(storages.length == 3){
            System.out.println("Now you have: " + storages[0] + " in first storage, " + storages[1] + " in second storage, " + storages[2] + " in third storage");
        }
        else if(storages.length == 4){
            System.out.println("Now you have: " + storages[0] + " in first storage, " + storages[1] + " in second storage, " + storages[2] + " in third storage" + storages[3] + " in fourth storage");
        }
        else{
            System.out.println("Now you have: " + storages[0] + " in first storage, " + storages[1] + " in second storage, " + storages[2] + " in third storage" + storages[3] + " in fourth storage" + storages[4] + " in fifth storage");
        }
    }
}
