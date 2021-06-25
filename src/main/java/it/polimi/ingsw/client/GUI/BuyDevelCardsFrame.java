package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.BuyCardMsg;
import it.polimi.ingsw.model.DevelopmentCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
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

public class BuyDevelCardsFrame extends JFrame implements ActionListener, MouseListener {
    Gui gui;
    LightModel lightModel;
    ArrayList<JComponent> components; //clickable develCard + inputTextField for destinstion slot
    JPanel head;
    JPanel body;
    JComboBox<Integer> slot;
    JLabel text;
    JButton submit;
    List<JLabel> jl;
    DevelopmentCard cardSelected = null;
    Map<JLabel,DevelopmentCard> labelMap;
    JPanel contentPane;

    public BuyDevelCardsFrame(Gui gui, LightModel lightModel) {
        this.gui = gui;
        this.lightModel = lightModel;
        head = new JPanel();
        body = new JPanel();
        slot = new JComboBox<Integer>();
        text = new JLabel();
        submit = new JButton("submit");
        labelMap = new HashMap<>();
        contentPane = new JPanel();

        slot.addItem(1);
        slot.addItem(2);
        slot.addItem(3);
        slot.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        slot.setVisible(true);


        JPanel textPanel = new JPanel();
        text.setText("Select a Card and the destination Slot");
        text.setVisible(true);
        textPanel.add(text);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        textPanel.setBounds(new Rectangle(5,40,335,100));
        textPanel.setVisible(true);
        submit.addActionListener(this);
        submit.setVisible(true);

        head.add(textPanel);
        head.add(slot);
        head.add(submit);

        jl = new ArrayList<>();
        for(DevelopmentCard dc : lightModel.getDevelopmentCardsToBuy()){
            //immagine ridimensionata
            //ImageIcon img = new ImageIcon(new ImageIcon("src/main/resources/DevelopmentCards/"+ dc.getPath()).getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH));

            InputStream resourceAsStream = BuyDevelCardsFrame.class.getResourceAsStream("/DevelopmentCards/"+ dc.getPath());
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(200, 240, Image.SCALE_SMOOTH)));
            slotLabel.setHorizontalAlignment(JLabel.CENTER);
            slotLabel.setVerticalAlignment(JLabel.CENTER);
            slotLabel.setBorder(BorderFactory.createLineBorder(Color.green,3));
            slotLabel.setVisible(true);
            labelMap.put(slotLabel,dc);
            jl.add(slotLabel);
        }

        body.setLayout(new GridLayout(3,4, 2, 2));
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
            gui.sendMessage(new BuyCardMsg(cardSelected.getColor(),cardSelected.getLevel(),slot.getItemAt(slot.getSelectedIndex())));
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
