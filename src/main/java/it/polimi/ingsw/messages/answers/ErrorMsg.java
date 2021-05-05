package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

import java.io.IOException;

public class ErrorMsg extends AnswerMsg{
    private String error;

    public ErrorMsg(String error) {
        this.error = error;
    }

    @Override
    public void processMessage(LightModel lightModel) { }

    @Override
    public void printMessage() {
        System.out.println(error);
    }
}
