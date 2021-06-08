package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class AskWhichPlayerMsg extends AnswerMsg{
    private TurnState phase;
    private String message;

    public AskWhichPlayerMsg(String message, TurnState phase){
        this.phase = phase;
        this.message = message;
    }
    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
