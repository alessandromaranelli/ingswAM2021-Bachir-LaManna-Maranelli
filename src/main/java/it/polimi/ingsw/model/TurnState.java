package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * The enum Turn state.
 */
public enum TurnState implements Serializable {
    /**
     * Startconnection turn state.
     * The player decides to join a game, create a new game or rejoin a game
     */
    STARTCONNECTION,
    /**
     * Beforestart turn state.
     * The player decides the nickname and the number of players of the game
     */
    BEFORESTART,
    /**
     * Wait turn state.
     * The player waits for other players to join the game
     */
    WAIT,
    /**
     * Preparation turn state.
     * The player draws 4 leader cards
     */
    PREPARATION,
    /**
     * Chooseleadercards turn state.
     * The player discards 2 leader cards
     */
    CHOOSELEADERCARDS,
    /**
     * Chooseresources turn state.
     * The player sets the storages types and he adds the initial resources
     */
    CHOOSERESOURCES,
    /**
     * Endpreparation turn state.
     * The player finishes the preparations
     */
    ENDPREPARATION,
    /**
     * Start turn state.
     * The player can choose an action for this turn
     */
    START,
    /**
     * Marketphase turn state.
     * The player starts the market phase and chooses a row or a column of the market
     */
    MARKETPHASE,
    /**
     * Whitemarbles turn state.
     * The player has to choose the color of the white marbles
     */
    WHITEMARBLES,
    /**
     * Choice turn state.
     * The player can choose between reorganize resources in the storages or directly add the resources gained from the
     * market to the storages
     */
    CHOICE,
    /**
     * Organizeresources turn state.
     * The player can change types of the storages
     */
    ORGANIZERESOURCES,
    /**
     * Manageresources turn state.
     * The player changes the position of the resources between the storages
     */
    MANAGERESOURCES,
    /**
     * Addresources turn state.
     * The player can add resources gained from the market to the storages or discard them
     */
    ADDRESOURCES,
    /**
     * Buydevelopmentcardphase turn state.
     * The player starts the development card buy phase and chooses the card to buy
     */
    BUYDEVELOPMENTCARDPHASE,
    /**
     * Paydevelopmentcard turn state.
     * The players pays the card
     */
    PAYDEVELOPMENTCARD,
    /**
     * Productionphase turn state.
     * The player starts the production phase and decides the productions to activate
     */
    PRODUCTIONPHASE,
    /**
     * Payproductions turn state.
     * The player pays the productions and gains the resources produced
     */
    PAYPRODUCTIONS,
    /**
     * Endturn turn state.
     * The player can organize the resources in the storages, do a leader action or pass the turn
     */
    ENDTURN,
    /**
     * Viewotherplayers turn state.
     * The player sees the state of the board of another player
     */
    VIEWOTHERPLAYERS
}
