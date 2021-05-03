package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

public class UpdateNicknameMessage extends AnswerMessage{
    private String message;
    private String nickname;
    private TurnState phase;
    private int playerID;
    private int numberOfPlayers;

    public UpdateNicknameMessage(String nickname, int playerID, int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.message = "You entered in a game with " + numberOfPlayers + " other players. Wait for other players to join";
        this.nickname = nickname;
        this.phase = TurnState.WAIT;
        this.playerID = playerID;
    }

    public void handleMessage(LightModel lightModel){
        lightModel.setNickname(nickname);
        lightModel.setPhase(phase);
        lightModel.setPlayerID(playerID);
        lightModel.setNumberOfPlayers(numberOfPlayers);
    }

    public void printMessage(){
        System.out.println(message);
    }
}
