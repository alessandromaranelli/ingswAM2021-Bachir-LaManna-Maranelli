package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;

public class ResourceLabel extends JLabel {
    JLabel j;
    public ResourceLabel(Resource r){
        j = new JLabel();
        ImageIcon img;
        switch(r){
            case SERVANT:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/servant.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                break;
            case SHIELD:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/shield.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                break;
            case STONE:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/stone.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                break;
            default:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/coin.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }
        j.setHorizontalAlignment(CENTER);
        j.setVerticalAlignment(TOP);
        j.setIcon(img);
        j.setVisible(true);
    }
    public void setQuantity(int i){
        j.setText("" + i);
        j.setVerticalTextPosition(JLabel.BOTTOM);
        j.setHorizontalTextPosition(JLabel.CENTER);
    }
}
