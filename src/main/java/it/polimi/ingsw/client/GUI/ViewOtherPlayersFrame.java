package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.ViewOtherPlayerNumberMsg;
import it.polimi.ingsw.messages.commands.preparation.AddInitResourcesMsg;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type View other players frame. It displays the chest, storages, faithtrack and leader cards of another player
 */
public class ViewOtherPlayersFrame extends JFrame implements ActionListener {
    private Gui gui;
    private LightModel lightModel;
    private JLabel label;
    private JComboBox<Integer> choice;
    private JButton button;
    private JPanel panel;

    /**
     * Instantiates a new View other players frame.
     *
     * @param gui        the gui
     * @param lightModel the light model
     */
    public ViewOtherPlayersFrame(Gui gui, LightModel lightModel){
        this.gui = gui;
        this.lightModel = lightModel;
        panel = new JPanel();

        label = new JLabel("Select playerID: ");
        label.setForeground(Color.BLACK);
        label.setBackground(Color.YELLOW);
        label.setFont(new Font("Comic Sans", Font.BOLD, 20));
        panel.add(label);

        choice = new JComboBox<>();
        choice.addItem(1);
        choice.addItem(2);
        choice.addItem(3);
        choice.addItem(4);
        panel.add(choice);

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
            CommandMsg msg = new ViewOtherPlayerNumberMsg((Integer) choice.getSelectedItem());
            gui.sendMessage(msg);
            dispose();
        }
    }
}
