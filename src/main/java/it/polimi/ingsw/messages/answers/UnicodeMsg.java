package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class UnicodeMsg extends AnswerMsg{
    private String code;

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
