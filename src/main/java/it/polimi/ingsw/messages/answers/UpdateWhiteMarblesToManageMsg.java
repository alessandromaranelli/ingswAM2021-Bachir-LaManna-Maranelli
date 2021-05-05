package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;

import java.io.IOException;

public class UpdateWhiteMarblesToManageMsg extends AnswerMsg{
    int whiteMarbles;

    public UpdateWhiteMarblesToManageMsg(int whiteMarbles) {
        this.whiteMarbles = whiteMarbles;
    }

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}
