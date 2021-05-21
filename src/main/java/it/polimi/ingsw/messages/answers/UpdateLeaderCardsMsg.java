package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateLeaderCardsMsg extends AnswerMsg{
    private List<LeaderCard> leaderCardInHand;
    private List<LeaderCard> leaderCardsPlayed;
    private TurnState phase;
    private String message;

    public UpdateLeaderCardsMsg(TurnState phase, ArrayList<LeaderCard> leaderCardInHand, ArrayList<LeaderCard> leaderCardsPlayed, String message) {
        this.leaderCardInHand = leaderCardInHand;
        this.leaderCardsPlayed = leaderCardsPlayed;
        this.phase = phase;
        this.message = message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(leaderCardInHand, leaderCardsPlayed, phase);
        /*
        lightModel.setLeaderCardsPlayed(leaderCardsPlayed);
        lightModel.setPhase(phase);
        if(lightModel.getLeaderCardsInHand().size()>0){
            System.out.println("\nHere are your LeadersInHand: ");
            for(LeaderCard leaderCard:lightModel.getLeaderCardsInHand())lightModel.getLeaderCardVisualizer().showLeaderData(leaderCard);
        }

        if(lightModel.getLeaderCardsPlayed().size()>0){
            System.out.println("\nHere are your LeadersPlayed: ");
            for(LeaderCard leaderCard:lightModel.getLeaderCardsPlayed())lightModel.getLeaderCardVisualizer().showLeaderData(leaderCard);
        }
        */
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
