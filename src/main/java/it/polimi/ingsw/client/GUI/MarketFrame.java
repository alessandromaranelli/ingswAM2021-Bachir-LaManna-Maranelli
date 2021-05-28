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
    JLabel label;

    public MarketFrame(LightModel lightModel){
        setTitle("Marble in excess: " + lightModel.getMarbleInExcess().toString());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2000, 500);
        getContentPane().setBackground(Color.CYAN);
        setLayout(new GridLayout(3, 4, 20, 20));

        labels = new ArrayList<JLabel>();
        processMarket(lightModel.getMarket());

        //label = new JLabel();
        //processMarble(lightModel.getMarbleInExcess());
        //add(label);

        pack();
        setVisible(true);
    }

    public void processMarket(Marble[][] market) {
            int z = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (market[i][j] instanceof RedMarble) {
                        labels.add(new JLabel());
                        labels.get(z).setForeground(Color.RED);
                        labels.get(z).setBackground(Color.LIGHT_GRAY);
                        labels.get(z).setText("R");
                        labels.get(z).setHorizontalAlignment(JLabel.CENTER);
                        labels.get(z).setVerticalAlignment(JLabel.CENTER);
                        labels.get(z).setFont(new Font("Comic Sans", Font.BOLD, 50));
                        labels.get(z).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        labels.get(z).setOpaque(true);
                    } else if (market[i][j] instanceof WhiteMarble) {
                        labels.add(new JLabel());
                        labels.get(z).setForeground(Color.WHITE);
                        labels.get(z).setBackground(Color.LIGHT_GRAY);
                        labels.get(z).setText("W");
                        labels.get(z).setHorizontalAlignment(JLabel.CENTER);
                        labels.get(z).setVerticalAlignment(JLabel.CENTER);
                        labels.get(z).setFont(new Font("Comic Sans", Font.BOLD, 50));
                        labels.get(z).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        labels.get(z).setOpaque(true);
                    } else if (market[i][j] instanceof BlueMarble) {
                        labels.add(new JLabel());
                        labels.get(z).setForeground(Color.BLUE);
                        labels.get(z).setBackground(Color.LIGHT_GRAY);
                        labels.get(z).setText("B");
                        labels.get(z).setHorizontalAlignment(JLabel.CENTER);
                        labels.get(z).setVerticalAlignment(JLabel.CENTER);
                        labels.get(z).setFont(new Font("Comic Sans", Font.BOLD, 50));
                        labels.get(z).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        labels.get(z).setOpaque(true);
                    } else if (market[i][j] instanceof GreyMarble) {
                        labels.add(new JLabel());
                        labels.get(z).setForeground(Color.GRAY);
                        labels.get(z).setBackground(Color.LIGHT_GRAY);
                        labels.get(z).setText("G");
                        labels.get(z).setHorizontalAlignment(JLabel.CENTER);
                        labels.get(z).setVerticalAlignment(JLabel.CENTER);
                        labels.get(z).setFont(new Font("Comic Sans", Font.BOLD, 50));
                        labels.get(z).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        labels.get(z).setOpaque(true);
                    } else if (market[i][j] instanceof YellowMarble) {
                        labels.add(new JLabel());
                        labels.get(z).setForeground(Color.YELLOW);
                        labels.get(z).setBackground(Color.LIGHT_GRAY);
                        labels.get(z).setText("Y");
                        labels.get(z).setHorizontalAlignment(JLabel.CENTER);
                        labels.get(z).setVerticalAlignment(JLabel.CENTER);
                        labels.get(z).setFont(new Font("Comic Sans", Font.BOLD, 50));
                        labels.get(z).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        labels.get(z).setOpaque(true);
                    } else if (market[i][j] instanceof PurpleMarble) {
                        labels.add(new JLabel());
                        labels.get(z).setForeground(Color.MAGENTA);
                        labels.get(z).setBackground(Color.LIGHT_GRAY);
                        labels.get(z).setText("M");
                        labels.get(z).setHorizontalAlignment(JLabel.CENTER);
                        labels.get(z).setVerticalAlignment(JLabel.CENTER);
                        labels.get(z).setFont(new Font("Comic Sans", Font.BOLD, 50));
                        labels.get(z).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        labels.get(z).setOpaque(true);
                    }

                    add(labels.get(z));
                    z++;
                }
            }
    }

    public void processMarble(Marble m){
        if (m instanceof RedMarble) {
            label.setForeground(Color.RED);
            label.setBackground(Color.RED);
            label.setText("R");
            label.setFont(new Font("Comic Sans", Font.BOLD, 50));
        } else if (m instanceof WhiteMarble) {
            label.setForeground(Color.WHITE);
            label.setBackground(Color.WHITE);
            label.setText("W");
            label.setFont(new Font("Comic Sans", Font.BOLD, 50));
        } else if (m instanceof BlueMarble) {
            label.setForeground(Color.BLUE);
            label.setBackground(Color.BLUE);
            label.setText("B");
            label.setFont(new Font("Comic Sans", Font.BOLD, 50));
        } else if (m instanceof GreyMarble) {
            label.setForeground(Color.GRAY);
            label.setBackground(Color.GRAY);
            label.setText("G");
            label.setFont(new Font("Comic Sans", Font.BOLD, 50));
        } else if (m instanceof YellowMarble) {
            label.setForeground(Color.YELLOW);
            label.setBackground(Color.YELLOW);
            label.setText("Y");
            label.setFont(new Font("Comic Sans", Font.BOLD, 50));
        } else if (m instanceof PurpleMarble) {
            label.setForeground(Color.MAGENTA);
            label.setBackground(Color.MAGENTA);
            label.setText("M");
            label.setFont(new Font("Comic Sans", Font.BOLD, 50));
        }
    }
}
