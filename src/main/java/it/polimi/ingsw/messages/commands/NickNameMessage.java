package it.polimi.ingsw.messages.commands;

import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

public class NickNameMessage extends CommandMessage{
    private String nickname;
    private int numOfPlayers;

    public NickNameMessage(String nickname, int numOfPlayers){
        this.nickname = nickname;
        this.numOfPlayers = numOfPlayers;
    }

    public void handleMessage(Controller controller, ClientHandler clientHandler){
        controller.setNickname(nickname, numOfPlayers, clientHandler);
    }
}
