package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * Interface CpuAction declares the method activateAction that will be then defined by the three classes of cpuAction
 * that implement this interface.
 */
public interface CpuAction {
    /**
     * This method describes an action that is different for every type of CpuAction token
     * @param table - the gameboard
     * @param faithTrack - the Cpu's FaithTrack
     * @param cpuActions - a list of all the action tokens
     */
    public void activateAction(Table table, FaithTrack faithTrack, ArrayList<CpuAction> cpuActions);
    public Color getcolor();
}