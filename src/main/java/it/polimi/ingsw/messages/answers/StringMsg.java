package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class StringMsg extends AnswerMsg {
    private String message;

    public StringMsg(String message){
        this.message = message;
    }

    public void processMessage(LightModel lightModel){
    }

    public void printMessage(){
        System.out.println(message);
    }
}
