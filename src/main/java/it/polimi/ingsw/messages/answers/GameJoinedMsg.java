package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

/**
 * The type GameJoinedMsg.
 */
public class GameJoinedMsg extends AnswerMsg{

    /**
     * Instantiates a new Game joined msg.
     */
    public GameJoinedMsg(){
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setPhase(TurnState.BEFORESTART);
        lightModel.update();
    }

    @Override
    public void printMessage() {

    }
}
