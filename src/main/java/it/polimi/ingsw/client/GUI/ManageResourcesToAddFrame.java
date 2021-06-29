package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.marketphase.AddResourceMsg;
import it.polimi.ingsw.messages.commands.marketphase.DiscardResourceMsg;
import it.polimi.ingsw.messages.commands.marketphase.ManageResourcesToOrganizeMsg;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * The type Manage resources to add frame. It displays the resources and if they have to be added or discarded
 */
public class ManageResourcesToAddFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private JPanel main, subMain, cost, availableResources;
    private JPanel storagePanel;
    private JButton submit;
    private JLabel label1, label2;

    //payment components
    private JPanel paymentPanel, storageResources;
    private JComboBox<Integer> storageN;
    private JComboBox<Integer> quantity;
    private JComboBox<Resource> resourceType;
    private JComboBox<String> choice;


    /**
     * Instantiates a new Manage resources to add frame.
     *
     * @param gui        the gui
     * @param lightModel the light model
     */
    public ManageResourcesToAddFrame(Gui gui, LightModel lightModel){
        this.lightModel = lightModel;
        this.gui = gui;

        main = new JPanel();
        subMain = new JPanel();
        availableResources = new JPanel();
        paymentPanel = new JPanel();
        storageResources = new JPanel();
        cost = new JPanel();
        storagePanel = new JPanel();
        storageN = new JComboBox<>();
        resourceType = new JComboBox<Resource>();
        quantity = new JComboBox<>();
        choice = new JComboBox<>();
        submit = new JButton("SUBMIT");

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createLineBorder(Color.cyan,3));
        subMain.setLayout(new BoxLayout(subMain, BoxLayout.X_AXIS));
        subMain.setBorder(BorderFactory.createLineBorder(Color.magenta,3));

        //build cost header panel with labels for each resource
        cost.setBorder(BorderFactory.createLineBorder(Color.red,3));
        Map<Resource,Integer> resourcesToAdd = lightModel.getResourcesToAdd();
        cost.add(new JLabel("Resources to add: "));
        ArrayList<ResourceLabel> rl = new ArrayList<>();
        for(Resource r: resourcesToAdd.keySet()){
            ResourceLabel j = new ResourceLabel(r);
            j.setQuantity(resourcesToAdd.get(r));
            j.setVisible(true);
            rl.add(j);
        }
        for (ResourceLabel r: rl){
            cost.add(r);
        }
        rl.removeAll(rl);


        //build available resources panel
        availableResources.setLayout(new BoxLayout(availableResources, BoxLayout.Y_AXIS));
        availableResources.setBorder(BorderFactory.createLineBorder(Color.gray,3));
        //availableResources.setVisible(true);
        storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.Y_AXIS));
        storagePanel.setBorder(BorderFactory.createLineBorder(Color.white,3));
        //storagePanel.setVisible(true);

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
        availableResources.add(storagePanel);

        label1 = new JLabel("Select storage:");
        for(int i=1; i <= lightModel.getStorageQuantity().size(); i++) {
            storageN.addItem(i);
        }
        storageN.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        //storageN.setVisible(false);

        label2 = new JLabel("Select quantity:");
        quantity.addItem(1);
        quantity.addItem(2);
        quantity.addItem(3);
        quantity.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        //quantity.setVisible(true);

        resourceType.addItem(Resource.COIN);
        resourceType.addItem(Resource.SERVANT);
        resourceType.addItem(Resource.SHIELD);
        resourceType.addItem(Resource.STONE);
        resourceType.setBorder(BorderFactory.createLineBorder(Color.black,3));
        //resourceType.setVisible(true);

        choice.addItem("Add");
        choice.addItem("Discard");
        choice.addActionListener(this);
        resourceType.setBorder(BorderFactory.createLineBorder(Color.black,3));

        submit.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        submit.addActionListener(this);
        //submit.setVisible(true);

        paymentPanel.add(choice);
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
        if(e.getSource().equals(choice)){
            if(choice.getSelectedItem().equals("Add")){
                storageN.setVisible(true);
                label1.setVisible(true);
            }else{
                storageN.setVisible(false);
                label1.setVisible(false);
            }
        }
        else if (e.getSource().equals(submit)){
            if(choice.getSelectedItem().equals("Add")) {
                CommandMsg msg = new AddResourceMsg((Resource) resourceType.getSelectedItem(), (Integer) storageN.getSelectedItem(), (Integer) quantity.getSelectedItem());
                gui.sendMessage(msg);
                dispose();
            }
            else if(choice.getSelectedItem().equals("Discard")) {
                CommandMsg msg = new DiscardResourceMsg((Resource) resourceType.getSelectedItem(), (Integer) quantity.getSelectedItem());
                gui.sendMessage(msg);
                dispose();
            }
        }
    }
}
