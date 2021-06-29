package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;


/**
 * The type UpdateStorageTypesMsg.
 */
public class UpdateStorageTypesMsg extends AnswerMsg{
    private Resource r1;
    private Resource r2;
    private Resource r3;
    private TurnState phase;
    private String message;

    /**
     * Instantiates a new Update storage types msg.
     *
     * @param phase the phase
     * @param r1    the r 1
     * @param r2    the r 2
     * @param r3    the r 3
     */
    public UpdateStorageTypesMsg(TurnState phase, Resource r1, Resource r2, Resource r3) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.phase = phase;
        this.message = "You changed your storages type to: " + r1.toString() + ", " + r2.toString() + ", " + r3.toString();
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(phase, r1, r2, r3);

    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
