package it.polimi.ingsw.messages.commands.preparation;

import it.polimi.ingsw.messages.commands.CommandMsg;
import it.polimi.ingsw.model.TurnState;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class NumberOfPlayersMsg extends CommandMsg {

    private int numberOfPlayers;

    public NumberOfPlayersMsg(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        if (numberOfPlayers<1 || numberOfPlayers>4){
            try {
                clientHandler.getOutput().writeObject("Numero di giocatori impossibile. Riprova!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        controller.setNumberOfPlayers(numberOfPlayers);
    }

}