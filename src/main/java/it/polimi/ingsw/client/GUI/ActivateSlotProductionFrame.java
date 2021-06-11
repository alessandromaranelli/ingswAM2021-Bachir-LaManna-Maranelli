package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.preparation.DiscardLeadersAtTheStartMsg;
import it.polimi.ingsw.messages.commands.productionphase.ActivateProductionMsg;
import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.LeaderCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ActivateSlotProductionFrame extends JFrame implements ActionListener, MouseListener {
    private Gui gui;
    private LightModel lightModel;
    private JPanel head;
    private JPanel body;
    private JLabel text;
    private JButton submit;
    private List<JLabel> jl;
    private int slot;
    private JPanel contentPane;

    public ActivateSlotProductionFrame(Gui gui, LightModel lightModel) {
        this.gui = gui;
        this.lightModel = lightModel;
        slot = -1;
        head = new JPanel();
        body = new JPanel();
        text = new JLabel();
        submit = new JButton("submit");
        contentPane = new JPanel();

        JPanel textPanel = new JPanel();
        text.setText("Select production to activate:");
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
        for(int i = 1; i <= 3; i++){
            if(lightModel.getDevelopmentCard2().get(i) == null){
                ImageIcon image = new ImageIcon(getClass().getResource("/DevelopmentCards/slot.png"));
                Image img = image.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);

                JLabel slotLabel = new JLabel(new ImageIcon(img));
                slotLabel.setHorizontalTextPosition(JLabel.CENTER);
                slotLabel.setVerticalTextPosition(JLabel.BOTTOM);
                slotLabel.setHorizontalAlignment(JLabel.CENTER);
                slotLabel.setVerticalAlignment(JLabel.CENTER);
                slotLabel.setBorder(BorderFactory.createLineBorder(Color.green,3));
                slotLabel.setVisible(true);
                jl.add(slotLabel);
            }
            else {
                DevelopmentCard card = lightModel.getDevelopmentCard2().get(i);
                ImageIcon img = new ImageIcon(new ImageIcon("src/main/resources/DevelopmentCards/" + card.getPath()).getImage().getScaledInstance(130, 300, Image.SCALE_SMOOTH));
                JLabel slotLabel = new JLabel();
                slotLabel.setIcon(img);
                slotLabel.setHorizontalTextPosition(JLabel.CENTER);
                slotLabel.setVerticalTextPosition(JLabel.BOTTOM);
                slotLabel.setHorizontalAlignment(JLabel.CENTER);
                slotLabel.setVerticalAlignment(JLabel.CENTER);
                slotLabel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
                slotLabel.setVisible(true);
                jl.add(slotLabel);
            }
        }

        body.setLayout(new GridLayout(1,3, 2, 2));
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
        if(slot != -1){
            CommandMsg msg = new ActivateProductionMsg(slot+1);
            gui.sendMessage(msg);
            dispose();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JLabel){
            for(int i = 0; i < 3; i++){
                if(i != jl.indexOf(e.getSource())){
                    jl.get(i).setBorder(BorderFactory.createLineBorder(Color.green, 3));
                }
                else{
                    jl.get(i).setBorder(BorderFactory.createLineBorder(Color.red, 3));
                    slot = i;
                }
            }
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
