package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class NickNameMsg extends CommandMsg {
    private String nickname;
    private int numOfPlayers;

    public NickNameMsg(String nickname, int numOfPlayers) {
        this.nickname = nickname;
        this.numOfPlayers = numOfPlayers;
    }

    public void processMessage(ClientHandler clientHandler, Controller controller) {
        controller.setNickname(nickname, numOfPlayers, clientHandler);
    }
}