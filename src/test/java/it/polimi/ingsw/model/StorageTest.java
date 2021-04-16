package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


public class StorageTest {

    @Test
    public void testgetType(){
        Storage storage = new Storage(1, Resource.COIN);
        assertEquals(Resource.COIN, storage.getType());
    }

    @Test
    public void testgetQuantity(){
        Storage storage = new Storage(1, Resource.COIN);
        assertEquals(0, storage.getQuantity());
    }

    @Test
    public void testgetMaxNumber(){
        Storage storage = new Storage(1, Resource.COIN);
        assertEquals(1, storage.getmaxNumber());
    }

    @Test
    public void testgetStorage(){
        Storage storage = new Storage(1, Resource.COIN);

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        assertEquals(m, storage.getStorage());
    }

    @Test
    public void testisEmpty(){
        Storage storage = new Storage(1, Resource.COIN);
        assertTrue(storage.isEmpty());
    }

    @Test(expected = ModelException.class)
    public void testsetType1() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.addToStorage(Resource.COIN, 1);
        storage.setType(Resource.COIN);
    }

    @Test
    public void testsetType2() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.setType(Resource.STONE);
        assertEquals(Resource.STONE, storage.getType());
    }

    @Test(expected = ModelException.class)
    public void testaddToStorage1() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.addToStorage(Resource.STONE, 1);
    }

    @Test(expected = ModelException.class)
    public void testaddToStorage2() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.addToStorage(Resource.COIN, 2);
    }

    @Test
    public void testaddToStorage3() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.addToStorage(Resource.COIN, 1);
        assertEquals(1, storage.getQuantity());
    }

    @Test(expected = ModelException.class)
    public void testsubFromStorage1() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.subFromStorage(Resource.STONE, 1);
    }

    @Test(expected = ModelException.class)
    public void testsubFromStorage2() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.subFromStorage(Resource.COIN, 1);
    }

    @Test
    public void testsubFromStorage3() throws ModelException{
        Storage storage = new Storage(1, Resource.COIN);
        storage.addToStorage(Resource.COIN, 1);
        storage.subFromStorage(Resource.COIN, 1);
        assertEquals(0, storage.getQuantity());
    }

    @Test
    public void testclearStorage(){
        Storage storage = new Storage(1, Resource.COIN);
        storage.clearStorage();
        assertEquals(0, storage.getQuantity());
    }
}
