package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class UpdateNicknameMsg extends AnswerMsg {
    private String message;
    private String nickname;
    private TurnState phase;
    private int playerID;
    private int numberOfPlayers;

    public UpdateNicknameMsg(String nickname, int playerID, int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.message = "You entered in a game with " + (numberOfPlayers-1) + " other players. Wait for other players to join";
        this.nickname = nickname;
        this.phase = TurnState.WAIT;
        this.playerID = playerID;
    }

    public void processMessage(LightModel lightModel){
        lightModel.update(nickname, playerID, numberOfPlayers, phase);
        /*
        lightModel.setNickname(nickname);
        lightModel.setPhase(phase);
        lightModel.setPlayerID(playerID);
        lightModel.setNumberOfPlayers(numberOfPlayers);
         */
    }

    public void printMessage(){
        System.out.println(message);
    }
}
