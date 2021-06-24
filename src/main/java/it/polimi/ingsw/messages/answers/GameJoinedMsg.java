package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class GameJoinedMsg extends AnswerMsg{
    public GameJoinedMsg(){

    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update();
    }

    @Override
    public void printMessage() {

    }
}
