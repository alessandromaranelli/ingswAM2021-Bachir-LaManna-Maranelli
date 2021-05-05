package it.polimi.ingsw.messages.commands.preparation;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;


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