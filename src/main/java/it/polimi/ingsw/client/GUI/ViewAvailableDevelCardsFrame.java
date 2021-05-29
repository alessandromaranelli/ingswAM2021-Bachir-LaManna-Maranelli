package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.BuyCardMsg;
import it.polimi.ingsw.model.DevelopmentCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
public class ViewAvailableDevelCardsFrame extends JFrame{
    List<JLabel> jl;
    LightModel lightModel;
    Gui gui;

    public ViewAvailableDevelCardsFrame(LightModel lightModel, Gui gui){
        jl = new ArrayList<>();
        setLayout(new GridLayout(3,4, 5,8));
        setMinimumSize(new Dimension(500,700));
        this.lightModel=lightModel;
        this.gui = gui;

        for(DevelopmentCard dc : lightModel.getDevelopmentCardsToBuy()){
            //immagine ridimensionata
            ImageIcon img = new ImageIcon(new ImageIcon("src/main/resources/DevelopmentCards/"+ dc.getPath()).getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH));
            //System.out.println(dc.getPath());
            JLabel slotLabel = new JLabel();
            slotLabel.setIcon(img);
            slotLabel.setHorizontalAlignment(JLabel.CENTER);
            slotLabel.setVerticalAlignment(JLabel.CENTER);
            slotLabel.setBorder(BorderFactory.createLineBorder(Color.green,3));
            slotLabel.setVisible(true);
            jl.add(slotLabel);
        }
        for(JLabel j: jl){
            add(j);
        }

        setVisible(true);
    }


}
