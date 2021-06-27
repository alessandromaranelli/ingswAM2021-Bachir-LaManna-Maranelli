package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class CpuActionDiscardTest {

    @Test
    public void testActivateActionLevel1() throws FileNotFoundException {
        CpuActionDiscard cpuActionDiscard=new CpuActionDiscard(Color.GREEN);
        ArrayList<CpuAction> cpuActions= new ArrayList<>();
        Table table= new Table();
        cpuActionDiscard.activateAction(table, null, cpuActions);
        assertEquals(2,table.getDevelopmentCardDeck(Color.GREEN,1).getDevelopmentCards().size());
    }

    @Test
    public void testActivateActionLevel2() throws FileNotFoundException {
        CpuActionDiscard cpuActionDiscard=new CpuActionDiscard(Color.GREEN);
        cpuActionDiscard.getcolor();
        ArrayList<CpuAction> cpuActions= new ArrayList<>();
        Table table= new Table();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        cpuActionDiscard.activateAction(table, null, cpuActions);
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,1).getDevelopmentCards().size());
        assertEquals(2,table.getDevelopmentCardDeck(Color.GREEN,2).getDevelopmentCards().size());
    }
    @Test
    public void testActivateActionLevel3() throws FileNotFoundException {
        CpuActionDiscard cpuActionDiscard=new CpuActionDiscard(Color.GREEN);
        ArrayList<CpuAction> cpuActions= new ArrayList<>();
        Table table= new Table();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,3).removeFromTop();
        cpuActionDiscard.activateAction(table, null, cpuActions);
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,1).getDevelopmentCards().size());
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,2).getDevelopmentCards().size());
        assertEquals(1,table.getDevelopmentCardDeck(Color.GREEN,3).getDevelopmentCards().size());
    }

    @Test
    public void testActivateActionLevel3bis() throws FileNotFoundException {
        CpuActionDiscard cpuActionDiscard=new CpuActionDiscard(Color.GREEN);
        ArrayList<CpuAction> cpuActions= new ArrayList<>();
        Table table= new Table();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,3).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,3).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,3).removeFromTop();
        cpuActionDiscard.activateAction(table, null, cpuActions);
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,1).getDevelopmentCards().size());
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,2).getDevelopmentCards().size());
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,3).getDevelopmentCards().size());
    }
    @Test
    public void testActivateActionLevel12() throws FileNotFoundException {
        CpuActionDiscard cpuActionDiscard=new CpuActionDiscard(Color.GREEN);
        ArrayList<CpuAction> cpuActions= new ArrayList<>();
        Table table= new Table();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        cpuActionDiscard.activateAction(table, null, cpuActions);
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,1).getDevelopmentCards().size());
        assertEquals(3,table.getDevelopmentCardDeck(Color.GREEN,2).getDevelopmentCards().size());
        assertEquals(4,table.getDevelopmentCardDeck(Color.GREEN,3).getDevelopmentCards().size());

    }
    @Test
    public void testActivateActionLevel23() throws FileNotFoundException {
        CpuActionDiscard cpuActionDiscard=new CpuActionDiscard(Color.GREEN);
        ArrayList<CpuAction> cpuActions= new ArrayList<>();
        Table table= new Table();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,1).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        table.getDevelopmentCardDeck(Color.GREEN,2).removeFromTop();
        cpuActionDiscard.activateAction(table, null, cpuActions);
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,1).getDevelopmentCards().size());
        assertEquals(0,table.getDevelopmentCardDeck(Color.GREEN,2).getDevelopmentCards().size());
        assertEquals(3,table.getDevelopmentCardDeck(Color.GREEN,3).getDevelopmentCards().size());

    }
}