package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import Exceptions.ModelException;

import java.util.Map;

import static it.polimi.ingsw.model.Resource.*;
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
        m.put(COIN, 0);
        m.put(STONE, 0);
        m.put(SHIELD, 0);
        m.put(Resource.SERVANT, 0);

        assertEquals(m, production.getTotalGain());
    }

    @Test
    public void testgetTotalCost(){
        Production production = new Production(p, w, c);
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 0);
        m.put(STONE, 0);
        m.put(SHIELD, 0);
        m.put(Resource.SERVANT, 0);

        assertEquals(m, production.getTotalCost());
    }

    @Test
    public void testgetFaithPoints(){
        Production production = new Production(p, w, c);
        assertEquals(0, production.getFaithPoints());
        production.setFaithPoints(5);
        assertEquals(5,production.getFaithPoints());
    }

    @Test
    public void testnumOfSpecialProduction1(){
        Production production = new Production(p, w, c);
        production.addSpecialProduction(SHIELD);
        assertEquals(1, production.numOfSpecialProduction());
    }

    @Test
    public void testnumOfSpecialProduction2(){
        Production production = new Production(p, w, c);
        production.addSpecialProduction(SHIELD);
        production.addSpecialProduction(COIN);
        assertEquals(2, production.numOfSpecialProduction());
    }

    @Test
    public void testgetTypeOfSpecialProduction1() throws ModelException{
        Production production = new Production(p, w, c);

        assertThrows(ModelException.class,()->{assertEquals(COIN, production.getTypeOfSpecialProduction(21));});
    }

    @Test
    public void testgetTypeOfSpecialProduction2() throws ModelException{
        Production production = new Production(p, w, c);
        production.addSpecialProduction(COIN);
        assertEquals(COIN, production.getTypeOfSpecialProduction(1));
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


    public void testisSpecialProductionActivated1() throws ModelException{
        Production production = new Production(p, w, c);
        assertFalse(production.isSpecialProductionActivated(1));
        assertThrows(ModelException.class,()->{production.isSpecialProductionActivated(1);});
    }

    @Test
    public void testisSpecialProductionActivated2() throws ModelException{
        Production production = new Production(p, w, c);
        production.addSpecialProduction(COIN);
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
        production.addSpecialProduction(COIN);
        assertEquals(COIN, production.getTypeOfSpecialProduction(1));
        assertFalse(production.isSpecialProductionActivated(1));
    }

    @Test
    public void testgetProductionInputOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 3);
        m.put(STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertEquals(m, production.getProductionInputOfSlot(1));
    }

    @Test
    public void testgetProductionOutputOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 3);
        m.put(STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertEquals(m, production.getProductionOutputOfSlot(1));
    }

    @Test
    public void testgetFaithPointsOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 3);
        m.put(STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertEquals(3, production.getFaithPointsOfSlot(1));
    }

    @Test
    public void testcontrolRequirementsOfSlot() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 3);
        m.put(STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);
        assertFalse(production.controlRequirementsOfSlot(1));
    }

    @Test
    public void testactivateProductionOfSlot1() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 3);
        m.put(STONE, 5);

        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, w, cardSlot);

        assertThrows(ModelException.class,()->{production.activateProductionOfSlot(1);});

    }

    @Test
    public void testactivateProductionOfSlot2() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 3);
        m.put(STONE, 5);

        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(COIN, 20);
        wareHouse.addToChest(STONE, 20);
        CardSlot cardSlot = new CardSlot();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 3);
        cardSlot.addCardToSlot(card, 1);
        Production production = new Production(p, wareHouse, cardSlot);
        production.activateProductionOfSlot(1);

        assertThrows(ModelException.class,()->{production.activateProductionOfSlot(1);});
    }

    @Test
    public void testactivateProductionOfSlot3() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 3);
        m.put(STONE, 5);

        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(COIN, 20);
        wareHouse.addToChest(STONE, 20);
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
        production.disactivateAllProductions();
    }


    @Test
    public void testactivatePersonalProduction1() throws ModelException{
        Production production = new Production(p, w, c);

        assertThrows(ModelException.class,()->{production.activatePersonalProduction(STONE, COIN, SHIELD);});
    }

    @Test
    public void testactivatePersonalProduction2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(STONE, 20);
        wareHouse.addToChest(COIN, 20);

        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(STONE, COIN, SHIELD);

        assertThrows(ModelException.class,()->{production.activatePersonalProduction(COIN, SHIELD, Resource.SERVANT);});
    }

    @Test
    public void testactivatePersonalProduction3() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(STONE, 20);
        wareHouse.addToChest(COIN, 20);

        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(STONE, COIN, SHIELD);
        assertTrue(production.isPersonalProductionActivated());
    }

    @Test
    public void testactivateSpecialProduction1() throws ModelException{
        Production production = new Production(p, w, c);

        assertThrows(ModelException.class,()->{production.activateSpecialProduction(SHIELD, 1);});
    }

    @Test
    public void testactivateSpecialProduction2() throws ModelException{
        Production production = new Production(p, w, c);
        production.addSpecialProduction(COIN);

        assertThrows(ModelException.class,()->{production.activateSpecialProduction(SHIELD, 1);});

    }

    @Test
    public void testactivateSpecialProduction3() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(COIN, 20);
        Production production = new Production(p, wareHouse, c);

        production.addSpecialProduction(COIN);
        production.activateSpecialProduction(SHIELD, 1);

        assertThrows(ModelException.class,()->{production.activateSpecialProduction(STONE, 1);});
    }

    @Test
    public void testactivateSpecialProduction4() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(COIN, 20);
        Production production = new Production(p, wareHouse, c);

        production.addSpecialProduction(COIN);
        production.activateSpecialProduction(SHIELD, 1);
        assertTrue(production.isSpecialProductionActivated(1));
        assertEquals(1, production.getFaithPoints());
        production.disactivateAllProductions();
    }

    @Test
    public void testpayCostfromStorage1() throws ModelException {
        Production production = new Production(p, w, c);

        assertThrows(ModelException.class,()->{production.payCostfromStorage(STONE, 50, 1);});
    }

    @Test
    public void testpayCostfromStorage2() throws ModelException {
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(COIN, 1);
        wareHouse.addResourcestoAdd(COIN, 1, 1);
        wareHouse.addResource(SHIELD, 1);
        wareHouse.addResourcestoAdd(SHIELD, 2, 1);


        Production production = new Production(p, wareHouse, c);production.activatePersonalProduction(COIN, SHIELD, Resource.SERVANT);
        production.payCostfromStorage(COIN, 1, 1);

        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN,0);
        m.put(SHIELD, 1);
        m.put(STONE, 0);
        m.put(Resource.SERVANT, 0);
        assertEquals(m, production.getTotalCost());
    }

    @Test
    public void testpayCostfromChest1() throws ModelException{
        Production production = new Production(p, w, c);

        assertThrows(ModelException.class,()->{production.payCostfromChest(COIN, 50);});
    }

    @Test
    public void testpayCostfromChest2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(COIN, 50);
        wareHouse.addToChest(Resource.SERVANT,20);
        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(COIN, Resource.SERVANT, SHIELD);
        production.payCostfromChest(COIN, 1);

        Map<Resource, Integer> m = new HashMap<>();
        m.put(COIN, 0);
        m.put(SHIELD, 0);
        m.put(STONE, 0);
        m.put(Resource.SERVANT, 1);
        assertEquals(m, production.getTotalCost());
    }

    @Test
    public void testpayAllfromChest1() throws ModelException{
      Production production = new Production(p, w, c);


      assertThrows(ModelException.class,()->{production.activatePersonalProduction(STONE, COIN, SHIELD);});
    }

    @Test
    public void testpayAllfromChest2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(STONE, 50);
        wareHouse.addToChest(COIN, 50);

        Production production = new Production(p, wareHouse, c);
        production.activatePersonalProduction(STONE, COIN, SHIELD);
        production.payAllfromChest();
        assertTrue(production.totalCostIsEmpty());
    }


    @Test
    public void testgainResourcesAndEndProduction1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);



        assertThrows(ModelException.class,()->{player.getPersonalBoard().getProduction().activatePersonalProduction(STONE, COIN, SHIELD);});
    }

    @Test
    public void testgainResourcesAndEndProduction2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);

        player.getPersonalBoard().getWareHouse().addToChest(COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(STONE, 50);
        player.getPersonalBoard().getProduction().addSpecialProduction(COIN);

        player.getPersonalBoard().getProduction().activatePersonalProduction(STONE, COIN, SHIELD);
        player.getPersonalBoard().getProduction().activateSpecialProduction(STONE, 1);

        player.getPersonalBoard().getProduction().payAllfromChest();
        player.getPersonalBoard().getProduction().gainResourcesAndEndProduction();

        assertFalse(player.getPersonalBoard().getProduction().isPersonalProductionActivated());
        assertFalse(player.getPersonalBoard().getProduction().isSpecialProductionActivated(1));
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromChest(SHIELD));
        System.out.println(player.getPersonalBoard().getWareHouse().getMapfromChest().toString());
        assertTrue(player.getPersonalBoard().getProduction().totalGainIsEmpty());
    }

    @Test
    public void testgainResourcesAndEndProduction3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().setTypeOfStorage(1,STONE);
        player.getPersonalBoard().getWareHouse().setTypeOfStorage(2,SHIELD);
        player.getPersonalBoard().getWareHouse().setTypeOfStorage(3,COIN);
        player.getPersonalBoard().getWareHouse().getStorages().get(0).addToStorage(STONE,1);
        player.getPersonalBoard().getWareHouse().getStorages().get(1).addToStorage(SHIELD,2);
        player.getPersonalBoard().getWareHouse().getStorages().get(2).addToStorage(COIN,2);


        player.getPersonalBoard().getProduction().activatePersonalProduction(STONE, COIN, SHIELD);

        player.getPersonalBoard().getProduction().payCostfromStorage(STONE,1,1);
        player.getPersonalBoard().getProduction().payCostfromStorage(COIN,1,3);
        player.getPersonalBoard().getProduction().gainResourcesAndEndProduction();

        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromChest(SHIELD));
        System.out.println(player.getPersonalBoard().getWareHouse().getMapfromChest().toString());
        System.out.println(player.getPersonalBoard().getWareHouse().getMapfromAllStorages().toString());
        assertTrue(player.getPersonalBoard().getProduction().totalGainIsEmpty());
    }
}