package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.client.OutputView;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * The type Gui. It creates the CustomFrame
 */
public class Gui implements Runnable{
    private CustomFrame frame;
    private OutputView outputView;
    private LightModel lightModel;

    /**
     * Instantiates a new Gui.
     *
     * @param outputView the output view
     * @param lightModel the light model
     */
    public Gui(OutputView outputView, LightModel lightModel){
        this.lightModel = lightModel;
        this.outputView = outputView;
    }

    public void run(){
        frame = new CustomFrame(this);
        frame.setTitle("Masters of Renaissance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLayout(new FlowLayout());
    }

    /**
     * Send message.
     *
     * @param commandMsg the command msg
     */
    public void sendMessage(CommandMsg commandMsg){
        outputView.sendCommandMessage(commandMsg);
    }

    /**
     * Nickname scene.
     */
    public void nicknameScene(){
        frame.nicknamePanel();
    }

    /**
     * Other player scene.
     *
     * @param nickname          the nickname
     * @param mapFromChest      the map from chest
     * @param storages          the storages
     * @param resourceList      the resource list
     * @param position          the position
     * @param popeFavours       the pope favours
     * @param leaderCardsPlayed the leader cards played
     */
    public void otherPlayerScene(String nickname, Map<Resource, Integer> mapFromChest, Integer[] storages, java.util.List<Resource> resourceList, int position, Boolean[] popeFavours, java.util.List<LeaderCard> leaderCardsPlayed){
        CustomFrame frame2 = new CustomFrame(this);
        frame2.setVisible(true);

        frame2.otherPlayerBoard(nickname, mapFromChest, storages, resourceList, position, popeFavours, leaderCardsPlayed);
    }

    /**
     * Wait scene.
     */
    public void waitScene(){
        frame.waitingPanel();
    }

    /**
     * Update personal board.
     *
     * @param lightModel the light model
     */
    public void updatePersonalBoard(LightModel lightModel){
        frame.updatePersonalBoard(lightModel);
    }

    /**
     * Error message.
     *
     * @param message the message
     */
    public void errorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Lorenzo action.
     *
     * @param message the message
     */
    public void lorenzoAction(String message){
        JOptionPane.showMessageDialog(null, message, "Lorenzo's Action", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Get light model light model.
     *
     * @return the light model
     */
    public LightModel getLightModel(){
        return lightModel;
    }
}
