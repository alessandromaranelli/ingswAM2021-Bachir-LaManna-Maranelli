package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

import java.io.IOException;

/**
 * The type ErrorMsg.
 */
public class ErrorMsg extends AnswerMsg{
    private String error;

    /**
     * Instantiates a new Error msg.
     *
     * @param error the error
     */
    public ErrorMsg(String error) {
        this.error = error;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(error);
    }

    @Override
    public void printMessage() {
        System.out.println(error);
    }
}
