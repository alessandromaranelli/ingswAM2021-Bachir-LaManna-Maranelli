package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class UpdateStorageMsg extends AnswerMsg{
    Integer[] storages;

    public UpdateStorageMsg(Integer[] storages) {
        this.storages = storages;
    }

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}
