package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.BuyDevelopmentPhaseMsg;
import it.polimi.ingsw.messages.commands.preparation.DrawLeadersMsg;
import it.polimi.ingsw.model.TurnState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    Gui gui;
    LightModel lightModel;
    JButton viewMarket;
    JButton viewLeaderCards;
    JButton viewAvailableDevelCards;
    JButton drawLeaderCards;
    JButton buyDevelopmentCard;
    JTextField discardLeaderCards;

    public ButtonPanel(LightModel lightModel, Gui gui){
        this.lightModel = lightModel;
        this.gui = gui;
        setBackground(Color.CYAN);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        viewMarket = new JButton("View Market");
        viewMarket.addActionListener(this);
        viewMarket.setFont(new Font("Comic Sans", Font.BOLD, 20));
        viewMarket.setForeground(Color.BLUE);
        viewMarket.setBackground(Color.ORANGE);
        viewMarket.setBorder(BorderFactory.createEtchedBorder());

        viewLeaderCards = new JButton("View Leader Cards");
        viewLeaderCards.addActionListener(this);
        viewLeaderCards.setFont(new Font("Comic Sans", Font.BOLD, 20));
        viewLeaderCards.setForeground(Color.BLUE);
        viewLeaderCards.setBackground(Color.ORANGE);
        viewLeaderCards.setBorder(BorderFactory.createEtchedBorder());

        drawLeaderCards = new JButton("Draw Leader Cards");
        drawLeaderCards.addActionListener(this);
        drawLeaderCards.setFont(new Font("Comic Sans", Font.BOLD, 20));
        drawLeaderCards.setForeground(Color.BLUE);
        drawLeaderCards.setBackground(Color.ORANGE);
        drawLeaderCards.setBorder(BorderFactory.createEtchedBorder());

        discardLeaderCards = new JTextField();
        //discardLeaderCards.setSize(new Dimension(5, 5));
        discardLeaderCards.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        discardLeaderCards.setForeground(Color.BLUE);
        discardLeaderCards.setBackground(Color.ORANGE);
        discardLeaderCards.setText("Choose leader cards to discard");
        //drawLeaderCards.setBorder(BorderFactory.createEtchedBorder());

        viewAvailableDevelCards = new JButton("View available Development Cards");
        viewAvailableDevelCards.addActionListener(this);
        viewAvailableDevelCards.setFont(new Font("Comic Sans", Font.BOLD, 20));
        viewAvailableDevelCards.setForeground(Color.BLUE);
        viewAvailableDevelCards.setBackground(Color.ORANGE);
        viewAvailableDevelCards.setBorder(BorderFactory.createEtchedBorder());

        buyDevelopmentCard = new JButton("Buy Card");
        buyDevelopmentCard.addActionListener(this);
        buyDevelopmentCard.setFont(new Font("Comic Sans", Font.BOLD, 20));
        buyDevelopmentCard.setForeground(Color.BLUE);
        buyDevelopmentCard.setBackground(Color.ORANGE);
        buyDevelopmentCard.setBorder(BorderFactory.createEtchedBorder());

        setVisibleButtons(lightModel.getPhase());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(viewMarket)){
            JFrame jFrame = new MarketFrame(lightModel);
        }
        else if(e.getSource().equals(viewLeaderCards)){
            LeaderCardsFrame jFrame = new LeaderCardsFrame(lightModel);
        }
        else if(e.getSource().equals(drawLeaderCards)){
            DrawLeadersMsg drawLeadersMsg = new DrawLeadersMsg();
            gui.sendMessage(drawLeadersMsg);
        }
        else if(e.getSource().equals(discardLeaderCards)){
            //.......
        }
        else if(e.getSource().equals(viewAvailableDevelCards)){
            new ViewAvailableDevelCardsFrame(lightModel,gui);
        }
        else if(e.getSource().equals(buyDevelopmentCard)){
            System.out.println("Sei stra babbo");
            //gui.sendMessage(new BuyDevelopmentPhaseMsg());
            new BuyDevelCardsFrame(gui,lightModel);
        }
    }

    private void setVisibleButtons(TurnState phase){
        add(viewMarket);
        add(viewLeaderCards);
        add(viewAvailableDevelCards);
        add(buyDevelopmentCard);

        //add(viewDevelopmentCardsToBuy)
        if(phase == TurnState.ENDTURN || phase == TurnState.ENDPREPARATION){
            //comando per terminare il turno e riorganizzare le risorse negli storage
        }
        if(phase ==  TurnState.PREPARATION){
            add(drawLeaderCards);
        }
        if(phase == TurnState.CHOOSELEADERCARDS){
            add(discardLeaderCards);
        }
        /*
        if(phase == TurnState.START){
            add(buyDevelopmentCard);
        }
*/
        setVisible(true);
    }
}
