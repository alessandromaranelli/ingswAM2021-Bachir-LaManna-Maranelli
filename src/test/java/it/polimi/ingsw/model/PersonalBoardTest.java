package it.polimi.ingsw.model;

import Exceptions.ModelException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalBoardTest {
    Game game= new Game();
    Player player= new Player("Luigi", 1,game);

    public PersonalBoardTest() throws FileNotFoundException {
    }

    @Test
    public void getProduction() {
        Production production= new Production(player.getPersonalBoard(), player.getPersonalBoard().getWareHouse(),player.getPersonalBoard().getCardSlot());
        assertEquals(production.getCardSlot(),player.getPersonalBoard().getProduction().getCardSlot());
        assertEquals(production.getWareHouse(),player.getPersonalBoard().getProduction().getWareHouse());
    }

    @Test
    public void getWareHouse() {
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT,13);
        assertEquals(13,player.getPersonalBoard().getWareHouse().getFromChest(Resource.SERVANT));
    }

    @Test
    public void getCardSlot() throws ModelException {
        Map<Resource, Integer> m = new HashMap<>();
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        player.getPersonalBoard().getCardSlot().addCardToSlot(card,1);
        assertEquals(card,player.getPersonalBoard().getCardSlot().getTopCardofSlot(1));

    }

    @Test
    public void getFaithTrack() {
        assertEquals(0,player.getPersonalBoard().getFaithTrack().getTrack().indexOf(player.getPersonalBoard().getFaithTrack().checkPlayerPosition()));
    }

    @Test
    public void moveFaithMarker() {
        player.getPersonalBoard().moveFaithMarker();
        assertEquals(1,player.getPersonalBoard().getFaithTrack().getTrack().indexOf(player.getPersonalBoard().getFaithTrack().checkPlayerPosition()));

    }

    @Test
    public void addGetReduction() {
        player.getPersonalBoard().setManageWhiteMarbles(3);
        player.getPersonalBoard().getLastReduction();
        player.getPersonalBoard().getLastWhiteMarble();
        player.getPersonalBoard().addWhiteMarbleToManage();
        player.getPersonalBoard().setManageWhiteMarbles(3);
        player.getPersonalBoard().getLeaderCardsInHand();
        player.getPersonalBoard().getLeaderCardsPlayed();
        player.getPersonalBoard().getWhiteMarble().add(Resource.COIN);
        player.getPersonalBoard().getLastWhiteMarble();
        player.getPersonalBoard().addReduction(Resource.SERVANT);
        player.getPersonalBoard().getLastReduction();
        ArrayList<Resource> resources=new ArrayList<>();
        resources.add(Resource.SERVANT);
        assertEquals(resources,player.getPersonalBoard().getReduction());
        assertEquals(3,player.getPersonalBoard().getManageWhiteMarbles());
    }

    @Test
    public void addGetWhiteMarble() {
        player.getPersonalBoard().addWhiteMarble(Resource.SERVANT);
        ArrayList<Resource> resources=new ArrayList<>();
        resources.add(Resource.SERVANT);
        assertEquals(resources,player.getPersonalBoard().getWhiteMarble());
        player.getPersonalBoard().addWhiteMarble(Resource.COIN);
    }

    @Test
    public void addGetWhiteMarbleToManage() {
        player.getPersonalBoard().addWhiteMarbleToManage();
        assertEquals(1,player.getPersonalBoard().getManageWhiteMarbles());
    }


    @Test(expected = ModelException.class)
    public void manageWhiteMarbles1() throws ModelException {
        player.getPersonalBoard().manageWhiteMarbles(Resource.COIN);
    }

    @Test
    public void manageWhiteMarbles2() throws ModelException {
        Map<Color, Integer> req = new HashMap<>();
        req.put(Color.PURPLE,2);
        req.put(Color.GREEN,1);
        LeaderCardWhiteMarble leaderCardWhiteMarble = new LeaderCardWhiteMarble(3,"This is a WhiteMarble Leader 1",req,Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCardWhiteMarble);
        player.getPersonalBoard().getLeaderCardsInHand().get(0).activateEffect(player.getPersonalBoard());
        player.getPersonalBoard().addWhiteMarbleToManage();
        player.getPersonalBoard().manageWhiteMarbles(Resource.COIN);
        assertEquals(0,player.getPersonalBoard().getManageWhiteMarbles());
        assertEquals(1,player.getPersonalBoard().getWareHouse().getResourcesToAdd().get(Resource.COIN));
    }

    @Test
    public void getLeaderCardsInHand() throws ModelException {
        LeaderCard leaderCard= new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCard);
        player.getPersonalBoard().getDescLeaderCardinHand(1);
        assertEquals(leaderCard,player.getPersonalBoard().getLeaderCardsInHand().get(0));
    }

    @Test
    public void getLeaderCardsPlayed() throws ModelException {
        LeaderCard leaderCard= new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsPlayed().add(leaderCard);
        player.getPersonalBoard().getDescLeaderCardPlayed(1);
        assertEquals(leaderCard,player.getPersonalBoard().getLeaderCardsPlayed().get(0));
    }

    @Test
    public void getDescLeaderCardinHand() throws ModelException {
        LeaderCard leaderCard= new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCard);
        assertEquals("leader red 1",player.getPersonalBoard().getDescLeaderCardinHand(1));
    }

    @Test
    public void getDescLeaderCardPlayed() throws ModelException {
        LeaderCard leaderCard= new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsPlayed().add(leaderCard);
        assertEquals("leader red 1",player.getPersonalBoard().getDescLeaderCardPlayed(1));
    }

    @Test
    public void verifyRequirementsLeaderCard() throws ModelException {
        LeaderCard leaderCard= new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCard);
        Map<Resource, Integer> m = new HashMap<>();
        leaderCard = new LeaderCardStorage(3,"This is a Storage Leader 1",Resource.COIN,Resource.STONE, "hello");
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCard);
        try {
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.GREEN, 1, 1, m, m, m, 0), 2);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        assertTrue(player.getPersonalBoard().verifyRequirementsLeaderCard(1));
        assertFalse(player.getPersonalBoard().verifyRequirementsLeaderCard(2));

    }

    @Test
    public void activateLeaderCard() throws ModelException {
        LeaderCard leaderCard= new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCard);
        Map<Resource, Integer> m = new HashMap<>();
        try {
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0), 1);
            player.getPersonalBoard().getCardSlot().addCardToSlot(new DevelopmentCard(Color.GREEN, 1, 1, m, m, m, 0), 2);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        player.getPersonalBoard().activateLeaderCard(1);
        assertEquals(leaderCard,player.getPersonalBoard().getLeaderCardsPlayed().get(0));
        assertEquals(0,player.getPersonalBoard().getLeaderCardsInHand().size());
        assertEquals(player.getPersonalBoard().getReduction().get(0),Resource.COIN);

    }

    @Test
    public void discardLeaderCard() throws ModelException {
        LeaderCard leaderCard= new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello");
        player.getPersonalBoard().getLeaderCardsInHand().add(leaderCard);
        player.getPersonalBoard().discardLeaderCard(1);
        assertEquals(1,player.getPersonalBoard().getFaithTrack().getTrack().indexOf(player.getPersonalBoard().getFaithTrack().checkPlayerPosition()));
        assertEquals(0,player.getPersonalBoard().getLeaderCardsInHand().size());

    }

    @Test
    public void countVictoryPoints() throws ModelException {
        Map<Resource,Integer> map=new HashMap<>();
        player.getPersonalBoard().getLeaderCardsPlayed().add(new LeaderCardReduction(2, "leader red 1", Color.BLUE, Color.GREEN, Resource.COIN, "hello"));
        player.getPersonalBoard().getLeaderCardsPlayed().add(new LeaderCardSpecialProduction(4,"This is a SpecialProduction Leader 2",Color.PURPLE,Resource.STONE, "hello"));
        player.getPersonalBoard().getCardSlot().getSlot(1).add(new DevelopmentCard(Color.GREEN,1,1,map,map,map,0));
        player.getPersonalBoard().getCardSlot().getSlot(1).add(new DevelopmentCard(Color.GREEN,2,6,map,map,map,0));
        player.getPersonalBoard().getCardSlot().getSlot(2).add(new DevelopmentCard(Color.YELLOW,1,4,map,map,map,0));
        player.getPersonalBoard().getCardSlot().getSlot(2).add(new DevelopmentCard(Color.PURPLE,2,5,map,map,map,0));
        player.getPersonalBoard().getCardSlot().getSlot(3).add(new DevelopmentCard(Color.BLUE,3,12,map,map,map,0));
        player.getPersonalBoard().getCardSlot().getSlot(3).add(new DevelopmentCard(Color.PURPLE,1,3,map,map,map,0));
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD,2);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE,3);
        player.getPersonalBoard().getWareHouse().getStorages().get(0).setType(Resource.SERVANT);
        player.getPersonalBoard().getWareHouse().getStorages().get(0).addToStorage(Resource.SERVANT,1);
        player.getPersonalBoard().getWareHouse().getStorages().get(1).setType(Resource.COIN);
        player.getPersonalBoard().getWareHouse().getStorages().get(1).addToStorage(Resource.COIN,2);
        player.getPersonalBoard().getWareHouse().getStorages().get(2).setType(Resource.SHIELD);
        player.getPersonalBoard().getWareHouse().getStorages().get(2).addToStorage(Resource.SHIELD,2);
        for (int i=0;i<17;i++){
            player.getPersonalBoard().getFaithTrack().movePositionForward();
        }
        player.getPersonalBoard().getFaithTrack().getPopeFavours().get(0).activate();
        player.getPersonalBoard().getFaithTrack().getPopeFavours().get(1).activate();
        assertEquals (53,player.getPersonalBoard().countVictoryPoints());
    }


    @Test
    public void controlCardToBuy() throws ModelException {
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.STONE,3);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE,6);
        assertTrue(player.getPersonalBoard().controlCardToBuy(card,1));
    }

    @Test
    public void chooseCardToBuyGetCardCost() throws ModelException {
        player.getPersonalBoard().addReduction(Resource.SERVANT);
        player.getPersonalBoard().addReduction(Resource.STONE);
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.STONE,3);
        m.put(Resource.SERVANT,2);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE,6);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT,6);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        player.getPersonalBoard().chooseCardToBuy(card,1);
        assertEquals(1,player.getPersonalBoard().getCardCost().get(Resource.SERVANT));
        assertEquals(2,player.getPersonalBoard().getCardCost().get(Resource.STONE));
    }


    @Test
    public void payCardfromStorage() throws ModelException {
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.STONE,3);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        player.getPersonalBoard().getWareHouse().getStorages().get(2).setType(Resource.STONE);
        player.getPersonalBoard().getWareHouse().getStorages().get(2).addToStorage(Resource.STONE,3);
        player.getPersonalBoard().chooseCardToBuy(card,1);
        player.getPersonalBoard().payCardfromStorage(Resource.STONE,2,3);
        assertEquals(1,player.getPersonalBoard().getCardCost().get(Resource.STONE));
        assertEquals(1,player.getPersonalBoard().getWareHouse().getFromStorage(3));
        assertFalse(player.getPersonalBoard().isCardPayed());
    }

    @Test
    public void payCardfromChest() throws ModelException {
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.STONE,3);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE,45);
        player.getPersonalBoard().chooseCardToBuy(card,1);
        player.getPersonalBoard().payCardfromChest(Resource.STONE,3);
        assertEquals(0,player.getPersonalBoard().getCardCost().get(Resource.STONE));
        assertEquals(42,player.getPersonalBoard().getWareHouse().getFromChest(Resource.STONE));
        assertTrue(player.getPersonalBoard().isCardPayed());
    }

    @Test
    public void payAllfromChest() throws ModelException {
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.STONE,3);
        m.put(Resource.COIN,5);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 0);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE,45);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN,6);
        player.getPersonalBoard().chooseCardToBuy(card,1);
        player.getPersonalBoard().payAllfromChest();
        assertEquals(0,player.getPersonalBoard().getCardCost().get(Resource.STONE));
        assertEquals(42,player.getPersonalBoard().getWareHouse().getFromChest(Resource.STONE));
        assertEquals(0,player.getPersonalBoard().getCardCost().get(Resource.COIN));
        assertEquals(1,player.getPersonalBoard().getWareHouse().getFromChest(Resource.COIN));
        assertTrue(player.getPersonalBoard().isCardPayed());
    }

}