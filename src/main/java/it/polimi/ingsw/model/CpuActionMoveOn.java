package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CpuActionMoveOn implements CpuAction {

    public CpuActionMoveOn() {
    }

    @Override
    public void activateAction(Table table, FaithTrack faithTrack, ArrayList<CpuAction> cpuActions) {
        faithTrack.movePositionForward();
        faithTrack.movePositionForward();
    }
}