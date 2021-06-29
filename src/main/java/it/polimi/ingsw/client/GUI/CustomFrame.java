package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.client.OutputView;
import it.polimi.ingsw.messages.commands.beforestart.JoinGameMsg;
import it.polimi.ingsw.messages.commands.beforestart.NewGameMsg;
import it.polimi.ingsw.messages.commands.beforestart.QuickStartMsg;
import it.polimi.ingsw.messages.commands.preparation.NickNameMsg;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.model.YellowMarble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * The type Custom frame. It contains the four panels: ButtonPanel, WarehousePanel, FaithTrackPanel, DevelCardsSlotPanel
 */
public class CustomFrame extends JFrame implements ActionListener {
    private Gui gui;
    private JButton button;
    private JButton quickstart;
    private JButton newMatch;
    private JButton rejoin;
    private JTextField textField1;
    private JTextField textField2;

    /**
     * Instantiates a new Custom frame.
     *
     * @param gui the gui
     */
    public CustomFrame(Gui gui){
        this.gui = gui;
        button = new JButton("Submit");
        button.setBounds(100, 100, 250, 100);
        button.addActionListener(this);
        button.setText("Submit");
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setIconTextGap(-15);
        button.setForeground(Color.BLUE);
        button.setBackground(Color.ORANGE);
        button.setBorder(BorderFactory.createEtchedBorder());

        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(250, 40));
        textField1.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        textField1.setForeground(Color.BLUE);
        textField1.setBackground(Color.ORANGE);
        textField1.setText("Nickname");

        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(250, 40));
        textField2.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        textField2.setForeground(Color.BLUE);
        textField2.setBackground(Color.ORANGE);
        textField2.setText("Number of players");

        quickstart = new JButton("Quickstart");
        quickstart.setBounds(100, 100, 250, 100);
        quickstart.addActionListener(this);
        quickstart.setHorizontalTextPosition(JButton.CENTER);
        quickstart.setVerticalTextPosition(JButton.BOTTOM);
        quickstart.setFont(new Font("Comic Sans", Font.BOLD, 25));
        quickstart.setIconTextGap(-15);
        quickstart.setForeground(Color.BLUE);
        quickstart.setBackground(Color.ORANGE);
        quickstart.setBorder(BorderFactory.createEtchedBorder());

        newMatch = new JButton("Create new game");
        newMatch.setBounds(100, 100, 250, 100);
        newMatch.addActionListener(this);
        newMatch.setHorizontalTextPosition(JButton.CENTER);
        newMatch.setVerticalTextPosition(JButton.BOTTOM);
        newMatch.setFont(new Font("Comic Sans", Font.BOLD, 25));
        newMatch.setIconTextGap(-15);
        newMatch.setForeground(Color.BLUE);
        newMatch.setBackground(Color.ORANGE);
        newMatch.setBorder(BorderFactory.createEtchedBorder());

        rejoin = new JButton("Rejoin game");
        rejoin.setBounds(100, 100, 250, 100);
        rejoin.addActionListener(this);
        rejoin.setHorizontalTextPosition(JButton.CENTER);
        rejoin.setVerticalTextPosition(JButton.BOTTOM);
        rejoin.setFont(new Font("Comic Sans", Font.BOLD, 25));
        rejoin.setIconTextGap(-15);
        rejoin.setForeground(Color.BLUE);
        rejoin.setBackground(Color.ORANGE);
        rejoin.setBorder(BorderFactory.createEtchedBorder());

        add(quickstart);
        add(newMatch);
        add(rejoin);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)){
            if(textField1.getText() == null){
                JOptionPane.showMessageDialog(null, "Insert nickname", "error", JOptionPane.ERROR_MESSAGE);
            }
            else if(Integer.parseInt(textField2.getText()) < 1 || Integer.parseInt(textField2.getText()) > 4){
                JOptionPane.showMessageDialog(null, "Invalid number of players", "error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                NickNameMsg nickNameMsg = new NickNameMsg(textField1.getText(), Integer.parseInt(textField2.getText()));
                gui.sendMessage(nickNameMsg);
            }
        }
        else if(e.getSource().equals(quickstart)){
            gui.sendMessage(new QuickStartMsg());
        }
        else if(e.getSource().equals(newMatch)){
            gui.sendMessage((new NewGameMsg()));
        }
        else if(e.getSource().equals(rejoin)){
            gui.sendMessage(new JoinGameMsg(gui.getLightModel().getUnicode()));
        }
    }

    /**
     * Nickname panel. It asks nickname and number of players of the game
     */
    public void nicknamePanel(){
        getContentPane().removeAll();
        add(button);
        add(textField1);
        add(textField2);
        pack();
        setVisible(true);
    }

    /**
     * Waiting panel. It is a waiting room while other players are connecting to the game
     */
    public void waitingPanel(){
        getContentPane().removeAll();
        JPanel jPanel = new WaitingPanel();
        add(jPanel);
        pack();
        setVisible(true);
    }

    /**
     * Update personal board whit the new information from the lightmodel
     *
     * @param lightModel the light model
     */
    public void updatePersonalBoard(LightModel lightModel){
        getContentPane().removeAll();
        setLayout(new GridLayout(2,2));
        //setSize(2000, 2000);
        JPanel jPanel1 = new WareHousePanel(lightModel);
        JPanel jPanel2 = new FaithTrackPanel(lightModel);
        JPanel jPanel3 = new ButtonPanel(lightModel, gui);
        JPanel jPanel4 = new DevelCardsSlotsPanel(lightModel);
        add(jPanel1);
        add(jPanel2);
        add(jPanel3);
        add(jPanel4);

        pack();
        setVisible(true);
    }

    /**
     * Other player board. It is a frame used to display the state of the board of another player
     *
     * @param nickname          the nickname of the player
     * @param mapFromChest      the map from chest of the player
     * @param storages          the storages of the player
     * @param resourceList      the resources of the player
     * @param position          the position in the faithtrack of the player
     * @param popeFavours       the pope favours of the player
     * @param leaderCardsPlayed the leader cards played by the player
     */
    public void otherPlayerBoard(String nickname, Map<Resource, Integer> mapFromChest, Integer[] storages, java.util.List<Resource> resourceList, int position, Boolean[] popeFavours, List<LeaderCard> leaderCardsPlayed){
        JPanel panel = new JPanel();
        JPanel jPanel1 = new WareHousePanel(mapFromChest, storages, resourceList);
        JPanel jPanel2 = new FaithTrackPanel(position, popeFavours);
        JPanel jPanel3 = new LeaderCardsPanel(leaderCardsPlayed);

        panel.add(jPanel1);
        panel.add(jPanel2);
        panel.add(jPanel3);
        this.setContentPane(panel);
        panel.setLayout(new GridLayout(3, 1));
        setVisible(true);
    }
}
