package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.preparation.AddInitResourcesMsg;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Add init resources frame. It asks to the player what resource he wants at the start of the game
 */
public class AddInitResourcesFrame extends JFrame implements ActionListener {
    private Gui gui;
    private LightModel lightModel;
    private JLabel label;
    private JComboBox<Resource> firstChoice;
    private JComboBox<Resource> secondChoice;
    private JButton button;
    private JPanel panel;

    /**
     * Instantiates a new Add init resources frame.
     *
     * @param gui        the gui
     * @param lightModel the light model
     */
    public AddInitResourcesFrame(Gui gui, LightModel lightModel){
        this.gui = gui;
        this.lightModel = lightModel;
        panel = new JPanel();

        label = new JLabel("Select init resources:");
        label.setForeground(Color.BLACK);
        label.setBackground(Color.YELLOW);
        label.setFont(new Font("Comic Sans", Font.BOLD, 20));
        panel.add(label);

        firstChoice = new JComboBox<>();
        firstChoice.addItem(Resource.SERVANT);
        firstChoice.addItem(Resource.SHIELD);
        firstChoice.addItem(Resource.COIN);
        firstChoice.addItem(Resource.STONE);
        panel.add(firstChoice);

        if(lightModel.getPlayerID() == 4) {
            secondChoice = new JComboBox<>();
            secondChoice.addItem(Resource.SERVANT);
            secondChoice.addItem(Resource.SHIELD);
            secondChoice.addItem(Resource.COIN);
            secondChoice.addItem(Resource.STONE);
            panel.add(secondChoice);
        }

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
            if(lightModel.getPlayerID() != 4){
                CommandMsg msg = new AddInitResourcesMsg((Resource) firstChoice.getSelectedItem());
                gui.sendMessage(msg);
            }
            else{
                CommandMsg msg = new AddInitResourcesMsg((Resource) firstChoice.getSelectedItem(), (Resource) secondChoice.getSelectedItem());
                gui.sendMessage(msg);
            }
            dispose();
        }
    }
}
