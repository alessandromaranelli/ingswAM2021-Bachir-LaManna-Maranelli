package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;

/**
 * The type UpdateMarketMsg.
 */
public class UpdateMarketMsg extends AnswerMsg {
    private Market market;
    private String message="\nMarket updated";

    /**
     * Instantiates a new Update market msg.
     *
     * @param market the market
     */
    public UpdateMarketMsg(Market market) {
        this.market = market;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(market);

    }

    @Override
    public void printMessage() { System.out.println(message);

    }
}
