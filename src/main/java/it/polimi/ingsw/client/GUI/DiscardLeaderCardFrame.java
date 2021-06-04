package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.preparation.DiscardLeadersAtTheStartMsg;
import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.LeaderCard;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscardLeaderCardFrame extends JFrame implements ActionListener, MouseListener {
    Gui gui;
    LightModel lightModel;
    JPanel head;
    JPanel body;
    JLabel text;
    JButton submit;
    List<JLabel> jl;
    int card1;
    int card2;
    JPanel contentPane;
    int i;

    public DiscardLeaderCardFrame(Gui gui, LightModel lightModel) {
        this.gui = gui;
        this.lightModel = lightModel;
        i=1;
        card1 = -1;
        card2 = -1;
        head = new JPanel();
        body = new JPanel();
        text = new JLabel();
        submit = new JButton("submit");
        contentPane = new JPanel();

        JPanel textPanel = new JPanel();
        text.setText("Select Leader Cards to discard");
        text.setVisible(true);
        textPanel.add(text);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        textPanel.setBounds(new Rectangle(5,40,335,100));
        textPanel.setVisible(true);
        submit.addActionListener(this);
        submit.setVisible(true);

        head.add(textPanel);
        head.add(submit);

        jl = new ArrayList<>();
        for(LeaderCard dc : lightModel.getLeaderCardsInHand()){
            ImageIcon img = new ImageIcon(new ImageIcon("src/main/resources/LeaderCards/"+ dc.getPath()).getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH));

            JLabel slotLabel = new JLabel();
            slotLabel.setIcon(img);
            slotLabel.setHorizontalAlignment(JLabel.CENTER);
            slotLabel.setVerticalAlignment(JLabel.CENTER);
            slotLabel.setBorder(BorderFactory.createLineBorder(Color.green,3));
            slotLabel.setVisible(true);
            jl.add(slotLabel);
        }

        body.setLayout(new GridLayout(1,4, 2, 2));
        for(JLabel j: jl){
            j.addMouseListener(this);
            body.add(j);
        }

        head.setVisible(true);
        body.setVisible(true);

        contentPane.add(head);
        contentPane.add(body);
        contentPane.setVisible(true);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        setContentPane(contentPane);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(card1 != -1 && card2 != -1){
            CommandMsg msg = new DiscardLeadersAtTheStartMsg(card1+1, card2+1);
            gui.sendMessage(msg);
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JLabel) {
            if(i == 1){
                for(int j=0; j < 4; j++) {
                    if(j != jl.indexOf(e.getSource())) {
                        jl.get(j).setBorder(BorderFactory.createLineBorder(Color.green, 3));
                    }
                    else{
                        jl.get(j).setBorder(BorderFactory.createLineBorder(Color.red, 3));

                        card1 = j;
                        i = i+1;
                    }
                }
            }
            else if(i == 2){
                if(jl.indexOf(e.getSource()) == card1) return;

                for(int j=0; j < 4; j++) {
                    if(j != jl.indexOf(e.getSource()) && j != card1) {
                        jl.get(j).setBorder(BorderFactory.createLineBorder(Color.green, 3));
                    }
                    else if(j == jl.indexOf(e.getSource())){
                        jl.get(j).setBorder(BorderFactory.createLineBorder(Color.red, 3));

                        card2 = j;
                        i = i+1;
                    }
                }
            }
            /*
            else{
                if(jl.indexOf(e.getSource()) == card1 || jl.indexOf(e.getSource()) == card2) return;

                for(int j=0; j < 4; j++){
                    if(j == card1){
                        jl.get(j).setBorder(BorderFactory.createLineBorder(Color.green, 3));
                    }
                    else if(j == jl.indexOf(e.getSource())){
                        jl.get(j).setBorder(BorderFactory.createLineBorder(Color.red, 3));
                    }

                    card1 = card2;
                    card2 = j;
                }
            }
            */
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
