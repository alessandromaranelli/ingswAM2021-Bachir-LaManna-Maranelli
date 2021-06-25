package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class MarbleLabel extends JLabel {
    public MarbleLabel(Marble m){
        Image img=null;
        ImageIcon icon = null;
        if (m instanceof WhiteMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliabianca.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliabianca.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof RedMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliarossa.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliarossa.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof YellowMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliagialla.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliagialla.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof PurpleMarble){
            //img = new Im ageIcon(new ImageIcon("src/main/resources/Resources/bigliaviola.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliaviola.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof BlueMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliablu.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliablu.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof GreyMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/biglianera.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/biglianera.JPG");
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

    public MarbleLabel(Marble m, String text){
        Image img=null;
        ImageIcon icon = null;
        if (m instanceof WhiteMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliabianca.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliabianca.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof RedMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliarossa.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliarossa.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof YellowMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliagialla.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliagialla.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof PurpleMarble){
            //img = new Im ageIcon(new ImageIcon("src/main/resources/Resources/bigliaviola.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliaviola.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof BlueMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliablu.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/bigliablu.JPG");
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
        if (m instanceof GreyMarble){
            //img = new ImageIcon(new ImageIcon("src/main/resources/Resources/biglianera.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            InputStream resourceAsStream = MarbleLabel.class.getResourceAsStream("/Resources/biglianera.JPG");
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
        setText(text);
        setVerticalTextPosition(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.LEFT);
        setVisible(true);
    }
}
