package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

import java.io.Serializable;

public abstract class AnswerMsg implements Serializable {
    public abstract void processMessage(LightModel lightModel);

    public abstract void printMessage();
}
