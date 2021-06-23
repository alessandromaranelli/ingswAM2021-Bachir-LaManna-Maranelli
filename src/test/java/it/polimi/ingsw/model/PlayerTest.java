package it.polimi.ingsw.model;

import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;
import it.polimi.ingsw.server.Match;
import it.polimi.ingsw.server.Server;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Set<ClientHandler> clientHandlers=new LinkedHashSet<>();
    Controller controller=new Controller(clientHandlers,new Match(new Server()));

    public PlayerTest() throws FileNotFoundException {
    }

    @Test
    public void testgetPlayerID() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        assertEquals(0, player.getPlayerID());
    }

    @Test
    public void testgetNickname() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        assertEquals("flavio", player.getNickname());
    }

    @Test
    public void testsetNickname() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setNickname("xxx");
        assertEquals("xxx", player.getNickname());
    }

    @Test
    public void testsetPlayerID() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setPlayerID(20);
        assertEquals(20, player.getPlayerID());
    }

    /*
    @Test
    public void testgetPersonalBoard() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        assertEquals(new PersonalBoard(game.getVaticanReportSections()), player.getPersonalBoard());
    } */

    @Test
    public void testsetAsCurrentPlayer() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        assertEquals(TurnState.START, player.getPhase());
        assertFalse(player.getLeaderAction());
    }

    @Test
    public void testgetPhase() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        assertEquals(TurnState.PREPARATION, player.getPhase());
    }

    @Test
    public void testgetLeaderAction() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        assertFalse(player.getLeaderAction());
    }

    @Test(expected = ModelException.class)
    public void testdrawLeaderCards1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.drawLeaderCards(controller);
    }

    @Test
    public void testdrawLeaderCards2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        assertEquals(4, player.getPersonalBoard().getLeaderCardsInHand().size());
        assertEquals(TurnState.CHOOSELEADERCARDS, player.getPhase());
        assertTrue(!player.getPersonalBoard().getLeaderCardsInHand().get(0).equals(player.getPersonalBoard().getLeaderCardsInHand().get(1)));
        assertTrue(!player.getPersonalBoard().getLeaderCardsInHand().get(0).equals(player.getPersonalBoard().getLeaderCardsInHand().get(2)));
        assertTrue(!player.getPersonalBoard().getLeaderCardsInHand().get(0).equals(player.getPersonalBoard().getLeaderCardsInHand().get(3)));
        assertTrue(!player.getPersonalBoard().getLeaderCardsInHand().get(1).equals(player.getPersonalBoard().getLeaderCardsInHand().get(2)));
        assertTrue(!player.getPersonalBoard().getLeaderCardsInHand().get(1).equals(player.getPersonalBoard().getLeaderCardsInHand().get(3)));
        assertTrue(!player.getPersonalBoard().getLeaderCardsInHand().get(2).equals(player.getPersonalBoard().getLeaderCardsInHand().get(3)));
    }

    @Test(expected = ModelException.class)
    public void testchooseLeaderCardsToDiscard1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.chooseLeaderCardsToDiscard(0, 1,controller);
    }

    @Test
    public void testchooseLeaderCardsToDiscard2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        LeaderCard card1 = player.getPersonalBoard().getLeaderCardsInHand().get(0);
        LeaderCard card2 = player.getPersonalBoard().getLeaderCardsInHand().get(3);

        player.chooseLeaderCardsToDiscard(2, 3,controller);
        assertEquals(card1, player.getPersonalBoard().getLeaderCardsInHand().get(0));
        assertEquals(card2, player.getPersonalBoard().getLeaderCardsInHand().get(1));
        assertEquals(TurnState.CHOOSERESOURCES, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testsetInitStorageTypes1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
    }

    @Test(expected = ModelException.class)
    public void testsetInitStorageTypes2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.COIN, Resource.SERVANT);
    }

    @Test
    public void testsetInitStorageTypes3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
        assertEquals(Resource.COIN, player.getPersonalBoard().getWareHouse().getTypeStorage(1));
        assertEquals(Resource.STONE, player.getPersonalBoard().getWareHouse().getTypeStorage(2));
        assertEquals(Resource.SERVANT, player.getPersonalBoard().getWareHouse().getTypeStorage(3));
    }

    @Test(expected = ModelException.class)
    public void testaddInitResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.addInitResources(controller,Resource.COIN);
    }

    @Test
    public void testaddInitResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(controller,Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
    }

    @Test
    public void testaddInitResources3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 1, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(controller,Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromStorage(1));
    }

    @Test
    public void testaddInitResources4() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 2, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(controller,Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromStorage(1));
        assertEquals(player.getPersonalBoard().getFaithTrack().getTrack().get(1), player.getPersonalBoard().getFaithTrack().checkPlayerPosition());
    }

    @Test(expected = ModelException.class)
    public void testaddInitResources5() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 3, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(controller,Resource.COIN);
    }

    @Test(expected = ModelException.class)
    public void testAddInitResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 3, game);
        player.addInitResources(controller,Resource.COIN, Resource.STONE);
    }

    @Test(expected = ModelException.class)
    public void testAddInitResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(controller,Resource.COIN, Resource.COIN);
    }

    @Test
    public void testAddInitResources3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 3, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.setInitStorageTypes(controller,Resource.STONE, Resource.COIN, Resource.SERVANT);
        player.addInitResources(controller,Resource.COIN, Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
        assertEquals(2, player.getPersonalBoard().getWareHouse().getFromStorage(2));
        assertEquals(player.getPersonalBoard().getFaithTrack().getTrack().get(2), player.getPersonalBoard().getFaithTrack().checkPlayerPosition());
    }

    @Test(expected = ModelException.class)
    public void testselectMarketPhase1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.selectMarketPhase(controller);
    }

    @Test
    public void testselectMarketPhase2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        assertEquals(TurnState.MARKETPHASE, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void teststartMarketPhase1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.startMarketPhase(controller,1, false);
    }

    @Test
    public void teststartMarketPhase2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        player.startMarketPhase(controller,1, true);
        assertEquals(TurnState.CHOICE, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testmanageWhiteMarbles1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.manageWhiteMarbles(controller,Resource.STONE);
    }

    @Test
    public void testmanageWhiteMarbles2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().addWhiteMarble(Resource.COIN);
        player.getPersonalBoard().addWhiteMarble(Resource.STONE);
        while(player.getPhase() != TurnState.WHITEMARBLES) {
            player.setAsCurrentPlayer();
            player.selectMarketPhase(controller);
            player.startMarketPhase(controller,1, false);
        }

        while(player.getPersonalBoard().getManageWhiteMarbles() > 0){
            player.manageWhiteMarbles(controller,Resource.COIN);
        }

        assertEquals(TurnState.CHOICE, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void teststartOrganizeResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.startOrganizeResources(controller);
    }

    @Test
    public void teststartOrganizeResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        player.startMarketPhase(controller,1, false);
        player.startOrganizeResources(controller);
        assertTrue(player.getPersonalBoard().getWareHouse().resourcesToOrganizeIsEmpty());
    }

    @Test(expected = ModelException.class)
    public void testsetStoragesTypes1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setStoragesTypes(controller,Resource.COIN, Resource.STONE, Resource.SERVANT);
    }

    @Test(expected = ModelException.class)
    public void testsetStoragesTypes2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        player.startMarketPhase(controller,1, false);
        player.startOrganizeResources(controller);
        player.setStoragesTypes(controller,Resource.COIN, Resource.STONE, Resource.COIN);
    }

    @Test
    public void testsetStoragesTypes3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        player.startMarketPhase(controller,1, false);
        player.startOrganizeResources(controller);
        player.setStoragesTypes(controller,Resource.COIN, Resource.STONE, Resource.SHIELD);

        assertEquals(Resource.COIN, player.getPersonalBoard().getWareHouse().getTypeStorage(1));
        assertEquals(Resource.STONE, player.getPersonalBoard().getWareHouse().getTypeStorage(2));
        assertEquals(Resource.SHIELD, player.getPersonalBoard().getWareHouse().getTypeStorage(3));
        assertEquals(TurnState.ADDRESOURCES, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testdefualtManageResourcesToOrganize1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.defaultManageResourcesToOrganize(controller);
    }

    @Test
    public void testdefaultManageResourcesToOrganize2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addResource(Resource.COIN, 1);
        player.getPersonalBoard().getWareHouse().addResource(Resource.SERVANT, 3);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.COIN, 1, 1);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.SERVANT, 3, 3);

        player.manageResourcesInStorages(controller);
        player.setStoragesTypes(controller,Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        player.defaultManageResourcesToOrganize(controller);
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromStorage(1));
        assertEquals(3, player.getPersonalBoard().getWareHouse().getFromStorage(3));
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testmanageResourcesToOrganize1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.manageResourcesToOrganize(controller,Resource.COIN, 1, 1);
    }

    @Test
    public void testmanageResourcesToOrganize2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addResource(Resource.COIN, 1);
        player.getPersonalBoard().getWareHouse().addResource(Resource.SERVANT, 3);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.COIN, 1, 1);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.SERVANT, 3, 3);

        player.manageResourcesInStorages(controller);
        player.setStoragesTypes(controller,Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        player.manageResourcesToOrganize(controller,Resource.COIN, 1, 1);
        player.manageResourcesToOrganize(controller,Resource.SERVANT, 3, 3);
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromStorage(1));
        assertEquals(3, player.getPersonalBoard().getWareHouse().getFromStorage(3));
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void teststartAddResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.startAddResources(controller);
    }

    @Test
    public void teststartAddResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        player.startMarketPhase(controller,1, false);
        player.startAddResources(controller);

        if(player.getPersonalBoard().getWareHouse().resourcesToAddIsEmpty()){
            assertEquals(TurnState.ENDTURN, player.getPhase());
        }
        else assertEquals(TurnState.ADDRESOURCES, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testaddResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.addResources(controller,Resource.COIN, 1, 1);
    }

    @Test
    public void testaddResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addResource(Resource.COIN, 1);
        player.getPersonalBoard().getWareHouse().addResource(Resource.SHIELD, 1);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.SHIELD, 2, 1);

        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        player.startMarketPhase(controller,1, false);
        player.startOrganizeResources(controller);
        player.setStoragesTypes(controller,Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        player.defaultManageResourcesToOrganize(controller);

        player.addResources(controller,Resource.COIN, 1, 1);
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromStorage(1));
        if(player.getPersonalBoard().getWareHouse().resourcesToAddIsEmpty()){
            assertEquals(TurnState.ENDTURN, player.getPhase());
        }
        else assertEquals(TurnState.ADDRESOURCES, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testdiscardResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.discardResources(controller,Resource.COIN, 1);
    }

    @Test
    public void testdiscardResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player2 = new Player("xxx", 1, game);
        Player player = new Player("flavio", 0, game);
        game.createNewPlayer(player2);
        player.getPersonalBoard().getWareHouse().addResource(Resource.COIN, 1);

        player.setAsCurrentPlayer();
        player.selectMarketPhase(controller);
        player.startMarketPhase(controller,1, false);
        player.startAddResources(controller);
        player.discardResources(controller,Resource.COIN, 1);
        assertEquals(player2.getPersonalBoard().getFaithTrack().getTrack().get(1), player2.getPersonalBoard().getFaithTrack().checkPlayerPosition());
        if(player.getPersonalBoard().getWareHouse().resourcesToAddIsEmpty()){
            assertEquals(TurnState.ENDTURN, player.getPhase());
        }
        else assertEquals(TurnState.ADDRESOURCES, player.getPhase());
    }

    @Test
    public void testmanageResourcesInStorages() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.manageResourcesInStorages(controller);
        assertEquals(TurnState.ORGANIZERESOURCES, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testselectBuyDevelopmentCardPhase1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.selectBuyDevelopmentCardPhase(controller);
    }

    @Test
    public void testselectBuyDevelopmentCardPhase2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectBuyDevelopmentCardPhase(controller);
        assertEquals(TurnState.BUYDEVELOPMENTCARDPHASE, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testchooseDevelopmentCard1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.chooseDevelopmentCard(controller,Color.BLUE, 1, 1);
    }

    @Test
    public void testchooseDevelopmentCard2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);
        DevelopmentCard card = game.getTable().viewDevelopmentCard(Color.BLUE, 1);

        player.setAsCurrentPlayer();
        player.selectBuyDevelopmentCardPhase(controller);
        player.chooseDevelopmentCard(controller,Color.BLUE, 1, 1);
        assertEquals(card, player.getPersonalBoard().getCardSlot().getTopCardofSlot(1));
        assertEquals(TurnState.PAYDEVELOPMENTCARD, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testpayCardAllFromChest1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.payCardAllFromChest();
    }

    @Test
    public void testpayCardAllFromChest2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);

        player.setAsCurrentPlayer();
        player.selectBuyDevelopmentCardPhase(controller);
        player.chooseDevelopmentCard(controller,Color.BLUE, 1, 1);
        player.payCardAllFromChest();
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testpayCardFromChest1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.payCardFromChest(controller,Resource.COIN, 1);
    }

    @Test
    public void testpayCardFromChest2() throws ModelException, FileNotFoundException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 1);
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);

        player.setAsCurrentPlayer();
        player.selectBuyDevelopmentCardPhase(controller);
        player.chooseDevelopmentCard(controller,Color.BLUE, 1, 1);

        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 1);
        player.getPersonalBoard().chooseCardToBuy(card, 2);
        player.payCardFromChest(controller,Resource.COIN, 1);
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testpayCardFromStorage1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.payCardFromStorage(controller,Resource.COIN, 1, 1);
    }

    @Test
    public void testpayCardFromStorage2() throws ModelException, FileNotFoundException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 1);
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);
        player.getPersonalBoard().getWareHouse().addResource(Resource.COIN, 1);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.COIN, 1, 1);

        player.setAsCurrentPlayer();
        player.selectBuyDevelopmentCardPhase(controller);
        player.chooseDevelopmentCard(controller,Color.BLUE, 1, 1);

        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 1);
        player.getPersonalBoard().chooseCardToBuy(card, 2);
        player.payCardFromStorage(controller,Resource.COIN, 1, 1);
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testselectProductionPhase1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.selectProductionPhase(controller);
    }

    @Test
    public void testselectProductionPhase2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        assertEquals(TurnState.PRODUCTIONPHASE, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testactivateProductionOfSlot1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.activateProductionOfSlot(controller,1);
    }

    @Test
    public void testactivateProductionOfSlot2() throws ModelException, FileNotFoundException{
        Map<Resource, Integer> m = new HashMap<>();
        m.put(Resource.COIN, 1);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, 1, 1, m, m, m, 1);

        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);
        player.getPersonalBoard().getCardSlot().addCardToSlot(card, 1);

        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        player.activateProductionOfSlot(controller,1);
        assertTrue(player.getPersonalBoard().getProduction().isProductionActivated(1));
    }

    @Test(expected = ModelException.class)
    public void testactivatePersonalProduction1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.activatePersonalProduction(controller,Resource.STONE, Resource.COIN, Resource.SHIELD);
    }

    @Test
    public void testactivatePersonalProduction2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);

        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        player.activatePersonalProduction(controller,Resource.STONE, Resource.COIN, Resource.SERVANT);
        assertTrue(player.getPersonalBoard().getProduction().isPersonalProductionActivated());
    }

    @Test(expected = ModelException.class)
    public void testactivateSpecialProduction1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.activateSpecialProduction(controller,Resource.SERVANT, 1);
    }

    @Test
    public void testactivateSpecialProduction2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);
        player.getPersonalBoard().getProduction().addSpecialProduction(Resource.COIN);

        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        player.activateSpecialProduction(controller,Resource.SERVANT, 1);
        assertTrue(player.getPersonalBoard().getProduction().isSpecialProductionActivated(1));
    }

    @Test(expected = ModelException.class)
    public void teststartPayProduction1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.startPayProduction(controller);
    }

    @Test
    public void teststartPayProduction2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        player.startPayProduction(controller);
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testpayProductionAllFromChest1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.payProductionAllFromChest();
    }

    @Test
    public void testpayProductionAllFromChest2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);

        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        player.activatePersonalProduction(controller,Resource.STONE, Resource.COIN, Resource.SHIELD);
        player.startPayProduction(controller);
        player.payProductionAllFromChest();
        assertEquals(51, player.getPersonalBoard().getWareHouse().getFromChest(Resource.SHIELD));
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testpayProductionFromChest1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.payCardFromChest(controller,Resource.SHIELD, 1);
    }

    @Test
    public void testpayProductionFromChest2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.COIN, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SERVANT, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.STONE, 50);
        player.getPersonalBoard().getWareHouse().addToChest(Resource.SHIELD, 50);

        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        player.activatePersonalProduction(controller,Resource.STONE, Resource.COIN, Resource.SHIELD);
        player.startPayProduction(controller);
        player.payProductionFromChest(controller,Resource.COIN, 1);
        player.payProductionFromChest(controller,Resource.STONE, 1);

        assertEquals(51, player.getPersonalBoard().getWareHouse().getFromChest(Resource.SHIELD));
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testpayProductionFromStorage1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.payProductionFromStorage(controller,Resource.STONE, 1, 1);
    }

    @Test
    public void testpayProductionFromStorage2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.getPersonalBoard().getWareHouse().addResource(Resource.COIN, 1);
        player.getPersonalBoard().getWareHouse().addResource(Resource.SERVANT, 1);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.COIN, 1, 1);
        player.getPersonalBoard().getWareHouse().addResourcestoAdd(Resource.SERVANT, 3, 1);


        player.setAsCurrentPlayer();
        player.selectProductionPhase(controller);
        player.activatePersonalProduction(controller,Resource.SERVANT, Resource.COIN, Resource.SHIELD);
        player.startPayProduction(controller);
        player.payProductionFromStorage(controller,Resource.COIN, 1, 1);
        player.payProductionFromStorage(controller,Resource.SERVANT, 1, 3);

        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromChest(Resource.SHIELD));
        assertEquals(TurnState.ENDTURN, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testactivateLeaderCard1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.activateLeaderCard(controller,1);
    }

    @Test(expected = ModelException.class)
    public void testactivateLeaderCard2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.addInitResources(controller,Resource.COIN);
        player.setAsCurrentPlayer();
        player.activateLeaderCard(controller,1);
    }

    @Test(expected = ModelException.class)
    public void testdiscardLeaderCard1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.discardLeaderCard(controller,1);
    }

    @Test
    public void testdiscardLeaderCard2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards(controller);
        player.chooseLeaderCardsToDiscard(1, 2,controller);
        player.addInitResources(controller,Resource.COIN);
        player.setAsCurrentPlayer();
        player.discardLeaderCard(controller,1);
        assertTrue(player.getLeaderAction());
    }
}