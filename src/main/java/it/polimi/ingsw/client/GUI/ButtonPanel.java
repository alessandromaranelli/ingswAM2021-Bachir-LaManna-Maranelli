package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.EndTurnMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.BuyDevelopmentPhaseMsg;
import it.polimi.ingsw.messages.commands.marketphase.SelectMarketPhaseMsg;
import it.polimi.ingsw.messages.commands.marketphase.StartOrganizeResourcesMsg;
import it.polimi.ingsw.messages.commands.preparation.DrawLeadersMsg;
import it.polimi.ingsw.messages.commands.productionphase.SelectProductionPhaseMsg;
import it.polimi.ingsw.messages.commands.productionphase.StartPayProductionMsg;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    Gui gui;
    LightModel lightModel;
    JButton endTurn;
    JButton viewMarket;
    JButton viewLeaderCards;
    JButton viewAvailableDevelCards;
    JButton drawLeaderCards;
    JButton buyDevelopmentCard;
    JButton payDevelCard;
    JButton marketPhase;
    JButton manageWhiteMarbles;
    JButton discardLeaderCards;
    JButton setStorageType;
    JButton addInitResources;
    JButton productionPhase;
    JButton activateSlotProduction;
    JButton activatePersonalProduction;
    JButton activateSpecialProduction1;
    JButton activateSpecialProduction2;
    JButton startPayProduction;
    JButton payProduction;

    public ButtonPanel(LightModel lightModel, Gui gui){
        this.lightModel = lightModel;
        this.gui = gui;
        setBackground(Color.CYAN);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        endTurn = new JButton("End turn");
        endTurn.addActionListener(this);
        endTurn.setFont(new Font("Comic Sans", Font.BOLD, 20));
        endTurn.setForeground(Color.BLUE);
        endTurn.setBackground(Color.ORANGE);
        endTurn.setBorder(BorderFactory.createEtchedBorder());

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

        discardLeaderCards = new JButton("Discard leader cards");
        discardLeaderCards.addActionListener(this);
        discardLeaderCards.setFont(new Font("Comic Sans", Font.BOLD, 20));
        discardLeaderCards.setForeground(Color.BLUE);
        discardLeaderCards.setBackground(Color.ORANGE);
        discardLeaderCards.setBorder(BorderFactory.createEtchedBorder());

        setStorageType = new JButton("Set storages type");
        setStorageType.addActionListener(this);
        setStorageType.setFont(new Font("Comic Sans", Font.BOLD, 20));
        setStorageType.setForeground(Color.BLUE);
        setStorageType.setBackground(Color.ORANGE);
        setStorageType.setBorder(BorderFactory.createEtchedBorder());

        addInitResources = new JButton("Add initial resources");
        addInitResources.addActionListener(this);
        addInitResources.setFont(new Font("Comic Sans", Font.BOLD, 20));
        addInitResources.setForeground(Color.BLUE);
        addInitResources.setBackground(Color.ORANGE);
        addInitResources.setBorder(BorderFactory.createEtchedBorder());

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

        payDevelCard = new JButton("Pay Card");
        payDevelCard.addActionListener(this);
        payDevelCard.setFont(new Font("Comic Sans", Font.BOLD, 20));
        payDevelCard.setForeground(Color.BLUE);
        payDevelCard.setBackground(Color.ORANGE);
        payDevelCard.setBorder(BorderFactory.createEtchedBorder());

        marketPhase = new JButton("Start Market Phase");
        marketPhase.addActionListener(this);
        marketPhase.setFont(new Font("Comic Sans", Font.BOLD, 20));
        marketPhase.setForeground(Color.BLUE);
        marketPhase.setBackground(Color.ORANGE);
        marketPhase.setBorder(BorderFactory.createEtchedBorder());

        manageWhiteMarbles = new JButton("Manage White Marbles");
        manageWhiteMarbles.addActionListener(this);
        manageWhiteMarbles.setFont(new Font("Comic Sans", Font.BOLD, 20));
        manageWhiteMarbles.setForeground(Color.BLUE);
        manageWhiteMarbles.setBackground(Color.ORANGE);
        manageWhiteMarbles.setBorder(BorderFactory.createEtchedBorder());

        productionPhase = new JButton("Start Production Phase");
        productionPhase.addActionListener(this);
        productionPhase.setFont(new Font("Comic Sans", Font.BOLD, 20));
        productionPhase.setForeground(Color.BLUE);
        productionPhase.setBackground(Color.ORANGE);
        productionPhase.setBorder(BorderFactory.createEtchedBorder());

        activateSlotProduction = new JButton("Activate production of a card");
        activateSlotProduction.addActionListener(this);
        activateSlotProduction.setFont(new Font("Comic Sans", Font.BOLD, 20));
        activateSlotProduction.setForeground(Color.BLUE);
        activateSlotProduction.setBackground(Color.ORANGE);
        activateSlotProduction.setBorder(BorderFactory.createEtchedBorder());

        activatePersonalProduction = new JButton("Activate personal production");
        activatePersonalProduction.addActionListener(this);
        activatePersonalProduction.setFont(new Font("Comic Sans", Font.BOLD, 20));
        activatePersonalProduction.setForeground(Color.BLUE);
        activatePersonalProduction.setBackground(Color.ORANGE);
        activatePersonalProduction.setBorder(BorderFactory.createEtchedBorder());

        activateSpecialProduction1 = new JButton("Activate first special production");
        activateSpecialProduction1.addActionListener(this);
        activateSpecialProduction1.setFont(new Font("Comic Sans", Font.BOLD, 20));
        activateSpecialProduction1.setForeground(Color.BLUE);
        activateSpecialProduction1.setBackground(Color.ORANGE);
        activateSpecialProduction1.setBorder(BorderFactory.createEtchedBorder());

        activateSpecialProduction2 = new JButton("Activate second special production");
        activateSpecialProduction2.addActionListener(this);
        activateSpecialProduction2.setFont(new Font("Comic Sans", Font.BOLD, 20));
        activateSpecialProduction2.setForeground(Color.BLUE);
        activateSpecialProduction2.setBackground(Color.ORANGE);
        activateSpecialProduction2.setBorder(BorderFactory.createEtchedBorder());

        startPayProduction = new JButton("End choosing production and start paying");
        startPayProduction.addActionListener(this);
        startPayProduction.setFont(new Font("Comic Sans", Font.BOLD, 20));
        startPayProduction.setForeground(Color.BLUE);
        startPayProduction.setBackground(Color.ORANGE);
        startPayProduction.setBorder(BorderFactory.createEtchedBorder());

        payProduction = new JButton("Pay production");
        payProduction.addActionListener(this);
        payProduction.setFont(new Font("Comic Sans", Font.BOLD, 20));
        payProduction.setForeground(Color.BLUE);
        payProduction.setBackground(Color.ORANGE);
        payProduction.setBorder(BorderFactory.createEtchedBorder());

        setVisibleButtons(lightModel.getPhase());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(endTurn)){
            CommandMsg msg = new EndTurnMsg();
            gui.sendMessage(msg);
        }

        if(e.getSource().equals(viewMarket)){
            JFrame jFrame = new MarketFrame(gui,lightModel,false);
        }
        else if(e.getSource().equals(viewAvailableDevelCards)){
            new ViewAvailableDevelCardsFrame(lightModel,gui);
        }
        else if(e.getSource().equals(viewLeaderCards)){
            LeaderCardsFrame jFrame = new LeaderCardsFrame(lightModel);
        }

        else if(e.getSource().equals(drawLeaderCards)){
            DrawLeadersMsg drawLeadersMsg = new DrawLeadersMsg();
            gui.sendMessage(drawLeadersMsg);
        }
        else if(e.getSource().equals(discardLeaderCards)){
            DiscardLeaderCardFrame jFrame = new DiscardLeaderCardFrame(gui, lightModel);
        }
        else if(e.getSource().equals(setStorageType)){
            new SetStorageTypeFrame(gui, lightModel, true);
        }
        else if(e.getSource().equals(addInitResources)){
            new AddInitResourcesFrame(gui, lightModel);
        }

        else if(e.getSource().equals(buyDevelopmentCard)){
            //System.out.println("Buy Card");
            //gui.sendMessage(new BuyDevelopmentPhaseMsg());
            new BuyDevelCardsFrame(gui,lightModel);
        }
        else if(e.getSource().equals((payDevelCard))){
            //System.out.println("Pay Card");
            //gui.sendMessage(new BuyDevelopmentPhaseMsg());
            new PayDevelCardFrame(gui,lightModel);
        }
        else if(e.getSource().equals(marketPhase)){
            gui.sendMessage(new SelectMarketPhaseMsg());
        }
        else if(e.getSource().equals(marketPhase)){
            gui.sendMessage(new StartOrganizeResourcesMsg());
        }

        else if(e.getSource().equals(productionPhase)){
            gui.sendMessage(new SelectProductionPhaseMsg());
        }
        else if(e.getSource().equals(activateSlotProduction)){
            new ActivateSlotProductionFrame(gui, lightModel);
        }
        else if(e.getSource().equals(activatePersonalProduction)){
            new ActivatePersonalProductionFrame(gui, lightModel);
        }
        else if(e.getSource().equals(activateSpecialProduction1)){
            new ActivateSpecialProductionFrame(gui, lightModel, 1);
        }
        else if(e.getSource().equals(activateSpecialProduction2)){
            new ActivateSpecialProductionFrame(gui, lightModel, 2);
        }
        else if(e.getSource().equals(startPayProduction)){
            CommandMsg msg = new StartPayProductionMsg();
            gui.sendMessage(msg);
        }
        else if(e.getSource().equals(payProduction)){
            new PayProductionFrame(gui, lightModel);
        }
    }

    private void setVisibleButtons(TurnState phase){
        add(viewMarket);
        add(viewLeaderCards);
        add(viewAvailableDevelCards);
        //add(buyDevelopmentCard);

        if((phase == TurnState.ENDTURN || phase == TurnState.ENDPREPARATION)){
            add(endTurn);
            //+ comando per riorganizzare le risorse
        }
        if(phase ==  TurnState.PREPARATION){
            add(drawLeaderCards);
        }
        if(phase == TurnState.CHOOSELEADERCARDS){
            add(discardLeaderCards);
        }
        if(phase == TurnState.CHOOSERESOURCES && lightModel.getPlayerID() == 1){
            add(setStorageType);
        }
        if(phase == TurnState.CHOOSERESOURCES && lightModel.getPlayerID() != 1){
            add(setStorageType);
            add(addInitResources);
        }

        if(phase == TurnState.START){
            add(buyDevelopmentCard);
            add(marketPhase);
            add(productionPhase);
        }
        if(phase == TurnState.PRODUCTIONPHASE){
            add(activateSlotProduction);
            add(activatePersonalProduction);
            add(activateSpecialProduction1);
            add(activateSpecialProduction2);
            add(startPayProduction);
        }
        if(phase == TurnState.PAYPRODUCTIONS){
            add(payProduction);
        }

        setVisible(true);
    }
}
