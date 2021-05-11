package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class CpuActionMsg extends AnswerMsg{

    @Override
    public void processMessage(LightModel lightModel) {
    }

    @Override
    public void printMessage() { System.out.println("Lorenzo did his move");

    }
}
