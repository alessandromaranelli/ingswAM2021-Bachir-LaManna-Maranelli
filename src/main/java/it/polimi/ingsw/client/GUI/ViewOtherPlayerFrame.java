package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.ViewOtherPlayerNumberMsg;
import it.polimi.ingsw.model.Player;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewOtherPlayerFrame extends JFrame implements ActionListener {
    private LightModel lightModel;
    private Gui gui;
    private List<JLabel> nicknames;
    private JComboBox<Integer> playerID;
    private JButton submit;
    private JPanel panel;
    private JLabel label;

    public ViewOtherPlayerFrame(Gui gui, LightModel lightModel){
        this.gui = gui;
        this.lightModel = lightModel;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        submit = new JButton("Submit");
        submit.setBorder(new EtchedBorder());
        label.setForeground(Color.RED);
        label = new JLabel("Select playerID to see");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);

        playerID = new JComboBox<>();
        nicknames = new ArrayList<>();
        /*
        for(Player p : lightModel.getPlayers()){
            playerID.addItem(p.getPlayerID());
            JLabel label = new JLabel(p.getNickname());
            nicknames.add(label);
        }
         */
        for(int i=1; i <= 4; i++){
            playerID.addItem(i);
        }
        panel.add(label);
        panel.add(playerID);
        panel.add(submit);
        /*
        for(int i=0; i < nicknames.size(); i++){
            panel.add(nicknames.get(i));
        }
         */
        setContentPane(panel);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(submit)){
            //System.out.println("AA");
            CommandMsg msg = new ViewOtherPlayerNumberMsg((Integer) playerID.getSelectedItem());
            gui.sendMessage(msg);
            dispose();
        }
    }
}
