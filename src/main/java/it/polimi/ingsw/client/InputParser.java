package it.polimi.ingsw.client;

import it.polimi.ingsw.model.TurnState;

/**
 * The type Input parser.
 */
public class InputParser {

    /**
     * Parse enum boolean.
     *
     * @param parts      the parts
     * @param client     the client
     * @param outputView the output view
     * @return the boolean
     */
    public boolean parseEnum(String parts[], Client client, OutputView outputView){
        if(parts[0].equals("QUICKSTART")&& parts.length==1 && client.getLightModel().getPhase() == TurnState.STARTCONNECTION){
            outputView.setType(TypeOfCommand.QUICKSTART);
            return true;
        }
        if(parts[0].equals("NEW GAME")&& parts.length==1 && client.getLightModel().getPhase() == TurnState.STARTCONNECTION){
            outputView.setType(TypeOfCommand.NEWMATCH);
            return true;
        }
        if(parts[0].equals("REJOIN")&& parts.length==1 && client.getLightModel().getPhase() == TurnState.STARTCONNECTION){
            outputView.setType(TypeOfCommand.REJOIN);
            return true;
        }
        if(parts[0].toLowerCase().equals("nickname") && parts[2].toLowerCase().equals("numberofplayers") &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                parts.length==4 && client.getLightModel().getPhase() == TurnState.BEFORESTART){
            outputView.setType(TypeOfCommand.NICKNAME);
            return true;
        }
        if (parts[0].toLowerCase().equals("drawleadercards") && parts.length==1 && client.getLightModel().getPhase() == TurnState.PREPARATION){
            outputView.setType(TypeOfCommand.DRAWLEADERCARDS);
            return true;
        }
        if (parts[0].toLowerCase().equals("discardleadercards") && parts.length==3 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.CHOOSELEADERCARDS){
            outputView.setType(TypeOfCommand.DISCARDLEADERCARDS);
            return true;
        }
        if (parts[0].toLowerCase().equals("setinitialstoragetypes") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2].equals("STONE")) &&
                (parts[3].equals("COIN") || parts[3].equals("SERVANT") || parts[3].equals("SHIELD") || parts[3].equals("STONE")) &&
                client.getLightModel().getPhase() == TurnState.CHOOSERESOURCES){
            outputView.setType(TypeOfCommand.SETINITSTORAGETYPES);
            return true;
        }
        if (parts[0].toLowerCase().equals("addinitialresources") &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts.length==2 || (parts.length==3 && (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2].equals("STONE"))) &&
                client.getLightModel().getPhase() == TurnState.CHOOSERESOURCES)){
            outputView.setType(TypeOfCommand.ADDINITRESOURCES);
            return true;
        }


        if (parts[0].toLowerCase().equals("selectmarketphase") && parts.length==1 && client.getLightModel().getPhase() == TurnState.START){
            outputView.setType(TypeOfCommand.SELECTMARKETPHASE);
            return true;
        }
        if (parts[0].toLowerCase().equals("startmarketphase") && parts.length==3 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (parts[2].equals("true")||parts[2].equals("false")) &&
                client.getLightModel().getPhase() == TurnState.MARKETPHASE){
            outputView.setType(TypeOfCommand.STARTMARKETPHASE);
            return true;
        }
        if (parts[0].toLowerCase().equals("managewhitemarbles") && parts.length==2 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) && client.getLightModel().getPhase() == TurnState.WHITEMARBLES){
            outputView.setType(TypeOfCommand.WHITEMARBLES);
            return true;
        }
        if (parts[0].toLowerCase().equals("startorganizeresources") && parts.length==1 && client.getLightModel().getPhase() == TurnState.CHOICE){
            outputView.setType(TypeOfCommand.ORGANIZERESOURCES);
            return true;
        }
        if (parts[0].toLowerCase().equals("setstoragetypes") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2].equals("STONE")) &&
                (parts[3].equals("COIN") || parts[3].equals("SERVANT") || parts[3].equals("SHIELD") || parts[3].equals("STONE")) &&
                client.getLightModel().getPhase() == TurnState.ORGANIZERESOURCES){
            outputView.setType(TypeOfCommand.SETSTORAGETYPES);
            return true;
        }
        if (parts[0].toLowerCase().equals("defaultmanageresourcestoorganize") && parts.length==1 && client.getLightModel().getPhase() == TurnState.MANAGERESOURCES){
            outputView.setType(TypeOfCommand.MANAGERESOURCESBYDEFAULT);
            return true;
        }
        if (parts[0].toLowerCase().equals("manageresourcestoorganize") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.MANAGERESOURCES){
            outputView.setType(TypeOfCommand.MANAGERESOURCES);
            return true;
        }
        if (parts[0].toLowerCase().equals("startaddresources") && parts.length==1 && client.getLightModel().getPhase() == TurnState.CHOICE){
            outputView.setType(TypeOfCommand.STARTADDRESOURCES);
            return true;
        }
        if (parts[0].toLowerCase().equals("addresources") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.ADDRESOURCES){
            outputView.setType(TypeOfCommand.ADDRESOURCES);
            return true;
        }
        if (parts[0].toLowerCase().equals("discardresources") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.ADDRESOURCES) {
            outputView.setType(TypeOfCommand.DISCARDRESOURCES);
            return true;
        }


        if (parts[0].toLowerCase().equals("reorganizeresources") && (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            outputView.setType(TypeOfCommand.REORGANIZERESOURCES);
            return true;
        }


        if (parts[0].toLowerCase().equals("selectbuydevelopmentcardphase") && parts.length==1 &&client.getLightModel().getPhase() == TurnState.START){
            outputView.setType(TypeOfCommand.SELECTBUYDEVELOPMENTCARDPHASE);
            return true;
        }
        if (parts[0].toLowerCase().equals("buydevelopmentcard") && parts.length==4 &&
                (parts[1].equals("GREEN") || parts[1].equals("BLUE") || parts[1].equals("PURPLE") || parts[1].equals("YELLOW")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.BUYDEVELOPMENTCARDPHASE){
            outputView.setType(TypeOfCommand.BUYDEVELOPMENTCARD);
            return true;
        }
        if (parts[0].toLowerCase().equals("paycardfromchest") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYDEVELOPMENTCARD){
            outputView.setType(TypeOfCommand.PAYCARDFROMCHEST);
            return true;
        }
        if (parts[0].toLowerCase().equals("paycardfromstorage") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYDEVELOPMENTCARD){
            outputView.setType(TypeOfCommand.PAYCARDFROMSTORAGE);
            return true;
        }


        if (parts[0].toLowerCase().equals("selectproductionphase") && parts.length==1 && client.getLightModel().getPhase() == TurnState.START){
            outputView.setType(TypeOfCommand.SELECTPRODUCTIONPHASE);
            return true;
        }
        if (parts[0].toLowerCase().equals("activatepersonalproduction") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("COIN") || parts[2].equals("SERVANT") || parts[2].equals("SHIELD") || parts[2].equals("STONE")) &&
                (parts[3].equals("COIN") || parts[3].equals("SERVANT") || parts[3].equals("SHIELD") || parts[3].equals("STONE")) &&
                client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            outputView.setType(TypeOfCommand.ACTIVATEPERSONALPRODUCTION);
            return true;
        }
        if (parts[0].toLowerCase().equals("activateproduction") && parts.length==2 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            outputView.setType(TypeOfCommand.ACTIVATEPRODUCTION);
            return true;
        }
        if (parts[0].toLowerCase().equals("activatespecialproduction") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            outputView.setType(TypeOfCommand.ACTIVATESPECIALPRODUCTION);
            return true;
        }
        if (parts[0].toLowerCase().equals("startpayproduction") && parts.length==1 &&client.getLightModel().getPhase() == TurnState.PRODUCTIONPHASE){
            outputView.setType(TypeOfCommand.STARTPAYPRODUCTION);
            return true;
        }
        if (parts[0].toLowerCase().equals("payproductionfromchest") && parts.length==3 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYPRODUCTIONS){
            outputView.setType(TypeOfCommand.PAYPRODUCTIONFROMCHEST);
            return true;
        }
        if (parts[0].toLowerCase().equals("payproductionfromstorage") && parts.length==4 &&
                (parts[1].equals("COIN") || parts[1].equals("SERVANT") || parts[1].equals("SHIELD") || parts[1].equals("STONE")) &&
                (parts[2].equals("0") || parts[2].equals("1") || parts[2].equals("2") || parts[2].equals("3") || parts[2].equals("4") ||
                        parts[2].equals("5") || parts[2].equals("6") || parts[2].equals("7") || parts[2].equals("8") || parts[2].equals("9")) &&
                (parts[3].equals("0") || parts[3].equals("1") || parts[3].equals("2") || parts[3].equals("3") || parts[3].equals("4") ||
                        parts[3].equals("5") || parts[3].equals("6") || parts[3].equals("7") || parts[3].equals("8") || parts[3].equals("9")) &&
                client.getLightModel().getPhase() == TurnState.PAYPRODUCTIONS){
            outputView.setType(TypeOfCommand.PAYPRODUCTIONFROMSTORAGE);
            return true;
        }


        if (parts[0].toLowerCase().equals("activateleadercard") && parts.length==2 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            outputView.setType(TypeOfCommand.ACTIVATELEADERCARD);
            return true;
        }
        if (parts[0].toLowerCase().equals("discardleadercard") && parts.length==2 &&
                (parts[1].equals("0") || parts[1].equals("1") || parts[1].equals("2") || parts[1].equals("3") || parts[1].equals("4") ||
                        parts[1].equals("5") || parts[1].equals("6") || parts[1].equals("7") || parts[1].equals("8") || parts[1].equals("9")) &&
                (client.getLightModel().getPhase() == TurnState.START || client.getLightModel().getPhase() == TurnState.ENDTURN)){
            outputView.setType(TypeOfCommand.DISCARDLEADERCARD);
            return true;
        }


        if (parts[0].toLowerCase().equals("endturn") && parts.length==1 &&(client.getLightModel().getPhase() == TurnState.ENDTURN || client.getLightModel().getPhase() == TurnState.ENDPREPARATION)){
            client.getLightModel().setPhase(TurnState.START);
            outputView.setType(TypeOfCommand.ENDTURN);
            return true;
        }

        if (parts[0].toLowerCase().equals("viewmarket")) {
            outputView.setType(TypeOfCommand.VIEWMARKET);
            return true;
        }
        if (parts[0].toLowerCase().equals("viewdevelopmentcard")){
            outputView.setType(TypeOfCommand.VIEWDEVELOPMENTCARD);
            return true;
        }
        if (parts[0].toLowerCase().equals("viewleaders")) {
            outputView.setType(TypeOfCommand.VIEWLEADERS);
            return true;
        }
        if (parts[0].toLowerCase().equals("viewfaithtrack")) {
            outputView.setType(TypeOfCommand.VIEWFAITHTRACK);
            return true;
        }
        if (parts[0].toLowerCase().equals("viewdevelopmentcardstobuy")) {
            outputView.setType(TypeOfCommand.VIEWDEVELOPMENTCARDSTOBUY);
            return true;
        }
        if (parts[0].toLowerCase().equals("viewproduction")) {
            outputView.setType(TypeOfCommand.VIEWPRODUCTIONS);
            return true;
        }
        if (parts[0].toLowerCase().equals("viewresources")) {
            outputView.setType(TypeOfCommand.VIEWRESOURCES);
            return true;
        }
        if (parts[0].toLowerCase().equals("viewotherplayers")) {
            outputView.setType(TypeOfCommand.VIEWOTHERPLAYERS);
            return true;
        }
        if ((parts[0].equals("0") || parts[0].equals("1") || parts[0].equals("2") || parts[0].equals("3") || parts[0].equals("4"))  && parts.length==1 &&(client.getLightModel().getPhase() == TurnState.VIEWOTHERPLAYERS )) {
            outputView.setType(TypeOfCommand.VIEWOTHERPLAYERNUMBER);
            return true;
        }
        else return false;
    }

}
