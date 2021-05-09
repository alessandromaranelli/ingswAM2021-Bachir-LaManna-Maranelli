package it.polimi.ingsw.messages.commands;

import it.polimi.ingsw.messages.answers.StartTurnMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

public class EndTurnMsg extends CommandMsg{

    public void processMessage(ClientHandler clientHandler, Controller controller) {
        if(controller.getGame().getCurrentPlayer().getPhase() != TurnState.ENDTURN || controller.getGame().getCurrentPlayer().getPhase() != TurnState.ENDPREPARATION){
            StringMsg stringMsg = new StringMsg("You can't end the turn now");
            clientHandler.sendAnswerMessage(stringMsg);
        }
        else{
            StringMsg stringMsg = new StringMsg("You ended your turn");
            clientHandler.sendAnswerMessage(stringMsg);
            controller.getGame().nextPlayer();

            for(ClientHandler c : controller.getClientConnectionThreads()){
                if(c.getPlayerID() == controller.getGame().getCurrentPlayer().getPlayerID()){
                    StartTurnMsg startTurnMsg = new StartTurnMsg(controller.getGame().getCurrentPlayer().getPhase(), controller.getGame().getCurrentPlayer().getNickname());
                    c.sendAnswerMessage(startTurnMsg);
                }
                else{
                    StartTurnMsg startTurnMsg = new StartTurnMsg(TurnState.ENDTURN, controller.getGame().getCurrentPlayer().getNickname());
                    c.sendAnswerMessage(startTurnMsg);
                }
            }
        }
    }
}
