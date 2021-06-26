package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

/**
 * The type LorenzoActionMsg.
 */
public class LorenzoAction extends AnswerMsg{
    private String message;

    /**
     * Instantiates a new Lorenzo action.
     *
     * @param msg the message
     */
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
