package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;

import java.util.*;

public class LightModelGUI extends LightModel{

    public LightModelGUI(Client client) {
        super(client);
    }

    public void update(){
        getClient().getGui().nicknameScene();
    }

    public void update(String nickname, int playerID, int numberOfPlayers, TurnState phase){            //UpdateNicknameMsg
        this.setNickname(nickname);
        this.setPhase(phase);
        this.setPlayerID(playerID);
        this.setNumberOfPlayers(numberOfPlayers);

        getClient().getGui().waitScene();

    }

    public void update(Marble[][] market, Marble marbleInExcess, List<DevelopmentCard> developmentCards, String currentPlayer, TurnState phase){    //GameStartMsg
        this.setMarket(market);
        this.setMarbleInExcess(marbleInExcess);
        this.setDevelopmentCardsToBuy(developmentCards);
        this.setCurrentPlayer(currentPlayer);
        this.setPhase(phase);

        this.setStorageType(Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        this.setStorageQuantity(0, 0, 0);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(List<LeaderCard> leaderCardInHand, List<LeaderCard> leaderCardsPlayed, TurnState phase){     //UpdateLeaderCardsMsg
        this.setLeaderCardsInHand(leaderCardInHand);
        this.setLeaderCardsPlayed(leaderCardsPlayed);
        this.setPhase(phase);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(TurnState phase, Resource r1, Resource r2, Resource r3){     //UpdateStorageTypesMsg
        this.setStorageType(r1, r2, r3);
        this.setPhase(phase);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(TurnState phase, Integer[] storages){                        //UpdateStorageMsg
        this.setPhase(phase);
        if(storages.length == 3){
            this.setStorageQuantity(storages[0], storages[1], storages[2]);
        }
        else if(storages.length == 4){
            this.setStorageQuantity(storages[0], storages[1], storages[2], storages[3]);
        }
        else this.setStorageQuantity(storages[0], storages[1], storages[2], storages[3], storages[4]);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(TurnState phase, int position, Boolean[] popeFavours){       //UpdateFaithMarkerPositionMsg
        this.setPhase(phase);
        this.setPosition(position);
        this.setPopeFavours(popeFavours);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(TurnState phase){                            //UpdatePhaseMsg
        this.setPhase(phase);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(Market market){                          //UpdateMarketMsg
        this.setMarket(market.getMarketTable());
        this.setMarbleInExcess(market.getMarbleInExcess());

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(TurnState phase, Map<Resource, Integer> map){     //UpdateResourcesToAddMsg
        this.setResourcesToAdd(map);
        this.setPhase(phase);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(int whiteMarbles){               //UpdateWhiteMarblesToManageMsg
        this.setWhiteMarblesToManage(whiteMarbles);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(Map<Resource, Integer> map, TurnState phase){     //ResourcesToOrganizeMsg
        this.setResourcesToOrganize(map);
        this.setPhase(phase);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(DevelopmentCard card, int slot){             //Update CardSlotMsg
        this.setDevelopmentCard2(card, slot);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(List<DevelopmentCard> cards){                //UpdateDecksMsg
        this.setDevelopmentCardsToBuy(cards);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void updateCardPrice(TurnState phase, Map<Resource, Integer> price){     //CardPriceMsg
        this.setPhase(phase);
        this.setCardCost(price);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void updateChest(TurnState phase, Map<Resource, Integer> mapFromChest){      //ChestMsg
        this.setChest(mapFromChest);
        this.setPhase(phase);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(Map<Resource, Integer> productionInput, Map<Resource, Integer> productionOutput, int faithPoint){        //UpdateCostGainsMsg
        this.setTotalCost(productionInput);
        this.setTotalGain(productionOutput);
        this.setFaithPoints(faithPoint);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(Map<Resource, Integer> totalCost){       //UpdateProductionCostMsg
        this.setTotalCost(totalCost);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(TurnState phase, Map<Resource, Integer> chest, int position, Boolean[] popeFavours){     //EndProductionMsg
        this.setPhase(phase);
        this.setChest(chest);
        this.setPosition(position);
        this.setPopeFavours(popeFavours);
        this.setFaithPoints(0);

        Map<Resource, Integer> totalGains = new HashMap<>();
        totalGains.put(Resource.SERVANT, 0);
        totalGains.put(Resource.COIN, 0);
        totalGains.put(Resource.SHIELD, 0);
        totalGains.put(Resource.STONE, 0);
        this.setTotalGain(totalGains);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(TurnState phase, String currentPlayer){          //StartTurnMsg
        this.setPhase(phase);
        this.setCurrentPlayer(currentPlayer);

        getClient().getGui().updatePersonalBoard(this);

    }

    public void update(String message){                                //ErrorMsg
        getClient().getGui().errorMessage(message);
    }

    public void update(ArrayList<Player> players) {
        this.setPlayers(players);
    }

    public void show(TurnState phase, String nickname, Map<Resource, Integer> mapFromChest, Integer[] storages, List<Resource> resourceList, int position, Boolean[] popeFavours, List<LeaderCard> leaderCardsPlayed){
        setPhase(phase);
        getClient().getGui().otherPlayerScene(nickname, mapFromChest, storages, resourceList, position, popeFavours, leaderCardsPlayed);
    }
}
