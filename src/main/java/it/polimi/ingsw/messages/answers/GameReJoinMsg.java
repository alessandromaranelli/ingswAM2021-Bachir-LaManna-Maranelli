package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.*;

import java.util.List;

public class GameReJoinMsg extends AnswerMsg {
    private String nick;
    private String message;
    private Marble[][] market;
    private Marble marbleInExcess;
    private List<DevelopmentCard> developmentCards;
    private PersonalBoard personalBoard;
    private String currentPlayer;
    private TurnState phase;

    public GameReJoinMsg(String nickname, Marble[][] m, Marble mx, List<DevelopmentCard> d, String c, TurnState phase){
        nick = nickname;
        market = m;
        marbleInExcess = mx;
        developmentCards = d;
        currentPlayer = c;
        this.phase = phase;
        message = "\n\nGame is going. Current player is: " + currentPlayer;
    }


    public void processMessage(LightModel lightModel){
        lightModel.update(market, marbleInExcess, developmentCards, currentPlayer, phase);
        lightModel.setNickname(nick);
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