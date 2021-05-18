package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.StartTurnMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UpdateDecksMsg;
import it.polimi.ingsw.messages.answers.WinMsg;
import it.polimi.ingsw.model.CpuAction;
import it.polimi.ingsw.model.CpuActionDiscard;
import it.polimi.ingsw.model.CpuActionShuffle;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

public class EndTurnMsg extends CommandMsg{

    public void processMessage(ClientHandler clientHandler, Controller controller) throws ModelException {
        if(controller.getGame().getCurrentPlayer().getPhase() != TurnState.ENDTURN && controller.getGame().getCurrentPlayer().getPhase() != TurnState.ENDPREPARATION){
            StringMsg stringMsg = new StringMsg("You can't end the turn now");
            clientHandler.sendAnswerMessage(stringMsg);
        }
        else{
            StringMsg stringMsg = new StringMsg("You ended your turn");
            clientHandler.sendAnswerMessage(stringMsg);
            if(controller.getGame().isSoloMatch()){
                CpuAction cpuAction= (CpuAction) controller.getGame().nextPlayer();
                if (cpuAction instanceof CpuActionDiscard){
                    UpdateDecksMsg updateDecksMsg = new UpdateDecksMsg("Lorenzo discarded two "+cpuAction.getcolor().toString()+" cards", controller.getGame().getTable().getTopDevelopmentcards());
                    clientHandler.sendAnswerMessage(updateDecksMsg);
                }
                if (cpuAction instanceof CpuActionShuffle){
                    clientHandler.sendAnswerMessage(new StringMsg("Lorenzo moved his faith marker 1 box forward"));
                }
                if (cpuAction instanceof CpuActionDiscard){
                    clientHandler.sendAnswerMessage(new StringMsg("Lorenzo moved his faith marker 2 boxes forward"));
                }
                if (controller.getGame().hasLorenzoWon()){
                    clientHandler.sendAnswerMessage(new WinMsg("Lorenzo Won"));
                    controller.endGame(true);
                    return;
                }

            }
            else controller.getGame().nextPlayer();

            if(!controller.isLastTurn() && controller.getGame().isGameAboutToFinish()){
                controller.startLastTurn();
            }
            if(controller.isLastTurn() && controller.getTurnsToPlay()>0){
                controller.setTurnsToPlay(controller.getTurnsToPlay()-1);
                if(controller.getTurnsToPlay()==0){
                    controller.endGame(false);
                }
            }
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
