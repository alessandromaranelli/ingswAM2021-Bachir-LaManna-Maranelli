package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ChestTest {

    @Test
    public void testgetResources(){
        Chest chest = new Chest();
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        m.put(Resource.SERVANT, 0);
        m.put(Resource.SHIELD, 0);
        m.put(Resource.STONE, 0);

        assertEquals(m, chest.getResources());
    }

    @Test
    public void testgetResource(){
        Chest chest = new Chest();
        assertEquals(0, chest.getResource(Resource.COIN));
    }

    @Test
    public void testgetNumof(){
        Chest chest = new Chest();
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);

        assertEquals(m, chest.getNumof(Resource.COIN));
    }

    @Test
    public void testaddToChest(){
        Chest chest = new Chest();
        chest.addToChest(Resource.COIN, 21);
        assertEquals(21, chest.getResource(Resource.COIN));
    }

    @Test(expected = ModelException.class)
    public void testsubFromChest1() throws ModelException{
        Chest chest = new Chest();
        chest.subFromChest(Resource.COIN, 21);
    }

    @Test
    public void testsubFromChest2() throws ModelException{
        Chest chest = new Chest();
        chest.addToChest(Resource.COIN, 21);
        chest.subFromChest(Resource.COIN, 21);
        assertEquals(0, chest.getResource(Resource.COIN));
    }
}