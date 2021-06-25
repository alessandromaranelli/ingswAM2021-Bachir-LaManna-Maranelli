package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class GameJoinedMsg extends AnswerMsg{

    public GameJoinedMsg(){
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setPhase(TurnState.BEFORESTART);
    }

    @Override
    public void printMessage() {

    }
}
