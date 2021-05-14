package it.polimi.ingsw.client;
import it.polimi.ingsw.messages.PongMsg;

public class PongThread extends Thread{
    private OutputView outputView;

    public PongThread(OutputView outputView){
        this.outputView = outputView;
    }

    public void run(){
        while(true) {
            sendPong();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPong(){
        PongMsg pongMsg=new PongMsg();
        outputView.sendCommandMessage(pongMsg);
    }
}
