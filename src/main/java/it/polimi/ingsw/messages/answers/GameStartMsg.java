package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.*;

import java.util.List;

/**
 * The type GameStartMsg.
 */
public class GameStartMsg extends AnswerMsg {
    private String message;
    private Marble[][] market;
    private Marble marbleInExcess;
    private List<DevelopmentCard> developmentCards;
    private String currentPlayer;
    private TurnState phase;
    private Boolean isSoloGame;

    /**
     * Instantiates a new Game start msg.
     *
     * @param m          the marketTray
     * @param mx         the marble in excess
     * @param d          the list of developmentcards
     * @param c          the current player's nickname
     * @param phase      the phase
     * @param isSoloGAme the is solo game
     */
    public GameStartMsg(Marble[][] m, Marble mx, List<DevelopmentCard> d, String c, TurnState phase, Boolean isSoloGAme){
        market = m;
        marbleInExcess = mx;
        developmentCards = d;
        currentPlayer = c;
        this.phase = phase;
        this.isSoloGame = isSoloGAme;
        message = "\n\nGame started. Current player is: " + currentPlayer;
    }


    public void processMessage(LightModel lightModel){
        lightModel.update(market, marbleInExcess, developmentCards, currentPlayer, phase, isSoloGame);

    }

    public void printMessage() {
        System.out.println(message);
    }
}
