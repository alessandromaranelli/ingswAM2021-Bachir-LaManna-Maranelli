package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.model.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLabel extends JLabel {
    public ResourceLabel(Resource r){
        Image img = null;
        ImageIcon icon = null;

            if(r == Resource.SERVANT) {
                InputStream resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/servant.png");
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            }
            else if(r == Resource.SHIELD) {
                InputStream resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/shield.png");
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            }
            else if(r == Resource.STONE) {
                InputStream resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/stone.png");
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            }
            else if(r == Resource.COIN) {
                InputStream resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/coin.png");
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            }

        setHorizontalAlignment(CENTER);
        setVerticalAlignment(TOP);
        setIcon(icon);
        setVisible(true);
    }
    public void setQuantity(int i){
        setText("" + i);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
    }
}
