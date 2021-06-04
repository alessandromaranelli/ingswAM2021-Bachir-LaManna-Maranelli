package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.marketphase.SelectMarketPhaseMsg;
import it.polimi.ingsw.messages.commands.marketphase.StartMarketPhaseMsg;
import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.*;
import java.util.List;

public class MarketFrame extends JFrame {
    List<JLabel> labels;
    JLabel label;
    JPanel head;
    JPanel body;
    JPanel excess;

    public MarketFrame(Gui gui,LightModel lightModel,boolean buyMarbles){
        setTitle("MarketTray");
        if(buyMarbles){
            head=new JPanel();
            JLabel jLabel=new JLabel();
            jLabel.setText("You can pick a row or column");
            head.add(jLabel);
            head.setBackground(Color.ORANGE);
            head.setSize(2000,100);
            add(head,BorderLayout.NORTH);
        }
        body=new JPanel();
        excess=new JPanel();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);
        body.setSize(2000, 500);
        body.setLayout(new GridLayout(4, 5, 20, 20));
        add(body,BorderLayout.CENTER);
        labels = new ArrayList<JLabel>();
        processMarket(lightModel.getMarket(),buyMarbles,gui);

        excess.add(new MarbleLabel(lightModel.getMarbleInExcess(),"Marble in excess:"));
        excess.setSize(2000,200);
        add(excess,BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void processMarket(Marble[][] market,boolean buyMarbles,Gui gui) {
            int z = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    labels.add(new MarbleLabel(market[i][j]));
                    body.add(labels.get(z));
                    z++;
                }
                JButton jButton=new JButton();
                jButton.setIcon(new ImageIcon(new ImageIcon("src/main/resources/Resources/frecciasx.PNG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                int finalI = i;
                jButton.addActionListener(e -> {
                    gui.sendMessage(new StartMarketPhaseMsg(finalI+1,true));
                    dispose();
                    System.out.println("Hai preso la"+(finalI +1)+"riga");});
                body.add(jButton);
                if(!buyMarbles)jButton.setVisible(false);
            }
            for(int k=0;k<4;k++){
                JButton jButton=new JButton();
                jButton.setIcon(new ImageIcon(new ImageIcon("src/main/resources/Resources/frecciasu.PNG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                int finalI = k;
                jButton.addActionListener(e -> {
                    gui.sendMessage(new StartMarketPhaseMsg(finalI+1,false));
                    dispose();
                    System.out.println("Hai preso la"+(finalI +1)+"colonna");});
                body.add(jButton);
                if(!buyMarbles)jButton.setVisible(false);
            }
    }

}
