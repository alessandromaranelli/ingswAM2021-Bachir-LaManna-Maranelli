package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class LorenzoAction extends AnswerMsg{
    private String message;

    public LorenzoAction(String msg){
        message = msg;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(message, true);
    }

    @Override
    public void printMessage() {

    }
}
