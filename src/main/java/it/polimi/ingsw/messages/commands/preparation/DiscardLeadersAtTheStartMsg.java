package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateLeaderCardsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class DiscardLeadersAtTheStartMsg extends CommandMsg {
    int leader1;
    int leader2;

    public DiscardLeadersAtTheStartMsg(int leader1, int leader2) {
        this.leader1 = leader1;
        this.leader2 = leader2;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        try{
            controller.getGame().getCurrentPlayer().chooseLeaderCardsToDiscard(leader1,leader2);
        }catch (ModelException e){
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateLeaderCardsMsg(
                    controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsInHand()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
