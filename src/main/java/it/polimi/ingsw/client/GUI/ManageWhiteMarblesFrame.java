package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.marketphase.ManageWhiteMarbleMsg;
import it.polimi.ingsw.messages.commands.marketphase.SetStorageTypesMsg;
import it.polimi.ingsw.messages.commands.preparation.SetInitStorageTypeMsg;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Manage white marbles frame. It displays and
 */
public class ManageWhiteMarblesFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private JPanel jPanel;
    private JLabel jLabel;
    private List<JComboBox<Resource>> boxList;
    private int whiteMarbles;
    private JButton button;

    /**
     * Instantiates a new Manage white marbles frame.
     *
     * @param gui        the gui
     * @param lightModel the light model
     * @param n          the n
     */
    public ManageWhiteMarblesFrame(Gui gui, LightModel lightModel, int n){
        lightModel = lightModel;
        gui = gui;
        whiteMarbles = n;
        jPanel = new JPanel();
        boxList = new ArrayList<>();

        jLabel = new JLabel("You must choose the color for " + whiteMarbles + " white marbles");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        jPanel.add(jLabel);

        for(int i=0; i < whiteMarbles; i++){
            JComboBox<Resource> box= new JComboBox<>();
            box.addItem(lightModel.getWhiteMarble().get(0));
            if(lightModel.getWhiteMarble().size() > 1 ){
                box.addItem(lightModel.getWhiteMarble().get(1));
            }
            boxList.add(box);
        }

        for(int i=0; i < whiteMarbles; i++){
            jPanel.add(boxList.get(i));
        }

        button = new JButton("Submit");
        button.addActionListener(this);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setBackground(Color.RED);
        button.setForeground(Color.BLACK);
        jPanel.add(button);

        setContentPane(jPanel);
        jPanel.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)){
            for(int i=0; i < whiteMarbles; i++) {
                CommandMsg msg = new ManageWhiteMarbleMsg((Resource) boxList.get(i).getSelectedItem());
                gui.sendMessage(msg);
                dispose();
            }
        }
    }
}
