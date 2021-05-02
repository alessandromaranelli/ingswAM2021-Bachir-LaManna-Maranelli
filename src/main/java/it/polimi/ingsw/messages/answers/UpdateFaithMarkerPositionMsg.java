package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;

import java.io.IOException;

public class UpdateFaithMarkerPositionMsg extends AnswerMsg{
    int position;
    Boolean[] popeFavours;

    public UpdateFaithMarkerPositionMsg(int position, Boolean[] popeFavours) {
        this.position = position;
        this.popeFavours = popeFavours;
    }

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
