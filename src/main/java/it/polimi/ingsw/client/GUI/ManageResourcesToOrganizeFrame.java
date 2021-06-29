package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.marketphase.ManageResourcesToOrganizeMsg;
import it.polimi.ingsw.messages.commands.productionphase.PayProductionFromChest;
import it.polimi.ingsw.messages.commands.productionphase.PayProductionFromStorage;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * The type Manage resources to organize frame. It displays the resources and in which storage they have to be added
 */
public class ManageResourcesToOrganizeFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private JPanel main, subMain, cost, availableResources;
    private JPanel storagePanel;
    private JButton submit;

    //payment components
    private JPanel paymentPanel, storageResources;
    private JComboBox<Integer> storageN;
    private JComboBox<Integer> quantity;
    private JComboBox<Resource> resourceType;
    private JLabel label1;
    private JLabel label2;


    /**
     * Instantiates a new Manage resources to organize frame.
     *
     * @param gui        the gui
     * @param lightModel the light model
     */
    public ManageResourcesToOrganizeFrame(Gui gui, LightModel lightModel){
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
        submit = new JButton("SUBMIT");

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createLineBorder(Color.cyan,3));
        subMain.setLayout(new BoxLayout(subMain, BoxLayout.X_AXIS));
        subMain.setBorder(BorderFactory.createLineBorder(Color.magenta,3));

        //build cost header panel with labels for each resource
        cost.setBorder(BorderFactory.createLineBorder(Color.red,3));
        Map<Resource,Integer> resourcesToOrganize = lightModel.getResourcesToOrganize();
        cost.add(new JLabel("Resources to organize: "));
        ArrayList<ResourceLabel> rl = new ArrayList<>();
        for(Resource r: resourcesToOrganize.keySet()){
            ResourceLabel j = new ResourceLabel(r);
            j.setQuantity(resourcesToOrganize.get(r));
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

        label1 = new JLabel("Select storage");
        for(int i=1; i <= lightModel.getStorageQuantity().size(); i++) {
            storageN.addItem(i);
        }
        storageN.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        //storageN.setVisible(false);

        label2 = new JLabel("Select quantity");
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

        submit.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        submit.addActionListener(this);
        //submit.setVisible(true);

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
       if (e.getSource().equals(submit)){
           CommandMsg msg = new ManageResourcesToOrganizeMsg((Resource) resourceType.getSelectedItem(), (Integer) storageN.getSelectedItem(), (Integer) quantity.getSelectedItem());
           gui.sendMessage(msg);
           dispose();
        }
    }
}
