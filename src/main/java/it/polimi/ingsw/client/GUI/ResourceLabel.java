package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.model.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLabel extends JLabel {
    public ResourceLabel(Resource r) {
        InputStream resourceAsStream;
        Image img = null;
        ImageIcon imgIcon = null;
        switch(r){
            case SERVANT:
                resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/servant.png");
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgIcon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                break;
            case SHIELD:
                resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/shield.png");
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgIcon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                break;
            case STONE:
                resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/stone.png");
                try {
                    img =
                            ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgIcon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                break;
            default:
                resourceAsStream = ResourceLabel.class.getResourceAsStream("/Resources/coin.png");
                try {
                    img = ImageIO.read(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgIcon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(TOP);
        setIcon(imgIcon);
        setVisible(true);
    }
    public void setQuantity(int i){
        setText("" + i);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
    }
}
