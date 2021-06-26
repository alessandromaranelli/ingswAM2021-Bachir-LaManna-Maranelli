package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TurnState;

import java.util.ArrayList;
import java.util.List;

/**
 * The type AskWhichPlayerMsg.
 */
public class AskWhichPlayerMsg extends AnswerMsg{
    private TurnState phase;
    private String message;

    /**
     * Instantiates a new Ask which player msg.
     *
     * @param message the message
     * @param phase   the phase
     */
    public AskWhichPlayerMsg(String message, TurnState phase){
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
