package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;

public class StringMessage extends AnswerMessage{
    String message;

    public StringMessage(String message){
        this.message = message;
    }

    public void handleMessage(LightModel lightModel){}

    public void printMessage(){
        System.out.println(message);
    }
}
