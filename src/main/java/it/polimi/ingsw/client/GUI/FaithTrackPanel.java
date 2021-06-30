package it.polimi.ingsw.client.GUI;
import it.polimi.ingsw.client.LightModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Faith track panel. It displays the faithtrack
 */
public class FaithTrackPanel extends JPanel{
    private JPanel track;
    private JPanel symbols;
    private JPanel lorenzo;
    private List<JLabel> jLabelList;
    private List<JLabel> lorenzoTrack;
    private List<JLabel> popeFavours;

    /**
     * Instantiates a new Faith track panel.
     *
     * @param lightModel the light model
     */
    public FaithTrackPanel(LightModel lightModel){
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        track = new JPanel();
        track.setBackground(Color.GRAY);
        symbols = new JPanel();
        symbols.setLayout(new GridLayout(1, 3));
        symbols.setBackground(Color.GRAY);
        lorenzo = new JPanel();
        lorenzo.setBackground(Color.GRAY);
        jLabelList = new ArrayList<>();
        lorenzoTrack = new ArrayList<>();
        popeFavours = new ArrayList<>();

        for(int i=0; i < 25; i++){
            jLabelList.add(new JLabel());
            jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            jLabelList.get(i).setForeground(Color.BLUE);
            jLabelList.get(i).setSize(60, 60);
            if(i >= 5 && i < 8){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            }
            if(i == 8){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
                jLabelList.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                jLabelList.get(i).setForeground(Color.YELLOW);
            }
            if(i >= 12 && i < 16){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
            }
            if(i == 16){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
                jLabelList.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                jLabelList.get(i).setForeground(Color.ORANGE);
            }
            if(i >= 19 && i < 24){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            if(i == 24){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                jLabelList.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                jLabelList.get(i).setForeground(Color.RED);
            }
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

        JLabel lab = new JLabel("Your ID: " + lightModel.getPlayerID());
        lab.setFont(new Font("Comic Sans", Font.BOLD, 20));
        lab.setForeground(Color.BLACK);
        add(lab);
        add(track);
        add(symbols);

        if(lightModel.isSoloGame()){
            JLabel label = new JLabel("Lorenzo's FaithTrack");
            label.setFont(new Font("Comic Sans", Font.BOLD, 20));
            label.setForeground(Color.BLACK);
            add(label);
            for(int i=0; i < 25; i++){
                lorenzoTrack.add(new JLabel());
                lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                lorenzoTrack.get(i).setForeground(Color.BLACK);
                lorenzoTrack.get(i).setSize(60, 60);
                if(i >= 5 && i < 8){
                    lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                }
                if(i == 8){
                    lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
                    lorenzoTrack.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                    lorenzoTrack.get(i).setForeground(Color.YELLOW);
                }
                if(i >= 12 && i < 16){
                    lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
                }
                if(i == 16){
                    lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
                    lorenzoTrack.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                    lorenzoTrack.get(i).setForeground(Color.ORANGE);
                }
                if(i >= 19 && i < 24){
                    lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                }
                if(i == 24){
                    lorenzoTrack.get(i).setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                    lorenzoTrack.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                    lorenzoTrack.get(i).setForeground(Color.RED);
                }
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


    /**
     * Instantiates a new Faith track panel.
     *
     * @param position    the position
     * @param popeFavours the pope favours
     */
    public FaithTrackPanel(int position, Boolean[] popeFavours, String nickname){
        setBackground(Color.GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        track = new JPanel();
        track.setBackground(Color.GRAY);
        symbols = new JPanel();
        symbols.setLayout(new GridLayout(1, 3));
        symbols.setBackground(Color.GRAY);
        jLabelList = new ArrayList<>();
        //popeFavours = new ArrayList<>();

        for(int i=0; i < 25; i++){
            jLabelList.add(new JLabel());
            jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            jLabelList.get(i).setForeground(Color.BLUE);
            jLabelList.get(i).setSize(60, 60);
            if(i >= 5 && i < 8){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            }
            if(i == 8){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
                jLabelList.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                jLabelList.get(i).setForeground(Color.YELLOW);
            }
            if(i >= 12 && i < 16){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
            }
            if(i == 16){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
                jLabelList.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                jLabelList.get(i).setForeground(Color.ORANGE);
            }
            if(i >= 19 && i < 24){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            if(i == 24){
                jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                jLabelList.get(i).setFont(new Font("Comic Sans", Font.BOLD, 18));
                jLabelList.get(i).setForeground(Color.RED);
            }
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

        JLabel lab = new JLabel(nickname);
        lab.setFont(new Font("Comic Sans", Font.BOLD, 20));
        lab.setForeground(Color.BLACK);
        add(lab);
        add(track);
        add(symbols);
        setVisible(true);
    }
}
