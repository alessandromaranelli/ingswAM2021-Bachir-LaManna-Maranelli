package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

/**
 * The type StartTurnMsg.
 */

public class StartTurnMsg extends AnswerMsg{
    private TurnState phase;
    private String currentPlayer;
    private String message;

    /**
     * Instantiates a new Start turn msg.
     *
     * @param phase         the phase
     * @param currentPlayer the current player
     */
    public StartTurnMsg(TurnState phase, String currentPlayer){
        this.phase = phase;
        this.currentPlayer = currentPlayer;
        message = "New current player is: " + currentPlayer;
    }

    public void processMessage(LightModel lightModel) {
        lightModel.update(phase, currentPlayer);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
