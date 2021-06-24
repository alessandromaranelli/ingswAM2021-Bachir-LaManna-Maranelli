package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.AskWhichPlayerMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

public class ViewOtherPlayersMsg extends CommandMsg{
    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws ModelException {
        String x="\nYou can chose between: ";
        controller.getAvailableChoices().clear();
        for (Player p:controller.getGame().getPlayers()){
            if (p.getPlayerID()!=clientHandler.getPlayerID()){
                x=x+"\nPress "+p.getPlayerID()+" if you want to see "+p.getNickname()+"'s situation";
                controller.getAvailableChoices().add(p.getPlayerID());
            }
        }
        clientHandler.sendAnswerMessage(new AskWhichPlayerMsg(x, TurnState.VIEWOTHERPLAYERS));
    }
}
