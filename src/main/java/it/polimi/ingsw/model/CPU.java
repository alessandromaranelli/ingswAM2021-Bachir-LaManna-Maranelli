package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;


public class CPU {
    private ArrayList<CpuAction> cpuActions;
    private FaithTrack faithTrack;
    private Game game;

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

    public int getCpuPosition() {
        return this.getFaithTrack().getTrack().indexOf(faithTrack.checkPlayerPosition());
    }

    public ArrayList<CpuAction> getCpuActions() {
        return cpuActions;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public void actionCpu(){
        cpuActions.get(0).activateAction(game.getTable(),faithTrack, cpuActions);
        CpuAction cpuActionTemp = cpuActions.get(0);
        for (int i=0;i<cpuActions.size()-1;i++){
            cpuActions.set(i,cpuActions.get(i+1));
        }
        cpuActions.set(cpuActions.size()-1,cpuActionTemp);
    }

}
