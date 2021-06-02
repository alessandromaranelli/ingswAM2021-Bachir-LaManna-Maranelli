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

public class Controller {
    private Game game;
    private final Set<ClientHandler> clientConnectionThreads;   //magari è meglio usare una mappa che associa ad ogni ID il relativo clientHandler così da evitare ogni volte di scorrere tutto il set alla ricerca del player giusto
    private Map<Player,ClientHandler> playerClientHandlerMap=new HashMap<>();
    private boolean gameStarted;                                //non so se serve
    private int numberOfPlayers;
    private boolean lastTurn;
    private int turnsToPlay;

    public Controller(Set<ClientHandler> clientConnectionThreads) throws FileNotFoundException {
        game = new Game();
        this.clientConnectionThreads = clientConnectionThreads;
        gameStarted = false;
        numberOfPlayers = 0;
        lastTurn=false;
    }

    public Game getGame() {
        return game;
    }

    public void startGame(){
        game.start();
        gameStarted=true;
    }

    public void setLastTurn(boolean lastTurn) {
        this.lastTurn = lastTurn;
    }

    public boolean isLastTurn() {
        return lastTurn;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getTurnsToPlay() {
        return turnsToPlay;
    }

    public void setTurnsToPlay(int turnsToPlay) {
        this.turnsToPlay = turnsToPlay;
    }

    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }

    public boolean isCurrentPlayer(ClientHandler clientHandler, CommandMsg commandMsg){
        if(!gameStarted)return true;
        if(clientHandler.getPlayerID()!=game.getCurrentPlayer().getPlayerID()){
            //Wrong player
            return false;
        }
        return true;
    }

    public void sendAll(AnswerMsg answer){
        for(ClientHandler clientHandler : clientConnectionThreads){
            clientHandler.sendAnswerMessage(answer);
        }
    }

    public void sendAllExcept(AnswerMsg answer, ClientHandler cH){
        for(ClientHandler clientHandler : clientConnectionThreads){
            if(!clientHandler.equals(cH)) {
                clientHandler.sendAnswerMessage(answer);
            }
        }
    }

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


    //metodo chiamato dal primo messaggio di un client in cui setta nickname e giocatori che vorrebbe nella partita
    //messo nel controller perchè serve synchronized
    public synchronized void setNickname(String nickname, int numberOfPlayers, ClientHandler clientHandler) {
        if (this.numberOfPlayers == 0) {
            if(numberOfPlayers<1 || numberOfPlayers>4) {
                StringMsg stringMsg = new StringMsg("Impossible number of players man");
                clientHandler.sendAnswerMessage(stringMsg);
            }
            this.numberOfPlayers = numberOfPlayers;
            Player player=new Player(nickname, 1, game);
            game.createNewPlayer(player);
            clientConnectionThreads.add(clientHandler);
            playerClientHandlerMap.put(player,clientHandler);
            UpdateNicknameMsg updateNicknameMsg = new UpdateNicknameMsg(nickname, 1, this.numberOfPlayers);
            clientHandler.sendAnswerMessage(updateNicknameMsg);
            clientHandler.setReady();                                   //serve a qualcosa il boolean ready?
            clientHandler.setPlayerID(1);
            return;
        }

        if (game.getPlayers().size() < this.numberOfPlayers) {
            for (Player p : game.getPlayers()) {
                if (p.getNickname().equals(nickname)) {
                    StringMsg stringMsg = new StringMsg("Nickname already taken, choose another nickname");
                    clientHandler.sendAnswerMessage(stringMsg);
                    return;
                }
            }

            Player player=new Player(nickname, game.getPlayers().size()+1, game);
            game.createNewPlayer(player);
            clientConnectionThreads.add(clientHandler);
            playerClientHandlerMap.put(player,clientHandler);//sarebbe meglio aggiungere i clienthandler al set solo dopo aver controllato che hanno un nickname giusto. E' come aggiungerli al set sapendo che sono già ready
            UpdateNicknameMsg updateNicknameMsg = new UpdateNicknameMsg(nickname, game.getPlayers().size(), this.numberOfPlayers);
            clientHandler.sendAnswerMessage(updateNicknameMsg);
            clientHandler.setReady();                                   //serve a qualcosa il boolean ready?
            clientHandler.setPlayerID(game.getPlayers().size());
            for(ClientHandler c : clientConnectionThreads){             //esempio nel quale se ho aggiunto tutti i clienthandler indistintamente, va male
                if(!c.equals(clientHandler)){
                    StringMsg stringMsg = new StringMsg("Player " + nickname + " has joined the game");
                    c.sendAnswerMessage(stringMsg);
                }
            }
        }
    }

    public void sendUpdateDrawLeaders(Player player){
        UpdateLeaderCardsMsg updateLeaderCardsMsg = new UpdateLeaderCardsMsg(TurnState.CHOOSELEADERCARDS,
                player.getPersonalBoard().getLeaderCardsInHand(),
                player.getPersonalBoard().getLeaderCardsPlayed(),
                "\nYou drew 4 leader cards");
        playerClientHandlerMap.get(player).sendAnswerMessage(updateLeaderCardsMsg);
        StringMsg stringMsg = new StringMsg(player.getNickname() + " drew 4 leader cards");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateDiscardLeaders(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateLeaderCardsMsg(player.getPhase(),
                player.getPersonalBoard().getLeaderCardsInHand(),
                player.getPersonalBoard().getLeaderCardsPlayed(),
                "\nYou discarded 2 leader cards"));
        StringMsg stringMsg = new StringMsg(player.getNickname() + " discarded 2 leader cards");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateInitStorageTypes(Player player,Resource i1, Resource i2, Resource i3){
        UpdateStorageTypesMsg updateStorageTypesMsg = new UpdateStorageTypesMsg(player.getPhase(), i1, i2, i3);
        playerClientHandlerMap.get(player).sendAnswerMessage(updateStorageTypesMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " changed his storages type");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

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

    public void sendUpdateSelectMarketPhase(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.MARKETPHASE, "You can select a row or column from the market");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        playerClientHandlerMap.get(player).sendAnswerMessage(new StringMsg("Here is the market!"));
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateMarketMsg(this.getGame().getTable().getMarket()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started Market phase");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

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
        if (player.getPhase()== TurnState.WHITEMARBLES){
            playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateWhiteMarblesToManageMsg(
                    player.getPersonalBoard().getManageWhiteMarbles()
            ));

            StringMsg stringMsg = new StringMsg(player.getNickname() + " picked "+marbles.size()+" from the market");
            this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));

            this.sendAll(new UpdateMarketMsg(this.getGame().getTable().getMarket()));
        }
    }

    public void sendUpdateManageWhiteMarble(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateResourcesToAddMsg(
                player.getPhase(),
                player.getPersonalBoard().getWareHouse().getResourcesToAdd()));

        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateWhiteMarblesToManageMsg(
                player.getPersonalBoard().getManageWhiteMarbles()));
    }

    public void sendUpdateStartOrganizeResources(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started organizing his resources");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateSetStorageTypes(Player player) throws ModelException {
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageTypesMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getTypeStorage(1),
                player.getPersonalBoard().getWareHouse().getTypeStorage(2),
                player.getPersonalBoard().getWareHouse().getTypeStorage(3)));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " set his storages types");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateDefaultManageResourcesToOrganize(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));

        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));
    }

    public void sendUpdateManageResourcesToOrganize(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));

        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));
    }

    public void sendUpdateStartAddResources(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.ADDRESOURCES, "You can add resources");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started adding resources to storages");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateAddResources(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateStorageMsg(player.getPhase(),
                player.getPersonalBoard().getWareHouse().getStorages().
                        stream().map(Storage::getQuantity).toArray(Integer[]::new)));
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateResourcesToAddMsg(
                player.getPhase(),
                player.getPersonalBoard().getWareHouse().getResourcesToAdd()));
    }

    public void sendUpdateDiscardResources(Player player){
        StringMsg stringMsg = new StringMsg(player.getNickname() + " discarded a resource, your position increases by 1 box!");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
        this.sendAllPos(playerClientHandlerMap.get(player));

        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateResourcesToAddMsg(
                player.getPhase(),
                player.getPersonalBoard().getWareHouse().getResourcesToAdd()));
    }

    public void sendUpdateManageResourcesInStorage(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new ResourcesToOrganizeMsg(
                player.getPersonalBoard().getWareHouse().getResourcesToOrganize(),
                player.getPhase()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started to reorganize resources in storages");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateBuydevelopmentPhase(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.BUYDEVELOPMENTCARDPHASE, "You can select a card");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started BuyDevelopmentCard phase");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateBuyCard(Player player,int slot) throws ModelException {
        UpdateDecksMsg updateDecksMsg = new UpdateDecksMsg("You bought the card", this.getGame().getTable().getTopDevelopmentcards());
        playerClientHandlerMap.get(player).sendAnswerMessage(updateDecksMsg);

        UpdateCardSlotMsg updateCardSlotMsg = new UpdateCardSlotMsg(player.getPersonalBoard().getCardSlot().getTopCardofSlot(slot), slot);
        playerClientHandlerMap.get(player).sendAnswerMessage(updateCardSlotMsg);

        CardPriceMsg cp = new CardPriceMsg(TurnState.PAYDEVELOPMENTCARD, player.getPersonalBoard().getCardCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(cp);

        UpdateDecksMsg updateDecksMsg1 = new UpdateDecksMsg(player.getNickname() + " bought a card", this.getGame().getTable().getTopDevelopmentcards());
        this.sendAllExcept(updateDecksMsg1, playerClientHandlerMap.get(player));
    }

    public void sendUpdatePayCardFromChest(Player player){
        CardPriceMsg cardPriceMsg = new CardPriceMsg(player.getPhase(), player.getPersonalBoard().getCardCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(cardPriceMsg);

        ChestMsg chestMsg = new ChestMsg(player.getPhase(), player.getPersonalBoard().getWareHouse().getMapfromChest());
        playerClientHandlerMap.get(player).sendAnswerMessage(chestMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " paid card from chest");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdatePayCardFromStorage(Player player){
        CardPriceMsg cardPriceMsg = new CardPriceMsg(player.getPhase(), player.getPersonalBoard().getCardCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(cardPriceMsg);

        UpdateStorageMsg updateStorageMsg = new UpdateStorageMsg(player.getPhase(), player.getPersonalBoard().getWareHouse().getMapfromAllStorages().values().toArray(new Integer[3]));
        playerClientHandlerMap.get(player).sendAnswerMessage(updateStorageMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " paid card from storage");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateSelectProductionPhase(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(TurnState.PRODUCTIONPHASE, "You can activate productions");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started Production phase");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateActivateProduction(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateCostsGainsMsg(player.getPersonalBoard().getProduction().getTotalCost(),
                player.getPersonalBoard().getProduction().getTotalGain(),
                player.getPersonalBoard().getProduction().getFaithPoints()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " activated a production");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateActivatePersonalProduction(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateCostsGainsMsg(player.getPersonalBoard().getProduction().getTotalCost(),
                player.getPersonalBoard().getProduction().getTotalGain(),
                player.getPersonalBoard().getProduction().getFaithPoints()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " activated personal production");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateActivateSpecialProduction(Player player){
        playerClientHandlerMap.get(player).sendAnswerMessage(new UpdateCostsGainsMsg(player.getPersonalBoard().getProduction().getTotalCost(),
                player.getPersonalBoard().getProduction().getTotalGain(),
                player.getPersonalBoard().getProduction().getFaithPoints()));

        StringMsg stringMsg = new StringMsg(player.getNickname() + " activated a special production");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

    public void sendUpdateStartPayProduction(Player player){
        UpdatePhaseMsg updatePhaseMsg = new UpdatePhaseMsg(player.getPhase(), "You can pay productions");
        playerClientHandlerMap.get(player).sendAnswerMessage(updatePhaseMsg);

        StringMsg stringMsg = new StringMsg(player.getNickname() + " started to pay productions");
        this.sendAllExcept(stringMsg, playerClientHandlerMap.get(player));
    }

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

    public void sendUpdatePayProductionFromStorage(Player player){
        UpdateProductionCostMsg updateProductionCostMsg = new UpdateProductionCostMsg(player.getPersonalBoard().getProduction().getTotalCost());
        playerClientHandlerMap.get(player).sendAnswerMessage(updateProductionCostMsg);

        UpdateStorageMsg updateStorageMsg = new UpdateStorageMsg(player.getPhase(), player.getPersonalBoard().getWareHouse().getMapfromAllStorages().values().toArray(new Integer[3]));
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

    public void startLastTurn(){
        setLastTurn(true);
        setTurnsToPlay(game.getPlayers().size()-1);
    }

    public void endGame(boolean Lorenzo) throws ModelException {
        if(Lorenzo){
            //end connection
            return;
        }
        Player player=game.getPlayerById(0);
        int winPoints=player.getPersonalBoard().countVictoryPoints();
        for(Player p: game.getPlayers()){
            if(p.getPersonalBoard().countVictoryPoints()>winPoints){
                player=p;
                winPoints=p.getPersonalBoard().countVictoryPoints();
            }
        }
        sendAll(new WinMsg("Player "+ player.getNickname()+" won the game with "+winPoints+" victoryPoints"));
        //end connections
    }

}
