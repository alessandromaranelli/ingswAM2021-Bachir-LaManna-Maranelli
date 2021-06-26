package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.*;

import java.util.List;
import java.util.Map;

/**
 * The type GameReJoinMsg.
 */
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

    /**
     * Instantiates a new Game rejoin msg.
     *
     * @param nickname     the nickname
     * @param position     the position
     * @param popeFavours  the pope favours
     * @param r1           the resource 1
     * @param r2           the resource 2
     * @param r3           the resource 3
     * @param storages     the storages
     * @param mapFromChest the map from chest
     * @param m            the marketTray
     * @param mx           the marble in excess
     * @param d            the the list of developmentcards
     * @param c            the current player's nickname
     * @param phase        the phase
     * @param isSoloMatch  the is solo match
     */
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

    }

    public void printMessage() {
        System.out.println(message);
    }
}