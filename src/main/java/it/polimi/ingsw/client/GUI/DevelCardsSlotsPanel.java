package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.DevelopmentCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * The type Devel cards slots panel. It contains three slots for the development cards
 */
public class DevelCardsSlotsPanel extends JPanel {
    private LightModel lightModel;

    /**
     * Instantiates a new Devel cards slots panel.
     *
     * @param lightModel the light model
     */
    public DevelCardsSlotsPanel(LightModel lightModel) {
        this.lightModel = lightModel;
        setBackground(new Color(206,119,42, 51));
        List<JLabel> jl = new ArrayList<>();

        for(int i=1; i <= 3; i++){
            if(lightModel.getDevelopmentCard2().get(i) == null){
                //ImageIcon image = new ImageIcon(getClass().getResource("/DevelopmentCards/slot.png"));
                //Image img = image.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);

                InputStream resourceAsStream = DevelCardsSlotsPanel.class.getResourceAsStream("/DevelopmentCards/slot.png");
                Image img = null;
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(200, 300, Image.SCALE_SMOOTH)));
                //slotLabel.setText("Slot " + slotN);
                //slotLabel.setIcon(img);
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
                //ImageIcon img = new ImageIcon(new ImageIcon("src/main/resources/DevelopmentCards/" + card.getPath()).getImage().getScaledInstance(130, 300, Image.SCALE_SMOOTH));

                //slotLabel.setText("Slot " + slotN);

                InputStream resourceAsStream = DevelCardsSlotsPanel.class.getResourceAsStream("/DevelopmentCards/" + card.getPath());
                Image img = null;
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(200, 300, Image.SCALE_SMOOTH)));
                slotLabel.setHorizontalTextPosition(JLabel.CENTER);
                slotLabel.setVerticalTextPosition(JLabel.BOTTOM);
                slotLabel.setHorizontalAlignment(JLabel.CENTER);
                slotLabel.setVerticalAlignment(JLabel.CENTER);
                slotLabel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
                slotLabel.setVisible(true);
                jl.add(slotLabel);
            }
        }

        for(JLabel j: jl){
            add(j);
        }
        setVisible(true);
        setLayout(new GridLayout(1, 3));
    }

}
