package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

import java.io.Serializable;

/**
 * The type AnswerMsg is the abstract class extended by all the messages that the server sends in response to the client.
 */
public abstract class AnswerMsg implements Serializable {
    /**
     * Processes the message.
     *
     * @param lightModel the light model
     */
    public abstract void processMessage(LightModel lightModel);

    /**
     * Prints the message.
     */
    public abstract void printMessage();
}
