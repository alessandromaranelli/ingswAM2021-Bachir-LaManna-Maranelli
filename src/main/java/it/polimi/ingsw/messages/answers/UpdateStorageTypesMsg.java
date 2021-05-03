package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;
import it.polimi.ingsw.model.Resource;

import java.io.IOException;

public class UpdateStorageTypesMsg extends AnswerMsg{
    Resource r1;
    Resource r2;
    Resource r3;

    public UpdateStorageTypesMsg(Resource r1, Resource r2, Resource r3) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
