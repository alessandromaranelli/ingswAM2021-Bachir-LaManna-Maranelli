package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

/**
 * The type StringMsg.
 */
public class StringMsg extends AnswerMsg {
    private String message;

    /**
     * Instantiates a new String msg.
     *
     * @param message the message
     */
    public StringMsg(String message){
        this.message = message;
    }

    public void processMessage(LightModel lightModel){
    }

    public void printMessage(){
        System.out.println(message);
    }
}
