package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class WareHouseTest {

    @Test
    public void testgetFromStorages() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 3);
        wareHouse.getFromStorages();

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.SERVANT, 3);
        assertEquals(m, wareHouse.getResourcesToOrganize());
        assertEquals(0, wareHouse.getFromStorage(1));
        assertEquals(0, wareHouse.getFromStorage(2));
        assertEquals(0, wareHouse.getFromStorage(3));
    }

    @Test
    public void testaddResource1(){
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 3);

        Map<Resource, Integer> m= new HashMap<>();
        m.put(Resource.COIN, 3);
        assertEquals(m, wareHouse.getResourcesToAdd());
    }

    @Test
    public void testaddResource2(){
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 3);
        wareHouse.addResource(Resource.COIN, 3);

        Map<Resource, Integer> m= new HashMap<>();
        m.put(Resource.COIN, 6);
        assertEquals(m, wareHouse.getResourcesToAdd());
    }

    @Test(expected = ModelException.class)
    public void testsetTypeStorage1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.setTypeOfStorage(21, Resource.COIN);
    }

    @Test
    public void testsetTypeStorage2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.setTypeOfStorage(2, Resource.COIN);
        assertEquals(Resource.COIN, wareHouse.getTypeStorage(2));
    }

    @Test
    public void testcontrolStoragesType1(){
        WareHouse wareHouse = new WareHouse();
        assertTrue(wareHouse.controlStoragesType());
    }

    @Test
    public void testcontrolStoragesType2() throws ModelException {
        WareHouse wareHouse = new WareHouse();
        wareHouse.getStorages().get(1).setType(Resource.COIN);
        wareHouse.getStorages().get(2).setType(Resource.COIN);
        assertFalse(wareHouse.controlStoragesType());
    }

    @Test
    public void testcontrolStoragesType3() throws FileNotFoundException, ModelException {
        Game game= new Game();
        PersonalBoard personalBoard= new PersonalBoard(game.getVaticanReportSections());
        LeaderCardStorage leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 1",Resource.COIN,Resource.STONE, "hello");
        leaderCardStorage.activateEffect(personalBoard);
        personalBoard.getWareHouse().getStorages().get(0).setType(Resource.SERVANT);
        personalBoard.getWareHouse().getStorages().get(1).setType(Resource.STONE);
        personalBoard.getWareHouse().getStorages().get(2).setType(Resource.SHIELD);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.COIN,3);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.SHIELD,2);
        assertFalse(personalBoard.getWareHouse().controlStoragesType());
    }

    @Test
    public void testcontrolStoragesType4() throws FileNotFoundException, ModelException {
        Game game= new Game();
        PersonalBoard personalBoard= new PersonalBoard(game.getVaticanReportSections());
        LeaderCardStorage leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 1",Resource.COIN,Resource.STONE, "hello");
        leaderCardStorage.activateEffect(personalBoard);
        personalBoard.getWareHouse().getStorages().get(0).setType(Resource.SERVANT);
        personalBoard.getWareHouse().getStorages().get(1).setType(Resource.COIN);
        personalBoard.getWareHouse().getStorages().get(2).setType(Resource.SHIELD);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.COIN,3);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.SHIELD,2);
        assertFalse(personalBoard.getWareHouse().controlStoragesType());
    }

    @Test
    public void testcontrolStoragesType5() throws FileNotFoundException, ModelException {
        Game game= new Game();
        PersonalBoard personalBoard= new PersonalBoard(game.getVaticanReportSections());
        LeaderCardStorage leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 1",Resource.COIN,Resource.STONE, "hello");
        leaderCardStorage.activateEffect(personalBoard);
        leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 2",Resource.SERVANT,Resource.COIN, "hello");
        leaderCardStorage.activateEffect(personalBoard);
        personalBoard.getWareHouse().getStorages().get(0).setType(Resource.SERVANT);
        personalBoard.getWareHouse().getStorages().get(1).setType(Resource.STONE);
        personalBoard.getWareHouse().getStorages().get(2).setType(Resource.SHIELD);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.COIN,3);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.SHIELD,2);
        assertFalse(personalBoard.getWareHouse().controlStoragesType());
    }

    @Test
    public void testcontrolStoragesType6() throws FileNotFoundException, ModelException {
        Game game= new Game();
        PersonalBoard personalBoard= new PersonalBoard(game.getVaticanReportSections());
        LeaderCardStorage leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 1",Resource.COIN,Resource.STONE, "hello");
        leaderCardStorage.activateEffect(personalBoard);
        leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 2",Resource.SERVANT,Resource.COIN, "hello");
        leaderCardStorage.activateEffect(personalBoard);
        personalBoard.getWareHouse().getStorages().get(0).setType(Resource.SERVANT);
        personalBoard.getWareHouse().getStorages().get(1).setType(Resource.COIN);
        personalBoard.getWareHouse().getStorages().get(2).setType(Resource.SHIELD);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.COIN,3);
        personalBoard.getWareHouse().getResourcesToOrganize().put(Resource.SHIELD,2);
        assertTrue(personalBoard.getWareHouse().controlStoragesType());
    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoOrganize1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResourcestoOrganize(Resource.COIN, 3, 3);
    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoOrganize2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 3);
        wareHouse.getFromStorages();

        wareHouse.addResourcestoOrganize(Resource.SERVANT, 21, 3);
    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoOrganize3() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 3);
        wareHouse.getFromStorages();

        wareHouse.addResourcestoOrganize(Resource.SERVANT, 3, 21);
    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoOrganize4() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 3);
        wareHouse.getFromStorages();

        wareHouse.addResourcestoOrganize(Resource.COIN, 3, 3);
    }

    @Test
    public void testaddResourcestoOrganize5() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 3);
        wareHouse.getFromStorages();

        wareHouse.addResourcestoOrganize(Resource.SERVANT, 3, 2);
        assertEquals(2, wareHouse.getFromStorage(3));

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.SERVANT, 1);
        assertEquals(m, wareHouse.getResourcesToOrganize());

        wareHouse.addResourcestoOrganize(Resource.SERVANT, 3, 1);
        assertTrue(wareHouse.resourcesToOrganizeIsEmpty());
    }

    @Test
    public void testdefaultAddResourcesToOrganize() throws ModelException {
        WareHouse wareHouse= new WareHouse();
        wareHouse.getStorages().get(0).setType(Resource.SERVANT);
        wareHouse.getStorages().get(1).setType(Resource.STONE);
        wareHouse.getStorages().get(2).setType(Resource.SHIELD);
        wareHouse.getResourcesToOrganize().put(Resource.SERVANT,1);
        wareHouse.getResourcesToOrganize().put(Resource.STONE,1);
        wareHouse.getResourcesToOrganize().put(Resource.SHIELD,1);
        wareHouse.defaultAddResourcesToOrganize();
        assertEquals(0,wareHouse.getResourcesToOrganize().values().stream().reduce(0, Integer::sum));

    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoAdd1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResourcestoAdd(Resource.COIN, 3 , 3);
    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoAdd2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 21, 3);
    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoAdd3() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.COIN, 3, 3);
    }

    @Test(expected = ModelException.class)
    public void testaddResourcestoAdd4() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 21);
    }

    @Test
    public void testaddResourcestoAdd5() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);

        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 2);
        assertEquals(2, wareHouse.getFromStorage(3));

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.SERVANT, 1);
        assertEquals(m, wareHouse.getResourcesToAdd());

        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 1);
        assertTrue(wareHouse.resourcesToAddIsEmpty());
    }

    @Test(expected = ModelException.class)
    public void testdiscardResourcesToAdd1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.discardResourcesToAdd(Resource.COIN, 3);
    }

    @Test(expected = ModelException.class)
    public void testdiscardResourcesToAdd2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 3);
        wareHouse.discardResourcesToAdd(Resource.COIN, 20);
    }

    @Test(expected = ModelException.class)
    public void testdiscardResourcesToAdd3() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 3);
        wareHouse.discardResourcesToAdd(Resource.SERVANT, 3);
    }

    @Test
    public void testdiscardResourcesToAdd4() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 3);
        wareHouse.discardResourcesToAdd(Resource.COIN, 2);

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 1);
        assertEquals(m, wareHouse.getResourcesToAdd());

        wareHouse.discardResourcesToAdd(Resource.COIN, 1);
        assertTrue(wareHouse.resourcesToAddIsEmpty());
    }

    @Test
    public void testresourcesToOrganizeIsEmpty1(){
        WareHouse wareHouse = new WareHouse();
        assertTrue(wareHouse.resourcesToOrganizeIsEmpty());
    }

    @Test
    public void testresourcesToOrganizeIsEmpty2(){
        WareHouse wareHouse = new WareHouse();
        wareHouse.getResourcesToOrganize().put(Resource.COIN,4);
        assertFalse(wareHouse.resourcesToOrganizeIsEmpty());
    }

    @Test
    public void testresourcesToAddIsEmpty1(){
        WareHouse wareHouse = new WareHouse();
        assertTrue(wareHouse.resourcesToAddIsEmpty());
    }

    @Test
    public void testresourcesToAddIsEmpty2(){
        WareHouse wareHouse = new WareHouse();
        wareHouse.getResourcesToAdd().put(Resource.COIN,4);
        assertFalse(wareHouse.resourcesToAddIsEmpty());
    }

    @Test
    public void testgetResourcesToAdd(){
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 3);

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        assertEquals(m, wareHouse.getResourcesToAdd());
    }

    @Test
    public void testgetResourcesToOrganize() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.COIN, 3);
        wareHouse.addResourcestoAdd(Resource.COIN, 1, 1);
        wareHouse.getFromStorages();

        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 1);
        assertEquals(m, wareHouse.getResourcesToOrganize());
    }

    @Test
    public void testgetFromChest(){
        WareHouse wareHouse = new WareHouse();
        assertEquals(0, wareHouse.getFromChest(Resource.COIN));
    }

    @Test(expected = ModelException.class)
    public void testgetFromStorage1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        assertEquals(0, wareHouse.getFromStorage(20));
    }

    @Test
    public void testgetFromStorage2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        assertEquals(0, wareHouse.getFromStorage(1));
    }

    @Test(expected = ModelException.class)
    public void testgetTypeStorage1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        assertEquals(Resource.COIN, wareHouse.getTypeStorage(20));
    }

    @Test
    public void testgetTypeStorage2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        assertEquals(Resource.COIN, wareHouse.getTypeStorage(1));
    }

    @Test
    public void testgetMapfromChest1(){
        WareHouse wareHouse = new WareHouse();
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);

        assertEquals(m, wareHouse.getMapfromChest(Resource.COIN));
    }

    @Test
    public void testgetMapfromChest2(){
        WareHouse wareHouse = new WareHouse();
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        m.put(Resource.SERVANT, 0);
        m.put(Resource.STONE, 0);
        m.put(Resource.SHIELD, 0);

        assertEquals(m, wareHouse.getMapfromChest());
    }

    @Test(expected = ModelException.class)
    public void testgetMapfromStorage1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        Map<Resource, Integer> m = wareHouse.getMapfromStorage(20);
    }

    @Test
    public void testgetMapfromStorage2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        assertEquals(m, wareHouse.getMapfromStorage(1));
    }

    @Test
    public void testgetMapfromAllStorages1() throws ModelException {
        WareHouse wareHouse = new WareHouse();
        Map<Resource, Integer> m = new HashMap<>();
        wareHouse.getStorages().get(0).setType(Resource.SERVANT);
        wareHouse.getStorages().get(1).setType(Resource.COIN);
        wareHouse.getStorages().get(2).setType(Resource.SHIELD);
        m.put(Resource.COIN, 0);
        m.put(Resource.SERVANT, 0);
        m.put(Resource.SHIELD, 0);
        assertEquals(m, wareHouse.getMapfromAllStorages());
    }

    @Test
    public void testgetMapfromAllStorages2() throws ModelException, FileNotFoundException {
        Game game= new Game();
        PersonalBoard personalBoard= new PersonalBoard(game.getVaticanReportSections());
        LeaderCardStorage leaderCardStorage = new LeaderCardStorage(3,"This is a Storage Leader 1",Resource.COIN,Resource.COIN, "hello");
        leaderCardStorage.activateEffect(personalBoard);
        Map<Resource, Integer> m = new HashMap<>();
        personalBoard.getWareHouse().getStorages().get(0).setType(Resource.SERVANT);
        personalBoard.getWareHouse().getStorages().get(1).setType(Resource.COIN);
        personalBoard.getWareHouse().getStorages().get(2).setType(Resource.SHIELD);
        m.put(Resource.COIN, 0);
        m.put(Resource.SERVANT, 0);
        m.put(Resource.SHIELD, 0);
        assertEquals(m, personalBoard.getWareHouse().getMapfromAllStorages());
    }

    @Test
    public void testTotalResources(){
        WareHouse wareHouse = new WareHouse();
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 0);
        m.put(Resource.SERVANT, 0);
        m.put(Resource.SHIELD, 0);
        m.put(Resource.STONE, 0);
        assertEquals(m, wareHouse.totalResources());
    }

    @Test(expected = ModelException.class)
    public void testsubFromStorage1() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.subFromStorage(Resource.COIN, 3, 20);
    }

    @Test
    public void testsubFromStorage2() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addResource(Resource.SERVANT, 3);
        wareHouse.addResourcestoAdd(Resource.SERVANT, 3, 3);
        wareHouse.subFromStorage(Resource.SERVANT, 2, 3);

        assertEquals(1, wareHouse.getFromStorage(3));
    }

    @Test
    public void testsubFromChest() throws ModelException{
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 3);
        wareHouse.subFromChest(Resource.COIN, 2);
        assertEquals(1, wareHouse.getFromChest(Resource.COIN));
    }

    @Test
    public void testaddToChest(){
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 3);
        assertEquals(3, wareHouse.getFromChest(Resource.COIN));
    }

    @Test
    public void testcontrolRequirements(){
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 3);
        m.put(Resource.SERVANT, 5);
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 50);
        wareHouse.addToChest(Resource.SERVANT, 50);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);

        assertTrue(wareHouse.controlRequirements(card));
    }

    @Test
    public void testcontrolForStorage(){
        WareHouse wareHouse = new WareHouse();
        wareHouse.addToChest(Resource.COIN, 50);

        assertTrue(wareHouse.controlForStorage(Resource.COIN, 5));
    }

    @Test
    public void testaddStorage() throws ModelException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.STONE, 0);
        WareHouse wareHouse = new WareHouse();
        wareHouse.addStorage(new Storage(2, Resource.STONE));
        assertEquals(m, wareHouse.getMapfromStorage(4));
    }

    @Test
    public void testaddInitResources1() throws ModelException {
        WareHouse wareHouse= new WareHouse();
        wareHouse.addInitResources(Resource.COIN);
        assertEquals(1,wareHouse.getFromStorage(1));

    }

    @Test
    public void testaddInitResources2() throws ModelException {
        WareHouse wareHouse= new WareHouse();
        wareHouse.getStorages().get(0).setType(Resource.COIN);
        wareHouse.getStorages().get(1).setType(Resource.SERVANT);
        wareHouse.getStorages().get(2).setType(Resource.STONE);
        wareHouse.addInitResources(Resource.COIN,Resource.SERVANT);
        assertEquals(1,wareHouse.getFromStorage(1));
        assertEquals(1,wareHouse.getFromStorage(2));

    }
}