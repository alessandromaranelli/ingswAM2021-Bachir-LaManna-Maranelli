package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;

import java.io.IOException;

/**
 * The type UpdateWhiteMarblesToManageMsg.
 */
public class UpdateWhiteMarblesToManageMsg extends AnswerMsg{
    private int whiteMarbles;
    private String message;


    /**
     * Instantiates a new Update white marbles to manage msg.
     *
     * @param whiteMarbles the white marbles
     */
    public UpdateWhiteMarblesToManageMsg(int whiteMarbles) {
        this.whiteMarbles = whiteMarbles;
        this.message="Still "+whiteMarbles+" white marbles to manage";
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(whiteMarbles);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
