package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.marketphase.DefaultManageResourcesToOrganizeMsg;
import it.polimi.ingsw.messages.commands.marketphase.SetStorageTypesMsg;
import it.polimi.ingsw.messages.commands.marketphase.StartOrganizeResourcesMsg;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketOrganizeChoiceFrame extends JFrame implements ActionListener {
    JButton defaultOrganize;
    JButton setStorages;

    public MarketOrganizeChoiceFrame(Gui gui, LightModel lightModel) {
        JPanel j = new JPanel();
        defaultOrganize=new JButton();
        setStorages=new JButton();
        defaultOrganize.addActionListener(e -> {
            gui.sendMessage(new SetStorageTypesMsg((Resource) lightModel.getStorageType().get(0), (Resource) lightModel.getStorageType().get(1), lightModel.getStorageType().get(2)));
            gui.sendMessage(new DefaultManageResourcesToOrganizeMsg());
            dispose();
        });
        defaultOrganize.setFont(new Font("Comic Sans", Font.BOLD, 20));
        defaultOrganize.setForeground(Color.BLUE);
        defaultOrganize.setBackground(Color.ORANGE);
        defaultOrganize.setBorder(BorderFactory.createEtchedBorder());
        defaultOrganize.setVisible(true);
        setStorages.addActionListener(e -> {
            SetStorageTypeFrame setStorageTypeFrame=new SetStorageTypeFrame(gui, lightModel, false);
            dispose();
        });
        setStorages.setFont(new Font("Comic Sans", Font.BOLD, 20));
        setStorages.setForeground(Color.BLUE);
        setStorages.setBackground(Color.ORANGE);
        setStorages.setBorder(BorderFactory.createEtchedBorder());
        setStorages.setVisible(true);
        j.add(setStorages);
        j.add(defaultOrganize);
        add(j);
        setSize(500,500);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
