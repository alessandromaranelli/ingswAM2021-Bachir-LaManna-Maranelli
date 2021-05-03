package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

import java.io.Serializable;

public abstract class AnswerMessage implements Serializable {
    public abstract void handleMessage(LightModel lightModel);

    public abstract void printMessage();
}
