package it.polimi.ingsw.server;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.*;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Controller manages the evolution of the game by setting the current player for every turn and by sending
 * the clientHandlers the AnswerMessages in response of the clients' actions.
 */
public class Controller {
    private Match match;
    private Game game;
    private final Set<ClientHandler> clientConnectionThreads;   //magari è meglio usare una mappa che associa ad ogni ID il relativo clientHandler così da evitare ogni volte di scorrere tutto il set alla ricerca del player giusto
    private Map<Player,ClientHandler> playerClientHandlerMap=new HashMap<>();
    private boolean gameStarted;                                //non so se serve
    private int numberOfPlayers;
    private boolean lastTurn;
    private int turnsToPlay;
    private ArrayList<Integer> availableChoices=new ArrayList<>();

    /**
     * Instantiates a new Controller.
     *
     * @param clientConnectionThreads the client connection threads
     * @param match                   the match
     * @throws FileNotFoundException the file not found exception
     */
    public Controller(Set<ClientHandler> clientConnectionThreads, Match match) throws FileNotFoundException {
        game = new Game();
        this.clientConnectionThreads = clientConnectionThreads;
        this.match=match;
        gameStarted = false;
        numberOfPlayers = 0;
        lastTurn=false;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the next player. If the next player is not connected, he misses his turn.
     *
     * @return the object (Player)
     */
    public Object nextPlayer(){
        Object o=game.nextPlayer();
        if (o instanceof Player){
            for(Player player: playerClientHandlerMap.keySet()){
                if (player.equals(o)) {
                    if (!playerClientHandlerMap.get(player).isConnected()) {
                        nextPlayer();
                        break;
                    }
                }
            }
        }
        return o;
    }

    /**
     * Gets available choices.
     *
     * @return the available choices
     */
    public ArrayList<Integer> getAvailableChoices() {
        return availableChoices;
    }

    /**
     * Starts a new game.
     */
    public void startGame(){
        game.start();
        gameStarted=true;
    }

    /**
     * Sets the last turn of the game.
     *
     * @param lastTurn the last turn
     */
    public void setLastTurn(boolean lastTurn) {
        this.lastTurn = lastTurn;
    }

    /**
     * Is last turn boolean.
     *
     * @return the boolean
     */
    public boolean isLastTurn() {
        return lastTurn;
    }

    /**
     * Is game started boolean.
     *
     * @return the boolean
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Gets number of players.
     *
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Sets number of players.
     *
     * @param numberOfPlayers the number of players
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Gets turns to play.
     *
     * @return the turns to play
     */
    public int getTurnsToPlay() {
        return turnsToPlay;
    }

    /**
     * Sets turns to play.
     *
     * @param turnsToPlay the turns to play
     */
    public void setTurnsToPlay(int turnsToPlay) {
        this.turnsToPlay = turnsToPlay;
    }

    /**
     * Gets client connection threads.
     *
     * @return the client connection threads
     */
    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }

    /**
     * Gets player client handler map.
     *
     * @return the player client handler map
     */
    public Map<Player, ClientHandler> getPlayerClientHandlerMap() {
        return playerClientHandlerMap;
    }

    /**
     * Is current player boolean.
     *
     * @param clientHandler the client handler
     * @return the boolean
     */
    public boolean isCurrentPlayer(ClientHandler clientHandler){
        if(!gameStarted)return true;
        if(clientHandler.getPlayerID()!=game.getCurrentPlayer().getPlayerID()){
            //Wrong player
            return false;
        }
        return true;
    }

    /**
     * Sends an AnswerMessage to everyone.
     *
     * @param answer the answer
     */
    public void sendAll(AnswerMsg answer){
        for(ClientHandler clientHandler : clientConnectionThreads){
            clientHandler.sendAnswerMessage(answer);
        }
    }

    /**
     * SendAllExcept sends an AnswerMessage to everyone except one.
     *
     * @param answer the answer
     * @param cH     the clientHandler
     */
    public void sendAllExcept(AnswerMsg answer, ClientHandler cH){
        for(ClientHandler clientHandler : clientConnectionThreads){
            if(!clientHandler.equals(cH)) {
                clientHandler.sendAnswerMessage(answer);
            }
        }
    }

    /**
     * Sends everyone his position in the faithTrack.
     *
     * @param cH the clientHandler
     */
    public void sendAllPos(ClientHandler cH){
        for(ClientHandler clientHandler : clientConnectionThreads){
            if(!clientHandler.equals(cH)) {
            clientHandler.sendAnswerMessage(new UpdateFaithMarkerPositionMsg(getGame().getPlayerById(clientHandler.getPlayerID()).getPhase(),
                    this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getTrack().indexOf(
                            this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                    this.getGame().getPlayerById(clientHandler.getPlayerID()).getPersonalBoard().getFaithTrack().getPopeFavours().
                            stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
            }
        }
    }


    /**
     * Sets the nickname of a player and sends the answer to the client.
     *
     * @param nickname        the nickname
     * @param numberOfPlayers the number of players
     * @param clientHandler   the client handler
     */

    public synchronized void setNickname(String nickname, int numberOfPlayers, ClientHandler clientHandler) {
        if (this.numberOfPlayers == 0) {
            if(numberOfPlayers<1 || numberOfPlayers>4) {
                StringMsg stringMsg = new StringMsg("Impossible number of players man");
                clientHandler.sendAnswerMessage(stringMsg);
            }
            this.numberOfPlayers = numberOfPlayers;
            if(numberOfPlayers>1) match.setFull(false);
            Player player=new Player(nickname, 1, game);
            game.createNewPlayer(player);
            clientConnectionThreads.add(clientHandler);
            playerClientHandlerMap.put(player,clientHandler);
            UpdateNicknameMsg updateNicknameMsg = new UpdateNicknameMsg(nickname, 1, this.numberOfPlayers);
            clientHandler.sendAnswerMessage(updateNicknameMsg);
            clientHandler.setReady();
            clientHandler.setPlayerID(1);
            return;
        }

        if (game.getPlayers().size() < this.numberOfPlayers) {
            for (Player p : game.getPlayers()) {
                if (p.getNickname().equals(nickname)) {
                    ErrorMsg stringMsg = new ErrorMsg("Nickname already taken, choose another nickname");
                    clientHandler.sendAnswerMessage(stringMsg);
                    return;
                }
            }

            Player player=new Player(nickname, game.getPlayers().size()+1, game);
            game.createNewPlayer(player);
            clientConnectionThreads.add(clientHandler);
            playerClientHandlerMap.put(player,clientHandler);
            UpdateNicknameMsg updateNicknameMsg = new UpdateNicknameMsg(nickname, game.getPlayers().size(), this.numberOfPlayers);
            clientHandler.sendAnswerMessage(updateNicknameMsg);
            clientHandler.setReady();
            clientHandler.setPlayerID(game.getPlayers().size());
            for(ClientHandler c : clientConnectionThreads){
                if(!c.equals(clientHandler)){
                    StringMsg stringMsg = new StringMsg("Player " + nickname + " has joined the game");
                    c.sendAnswerMessage(stringMsg);
                }
            }
        }
    }

    /**
     * Send update draw leaders.
     *
     * @param player the player
     */
    public void sendUpdateDrawLeaders(Player player){
        UpdateLeaderCardsMsg updateLeaderCardsMsg = new UpdateLeaderCardsMsg(TurnState.CHOOSELEADERCARDS,
                player.getPersonalBoard().getLeaderCardsInHand(),
                player.getPersonalBoard().getLeaderCardsPlayed(),
                "\nYou drew 4 leader cards");
        playerClientHandlerMap.get(player).sendAnswerMessage(updateLeaderCardsMsg);
        StringMsg stringMsg = new StringMsg(player.getNickname() + " drew 4 leader cards");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update discard leaders.
     *
     * @param player the player
     */
    public void sendUpdateDiscardLeaders(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateLeaderCardsMsg(player.getPhase(),
                player.getPersonalBoard().getLeaderCardsInHand(),
                player.getPersonalBoard().getLeaderCardsPlayed(),
                "\nYou discarded 2 leader cards"));
        StringMsg stringMsg = new StringMsg(player.getNickname() + " discarded 2 leader cards");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update init storage types.
     *
     * @param player the player
     * @param i1     the 1
     * @param i2     the 2
     * @param i3     the 3
     */
    public void sendUpdateInitStorageTypes(Player player,Resource i1, Resource i2, Resource i3){
        UpdateStorageTypesMsg updateStorageTypesMsg = new UpdateStorageTypesMsg(player.getPhase(), i1, i2, i3);
        playerClientHandlerMap.get(player).sendAnswerMessage(updateStorageTypesMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " changed his storages type");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update add init resources.
     *
     * @param player the player
     */
    public void sendUpdateAddInitResources(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageMsg(TurnState.ENDPREPARATION,
                player.getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateFaithMarkerPositionMsg(TurnState.ENDPREPARATION,
               player.getPersonalBoard().getFaithTrack().getTrack().indexOf(
                        player.getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                player.getPersonalBoard().getFaithTrack().getPopeFavours().
                        stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " added initial resources");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update select market phase.
     *
     * @param player the player
     */
    public void sendUpdateSelectMarketPhase(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.MARKETPHASE, "You can select a row or column from the market");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        playerClientHandlerMap.get(player).sendAnswerMessage(new StringMsg("Here is the market!"));
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateMarketMsg(this.getGame().getTable().getMarket()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started Market phase");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update start market phase.
     *
     * @param player  the player
     * @param marbles the marbles
     */
    public void sendUpdateStartMarketPhase(Player player, ArrayList<Marble> marbles){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateResourcesToAddMsg(
                player.getPhase(),
                player.getPersonalBoard().getWareHouse().getResourcesToAdd()));
        for(Marble m: marbles){
            if(m instanceof RedMarble) {
                playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateFaithMarkerPositionMsg(player.getPhase(),
                        player.getPersonalBoard().getFaithTrack().getTrack().indexOf(
                                player.getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                        player.getPersonalBoard().getFaithTrack().getPopeFavours().
                                stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));
                break;
            }
        }
        if (player.getPhase() == TurnState.WHITEMARBLES){
            playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateWhiteMarblesToManageMsg(
                    player.getPersonalBoard().getManageWhiteMarbles()
            ));}

            StringMsg stringMsg = new StringMsg(player.getNickname() + " picked "+marbles.size()+" from the market");
            this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));

            this.sendAll(new UpdateMarketMsg(this.getGame().getTable().getMarket()));
    }

    /**
     * Send update manage white marble.
     *
     * @param player the player
     */
    public void sendUpdateManageWhiteMarble(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateResourcesToAddMsg(
                player.getPhase(),
                player.getPersonalBoard().getWareHouse().getResourcesToAdd()));

        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateWhiteMarblesToManageMsg(
                player.getPersonalBoard().getManageWhiteMarbles()));
    }

    /**
     * Send update start organize resources.
     *
     * @param player the player
     */
    public void sendUpdateStartOrganizeResources(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started organizing his resources");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update set storage types.
     *
     * @param player the player
     * @throws ModelException the model exception
     */
    public void sendUpdateSetStorageTypes(Player player) throws ModelException {
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageTypesMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getTypeStorage(1),
                player.getPersonalBoard().getWareHouse().getTypeStorage(2),
                player.getPersonalBoard().getWareHouse().getTypeStorage(3)));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " set his storages types");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update default manage resources to organize.
     *
     * @param player the player
     */
    public void sendUpdateDefaultManageResourcesToOrganize(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));

        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));
    }

    /**
     * Send update manage resources to organize.
     *
     * @param player the player
     */
    public void sendUpdateManageResourcesToOrganize(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));

        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started to reorganize resources in storages");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update start add resources.
     *
     * @param player the player
     */
    public void sendUpdateStartAddResources(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.ADDRESOURCES, "You can add resources");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started adding resources to storages");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update add resources.
     *
     * @param player the player
     */
    public void sendUpdateAddResources(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateResourcesToAddMsg(
                player.getPhase(),
                player.getPersonalBoard().getWareHouse().getResourcesToAdd()));
    }

    /**
     * Send update discard resources.
     *
     * @param player the player
     */
    public void sendUpdateDiscardResources(Player player){
        StringMsg stringMsg = new StringMsg(player.getNickname() + " discarded a resource, your position increases by 1 box!");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
        this.sendAllPos(playerClientHandlerMap.get(player));

        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateResourcesToAddMsg(
                player.getPhase(),
                player.getPersonalBoard().getWareHouse().getResourcesToAdd()));
    }

    /**
     * Send update manage resources in storage.
     *
     * @param player the player
     */
    public void sendUpdateManageResourcesInStorage(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started to reorganize resources in storages");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update buydevelopment phase.
     *
     * @param player the player
     */
    public void sendUpdateBuydevelopmentPhase(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.BUYDEVELOPMENTCARDPHASE, "You can select a card");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started BuyDevelopmentCard phase");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update buy card.
     *
     * @param player the player
     * @param slot   the slot
     * @throws ModelException the model exception
     */
    public void sendUpdateBuyCard(Player player,int slot) throws ModelException {
        UpdateDecksMsg updateDecksMsg = new UpdateDecksMsg("\nYou bought the card", this.getGame().getTable().getTopDevelopmentcards());
        playerClientHandlerMap.get(player).sendAnswerMessage(updateDecksMsg);

        UpdateCardSlotMsg updateCardSlotMsg = new UpdateCardSlotMsg(player.getPersonalBoard().getCardSlot().getTopCardofSlot(slot), slot);
        playerClientHandlerMap.get(player).sendAnswerMessage(updateCardSlotMsg);

        CardPriceMsg cp = new CardPriceMsg(TurnState.PAYDEVELOPMENTCARD, player.getPersonalBoard().getCardCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(cp);

        UpdateDecksMsg updateDecksMsg1 = new UpdateDecksMsg(player.getNickname() + " bought a card", this.getGame().getTable().getTopDevelopmentcards());
        this.sendAllExcept(updateDecksMsg1, playerClientHandlerMap.get(player));
    }

    /**
     * Send update pay card from chest.
     *
     * @param player the player
     */
    public void sendUpdatePayCardFromChest(Player player){
        CardPriceMsg cardPriceMsg = new CardPriceMsg(player.getPhase(), player.getPersonalBoard().getCardCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(cardPriceMsg);

        ChestMsg chestMsg = new ChestMsg(player.getPhase(), player.getPersonalBoard().getWareHouse().getMapfromChest());
        playerClientHandlerMap.get(player).sendAnswerMessage(chestMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " paid card from chest");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update pay card from storage.
     *
     * @param player the player
     */
    public void sendUpdatePayCardFromStorage(Player player){
        CardPriceMsg cardPriceMsg = new CardPriceMsg(player.getPhase(), player.getPersonalBoard().getCardCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(cardPriceMsg);

        UpdateStorageMsg updateStorageMsg = new UpdateStorageMsg(player.getPhase(), player.getPersonalBoard().getWareHouse().getStorages().
                stream().map(Storage::getQuantity).toArray(Integer[]::new));
        playerClientHandlerMap.get(player).sendAnswerMessage(updateStorageMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " paid card from storage");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update select production phase.
     *
     * @param player the player
     */
    public void sendUpdateSelectProductionPhase(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.PRODUCTIONPHASE, "You can activate productions");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started Production phase");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update activate production.
     *
     * @param player the player
     */
    public void sendUpdateActivateProduction(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateCostsGainsMsg(player.getPersonalBoard().getProduction().getTotalCost(),
                player.getPersonalBoard().getProduction().getTotalGain(),
                player.getPersonalBoard().getProduction().getFaithPoints()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " activated a production");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update activate personal production.
     *
     * @param player the player
     */
    public void sendUpdateActivatePersonalProduction(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateCostsGainsMsg(player.getPersonalBoard().getProduction().getTotalCost(),
                player.getPersonalBoard().getProduction().getTotalGain(),
                player.getPersonalBoard().getProduction().getFaithPoints()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " activated personal production");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update activate special production.
     *
     * @param player the player
     */
    public void sendUpdateActivateSpecialProduction(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateCostsGainsMsg(player.getPersonalBoard().getProduction().getTotalCost(),
                player.getPersonalBoard().getProduction().getTotalGain(),
                player.getPersonalBoard().getProduction().getFaithPoints()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " activated a special production");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update start pay production.
     *
     * @param player the player
     */
    public void sendUpdateStartPayProduction(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(player.getPhase(), "You can pay productions");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started to pay productions");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update pay production from chest.
     *
     * @param player the player
     */
    public void sendUpdatePayProductionFromChest(Player player){
        UpdateProductionCostMsg updateProductionCostMsg = new UpdateProductionCostMsg(player.getPersonalBoard().getProduction().getTotalCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(updateProductionCostMsg);

        if(player.getPersonalBoard().getProduction().totalCostIsEmpty()){
            EndProductionMsg endProductionMsg = new EndProductionMsg(TurnState.ENDTURN, player.getPersonalBoard().getWareHouse().getMapfromChest(),
                    player.getPersonalBoard().getFaithTrack().getTrack().indexOf(
                            player.getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                    player.getPersonalBoard().getFaithTrack().getPopeFavours().
                            stream().map(PopeFavour::isActivated).toArray(Boolean[]::new));
            playerClientHandlerMap.get(player).sendAnswerMessage(endProductionMsg);

            StringMsg stringMsg1 = new StringMsg(player.getNickname() + " ended production phase");
            this.sendAllExcept(stringMsg1, playerClientHandlerMap.get(player));
        }
        else{
            ChestMsg chestMsg = new ChestMsg(TurnState.PAYPRODUCTIONS, player.getPersonalBoard().getWareHouse().getMapfromChest());
            playerClientHandlerMap.get(player).sendAnswerMessage(chestMsg);

            StringMsg stringMsg = new StringMsg(player.getNickname() + " paid productions from chest");
            this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
        }
    }

    /**
     * Send update pay production from storage.
     *
     * @param player the player
     */
    public void sendUpdatePayProductionFromStorage(Player player){
        UpdateProductionCostMsg updateProductionCostMsg = new UpdateProductionCostMsg(player.getPersonalBoard().getProduction().getTotalCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(updateProductionCostMsg);

        UpdateStorageMsg updateStorageMsg = new UpdateStorageMsg(player.getPhase(), player.getPersonalBoard().getWareHouse().getStorages().
                stream().map(Storage::getQuantity).toArray(Integer[]::new));
        playerClientHandlerMap.get(player).sendAnswerMessage(updateStorageMsg);

        if(player.getPersonalBoard().getProduction().totalCostIsEmpty()){
            EndProductionMsg endProductionMsg = new EndProductionMsg(TurnState.ENDTURN, player.getPersonalBoard().getWareHouse().getMapfromChest(),
                    player.getPersonalBoard().getFaithTrack().getTrack().indexOf(
                            player.getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                    player.getPersonalBoard().getFaithTrack().getPopeFavours().
                            stream().map(PopeFavour::isActivated).toArray(Boolean[]::new));
            playerClientHandlerMap.get(player).sendAnswerMessage(endProductionMsg);

            StringMsg stringMsg1 = new StringMsg(player.getNickname() + " ended production phase");
            this.sendAllExcept(stringMsg1, playerClientHandlerMap.get(player));
        }
        else{
            StringMsg stringMsg = new StringMsg(player.getNickname() + " paid productions from storages");
            this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
        }
    }

    /**
     * Send update activate leader.
     *
     * @param player    the player
     * @param leaderAct the leader act
     * @throws ModelException the model exception
     */
    public void sendUpdateActivateLeader(Player player, char leaderAct) throws ModelException {
        if (leaderAct=='s') playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageLeaderMsg(player.getPersonalBoard().getWareHouse().getTypeStorage(4)));
        else if (leaderAct=='r') playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateReductionLeaderMsg(player.getPersonalBoard().getLastReduction()));
        else if (leaderAct=='w') playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateWhiteMarbleLeaderMsg(player.getPersonalBoard().getLastWhiteMarble()));
        else if (leaderAct=='p') playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateSpecialProductionLeaderMsg(player.getPersonalBoard().getProduction().getTypeOfSpecialProduction(player.getPersonalBoard().getProduction().numOfSpecialProduction())));

        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateLeaderCardsMsg(player.getPhase(),
                player.getPersonalBoard().getLeaderCardsInHand(),
                player.getPersonalBoard().getLeaderCardsPlayed(),
                "LeaderCard successfully activated"));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " activated a leader card");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Send update discard leader.
     *
     * @param player the player
     */
    public void sendUpdateDiscardLeader(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateFaithMarkerPositionMsg(player.getPhase(),
                player.getPersonalBoard().getFaithTrack().getTrack().indexOf(
                        player.getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                player.getPersonalBoard().getFaithTrack().getPopeFavours().
                        stream().map(PopeFavour::isActivated).toArray(Boolean[]::new)));

        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateLeaderCardsMsg(player.getPhase(),
                player.getPersonalBoard().getLeaderCardsInHand(),
                player.getPersonalBoard().getLeaderCardsPlayed(),
                "LeaderCard successfully discarded"));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " discarded a leader card");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    /**
     * Start last turn.
     */
    public void startLastTurn(){
        setLastTurn(true);
        setTurnsToPlay(game.getPlayers().size()-1);
    }

    /**
     * End game.
     *
     * @param Lorenzo the lorenzo
     * @throws ModelException the model exception
     */
    public void endGame(boolean Lorenzo) throws ModelException {
        if(Lorenzo){
            for (ClientHandler clientHandler : clientConnectionThreads){
                clientHandler.closeSocketStreams();
            }
            return;
        }
        Player player=game.getPlayerById(1);
        int winPoints=player.getPersonalBoard().countVictoryPoints();
        for(Player p: game.getPlayers()){
            if(p.getPersonalBoard().countVictoryPoints()>winPoints){
                player=p;
                winPoints=p.getPersonalBoard().countVictoryPoints();
            }
        }
        sendAll(new WinMsg("\nPlayer "+ player.getNickname()+" won the game with "+winPoints+" victoryPoints"));
        for (ClientHandler clientHandler : clientConnectionThreads){
            clientHandler.closeSocketStreams();
        }
        match.closeMatch();
    }

}
