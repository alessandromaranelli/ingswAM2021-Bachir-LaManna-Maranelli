package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.awt.*;

public class MarbleLabel extends JLabel {
    public MarbleLabel(Marble m){
        ImageIcon img=null;
        if (m instanceof WhiteMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliabianca.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof RedMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliarossa.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof YellowMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliagialla.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof PurpleMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliaviola.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof BlueMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliablu.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof GreyMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/biglianera.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(TOP);
        setIcon(img);
        setVisible(true);
    }

    public MarbleLabel(Marble m, String text){
        ImageIcon img=null;
        if (m instanceof WhiteMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliabianca.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof RedMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliarossa.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof YellowMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliagialla.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof PurpleMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliaviola.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof BlueMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/bigliablu.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        if (m instanceof GreyMarble)img = new ImageIcon(new ImageIcon("src/main/resources/Resources/biglianera.JPG").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(TOP);
        setIcon(img);
        setText(text);
        setVerticalTextPosition(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.CENTER);
        setVisible(true);
    }
}
