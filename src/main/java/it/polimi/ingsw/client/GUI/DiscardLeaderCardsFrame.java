package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.leadermanage.ActivateLeaderMsg;
import it.polimi.ingsw.messages.commands.leadermanage.DiscardLeaderMsg;
import it.polimi.ingsw.model.LeaderCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscardLeaderCardsFrame extends JFrame implements ActionListener, MouseListener {
    private Gui gui;
    private LightModel lightModel;
    private JPanel head;
    private JPanel body;
    private JLabel text;
    private JButton submit;
    private List<JLabel> jl;
    private int card1;
    private int card2;
    private JPanel contentPane;
    private int i;
    private LeaderCard cardSelected = null;
    Map<JLabel, LeaderCard> labelMap;
    Map<LeaderCard, Integer> leaderMap;

    public DiscardLeaderCardsFrame(Gui gui, LightModel lightModel){
        this.gui = gui;
        this.lightModel = lightModel;
        labelMap = new HashMap<>();
        leaderMap = new HashMap<>();

        head = new JPanel();
        body = new JPanel();
        text = new JLabel();
        submit = new JButton("submit");
        contentPane = new JPanel();

        JPanel textPanel = new JPanel();
        text.setText("Select Leader Card to activate");
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
        int i=0;
        for(LeaderCard ld : lightModel.getLeaderCardsInHand()){
            //ImageIcon img = new ImageIcon(new ImageIcon("src/main/resources/LeaderCards/"+ ld.getPath()).getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = DiscardLeaderCardsFrame.class.getResourceAsStream("src/main/resources/LeaderCards/"+ ld.getPath());
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(200, 300, Image.SCALE_SMOOTH)));

            i++;
            slotLabel.setHorizontalAlignment(JLabel.CENTER);
            slotLabel.setVerticalAlignment(JLabel.CENTER);
            slotLabel.setBorder(BorderFactory.createLineBorder(Color.green,3));
            slotLabel.setVisible(true);
            labelMap.put(slotLabel,ld);
            leaderMap.put(ld,i);
            jl.add(slotLabel);
        }

        body.setLayout(new GridLayout(1,jl.size(), 2, 2));
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
        if(cardSelected!=null){
            //System.out.println("");
            gui.sendMessage(new DiscardLeaderMsg(leaderMap.get(cardSelected)));
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JLabel) {
            for(JLabel l: jl){
                l.setBorder(BorderFactory.createLineBorder(Color.green,3));
            }
            cardSelected = labelMap.get((JLabel) e.getSource());
            JLabel j = (JLabel) e.getSource();
            j.setBorder(BorderFactory.createLineBorder(Color.red, 3));
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
