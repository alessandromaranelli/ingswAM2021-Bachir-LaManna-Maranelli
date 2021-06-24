package it.polimi.ingsw.client.GUI;
import it.polimi.ingsw.client.LightModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FaithTrackPanel extends JPanel{
    private JPanel track;
    private JPanel symbols;
    private JPanel lorenzo;
    private List<JLabel> jLabelList;
    private List<JLabel> lorenzoTrack;
    private List<JLabel> popeFavours;

    public FaithTrackPanel(LightModel lightModel){
        setBackground(Color.RED);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        track = new JPanel();
        track.setBackground(Color.RED);
        symbols = new JPanel();
        symbols.setBackground(Color.RED);
        lorenzo = new JPanel();
        lorenzo.setBackground(Color.RED);
        jLabelList = new ArrayList<>();
        lorenzoTrack = new ArrayList<>();
        popeFavours = new ArrayList<>();

        for(int i=0; i < 25; i++){
            jLabelList.add(new JLabel());
            jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            jLabelList.get(i).setForeground(Color.BLACK);
            jLabelList.get(i).setSize(60, 60);
            if(i == lightModel.getPosition()){
                jLabelList.get(i).setText("X");
            }
            else{
                jLabelList.get(i).setText("" + i);
            }

            track.add(jLabelList.get(i));
        }

        if(lightModel.getPopeFavours()[0] == true) {
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/symbol2.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }
        else{
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/notsymbol2.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }

        if(lightModel.getPopeFavours()[1] == true) {
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/symbol3.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }
        else{
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/notsymbol3.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }

        if(lightModel.getPopeFavours()[2] == true) {
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/symbol4.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }
        else{
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/notsymbol4.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }

        add(track);
        add(symbols);

        if(lightModel.isSoloGame()){
            JLabel label = new JLabel("Lorenzo's FaithTrack");
            label.setFont(new Font("Comic Sans", Font.BOLD, 20));
            label.setForeground(Color.BLUE);
            add(label);
            for(int i=0; i < 25; i++){
                lorenzoTrack.add(new JLabel());
                lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                lorenzoTrack.get(i).setForeground(Color.BLACK);
                lorenzoTrack.get(i).setSize(60, 60);
                if(i == lightModel.getPosition()){
                    lorenzoTrack.get(i).setText("X");
                }
                else{
                    lorenzoTrack.get(i).setText("" + i);
                }

                lorenzo.add(lorenzoTrack.get(i));
            }
            add(lorenzo);
        }

        setVisible(true);
    }


    public FaithTrackPanel(int position, Boolean[] popeFavours){
        setBackground(Color.RED);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        track = new JPanel();
        track.setBackground(Color.RED);
        symbols = new JPanel();
        symbols.setBackground(Color.RED);
        jLabelList = new ArrayList<>();
        //popeFavours = new ArrayList<>();

        for(int i=0; i < 25; i++){
            jLabelList.add(new JLabel());
            jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            jLabelList.get(i).setForeground(Color.BLACK);
            jLabelList.get(i).setSize(60, 60);
            if(i == position){
                jLabelList.get(i).setText("X");
            }
            else{
                jLabelList.get(i).setText("" + i);
            }

            track.add(jLabelList.get(i));
        }

        if(popeFavours[0] == true) {
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/symbol2.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            //popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }
        else{
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/notsymbol2.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            //popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }

        if(popeFavours[1] == true) {
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/symbol3.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            //popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }
        else{
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/notsymbol3.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            //popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }

        if(popeFavours[2] == true) {
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/symbol4.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            //popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }
        else{
            InputStream resourceAsStream = FaithTrackPanel.class.getResourceAsStream("/Resources/notsymbol4.png");
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel slotLabel = new JLabel(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
            //popeFavours.add(slotLabel);
            symbols.add(slotLabel);
        }

        add(track);
        add(symbols);
        setVisible(true);
    }
}
