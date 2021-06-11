package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.GUI.*;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.messages.commands.EndTurnMsg;
import it.polimi.ingsw.messages.commands.buydevelopmentphase.BuyDevelopmentPhaseMsg;
import it.polimi.ingsw.messages.commands.marketphase.DefaultManageResourcesToOrganizeMsg;
import it.polimi.ingsw.messages.commands.marketphase.SelectMarketPhaseMsg;
import it.polimi.ingsw.messages.commands.marketphase.StartAddResourcesMsg;
import it.polimi.ingsw.messages.commands.marketphase.StartOrganizeResourcesMsg;
import it.polimi.ingsw.messages.commands.preparation.DrawLeadersMsg;
import it.polimi.ingsw.messages.commands.productionphase.SelectProductionPhaseMsg;
import it.polimi.ingsw.messages.commands.productionphase.StartPayProductionMsg;
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
    JButton viewProductions;
    JButton drawLeaderCards;
    JButton discardLeaderCards;
    JButton setStorageType;
    JButton addInitResources;
    JButton marketPhase;
    JButton startMarketPhase;
    JButton manageWhiteMarbles;
    JButton startOrganizeResources;
    JButton defaultManageResourcesToOrganize;
    JButton manageResourcesToOrganize;
    JButton startAddResources;
    JButton manageResourcesToAdd;
    JButton developmentCardPhase;
    JButton buyDevelopmentCard;
    JButton payDevelCard;
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

        viewProductions = new JButton("View State of Productions");
        viewProductions.addActionListener(this);
        viewProductions.setFont(new Font("Comic Sans", Font.BOLD, 20));
        viewProductions.setForeground(Color.BLUE);
        viewProductions.setBackground(Color.ORANGE);
        viewProductions.setBorder(BorderFactory.createEtchedBorder());

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

        marketPhase = new JButton("Start Market Phase");
        marketPhase.addActionListener(this);
        marketPhase.setFont(new Font("Comic Sans", Font.BOLD, 20));
        marketPhase.setForeground(Color.BLUE);
        marketPhase.setBackground(Color.ORANGE);
        marketPhase.setBorder(BorderFactory.createEtchedBorder());

        startMarketPhase = new JButton("Select row or column");
        startMarketPhase.addActionListener(this);
        startMarketPhase.setFont(new Font("Comic Sans", Font.BOLD, 20));
        startMarketPhase.setForeground(Color.BLUE);
        startMarketPhase.setBackground(Color.ORANGE);
        startMarketPhase.setBorder(BorderFactory.createEtchedBorder());

        manageWhiteMarbles = new JButton("Choose colors for white marbles");
        manageWhiteMarbles.addActionListener(this);
        manageWhiteMarbles.setFont(new Font("Comic Sans", Font.BOLD, 20));
        manageWhiteMarbles.setForeground(Color.BLUE);
        manageWhiteMarbles.setBackground(Color.ORANGE);
        manageWhiteMarbles.setBorder(BorderFactory.createEtchedBorder());

        startOrganizeResources = new JButton("Organize resources in storages");
        startOrganizeResources.addActionListener(this);
        startOrganizeResources.setFont(new Font("Comic Sans", Font.BOLD, 20));
        startOrganizeResources.setForeground(Color.BLUE);
        startOrganizeResources.setBackground(Color.ORANGE);
        startOrganizeResources.setBorder(BorderFactory.createEtchedBorder());

        defaultManageResourcesToOrganize = new JButton("Put resources automatically in the correct storage");
        defaultManageResourcesToOrganize.addActionListener(this);
        defaultManageResourcesToOrganize.setFont(new Font("Comic Sans", Font.BOLD, 20));
        defaultManageResourcesToOrganize.setForeground(Color.BLUE);
        defaultManageResourcesToOrganize.setBackground(Color.ORANGE);
        defaultManageResourcesToOrganize.setBorder(BorderFactory.createEtchedBorder());

        manageResourcesToOrganize = new JButton("Put resources in storages");
        manageResourcesToOrganize.addActionListener(this);
        manageResourcesToOrganize.setFont(new Font("Comic Sans", Font.BOLD, 20));
        manageResourcesToOrganize.setForeground(Color.BLUE);
        manageResourcesToOrganize.setBackground(Color.ORANGE);
        manageResourcesToOrganize.setBorder(BorderFactory.createEtchedBorder());

        startAddResources = new JButton("Add or discard resources to storages");
        startAddResources.addActionListener(this);
        startAddResources.setFont(new Font("Comic Sans", Font.BOLD, 20));
        startAddResources.setForeground(Color.BLUE);
        startAddResources.setBackground(Color.ORANGE);
        startAddResources.setBorder(BorderFactory.createEtchedBorder());

        manageResourcesToAdd = new JButton("Select resources");
        manageResourcesToAdd.addActionListener(this);
        manageResourcesToAdd.setFont(new Font("Comic Sans", Font.BOLD, 20));
        manageResourcesToAdd.setForeground(Color.BLUE);
        manageResourcesToAdd.setBackground(Color.ORANGE);
        manageResourcesToAdd.setBorder(BorderFactory.createEtchedBorder());

        viewAvailableDevelCards = new JButton("View available Development Cards");
        viewAvailableDevelCards.addActionListener(this);
        viewAvailableDevelCards.setFont(new Font("Comic Sans", Font.BOLD, 20));
        viewAvailableDevelCards.setForeground(Color.BLUE);
        viewAvailableDevelCards.setBackground(Color.ORANGE);
        viewAvailableDevelCards.setBorder(BorderFactory.createEtchedBorder());

        developmentCardPhase = new JButton("Start Buy Development Card Phase");
        developmentCardPhase.addActionListener(this);
        developmentCardPhase.setFont(new Font("Comic Sans", Font.BOLD, 20));
        developmentCardPhase.setForeground(Color.BLUE);
        developmentCardPhase.setBackground(Color.ORANGE);
        developmentCardPhase.setBorder(BorderFactory.createEtchedBorder());

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
        else if(e.getSource().equals(viewProductions)){
            new ProductionFrame(lightModel);
        }

        else if(e.getSource().equals(drawLeaderCards)){
            DrawLeadersMsg drawLeadersMsg = new DrawLeadersMsg();
            gui.sendMessage(drawLeadersMsg);
        }
        else if(e.getSource().equals(discardLeaderCards)){
            DiscardLeaderCardFrame jFrame = new DiscardLeaderCardFrame(gui, lightModel);
        }
        else if(e.getSource().equals(setStorageType) && lightModel.getPhase() == TurnState.CHOOSERESOURCES){
            new SetStorageTypeFrame(gui, lightModel, true);
        }
        else if(e.getSource().equals(addInitResources)){
            new AddInitResourcesFrame(gui, lightModel);
        }

        else if(e.getSource().equals(marketPhase)){
            gui.sendMessage(new SelectMarketPhaseMsg());
        }
        else if(e.getSource().equals(startMarketPhase)){
            new MarketFrame(gui, lightModel, true);
        }
        else if(e.getSource().equals(manageWhiteMarbles)){
            new ManageWhiteMarblesFrame(gui, lightModel, lightModel.getWhiteMarblesToManage());
        }
        else if(e.getSource().equals(startOrganizeResources)){
            gui.sendMessage(new StartOrganizeResourcesMsg());
        }
        else if(e.getSource().equals(setStorageType) && lightModel.getPhase() == TurnState.ORGANIZERESOURCES){
            new SetStorageTypeFrame(gui, lightModel, false);
        }
        else if(e.getSource().equals(defaultManageResourcesToOrganize)){
            gui.sendMessage(new DefaultManageResourcesToOrganizeMsg());
        }
        else if(e.getSource().equals(manageResourcesToOrganize)){
            new ManageResourcesToOrganizeFrame(gui, lightModel);
        }
        else if(e.getSource().equals(startAddResources)){
            gui.sendMessage(new StartAddResourcesMsg());
        }
        else if(e.getSource().equals(manageResourcesToAdd)){
            new ManageResourcesToAddFrame(gui, lightModel);
        }

        else if(e.getSource().equals(developmentCardPhase)){
            gui.sendMessage(new BuyDevelopmentPhaseMsg());
        }
        else if(e.getSource().equals(buyDevelopmentCard)){
            //System.out.println("Buy Card");
            new BuyDevelCardsFrame(gui,lightModel);
        }
        else if(e.getSource().equals((payDevelCard))){
            //System.out.println("Pay Card");
            //gui.sendMessage(new BuyDevelopmentPhaseMsg());
            new PayDevelCardFrame(gui,lightModel);
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
        add(viewProductions);

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
            add(marketPhase);
            add(developmentCardPhase);
            add(productionPhase);
        }

        if(phase == TurnState.MARKETPHASE){
            add(startMarketPhase);
        }
        if(phase == TurnState.WHITEMARBLES){
            add(manageWhiteMarbles);
        }
        if(phase == TurnState.CHOICE){
            add(startOrganizeResources);
            add(startAddResources);
        }
        if(phase == TurnState.ORGANIZERESOURCES){
            add(setStorageType);
        }
        if(phase == TurnState.MANAGERESOURCES){
            add(defaultManageResourcesToOrganize);
            add(manageResourcesToOrganize);
        }
        if(phase == TurnState.ADDRESOURCES){
            add(manageResourcesToAdd);
        }


        if(phase == TurnState.BUYDEVELOPMENTCARDPHASE){
            add(buyDevelopmentCard);
        }
        if(phase == TurnState.PAYDEVELOPMENTCARD){
            add(payDevelCard);
        }


        if(phase == TurnState.PRODUCTIONPHASE){
            add(activateSlotProduction);            //attiva la produzione di una carta in uno dei 3 slot
            add(activatePersonalProduction);
            add(activateSpecialProduction1);        //attiva una delle due produzioni speciali
            add(activateSpecialProduction2);
            add(startPayProduction);                //finisce la fase di attivazione e inizia il pagamento
        }
        if(phase == TurnState.PAYPRODUCTIONS){
            add(payProduction);
        }

        setVisible(true);
    }
}
