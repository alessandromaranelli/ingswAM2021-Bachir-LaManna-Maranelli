package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;
import java.util.Map;

public class StorageMsg extends AnswerMsg{
    private Map<Resource,Integer> storages;
    public StorageMsg(Map<Resource, Integer> mapfromAllStorages) {
        storages = mapfromAllStorages;
    }

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}
