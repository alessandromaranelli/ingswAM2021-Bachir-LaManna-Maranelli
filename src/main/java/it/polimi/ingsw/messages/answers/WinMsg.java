package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class WinMsg extends AnswerMsg {
    private String message;

    public WinMsg(String message){
        this.message = message;
    }

    public void processMessage(LightModel lightModel){}

    public void printMessage(){
        System.out.println(message);
    }
}
