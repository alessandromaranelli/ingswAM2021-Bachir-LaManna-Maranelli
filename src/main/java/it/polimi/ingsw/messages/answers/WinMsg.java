package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

/**
 * The type WinMsg.
 */
public class WinMsg extends AnswerMsg {
    private String message;

    /**
     * Instantiates a new Win msg.
     *
     * @param message the message
     */
    public WinMsg(String message){
        this.message = message;
    }

    public void processMessage(LightModel lightModel){
        lightModel.update(message);
    }

    public void printMessage(){
        System.out.println(message);
    }
}
