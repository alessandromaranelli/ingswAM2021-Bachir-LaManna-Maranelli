package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.marketphase.SetStorageTypesMsg;
import it.polimi.ingsw.messages.commands.preparation.SetInitStorageTypeMsg;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SetStorageTypeFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private JLabel jLabel;
    private List<JComboBox<Resource>> types;
    private JButton button;
    private JPanel panel;
    private boolean init;

    public SetStorageTypeFrame(Gui gui, LightModel lightModel, boolean init){
        this.gui = gui;
        this.lightModel = lightModel;
        this.init = init;
        types = new ArrayList<>();
        panel = new JPanel();

        jLabel = new JLabel("Set storage types");
        jLabel.setBackground(Color.YELLOW);
        jLabel.setForeground(Color.BLACK);
        panel.add(jLabel);

        for(int i=0; i < 3; i++){
            JComboBox<Resource> box = new JComboBox<>();
            box.addItem(Resource.COIN);
            box.addItem(Resource.SERVANT);
            box.addItem(Resource.STONE);
            box.addItem(Resource.SHIELD);
            types.add(box);
        }

        for(int i=0; i < 3; i++){
            panel.add(types.get(i));
        }

        button = new JButton("Submit");
        button.addActionListener(this
                /*e -> {
            CommandMsg msg = null;
            if(init)msg=new SetInitStorageTypeMsg((Resource) types.get(0).getSelectedItem(), (Resource) types.get(1).getSelectedItem(), (Resource) types.get(2).getSelectedItem());
            else msg=new SetStorageTypesMsg((Resource) types.get(0).getSelectedItem(), (Resource) types.get(1).getSelectedItem(), (Resource) types.get(2).getSelectedItem());
            gui.sendMessage(msg);
            dispose();
                            }*/);
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
            if(init){
                CommandMsg msg = new SetInitStorageTypeMsg((Resource) types.get(0).getSelectedItem(), (Resource) types.get(1).getSelectedItem(), (Resource) types.get(2).getSelectedItem());
                gui.sendMessage(msg);
                dispose();
            }
            else{
                CommandMsg msg = new SetStorageTypesMsg((Resource) types.get(0).getSelectedItem(), (Resource) types.get(1).getSelectedItem(), (Resource) types.get(2).getSelectedItem());
                gui.sendMessage(msg);
                dispose();
            }
        }
    }
}
