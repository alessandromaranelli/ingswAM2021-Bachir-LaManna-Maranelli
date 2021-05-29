package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.LeaderCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LeaderCardsFrame extends JFrame {
    JPanel hand;
    JPanel board;
    List<LeaderCard> leaderCardsInHand;
    List<LeaderCard> leaderCardsPlayed;
    List<JLabel> cardsInHand;
    List<JLabel> cardsPlayed;

    public LeaderCardsFrame(LightModel lightModel) {
        leaderCardsInHand = new ArrayList<>();
        leaderCardsPlayed = new ArrayList<>();
        setTitle("Leader cards");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(2000, 2000));
        setResizable(true);
        setBackground(Color.CYAN);
        setLayout(new GridLayout(2,1 ));

        leaderCardsInHand = lightModel.getLeaderCardsInHand();
        leaderCardsPlayed = lightModel.getLeaderCardsPlayed();

        hand = new JPanel();
        hand.setLayout(new FlowLayout());
        hand.setBackground(Color.CYAN);
        //hand.setPreferredSize(new Dimension(1000, 1000));

        board = new JPanel();
        board.setLayout(new FlowLayout());
        //board.setPreferredSize(new Dimension(1000, 1000));
        board.setBackground(Color.GRAY);

        cardsInHand = new ArrayList<>();
        cardsPlayed = new ArrayList<>();

        create();
    }

    /*
    public void paint(Graphics g) {
        JLabel jLabel1 = new JLabel("Leader Cards in hand");
        jLabel1.setForeground(Color.ORANGE);
        jLabel1.setFont(new Font("Comic Sans", Font.BOLD, 10));
        jLabel1.setBorder(BorderFactory.createEtchedBorder());
        add(jLabel1);


        ClassLoader cl = this.getClass().getClassLoader();
        int x = 10;
        int y = 30;

        for (LeaderCard c : leaderCardsInHand) {
            InputStream url = cl.getResourceAsStream("LeaderCards/" + c.getPath());

            BufferedImage img = null;
            try {
                img = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int W = img.getWidth();
            int H = img.getHeight();

            g.drawImage(img, x, y, W / 2, H / 2, null);
            x += W / 2 + 10;
        }


        JLabel jLabel2 = new JLabel("Leader Cards played:");
        jLabel2.setForeground(Color.ORANGE);
        jLabel2.setFont(new Font("Comic Sans", Font.BOLD, 10));
        jLabel2.setBorder(BorderFactory.createEtchedBorder());
        add(jLabel2);


        ClassLoader cl1 = this.getClass().getClassLoader();
        x = 10;
        y = 400;

        for (LeaderCard c : leaderCardsPlayed) {
            InputStream url = cl.getResourceAsStream("LeaderCards/" + c.getPath());

            BufferedImage img = null;
            try {
                img = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int W = img.getWidth();
            int H = img.getHeight();

            g.drawImage(img, x, y, W / 2, H / 2, null);
            x += W / 2 + 10;
        }
    }


     */


    public void create() {
        int i = 0;
        JLabel jLabel1 = new JLabel("Leader Cards in hand: ");
        jLabel1.setForeground(Color.ORANGE);
        jLabel1.setFont(new Font("Comic Sans", Font.BOLD, 20));
        jLabel1.setBorder(BorderFactory.createEtchedBorder());
        hand.add(jLabel1);

        for (LeaderCard c : leaderCardsInHand) {
            ImageIcon image = new ImageIcon(getClass().getResource("/LeaderCards/" + c.getPath()));
            Image img = image.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);

            cardsInHand.add(new JLabel(new ImageIcon(img)));
            cardsInHand.get(i).setHorizontalAlignment(SwingConstants.CENTER);
            cardsInHand.get(i).setVerticalAlignment(SwingConstants.CENTER);
            hand.add(cardsInHand.get(i));
            i++;
        }


        i = 0;
        JLabel jLabel2 = new JLabel("Leader Cards played: ");
        jLabel2.setForeground(Color.ORANGE);
        jLabel2.setFont(new Font("Comic Sans", Font.BOLD, 20));
        jLabel2.setBorder(BorderFactory.createEtchedBorder());
        board.add(jLabel2);

        for (LeaderCard c : leaderCardsPlayed) {
            ImageIcon image = new ImageIcon(getClass().getResource("/LeaderCards/" + c.getPath()));
            Image img = image.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);

            cardsPlayed.add(new JLabel(new ImageIcon(img)));
            cardsPlayed.get(i).setHorizontalAlignment(SwingConstants.CENTER);
            cardsPlayed.get(i).setVerticalAlignment(SwingConstants.CENTER);
            board.add(cardsPlayed.get(i));
            i++;
        }

        add(board);
        add(hand);
        pack();
        setVisible(true);
    }
}