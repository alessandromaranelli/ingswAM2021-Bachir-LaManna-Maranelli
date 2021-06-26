package it.polimi.ingsw.messages;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.answers.AnswerMsg;


/**
 * The type PingMsg.
 */
public class PingMsg extends AnswerMsg {
    private String ping="ping";

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}
