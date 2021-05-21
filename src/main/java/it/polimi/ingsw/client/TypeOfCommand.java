package it.polimi.ingsw.client;

public enum TypeOfCommand {
    FOLD,
    NICKNAME,
    DRAWLEADERCARDS,
    DISCARDLEADERCARDS,
    SETINITSTORAGETYPES,
    ADDINITRESOURCES,

    VIEWMARKET, VIEWLEADERS, VIEWFAITHTRACK, VIEWDEVELOPMENTCARDSTOBUY, VIEWPRODUCTIONS, VIEWRESOURCES,

    SELECTMARKETPHASE,
    STARTMARKETPHASE,
    WHITEMARBLES,
    SELECTBUYDEVELOPMENTCARDPHASE,
    ORGANIZERESOURCES,
    SETSTORAGETYPES,
    MANAGERESOURCESBYDEFAULT,
    MANAGERESOURCES,
    STARTADDRESOURCES,
    ADDRESOURCES,
    DISCARDRESOURCES,

    PAYCARDFROMCHEST, PAYCARDFROMSTORAGE, SELECTPRODUCTIONPHASE, ACTIVATEPERSONALPRODUCTION, ACTIVATEPRODUCTION, ACTIVATESPECIALPRODUCTION, STARTPAYPRODUCTION, PAYPRODUCTIONFROMCHEST, PAYPRODUCTIONFROMSTORAGE, BUYDEVELOPMENTCARD,

    ACTIVATELEADERCARD,
    DISCARDLEADERCARD,
    REORGANIZERESOURCES,
    VIEWDEVELOPMENTCARD,
    ENDTURN;
}
