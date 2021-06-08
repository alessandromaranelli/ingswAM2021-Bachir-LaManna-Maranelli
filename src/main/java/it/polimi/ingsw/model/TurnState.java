package it.polimi.ingsw.model;

import java.io.Serializable;

public enum TurnState implements Serializable {
    BEFORESTART,
    WAIT,
    PREPARATION,
    CHOOSELEADERCARDS,
    CHOOSERESOURCES,
    ENDPREPARATION,
    START,
    MARKETPHASE,
    WHITEMARBLES,
    CHOICE,
    ORGANIZERESOURCES,
    MANAGERESOURCES,
    ADDRESOURCES,
    BUYDEVELOPMENTCARDPHASE,
    PAYDEVELOPMENTCARD,
    PRODUCTIONPHASE,
    PAYPRODUCTIONS,
    ENDTURN,
    VIEWOTHERPLAYERS
}
