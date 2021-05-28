package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.client.OutputView;
import it.polimi.ingsw.messages.commands.preparation.NickNameMsg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomFrame extends JFrame implements ActionListener {
    private Gui gui;
    private JButton button;
    private JTextField textField1;
    private JTextField textField2;

    public CustomFrame(Gui gui){
        this.gui = gui;
        button = new JButton("Submit");
        button.setBounds(100, 100, 250, 100);
        button.addActionListener(this);
        button.setText("Submit");
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setIconTextGap(-15);
        button.setForeground(Color.BLUE);
        button.setBackground(Color.ORANGE);
        button.setBorder(BorderFactory.createEtchedBorder());

        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(250, 40));
        textField1.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        textField1.setForeground(Color.BLUE);
        textField1.setBackground(Color.ORANGE);
        textField1.setText("Nickname");

        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(250, 40));
        textField2.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        textField2.setForeground(Color.BLUE);
        textField2.setBackground(Color.ORANGE);
        textField2.setText("Number of players");

        add(button);
        add(textField1);
        add(textField2);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)){
            if(textField1.getText() == null){
                JOptionPane.showMessageDialog(null, "Insert nickname", "error", JOptionPane.ERROR_MESSAGE);
            }
            else if(Integer.parseInt(textField2.getText()) < 1 || Integer.parseInt(textField2.getText()) > 4){
                JOptionPane.showMessageDialog(null, "Invalid number of players", "error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                NickNameMsg nickNameMsg = new NickNameMsg(textField1.getText(), Integer.parseInt(textField2.getText()));
                gui.sendMessage(nickNameMsg);
            }
        }
    }

    public void waitingPanel(){
        getContentPane().removeAll();
        JPanel jPanel = new WaitingPanel();
        add(jPanel);
        pack();
        setVisible(true);
    }

    public void updatePersonalBoard(LightModel lightModel){
        getContentPane().removeAll();
        setLayout(new GridLayout(2,2));
        //setSize(2000, 2000);
        JPanel jPanel1 = new WareHousePanel(lightModel);
        JPanel jPanel2 = new FaithTrackPanel(lightModel);
        JPanel jPanel3 = new ButtonPanel(lightModel, gui);
        JPanel jPanel4 = new DevelCardsSlotsPanel(lightModel);
        add(jPanel1);
        add(jPanel2);
        add(jPanel3);
        add(jPanel4);

        pack();
        setVisible(true);
    }
}
