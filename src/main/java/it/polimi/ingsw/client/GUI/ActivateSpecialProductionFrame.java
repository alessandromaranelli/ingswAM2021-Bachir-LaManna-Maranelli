package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.productionphase.ActivateSpecialProduction;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Activate special production frame. It asks to the player what resource he wants to produce
 */
public class ActivateSpecialProductionFrame extends JFrame implements ActionListener {
    private Gui gui;
    private LightModel lightModel;
    private JPanel panel;
    private JLabel label1;
    private JComboBox<Resource> box1;
    private JButton button;
    private int i;


    /**
     * Instantiates a new Activate special production frame.
     *
     * @param gui        the gui
     * @param lightModel the light model
     * @param i          the
     */
    public ActivateSpecialProductionFrame(Gui gui, LightModel lightModel, int i){
        this.gui = gui;
        this.lightModel = lightModel;
        this.i = i;
        panel = new JPanel();

        label1 = new JLabel("Select resource to produce");
        label1.setBackground(Color.YELLOW);
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        box1 = new JComboBox<>();
        box1.addItem(Resource.STONE);
        box1.addItem(Resource.COIN);
        box1.addItem(Resource.SHIELD);
        box1.addItem(Resource.SERVANT);
        panel.add(box1);

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
            CommandMsg msg = new ActivateSpecialProduction((Resource) box1.getSelectedItem(), i);
            gui.sendMessage(msg);
            dispose();
        }
    }
}
