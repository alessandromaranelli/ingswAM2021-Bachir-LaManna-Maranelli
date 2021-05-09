package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class UpdatePhaseMsg extends AnswerMsg{
    TurnState phase;
    String message;

    public UpdatePhaseMsg(TurnState phase, String message){
        this.phase = phase;
        this.message = message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setPhase(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
