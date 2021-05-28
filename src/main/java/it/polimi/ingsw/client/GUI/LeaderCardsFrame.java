package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.LeaderCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LeaderCardsFrame extends JFrame {
    List<LeaderCard> leaderCardsInHand;
    List<LeaderCard> leaderCardsPlayed;

    public LeaderCardsFrame(LightModel lightModel) {
        setTitle("Leader cards");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2000, 2000);
        getContentPane().setBackground(Color.CYAN);
        setLayout(new GridLayout(3, 4, 20, 20));

        leaderCardsInHand = lightModel.getLeaderCardsInHand();
        leaderCardsPlayed = lightModel.getLeaderCardsPlayed();

        paint(this.getGraphics());

        pack();
        setVisible(true);
    }

    public void paint(Graphics g) {
        ClassLoader cl = this.getClass().getClassLoader();
        int x = 10;
        int y = 30;

        for (LeaderCard c : leaderCardsInHand) {
            InputStream url = cl.getResourceAsStream(c.getPath());

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
}