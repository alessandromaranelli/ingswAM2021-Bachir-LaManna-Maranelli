package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.preparation.SetInitStorageTypeMsg;
import it.polimi.ingsw.messages.commands.productionphase.ActivatePersonalProductionMsg;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActivatePersonalProductionFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JComboBox<Resource> box1;
    private JComboBox<Resource> box2;
    private JComboBox<Resource> box3;
    private JButton button;
    private JPanel panel;
    private JPanel input1;
    private JPanel input2;
    private JPanel output;

    public ActivatePersonalProductionFrame(Gui gui, LightModel lightModel){
        this.gui = gui;
        this.lightModel = lightModel;
        panel = new JPanel();
        input1 = new JPanel();
        input2 = new JPanel();
        output = new JPanel();
        input1.setLayout(new BoxLayout(input1, BoxLayout.Y_AXIS));
        input2.setLayout(new BoxLayout(input2, BoxLayout.Y_AXIS));
        output.setLayout(new BoxLayout(output, BoxLayout.Y_AXIS));

        jLabel1 = new JLabel("Select first resource to pay:");
        jLabel1.setBackground(Color.YELLOW);
        jLabel1.setForeground(Color.BLACK);
        input1.add(jLabel1);

        jLabel2 = new JLabel("Select second resource to pay:");
        jLabel2.setBackground(Color.YELLOW);
        jLabel2.setForeground(Color.BLACK);
        input2.add(jLabel2);

        jLabel3 = new JLabel("Select resource to produce:");
        jLabel3.setBackground(Color.YELLOW);
        jLabel3.setForeground(Color.BLACK);
        output.add(jLabel3);

        box1 = new JComboBox<>();
        box1.addItem(Resource.COIN);
        box1.addItem(Resource.SERVANT);
        box1.addItem(Resource.STONE);
        box1.addItem(Resource.SHIELD);
        input1.add(box1);

        box2 = new JComboBox<>();
        box2.addItem(Resource.COIN);
        box2.addItem(Resource.SERVANT);
        box2.addItem(Resource.STONE);
        box2.addItem(Resource.SHIELD);
        input2.add(box2);

        box3 = new JComboBox<>();
        box3.addItem(Resource.COIN);
        box3.addItem(Resource.SERVANT);
        box3.addItem(Resource.STONE);
        box3.addItem(Resource.SHIELD);
        output.add(box3);

        panel.add(input1);
        panel.add(input2);
        panel.add(output);

        button = new JButton("Submit");
        button.addActionListener(this);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setBackground(Color.RED);
        button.setForeground(Color.BLACK);
        panel.add(button);

        setContentPane(panel);
        panel.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)){
            CommandMsg msg = new ActivatePersonalProductionMsg((Resource) box1.getSelectedItem(), (Resource) box2.getSelectedItem(), (Resource) box3.getSelectedItem());
            gui.sendMessage(msg);
            dispose();
        }
    }
}
