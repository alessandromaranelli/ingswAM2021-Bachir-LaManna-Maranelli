package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CPUTest {
    Game game=new Game();
    CPU cpu=new CPU(game);

    public CPUTest() throws FileNotFoundException {
    }

    @Test
    public void getCpuPosition() {
        assertEquals(0,cpu.getCpuPosition());
    }

    @Test
    public void actionCpu() {
        ArrayList<CpuAction> cpuActionArrayList= new ArrayList<>();
        cpuActionArrayList.addAll(cpu.getCpuActions());
        cpu.actionCpu();
        for (int i=0;i<cpuActionArrayList.size()-1;i++){
            assertEquals(cpuActionArrayList.get(i+1),cpu.getCpuActions().get(i));
        }
        assertEquals(cpuActionArrayList.get(0),cpu.getCpuActions().get(cpuActionArrayList.size()-1));
    }
}