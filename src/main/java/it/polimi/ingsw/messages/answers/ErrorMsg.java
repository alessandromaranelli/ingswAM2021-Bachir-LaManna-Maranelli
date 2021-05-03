package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;

import java.io.IOException;

public class ErrorMsg extends AnswerMsg{
    private String error;

    public ErrorMsg(String error) {
        this.error = error;
    }

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {
        System.out.println(error);
    }
}
