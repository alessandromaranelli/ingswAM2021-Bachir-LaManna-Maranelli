package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class SpecialProductionTest {

    @Test
    public void testgetCost(){
        SpecialProduction specialProduction = new SpecialProduction(Resource.COIN);
        assertEquals(Resource.COIN, specialProduction.getCost());
    }

    @Test
    public void testisActivated(){
        SpecialProduction specialProduction = new SpecialProduction(Resource.COIN);
        assertFalse(specialProduction.isActivated());
    }

    @Test
    public void testactivate(){
        SpecialProduction specialProduction = new SpecialProduction(Resource.COIN);
        specialProduction.activate();
        assertTrue(specialProduction.isActivated());
    }

    @Test
    public void testdisactivate(){
        SpecialProduction specialProduction = new SpecialProduction(Resource.COIN);
        specialProduction.activate();
        specialProduction.disactivate();
        assertFalse(specialProduction.isActivated());
    }
}