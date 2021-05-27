package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.client.OutputView;
import it.polimi.ingsw.messages.commands.CommandMsg;

import javax.swing.*;
import java.awt.*;

public class Gui implements Runnable{
    private CustomFrame frame;
    private OutputView outputView;

    public Gui(OutputView outputView){
        this.outputView = outputView;
    }

    public void run(){
        frame = new CustomFrame(this);
        frame.setTitle("Masters of Reneissance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLayout(new FlowLayout());
    }

    public void sendMessage(CommandMsg commandMsg){
        outputView.sendCommandMessage(commandMsg);
    }

    public void waitScene(){
        frame.waitingPanel();
    }

    public void updatePersonalBoard(LightModel lightModel){
        frame.updatePersonalBoard(lightModel);
    }
}
