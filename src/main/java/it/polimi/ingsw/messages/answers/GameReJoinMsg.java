package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.*;

import java.util.List;
import java.util.Map;

public class GameReJoinMsg extends AnswerMsg {
    private String nick;
    private int position;
    private Boolean[] popeFavours;
    private Resource r1;
    private Resource r2;
    private Resource r3;
    private Integer[] storages;
    private Map<Resource,Integer> map;
    private String message;
    private Marble[][] market;
    private Marble marbleInExcess;
    private List<DevelopmentCard> developmentCards;
    private String currentPlayer;
    private TurnState phase;
    private Boolean isSoloMatch;

    public GameReJoinMsg(String nickname, int position, Boolean[] popeFavours, Resource r1, Resource r2, Resource r3,Integer[] storages,Map<Resource, Integer> mapFromChest,Marble[][] m, Marble mx, List<DevelopmentCard> d, String c, TurnState phase, Boolean isSoloMatch){
        nick = nickname;
        this.position = position;
        this.popeFavours = popeFavours;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.storages = storages;
        this.map = mapFromChest;
        market = m;
        marbleInExcess = mx;
        developmentCards = d;
        currentPlayer = c;
        this.phase = phase;
        this.isSoloMatch = isSoloMatch;
        message = "\n\nGame is going. Current player is: " + currentPlayer;
    }


    public void processMessage(LightModel lightModel){
        lightModel.setNickname(nick);
        lightModel.setPosition(position);
        lightModel.setPopeFavours(popeFavours);
        lightModel.setStorageType(r1, r2, r3);
        if(storages.length == 3){
            lightModel.setStorageQuantity(storages[0], storages[1], storages[2]);
        }
        else if(storages.length == 4){
            lightModel.setStorageQuantity(storages[0], storages[1], storages[2], storages[3]);
        }
        else lightModel.setStorageQuantity(storages[0], storages[1], storages[2], storages[3], storages[4]);
        lightModel.setChest(map);
        lightModel.update(market, marbleInExcess, developmentCards, currentPlayer, phase, isSoloMatch);

        /*
        lightModel.setMarket(market);
        lightModel.setMarbleInExcess(marbleInExcess);
        lightModel.setDevelopmentCardsToBuy(developmentCards);
        lightModel.setCurrentPlayer(currentPlayer);
        lightModel.setPhase(phase);

        lightModel.setStorageType(Resource.COIN, Resource.SHIELD, Resource.SERVANT);
        lightModel.setStorageQuantity(0, 0, 0);
        //lightModel.setChest(personalBoard.getWareHouse().getMapfromChest());
        //lightModel.setResourcesToOrganize(personalBoard.getWareHouse().getResourcesToOrganize());
        //lightModel.setResourcesToAdd(personalBoard.getWareHouse().getResourcesToAdd());
        */
    }

    public void printMessage() {
        System.out.println(message);
    }
}