package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.ViewOtherPlayerNumberMsg;
import it.polimi.ingsw.model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewOtherPlayerFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private List<JLabel> nicknames;
    private JComboBox<Integer> playerID;
    private JButton button;
    private JPanel panel;

    public ViewOtherPlayerFrame(Gui gui, LightModel lightModel){
        this.gui = gui;
        this.lightModel = lightModel;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        button = new JButton("Submit");

        playerID = new JComboBox<>();
        nicknames = new ArrayList<>();
        for(Player p : lightModel.getPlayers()){
            playerID.addItem(p.getPlayerID());
            JLabel label = new JLabel(p.getNickname());
            nicknames.add(label);
        }

        panel.add(playerID);
        panel.add(button);
        for(int i=0; i < nicknames.size(); i++){
            panel.add(nicknames.get(i));
        }
        setContentPane(panel);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)){
            CommandMsg msg = new ViewOtherPlayerNumberMsg((Integer) playerID.getSelectedItem());
            gui.sendMessage(msg);
            dispose();
        }
    }
}
