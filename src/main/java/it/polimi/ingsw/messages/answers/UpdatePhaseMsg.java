package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

/**
 * The type UpdatePhaseMsg.
 */
public class UpdatePhaseMsg extends AnswerMsg{
    private TurnState phase;
    private String message;

    /**
     * Instantiates a new Update phase msg.
     *
     * @param phase   the phase
     * @param message the message
     */
    public UpdatePhaseMsg(TurnState phase, String message){
        this.phase = phase;
        this.message = message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
