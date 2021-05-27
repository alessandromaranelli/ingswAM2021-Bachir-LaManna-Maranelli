package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.*;
import java.util.List;

public class MarketFrame extends JFrame {
    List<JLabel> labels;

    public MarketFrame(LightModel lightModel){
        setTitle("Market");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        getContentPane().setBackground(Color.CYAN);
        setLayout(new GridLayout(3, 4));

        Marble[][] market = lightModel.getMarket();
        labels = new ArrayList<JLabel>();
        processMarket(lightModel.getMarket());

        pack();
        setVisible(true);
    }

    public void processMarket(Marble[][] market){
        for(int i=0; i < 3; i++){
            for(int j=0; j < 4; j++){
                if(market[i][j] instanceof RedMarble){
                    labels.add(new JLabel());
                    labels.get(i+j).setBackground(Color.RED);
                }
                else if(market[i][j] instanceof WhiteMarble){
                    labels.add(new JLabel());
                    labels.get(i+j).setBackground(Color.WHITE);
                }
                else if(market[i][j] instanceof BlueMarble){
                    labels.add(new JLabel());
                    labels.get(i+j).setBackground(Color.BLUE);
                }
                else if(market[i][j] instanceof GreyMarble){
                    labels.add(new JLabel());
                    labels.get(i+j).setBackground(Color.GRAY);
                }
                else if(market[i][j] instanceof YellowMarble){
                    labels.add(new JLabel());
                    labels.get(i+j).setBackground(Color.YELLOW);
                }
                else if(market[i][j] instanceof PurpleMarble){
                    labels.add(new JLabel());
                    labels.get(i+j).setBackground(Color.MAGENTA);
                }

                add(labels.get(i+j));
            }
        }
    }
}
