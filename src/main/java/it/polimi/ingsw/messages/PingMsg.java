package it.polimi.ingsw.messages;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.answers.AnswerMsg;

//sono solo messaggi di comodo che servono al client per capire che il server è ancora vivo
public class PingMsg extends AnswerMsg {
    private String ping="ping";

    @Override
    public void processMessage(LightModel lightModel) {

    }

    @Override
    public void printMessage() {

    }
}
