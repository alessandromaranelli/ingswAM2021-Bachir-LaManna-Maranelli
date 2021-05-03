package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;
import it.polimi.ingsw.model.LeaderCard;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateLeaderCardsMsg extends AnswerMsg{
    ArrayList<LeaderCard> leaderCardArrayList;

    public UpdateLeaderCardsMsg(ArrayList<LeaderCard> leaderCardArrayList) {
        this.leaderCardArrayList = leaderCardArrayList;
    }

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
