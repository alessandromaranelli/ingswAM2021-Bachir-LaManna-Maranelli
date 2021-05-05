package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateLeaderCardsMsg extends AnswerMsg{
    List<LeaderCard> leaderCardInHand;
    List<LeaderCard> leaderCardsPlayed;
    TurnState phase;
    String message;

    public UpdateLeaderCardsMsg(TurnState phase, ArrayList<LeaderCard> leaderCardInHand, ArrayList<LeaderCard> leaderCardsPlayed, String message) {
        this.leaderCardInHand = leaderCardInHand;
        this.leaderCardsPlayed = leaderCardsPlayed;
        this.phase = phase;
        this.message = message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setLeaderCardsInHand(leaderCardInHand);
        lightModel.setLeaderCardsPlayed(leaderCardsPlayed);
        lightModel.setPhase(phase);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
