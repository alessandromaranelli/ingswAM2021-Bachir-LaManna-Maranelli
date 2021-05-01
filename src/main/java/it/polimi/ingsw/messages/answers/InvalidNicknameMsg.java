package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;
import it.polimi.ingsw.messages.commands.CommandMsg;

import java.io.IOException;

public class InvalidNicknameMsg extends AnswerMsg{
    private String message="NickName invalido, inseriscine un altro!";

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
