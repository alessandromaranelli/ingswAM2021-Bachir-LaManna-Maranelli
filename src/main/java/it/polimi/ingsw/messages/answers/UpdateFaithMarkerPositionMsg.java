package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;

public class UpdateFaithMarkerPositionMsg extends AnswerMsg{
    private int position;
    private Boolean[] popeFavours;
    private TurnState phase;
    private String message;

    public UpdateFaithMarkerPositionMsg(TurnState phase, int position, Boolean[] popeFavours) {
        this.position = position;
        this.popeFavours = popeFavours;
        this.phase = phase;
        this.message = "Now you are in: " + position + " position on the faithTrack and the three popeFavours are: " + popeFavours[0] + ", " + popeFavours[1] + ", " + popeFavours[2];
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase, position, popeFavours);
        /*
        lightModel.setPhase(phase);
        lightModel.setPosition(position);
        lightModel.setPopeFavours(popeFavours);
        lightModel.getFaithTrackVisualizer().plot(position,popeFavours);

         */
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
