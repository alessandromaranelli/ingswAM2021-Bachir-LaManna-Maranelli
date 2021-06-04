package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.PayCardFromChestMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.PayCardFromStorageMsg;
import it.polimi.ingsw.messages.commands.marketphase.StartMarketPhaseMsg;
import it.polimi.ingsw.messages.commands.marketphase.StartOrganizeResourcesMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.util.ArrayList;
import java.util.Map;

public class MarketBuyFrame extends JFrame implements ActionListener {
    LightModel lightModel;
    Gui gui;
    JPanel main, subMain, toAdd, availableResources;
    JPanel storagePanel, chestPanel;
    JButton submit;
    JButton startOrganize;

    //payment components
    JPanel paymentPanel, storageResources, chestResources;
    JTextField quantity;


    public MarketBuyFrame(Gui gui, LightModel lightModel) {
        this.lightModel = lightModel;
        this.gui = gui;

        //instantiate attributes
        main = new JPanel();
        subMain = new JPanel();
        availableResources = new JPanel();
        paymentPanel = new JPanel();
        storageResources = new JPanel();
        chestResources = new JPanel();
        toAdd = new JPanel();
        storagePanel = new JPanel();
        chestPanel = new JPanel();
        quantity = new JTextField("Quantity");
        submit = new JButton("SUBMIT");

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createLineBorder(Color.cyan, 3));
        subMain.setLayout(new BoxLayout(subMain, BoxLayout.X_AXIS));
        subMain.setBorder(BorderFactory.createLineBorder(Color.magenta, 3));

        //build cost header panel with labels for each resource
        toAdd.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        Map<Resource, Integer> resourcesToAdd = lightModel.getResourcesToAdd();
        toAdd.add(new JLabel("Remaining resources to add: "));
        ArrayList<ResourceLabel> rl = new ArrayList<>();
        for (Resource r : resourcesToAdd.keySet()) {
            ResourceLabel j = new ResourceLabel(r);
            j.setQuantity(resourcesToAdd.get(r));
            j.setVisible(true);
            rl.add(j);
        }
        for (ResourceLabel r : rl) {
            toAdd.add(r);
        }
        rl.removeAll(rl);
        //cost.setVisible(true);


        //build available resources panel, with 2 sub-panels (Storage, Chest)
        availableResources.setLayout(new BoxLayout(availableResources, BoxLayout.Y_AXIS));
        availableResources.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
        //availableResources.setVisible(true);
        storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.Y_AXIS));
        storagePanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));
        //storagePanel.setVisible(true);
        chestPanel.setLayout(new BoxLayout(chestPanel, BoxLayout.Y_AXIS));
        chestPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
        //chestPanel.setVisible(true);

        //build storagePanel
        storagePanel.add(new JLabel("STORAGE:"));
        ResourceLabel resourceLabel = null;
        for (int i = 0; i < lightModel.getStorageType().size(); i++) {
            resourceLabel = new ResourceLabel(lightModel.getStorageType().get(i));
            resourceLabel.setQuantity(lightModel.getStorageQuantity().get(i));
            rl.add(resourceLabel);
        }
        for (ResourceLabel r : rl) {
            storagePanel.add(r);
        }
        //build chestPanel
        chestPanel.add(new JLabel("CHEST:"));
        for (Resource r : lightModel.getChest().keySet()) {
            resourceLabel = new ResourceLabel(r);
            resourceLabel.setQuantity(lightModel.getChest().get(r));
            chestPanel.add(resourceLabel);
        }
        availableResources.add(storagePanel);
        availableResources.add(chestPanel);

        //build paymentPanel

        startOrganize = new JButton("Start Organize Resources");
        startOrganize.addActionListener(e -> {
            gui.sendMessage(new StartOrganizeResourcesMsg());
        });
        startOrganize.setFont(new Font("Comic Sans", Font.BOLD, 20));
        startOrganize.setForeground(Color.BLUE);
        startOrganize.setBackground(Color.ORANGE);
        startOrganize.setBorder(BorderFactory.createEtchedBorder());


        submit.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        submit.addActionListener(this);
        //submit.setVisible(true);

        paymentPanel.add(startOrganize);
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
        //paymentPanel.setVisible(true);

        subMain.add(availableResources);
        subMain.add(paymentPanel);
        //subMain.setVisible(true);

        main.add(toAdd);
        main.add(subMain);
        main.setVisible(true);
        main.setOpaque(true);


        setContentPane(main);
        setSize(500, 500);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}