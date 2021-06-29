package it.polimi.ingsw.model;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CpuActionMoveOnTest {
    @Test
    public void testActivateAction() throws FileNotFoundException {
        CpuActionMoveOn cpuActionMoveOn = new CpuActionMoveOn();
        cpuActionMoveOn.getcolor();
        ArrayList<CpuAction> cpuActions= new ArrayList<>();
        cpuActions.add(new CpuActionShuffle());
        cpuActions.add(new CpuActionDiscard(Color.YELLOW));
        cpuActions.add(new CpuActionMoveOn());
        cpuActions.add(new CpuActionDiscard(Color.GREEN));
        cpuActions.add(new CpuActionDiscard(Color.PURPLE));
        cpuActions.add(new CpuActionDiscard(Color.BLUE));
        Game game= new Game();
        FaithTrack faithTrack= new FaithTrack(game.getVaticanReportSections());
        cpuActionMoveOn.activateAction(game.getTable(), faithTrack, cpuActions);
        Box temp=faithTrack.checkPlayerPosition();
        assertEquals(2,faithTrack.getTrack().indexOf(temp));

    }

}