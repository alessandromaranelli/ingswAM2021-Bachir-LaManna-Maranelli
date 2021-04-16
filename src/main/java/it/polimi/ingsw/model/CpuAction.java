package it.polimi.ingsw.model;

import java.util.ArrayList;

public interface CpuAction {
    public void activateAction(Table table, FaithTrack faithTrack, ArrayList<CpuAction> cpuActions);
}