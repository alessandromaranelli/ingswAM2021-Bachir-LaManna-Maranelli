package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.LeaderCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Leader cards panel. It displays the leader cards
 */
public class LeaderCardsPanel extends JPanel {
    private List<LeaderCard> leaderCards;
    private List<JLabel> cards;

    /**
     * Instantiates a new Leader cards panel.
     *
     * @param leaderCard the leader card
     */
    public LeaderCardsPanel(List<LeaderCard> leaderCard) {
        this.leaderCards = new ArrayList<>(leaderCard);
        setBackground(Color.CYAN);

        this.setLayout(new FlowLayout());
        cards = new ArrayList<>();

        create();
    }


    /**
     * Create.
     */
    public void create() {
        int i = 0;

        for (LeaderCard c : leaderCards) {
            //ImageIcon image = new ImageIcon(getClass().getResource("/LeaderCards/" + c.getPath()));
            //Image img = image.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);

            InputStream resourceAsStream = LeaderCardsPanel.class.getResourceAsStream("/LeaderCards/" + c.getPath());
            Image img = null;
            try {
                img = ImageIO.read(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            cards.add(new JLabel(new ImageIcon(img.getScaledInstance(200, 300, Image.SCALE_SMOOTH))));
            cards.get(i).setHorizontalAlignment(SwingConstants.CENTER);
            cards.get(i).setVerticalAlignment(SwingConstants.CENTER);
            this.add(cards.get(i));
            i++;
        }

        setVisible(true);
    }
}
