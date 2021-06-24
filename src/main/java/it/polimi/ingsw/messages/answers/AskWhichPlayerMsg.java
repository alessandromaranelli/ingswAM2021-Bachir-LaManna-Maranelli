package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TurnState;

import java.util.ArrayList;
import java.util.List;

public class AskWhichPlayerMsg extends AnswerMsg{
    private TurnState phase;
    private String message;
    private ArrayList<Player> players;

    public AskWhichPlayerMsg(String message, TurnState phase, ArrayList<Player> player){
        this.phase = phase;
        this.message = message;
        this.players = player;
    }
    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(players);
        lightModel.update(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
