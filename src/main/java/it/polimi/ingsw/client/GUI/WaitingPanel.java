package it.polimi.ingsw.client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WaitingPanel extends JPanel{
    private JLabel label;

    public WaitingPanel(){
        label = new JLabel();

        setBackground(Color.ORANGE);
        setLayout(new BorderLayout());
        //setBounds(0, 0, 5000, 5000);

        label.setText("Waiting for other players...");
        label.setFont(new Font("Comic Sans", Font.BOLD, 50));
        //label.setHorizontalTextPosition(JLabel.CENTER);
        //label.setVerticalTextPosition((JLabel.TOP));
        //label.setForeground(Color.GRAY);
        //label.setBackground(Color.RED);
        //label.setOpaque(true);

        add(label);
        setVisible(true);
    }
}
