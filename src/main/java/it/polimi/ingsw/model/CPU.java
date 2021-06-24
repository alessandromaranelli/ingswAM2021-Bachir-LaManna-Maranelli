package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class CPU defines the entity that plays against the player in a solo game.
 * It is linked with the game, and it has a FaithTrack and a list of all the
 * possible actions that it can do.
 */
public class CPU implements Serializable {
    private ArrayList<CpuAction> cpuActions;
    private FaithTrack faithTrack;
    private Game game;

    /**
     * Constructor CPU creates a new CPU instance
     * @param game - the actual game
     */
    public CPU(Game game) {
        this.game=game;
        cpuActions= new ArrayList<>();
        cpuActions.add(new CpuActionShuffle());
        cpuActions.add(new CpuActionDiscard(Color.YELLOW));
        cpuActions.add(new CpuActionMoveOn());
        cpuActions.add(new CpuActionDiscard(Color.GREEN));
        cpuActions.add(new CpuActionDiscard(Color.PURPLE));
        cpuActions.add(new CpuActionDiscard(Color.BLUE));
        Collections.shuffle(cpuActions);
        faithTrack= new FaithTrack(game.getVaticanReportSections());
    }

    /**
     * Getter method that returns the Cpu position
     * @return int CpuPosition
     */
    public int getCpuPosition() {
        return this.getFaithTrack().getTrack().indexOf(faithTrack.checkPlayerPosition());
    }

    /**
     * Getter method that returns the cpuActions
     * @return ArrayList<CpuAction> cpuActions
     */
    public ArrayList<CpuAction> getCpuActions() {
        return cpuActions;
    }

    /**
     * Getter method that returns the faithTrack
     * @return FaithTrack faithTrack
     */
    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    /**
     * Method that implements the turn of the CPU: the first token of the list of actions gets activated
     * and a the end it is put at the end of the list
     */
    public CpuAction actionCpu(){
        cpuActions.get(0).activateAction(game.getTable(),faithTrack, cpuActions);
        CpuAction cpuActionTemp = cpuActions.get(0);
        for (int i=0;i<cpuActions.size()-1;i++){
            cpuActions.set(i,cpuActions.get(i+1));
        }
        cpuActions.set(cpuActions.size()-1,cpuActionTemp);
        return cpuActionTemp;
    }

}
