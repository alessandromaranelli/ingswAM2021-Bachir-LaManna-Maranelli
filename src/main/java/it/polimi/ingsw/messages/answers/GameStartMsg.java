package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.*;

import java.util.List;

public class GameStartMsg extends AnswerMsg {
    private String message;
    private Marble[][] market;
    private Marble marbleInExcess;
    private List<DevelopmentCard> developmentCards;
    private PersonalBoard personalBoard;
    private String currentPlayer;
    private TurnState phase;

    public GameStartMsg(Marble[][] m, Marble mx, List<DevelopmentCard> d, PersonalBoard p, String c, TurnState phase){
        market = m;
        marbleInExcess = mx;
        developmentCards = d;
        personalBoard = p;
        currentPlayer = c;
        this.phase = phase;
        message = "Game started. Current player is: " + currentPlayer;
    }


    public void processMessage(LightModel lightModel){
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
    }

    public void printMessage() {
        System.out.println(message);
    }
}
