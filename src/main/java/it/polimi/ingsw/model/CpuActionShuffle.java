package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;

public class CpuActionShuffle implements CpuAction {
    @Override

    public void activateAction(Table table, FaithTrack faithTrack, ArrayList<CpuAction> cpuActions) {
        faithTrack.movePositionForward();
        Collections.shuffle(cpuActions);
    }
}