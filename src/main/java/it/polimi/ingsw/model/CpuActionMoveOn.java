package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * CpuActionMoveOn defines a token that moves the Cpu position two Boxes forward.
 */
public class CpuActionMoveOn implements CpuAction {

    /**
     * Constructor CpuActionMoveOn creates a new CpuActionMoveOn instance
     */
    public CpuActionMoveOn() {
    }

    /**
     * This method moves the Cpu position two Boxes forward
     * @param table - the gameboard
     * @param faithTrack - the Cpu's FaithTrack
     * @param cpuActions - a list of all the action tokens
     */
    @Override
    public void activateAction(Table table, FaithTrack faithTrack, ArrayList<CpuAction> cpuActions) {
        faithTrack.movePositionForward();
        faithTrack.movePositionForward();
    }

    @Override
    public Color getcolor() {
        return null;
    }
}