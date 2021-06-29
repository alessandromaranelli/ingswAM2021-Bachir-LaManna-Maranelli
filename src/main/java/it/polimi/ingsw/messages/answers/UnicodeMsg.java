package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

/**
 * The type UnicodeMsg.
 */
public class UnicodeMsg extends AnswerMsg{
    private String code;

    /**
     * Instantiates a new Unicode msg.
     *
     * @param code the code
     */
    public UnicodeMsg(String code){
        this.code = code;
    }

    public void processMessage(LightModel lightModel){
        lightModel.setUnicode(code);
    }

    public void printMessage(){
        System.out.println();
    }
}
