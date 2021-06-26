package it.polimi.ingsw.messages.commands.preparation;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;


/**
 * The type NickNameMsg.
 */
public class NickNameMsg extends CommandMsg {
    private String nickname;
    private int numOfPlayers;

    /**
     * Instantiates a new Nick name msg.
     *
     * @param nickname     the nickname
     * @param numOfPlayers the num of players
     */
    public NickNameMsg(String nickname, int numOfPlayers) {
        this.nickname = nickname;
        this.numOfPlayers = numOfPlayers;
    }

    public void processMessage(ClientHandler clientHandler, Controller controller) {
        controller.setNickname(nickname, numOfPlayers, clientHandler);
    }
}