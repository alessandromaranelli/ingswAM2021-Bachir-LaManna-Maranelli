package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;

import java.io.IOException;

public class UpdateWhiteMarblesToManageMsg extends AnswerMsg{
    private int whiteMarbles;
    private String message="Still "+whiteMarbles+" white marbles to manage";


    public UpdateWhiteMarblesToManageMsg(int whiteMarbles) {
        this.whiteMarbles = whiteMarbles;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(whiteMarbles);
        /*
        lightModel.setWhiteMarblesToManage(whiteMarbles);

         */
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
