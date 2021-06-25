package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class GameJoinedMsg extends AnswerMsg{
    private TurnState phase;

    public GameJoinedMsg(){
        phase=TurnState.BEFORESTART;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase);
    }

    @Override
    public void printMessage() {

    }
}
