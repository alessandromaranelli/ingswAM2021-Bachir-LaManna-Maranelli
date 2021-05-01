package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;

import java.io.IOException;

public class NumberOfPlayersRequestMsg extends AnswerMsg{
    private String message="Please insert the number of players of the game";

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
