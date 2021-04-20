package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ProductionTest {
    WareHouse w = new WareHouse();
    CardSlot c = new CardSlot();
    Game game = new Game();
    PersonalBoard p = new PersonalBoard(game.getVaticanReportSections());

    public ProductionTest() throws FileNotFoundException {
    }

    @Test
    public void testgetTotalGain(){
        Production production = new Production(p, w, c);
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        m.put(Resource.STONE, 0);
        m.put(Resource.SHIELD, 0);
        m.put(Resource.SERVANT, 0);

        assertEquals(m, production.getTotalGain());
    }

    @Test
    public void testgetTotalCost(){
        Production production = new Production(p, w, c);
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        m.put(Resource.STONE, 0);
        m.put(Resource.SHIELD, 0);
        m.put(Resource.SERVANT, 0);

        assertEquals(m, production.getTotalCost());
    }

    @Test
    public void testgetFaithPoints(){
        Production production = new Production(p, w, c);
        assertEquals(0, production.getFaithPoints());
    }

    @Test
    public void testnumOfSpecialProduction1(){
        Production production = new Production(p, w, c);
        production.addSpecialProduction(Resource.SHIELD);
        assertEquals(1, production.numOfSpecialProduction());
    }

    @Test
    public void testnumOfSpecialProduction2(){
        Production production = new Production(p, w, c);
        production.addSpecialProduction(Resource.SHIELD);
        production.addSpecialProduction(Resource.COIN);
        assertEquals(2, production.numOfSpecialProduction());
    }

    @Test(expected = ModelException.class)
    public void testgetTypeOfSpecialProduction1() throws ModelException{
        Production production = new Production(p, w, c);
        assertEquals(Resource.COIN, production.getTypeOfSpecialProduction(21));
    }

    @Test
    public void testgetTypeOfSpecialProduction2() throws ModelException{
        Production production = new Production(p, w, c);
        production.addSpecialProduction(Resource.COIN);
        assertEquals(Resource.COIN, production.getTypeOfSpecialProduction(1));
    }

    @Test
    public void testisPersonalProductionActivated(){
        Production production = new Production(p, w, c);
        assertFalse(production.isPersonalProductionActivated());
    }

    @Test
    public void testisProductionActivated(){
        Production production = new Production(p, w, c);
        assertFalse(production.isProductionActivated(1));
    }

    @Test(expected =  ModelException.class)
    public void testisSpecialProductionActivated1() throws ModelException{
        Production production = new Production(p, w, c);
        assertFalse(production.isSpecialProductionActivated(1));
    }

    @Test
    public void testisSpecialProductionActivated2() throws ModelException{
        Production production = new Production(p, w, c);
        production.addSpecialProduction(Resource.COIN);
        assertFalse(production.isSpecialProductionActivated(1));
    }

    @Test
    public void testtotalGainIsEmpty(){
        Production production = new Production(p, w, c);
        assertTrue(production.totalGainIsEmpty());

    }

    @Test
    public void testtotalCostIsEmpty(){
        Production production = new Production(p, w, c);
        assertTrue(production.totalCostIsEmpty());
    }

    @Test
    public void testaddSpecialProduction() throws ModelException{
        Production production = new Production(p, w, c);
        production.addSpecialProduction(Resource.COIN);
        assertEquals(Resource.COIN, production.getTypeOfSpecialProduction(1));
        assertFalse(production.isSpecialProductionActivated(1));
    }

    @Test
    public void testgetProductionInputOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertEquals(m, production.getProductionInputOfSlot(1));
    }

    @Test
    public void testgetProductionOutputOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertEquals(m, production.getProductionOutputOfSlot(1));
    }

    @Test
    public void testgetFaithPointsOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertEquals(3, production.getFaithPointsOfSlot(1));
    }

    @Test
    public void testcontrolRequirementsOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertFalse(production.controlRequirementsOfSlot(1));
    }

    @Test(expected = ModelException.class)
    public void testactivateProductionOfSlot1() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        production.activateProductionOfSlot(1);
    }

    @Test(expected = ModelException.class)
    public void testactivateProductionOfSlot2() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.STONE, 5);

        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 20);
        wareHouse.addToChest(Resource.STONE, 20);
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, wareHouse, cardSlot);
        production.activateProductionOfSlot(1);
        production.activateProductionOfSlot(1);
    }

    @Test
    public void testactivateProductionOfSlot3() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.STONE, 5);

        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 20);
        wareHouse.addToChest(Resource.STONE, 20);
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);

        Production production = new Production(p, wareHouse, cardSlot);
        production.activateProductionOfSlot(1);
        production.getTotalCost().values().removeIf(f -> f==0);
        production.getTotalGain().values().removeIf(f -> f==0);
        assertEquals(m, production.getTotalCost());
        assertEquals(m, production.getTotalGain());
        assertEquals(3, production.getFaithPoints());
        assertTrue(production.isProductionActivated(1));
    }


    @Test(expected = ModelException.class)
    public void testactivatePersonalProduction1() throws ModelException{
        Production production = new Production(p, w, c);
        production.activatePersonalProduction(Resource.STONE, Resource.COIN, Resource.SHIELD);
    }

    @Test(expected = ModelException.class)
    public void testactivatePersonalProduction2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.STONE, 20);
        wareHouse.addToChest(Resource.COIN, 20);

        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(Resource.STONE, Resource.COIN, Resource.SHIELD);
        production.activatePersonalProduction(Resource.COIN, Resource.SHIELD, Resource.SERVANT);
    }

    @Test
    public void testactivatePersonalProduction3() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.STONE, 20);
        wareHouse.addToChest(Resource.COIN, 20);

        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(Resource.STONE, Resource.COIN, Resource.SHIELD);
        assertTrue(production.isPersonalProductionActivated());
    }

    @Test(expected = ModelException.class)
    public void testactivateSpecialProduction1() throws ModelException{
        Production production = new Production(p, w, c);
        production.activateSpecialProduction(Resource.SHIELD, 1);
    }

    @Test(expected = ModelException.class)
    public void testactivateSpecialProduction2() throws ModelException{
        Production production = new Production(p, w, c);
        production.addSpecialProduction(Resource.COIN);
        production.activateSpecialProduction(Resource.SHIELD, 1);
    }

    @Test(expected = ModelException.class)
    public void testactivateSpecialProduction3() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 20);
        Production production = new Production(p, wareHouse, c);

        production.addSpecialProduction(Resource.COIN);
        production.activateSpecialProduction(Resource.SHIELD, 1);
        production.activateSpecialProduction(Resource.STONE, 1);
    }

    @Test
    public void testactivateSpecialProduction4() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 20);
        Production production = new Production(p, wareHouse, c);

        production.addSpecialProduction(Resource.COIN);
        production.activateSpecialProduction(Resource.SHIELD, 1);
        assertTrue(production.isSpecialProductionActivated(1));
        assertEquals(1, production.getFaithPoints());
    }

    @Test(expected = ModelException.class)
    public void testpayCostfromStorage1() throws ModelException {
        Production production = new Production(p, w, c);
        production.payCostfromStorage(Resource.STONE, 50, 1);
    }

    @Test
    public void testpayCostfromStorage2() throws ModelException {
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 1);
        wareHouse.addResourcestoAdd(Resource.COIN, 1, 1);
        wareHouse.addResource(Resource.SHIELD, 1);
        wareHouse.addResourcestoAdd(Resource.SHIELD, 2, 1);


        Production production = new Production(p, wareHouse, c);production.activatePersonalProduction(Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        production.payCostfromStorage(Resource.COIN, 1, 1);

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN,0);
        m.put(Resource.SHIELD, 1);
        m.put(Resource.STONE, 0);
        m.put(Resource.SERVANT, 0);
        assertEquals(m, production.getTotalCost());
    }

    @Test(expected = ModelException.class)
    public void testpayCostfromChest1() throws ModelException{
        Production production = new Production(p, w, c);
        production.payCostfromChest(Resource.COIN, 50);
    }

    @Test
    public void testpayCostfromChest2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 50);
        wareHouse.addToChest(Resource.SERVANT,20);
        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(Resource.COIN, Resource.SERVANT, Resource.SHIELD);
        production.payCostfromChest(Resource.COIN, 1);

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        m.put(Resource.SHIELD, 0);
        m.put(Resource.STONE, 0);
        m.put(Resource.SERVANT, 1);
        assertEquals(m, production.getTotalCost());
    }

    @Test(expected = ModelException.class)
    public void testpayAllfromChest1() throws ModelException{
      Production production = new Production(p, w, c);
      production.activatePersonalProduction(Resource.STONE, Resource.COIN, Resource.SHIELD);
      production.payAllfromChest();
    }

    @Test
    public void testpayAllfromChest2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.STONE, 50);
        wareHouse.addToChest(Resource.COIN, 50);

        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(Resource.STONE, Resource.COIN, Resource.SHIELD);
        production.payAllfromChest();
        assertTrue(production.totalCostIsEmpty());
    }


    @Test(expected = ModelException.class)
    public void testgainResourcesAndEndProduction1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);

        player.getPersonalBoard().getProduction().activatePersonalProduction(Resource.STONE, Resource.COIN, Resource.SHIELD);
        player.getPersonalBoard().getProduction().gainResourcesAndEndProduction();
    }

    @Test
    public void testgainResourcesAndEndProduction2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);

        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getProduction().addSpecialProduction(Resource.COIN);

        player.getPersonalBoard().getProduction().activatePersonalProduction(Resource.STONE, Resource.COIN, Resource.SHIELD);
        player.getPersonalBoard().getProduction().activateSpecialProduction(Resource.STONE, 1);

        player.getPersonalBoard().getProduction().payAllfromChest();
        player.getPersonalBoard().getProduction().gainResourcesAndEndProduction();

        assertFalse(player.getPersonalBoard().getProduction().isPersonalProductionActivated());
        assertFalse(player.getPersonalBoard().getProduction().isSpecialProductionActivated(1));
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromChest(Resource.SHIELD));
        assertTrue(player.getPersonalBoard().getProduction().totalGainIsEmpty());
    }
}