package it.polimi.ingsw.messages.commands;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ShowPlayerSituationMsg;
import it.polimi.ingsw.messages.answers.UpdatePhaseMsg;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.util.ArrayList;
import java.util.List;

public class ViewOtherPlayerNumberMsg extends CommandMsg{
    int playerNumb;

    public ViewOtherPlayerNumberMsg(int playerNumb) {
        this.playerNumb = playerNumb;
    }

    @Override
    public void processMessage(ClientHandler clientHandler, Controller controller) throws ModelException {
        Player p;
        if(!controller.getAvailableChoices().contains(playerNumb)) clientHandler.sendAnswerMessage(new UpdatePhaseMsg(TurnState.VIEWOTHERPLAYERS,"You selected a player that does not exist, insert another number"));
        else{
            p=controller.getGame().getPlayerById(playerNumb);
            List<Resource> resourceList=new ArrayList<>();
            resourceList.add(p.getPersonalBoard().getWareHouse().getTypeStorage(1));
            resourceList.add(p.getPersonalBoard().getWareHouse().getTypeStorage(2));
            resourceList.add(p.getPersonalBoard().getWareHouse().getTypeStorage(3));
            clientHandler.sendAnswerMessage(new ShowPlayerSituationMsg(
                    p.getNickname(),
                    p.getPersonalBoard().getWareHouse().getMapfromChest(),
                    p.getPersonalBoard().getWareHouse().getStorages().
                            stream().map(Storage::getQuantity).toArray(Integer[]::new),
                    resourceList,
                    p.getPersonalBoard().getFaithTrack().getTrack().indexOf(
                            p.getPersonalBoard().getFaithTrack().checkPlayerPosition()),
                    p.getPersonalBoard().getFaithTrack().getPopeFavours().
                            stream().map(PopeFavour::isActivated).toArray(Boolean[]::new),
                    p.getPersonalBoard().getLeaderCardsPlayed(),
                    controller.getGame().getCurrentPlayer().getPhase(),
                    "This is the situation of player "+p.getPlayerID()+": "+p.getNickname()));
        }
    }
}
