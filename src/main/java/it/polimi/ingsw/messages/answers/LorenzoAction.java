package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

/**
 * The type LorenzoActionMsg.
 */
public class LorenzoAction extends AnswerMsg{
    private String message;
    private int position;

    /**
     * Instantiates a new Lorenzo action.
     *
     * @param msg the message
     */
    public LorenzoAction(String msg, int pos){
        message = msg;
        position = pos;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(message, position);
    }

    @Override
    public void printMessage() {

    }
}
