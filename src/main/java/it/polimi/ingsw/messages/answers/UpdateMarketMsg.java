package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.ServerHandler;
import it.polimi.ingsw.model.Market;

import java.io.IOException;

public class UpdateMarketMsg extends AnswerMsg {
    Market market;

    public UpdateMarketMsg(Market market) {
        this.market = market;
    }

    @Override
    public void processMessage(ServerHandler serverHandler) throws IOException {

    }
}
