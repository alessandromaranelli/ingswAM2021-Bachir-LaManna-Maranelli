package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * CpuActionShuffle defines a token that moves the Cpu position one Box forward and shuffles the list
 * of all the tokens.
 */
public class CpuActionShuffle implements CpuAction {

    /**
     * Constructor CpuActionShuffle creates a new CpuActionShuffle instance
     */
    public CpuActionShuffle() {
    }

    /**
     * This method moves the Cpu position one Box forward and shuffles the list of all the tokens
     * @param table - the gameboard
     * @param faithTrack - the Cpu's FaithTrack
     * @param cpuActions - a list of all the action tokens
     */
    @Override
    public void activateAction(Table table, FaithTrack faithTrack, ArrayList<CpuAction> cpuActions) {
        faithTrack.movePositionForward();
        Collections.shuffle(cpuActions);
    }

    @Override
    public Color getcolor() {
        return null;
    }
}