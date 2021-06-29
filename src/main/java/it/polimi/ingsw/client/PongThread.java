package it.polimi.ingsw.client;
import it.polimi.ingsw.messages.PongMsg;

/**
 * The type Pong thread. It sends to the server a message every 10000 second to know if it is alive
 */
public class PongThread extends Thread{
    private OutputView outputView;

    /**
     * Instantiates a new Pong thread.
     *
     * @param outputView the output view
     */
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

    /**
     * Send pong.
     */
    public void sendPong(){
        PongMsg pongMsg=new PongMsg();
        outputView.sendCommandMessage(pongMsg);
    }
}
