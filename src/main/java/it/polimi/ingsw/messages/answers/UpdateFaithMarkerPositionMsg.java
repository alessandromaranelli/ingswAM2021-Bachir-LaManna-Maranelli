package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;

public class UpdateFaithMarkerPositionMsg extends AnswerMsg{
    int position;
    Boolean[] popeFavours;
    TurnState phase;
    String message;

    public UpdateFaithMarkerPositionMsg(TurnState phase, int position, Boolean[] popeFavours) {
        this.position = position;
        this.popeFavours = popeFavours;
        this.phase = phase;
        this.message = "Now you have: " + position + " faithpoints and the three popeFavours are: " + popeFavours[0] + ", " + popeFavours[1] + ", " + popeFavours[2];
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.setPhase(phase);
        lightModel.setFaithPoints(position);
        lightModel.setPopeFavours(popeFavours);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
