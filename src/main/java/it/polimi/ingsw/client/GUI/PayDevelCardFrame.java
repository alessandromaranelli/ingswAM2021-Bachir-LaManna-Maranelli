package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.PayCardFromChestMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.PayCardFromStorageMsg;
import it.polimi.ingsw.messages.commands.productionphase.PayProductionFromChest;
import it.polimi.ingsw.messages.commands.productionphase.PayProductionFromStorage;
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

/**
 * The type Pay devel card frame. It displays the cost to pay and the player decides how to pay it
 */
public class PayDevelCardFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private JPanel main, subMain, cost, availableResources;
    private JPanel storagePanel, chestPanel;
    private JButton submit;

    //payment components
    private JPanel paymentPanel, storageResources, chestResources;
    private JComboBox<String> warehouseType;
    private JComboBox<Integer> quantity, storageN;
    private JComboBox<Resource> resourceType;
    private JLabel label1;
    private JLabel label2;


    /**
     * Instantiates a new Pay devel card frame.
     *
     * @param gui        the gui
     * @param lightModel the light model
     */
    public PayDevelCardFrame(Gui gui, LightModel lightModel){
        this.lightModel = lightModel;
        this.gui = gui;

        //instantiate attributes
        main = new JPanel();
        subMain = new JPanel();
        availableResources = new JPanel();
        paymentPanel = new JPanel();
        storageResources = new JPanel();
        chestResources = new JPanel();
        cost = new JPanel();
        storagePanel = new JPanel();
        chestPanel = new JPanel();
        warehouseType =new JComboBox<>();
        storageN = new JComboBox<>();
        resourceType = new JComboBox<Resource>();
        quantity = new JComboBox<>();
        submit = new JButton("SUBMIT");

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createLineBorder(Color.cyan,3));
        subMain.setLayout(new BoxLayout(subMain, BoxLayout.X_AXIS));
        subMain.setBorder(BorderFactory.createLineBorder(Color.magenta,3));

        //build cost header panel with labels for each resource
        cost.setBorder(BorderFactory.createLineBorder(Color.red,3));
        Map<Resource,Integer> costMap = lightModel.getCardCost();
        cost.add(new JLabel("Remaining card cost: "));
        ArrayList<ResourceLabel> rl = new ArrayList<>();
        for(Resource r: costMap.keySet()){
            ResourceLabel j = new ResourceLabel(r);
            j.setQuantity(costMap.get(r));
            j.setVisible(true);
            rl.add(j);
        }
        for (ResourceLabel r: rl){
            cost.add(r);
        }
        rl.removeAll(rl);
        //cost.setVisible(true);


        //build available resources panel, with 2 sub-panels (Storage, Chest)
        availableResources.setLayout(new BoxLayout(availableResources, BoxLayout.Y_AXIS));
        availableResources.setBorder(BorderFactory.createLineBorder(Color.gray,3));
        //availableResources.setVisible(true);
        storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.Y_AXIS));
        storagePanel.setBorder(BorderFactory.createLineBorder(Color.white,3));
        //storagePanel.setVisible(true);
        chestPanel.setLayout(new BoxLayout(chestPanel, BoxLayout.Y_AXIS));
        chestPanel.setBorder(BorderFactory.createLineBorder(Color.orange,3));
        //chestPanel.setVisible(true);

        //build storagePanel
        storagePanel.add(new JLabel("STORAGE:"));
        ResourceLabel resourceLabel = null;
        for(int i=0; i < lightModel.getStorageType().size(); i++){
            resourceLabel = new ResourceLabel(lightModel.getStorageType().get(i));
            resourceLabel.setQuantity(lightModel.getStorageQuantity().get(i));
            rl.add(resourceLabel);
        }
        for (ResourceLabel r: rl){
            storagePanel.add(r);
        }
        //build chestPanel
        chestPanel.add(new JLabel("CHEST:"));
        for(Resource r: lightModel.getChest().keySet()){
            resourceLabel = new ResourceLabel(r);
            resourceLabel.setQuantity(lightModel.getChest().get(r));
            chestPanel.add(resourceLabel);
        }
        availableResources.add(storagePanel);
        availableResources.add(chestPanel);

        //build paymentPanel

        warehouseType.addItem("Storage");
        warehouseType.addItem("Chest");
        warehouseType.addActionListener(this);
        //warehouseType.setVisible(true);
        warehouseType.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        label1 = new JLabel("Select storage");
        for(int i=1; i <= lightModel.getStorageQuantity().size(); i++) {
            storageN.addItem(i);
        }
        storageN.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        //storageN.setVisible(true);

        label2 = new JLabel("Select quantity");
        for(int i=1; i <= 10; i++) {
            quantity.addItem(i);
        }
        quantity.setBorder(BorderFactory.createLineBorder(Color.green,3));
        //quantity.setVisible(true);

        resourceType.addItem(Resource.COIN);
        resourceType.addItem(Resource.SERVANT);
        resourceType.addItem(Resource.SHIELD);
        resourceType.addItem(Resource.STONE);
        resourceType.setBorder(BorderFactory.createLineBorder(Color.black,3));
        //resourceType.setVisible(true);


        submit.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        submit.addActionListener(this);
        //submit.setVisible(true);

        paymentPanel.add(warehouseType);
        paymentPanel.add(resourceType);
        paymentPanel.add(label1);
        paymentPanel.add(storageN);
        paymentPanel.add(label2);
        paymentPanel.add(quantity);
        paymentPanel.add(submit);
        paymentPanel.setLayout(new BoxLayout(paymentPanel,BoxLayout.Y_AXIS));
        //paymentPanel.setVisible(true);

        subMain.add(availableResources);
        subMain.add(paymentPanel);
        //subMain.setVisible(true);

        main.add(cost);
        main.add(subMain);
        main.setVisible(true);
        main.setOpaque(true);



        setContentPane(main);
        setSize(500,500);
        pack();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(warehouseType)) {
            if (warehouseType.getSelectedItem().equals("Storage")) {
                storageN.setVisible(true);
                label1.setVisible(true);
            } else {
                storageN.setVisible(false);
                label1.setVisible(false);
            }
        } else if (e.getSource().equals(submit)) {
            if (warehouseType.getSelectedItem().equals("Storage")) {
                //System.out.println("Storage" + resourceType.getSelectedItem() + storageN.getSelectedItem().toString() + quantity.getText());
                CommandMsg msg = new PayCardFromStorageMsg((Resource) resourceType.getSelectedItem(), (Integer) storageN.getSelectedItem(), (Integer) quantity.getSelectedItem());
                gui.sendMessage(msg);
                dispose();
            } else {
                //System.out.println("Chest" + resourceType.getSelectedItem() + quantity.getText());
                CommandMsg msg = new PayCardFromChestMsg((Resource) resourceType.getSelectedItem(), (Integer) quantity.getSelectedItem());
                gui.sendMessage(msg);
                dispose();
            }
        }
    }
}
