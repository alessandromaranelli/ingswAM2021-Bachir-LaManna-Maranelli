package it.polimi.ingsw.messages.commands.preparation;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.UpdateLeaderCardsMsg;
import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class DrawLeadersMsg extends CommandMsg {

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException, ModelException {
        try{
            controller.getGame().getCurrentPlayer().drawLeaderCards();
        }catch (ModelException e){
            clientHandler.getOutput().writeObject(new ErrorMsg(e.getMessage()));
            return;
        }
        try{
            clientHandler.getOutput().writeObject(new UpdateLeaderCardsMsg(controller.getGame().getCurrentPlayer().getPersonalBoard().getLeaderCardsInHand()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
