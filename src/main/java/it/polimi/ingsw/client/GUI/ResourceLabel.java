package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;

public class ResourceLabel extends JLabel {
    public ResourceLabel(Resource r){
        ImageIcon img;
        switch(r){
            case SERVANT:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/servant.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                break;
            case SHIELD:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/shield.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                break;
            case STONE:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/stone.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                break;
            default:
                img = new ImageIcon(new ImageIcon("src/main/resources/Resources/coin.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(TOP);
        setIcon(img);
        setVisible(true);
    }
    public void setQuantity(int i){
        setText("" + i);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
    }
}
