package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import Exceptions.ModelException;
import org.junit.jupiter.api.*;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

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

    @Test
    public void testgetPersonalBoard() throws FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        assertEquals(new PersonalBoard(game.getVaticanReportSections()), player.getPersonalBoard());
    }

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
        player.drawLeaderCards();
    }

    @Test
    public void testdrawLeaderCards2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards();
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
        player.chooseLeaderCardsToDiscard(0, 1);
    }

    @Test
    public void testchooseLeaderCardsToDiscard2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards();
        LeaderCard card1 = player.getPersonalBoard().getLeaderCardsInHand().get(0);
        LeaderCard card2 = player.getPersonalBoard().getLeaderCardsInHand().get(3);

        player.chooseLeaderCardsToDiscard(1, 2);
        assertEquals(card1, player.getPersonalBoard().getLeaderCardsInHand().get(0));
        assertEquals(card2, player.getPersonalBoard().getLeaderCardsInHand().get(1));
        assertEquals(TurnState.CHOOSERESOURCES, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void testsetInitStorageTypes1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setInitStorageTypes(Resource.COIN, Resource.STONE, Resource.SERVANT);
    }

    @Test(expected = ModelException.class)
    public void testsetInitStorageTypes2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.COIN, Resource.COIN, Resource.SERVANT);
    }

    @Test
    public void testsetInitStorageTypes3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.COIN, Resource.STONE, Resource.SERVANT);
        assertEquals(Resource.COIN, player.getPersonalBoard().getWareHouse().getTypeStorage(1));
        assertEquals(Resource.STONE, player.getPersonalBoard().getWareHouse().getTypeStorage(2));
        assertEquals(Resource.SERVANT, player.getPersonalBoard().getWareHouse().getTypeStorage(3));
    }

    @Test(expected = ModelException.class)
    public void testaddInitResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.addInitResources(Resource.COIN);
    }

    @Test
    public void testaddInitResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
    }

    @Test
    public void testaddInitResources3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 1, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromStorage(1));
    }

    @Test
    public void testaddInitResources4() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 2, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
        assertEquals(1, player.getPersonalBoard().getWareHouse().getFromStorage(1));
        assertEquals(player.getPersonalBoard().getFaithTrack().getTrack().get(1), player.getPersonalBoard().getFaithTrack().checkPlayerPosition());
    }

    @Test(expected = ModelException.class)
    public void testaddInitResources5() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 3, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(Resource.COIN);
    }

    @Test(expected = ModelException.class)
    public void testAddInitResources1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 3, game);
        player.addInitResources(Resource.COIN, Resource.STONE);
    }

    @Test(expected = ModelException.class)
    public void testAddInitResources2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.COIN, Resource.STONE, Resource.SERVANT);
        player.addInitResources(Resource.COIN, Resource.COIN);
    }

    @Test
    public void testAddInitResources3() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 3, game);
        player.drawLeaderCards();
        player.chooseLeaderCardsToDiscard(0, 1);
        player.setInitStorageTypes(Resource.STONE, Resource.COIN, Resource.SERVANT);
        player.addInitResources(Resource.COIN, Resource.COIN);
        assertEquals(TurnState.ENDPREPARATION, player.getPhase());
        assertEquals(2, player.getPersonalBoard().getWareHouse().getFromStorage(2));
        assertEquals(player.getPersonalBoard().getFaithTrack().getTrack().get(2), player.getPersonalBoard().getFaithTrack().checkPlayerPosition());
    }

    @Test(expected = ModelException.class)
    public void testselectMarketPhase1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.selectMarketPhase();
    }

    @Test
    public void testselectMarketPhase2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase();
        assertEquals(TurnState.MARKETPHASE, player.getPhase());
    }

    @Test(expected = ModelException.class)
    public void teststartMarketPhase1() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.startMarketPhase(1, false);
    }

    @Test
    public void teststartMarketPhase2() throws ModelException, FileNotFoundException{
        Game game = new Game();
        Player player = new Player("flavio", 0, game);
        player.setAsCurrentPlayer();
        player.selectMarketPhase();
        player.startMarketPhase(1, false);
        assertEquals(TurnState.CHOICE, player.getPhase());
    }

    @Test
    public void manageWhiteMarbles() {
    }

    @Test
    public void startOrganizeResources() {
    }

    @Test
    public void setStoragesTypes() {
    }

    @Test
    public void defualtManageResourcesToOrganize() {
    }

    @Test
    public void manageResourcesToOrganize() {
    }

    @Test
    public void startAddResources() {
    }

    @Test
    public void addResources() {
    }

    @Test
    public void discardResources() {
    }

    @Test
    public void manageResourcesInStorages() {
    }

    @Test
    public void selectBuyDevelopmentCardPhase() {
    }

    @Test
    public void chooseDevelopmentCard() {
    }

    @Test
    public void payCardAllFromChest() {
    }

    @Test
    public void payCardFromChest() {
    }

    @Test
    public void payCardFromStorage() {
    }

    @Test
    public void selectProductionPhase() {
    }

    @Test
    public void activateProductionOfSlot() {
    }

    @Test
    public void activatePersonalProduction() {
    }

    @Test
    public void activateSpecialProduction() {
    }

    @Test
    public void startPayProduction() {
    }

    @Test
    public void payProductionAllFromChest() {
    }

    @Test
    public void payProductionFromChest() {
    }

    @Test
    public void payProductionFromStorage() {
    }

    @Test
    public void activateLeaderCard() {
    }

    @Test
    public void discardLeaderCard() {
    }
}