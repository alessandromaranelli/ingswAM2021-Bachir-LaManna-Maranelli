package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;

import java.io.IOException;

public class UpdateStorageMsg extends AnswerMsg{
    Integer[] storages;

    public UpdateStorageMsg(Integer[] storages) {
        this.storages = storages;
    }

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
