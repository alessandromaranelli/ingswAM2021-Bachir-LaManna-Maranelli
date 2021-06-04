package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.DevelopmentCard;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DevelCardsSlotsPanel extends JPanel {
    LightModel lightModel;

    public DevelCardsSlotsPanel(LightModel lightModel) {

        this.lightModel = lightModel;
        setBackground(new Color(206,119,42, 51));
        setBorder(BorderFactory.createLineBorder(Color.green,3));
        int slotN = 0;
        List<JLabel> jl = new ArrayList<>();


        //---------------per provare-----------------
        /*ArrayList<String> d = new ArrayList<>();
        d.add("src/main/resources/DevelopmentCards/Masters of Renaissance_Cards_FRONT_3mmBleed_1-18-1.png");
        d.add("src/main/resources/DevelopmentCards/Masters of Renaissance_Cards_FRONT_3mmBleed_1-30-1.png");
        d.add("src/main/resources/DevelopmentCards/Masters of Renaissance_Cards_FRONT_3mmBleed_1-3-1.png");
        for(String s:d){*/
        for(DevelopmentCard dc: lightModel.getDevelopmentCard()){
            slotN = lightModel.getDevelopmentCard().indexOf(dc);
            //ImageIcon img = new ImageIcon((new ImageIcon(s).getImage().getScaledInstance(130, 300, Image.SCALE_SMOOTH)));

            //immagine ridimensionata
            ImageIcon img = new ImageIcon(new ImageIcon("src/main/resources/DevelopmentCards/"+ dc.getPath()).getImage().getScaledInstance(130, 300, Image.SCALE_SMOOTH));
            //System.out.println(dc.getPath());
            JLabel slotLabel = new JLabel();
            slotLabel.setText("Slot " + slotN);
            slotLabel.setIcon(img);
            slotLabel.setHorizontalTextPosition(JLabel.CENTER);
            slotLabel.setVerticalTextPosition(JLabel.BOTTOM);
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
