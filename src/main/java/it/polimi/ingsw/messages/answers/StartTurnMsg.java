package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.TurnState;

//Lo StartTurnMsg è sempre e solo una risposta ad una richiesta di fine turno da parte di un giocatore
public class StartTurnMsg extends AnswerMsg{
    private TurnState phase;
    private String currentPlayer;
    private String message;

    public StartTurnMsg(TurnState phase, String currentPlayer){
        this.phase = phase;
        this.currentPlayer = currentPlayer;
        message = "New current player is: " + currentPlayer;
    }

    public void processMessage(LightModel lightModel) {
        lightModel.setPhase(phase);
        lightModel.setCurrentPlayer(currentPlayer);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
