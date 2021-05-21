package it.polimi.ingsw.messages.answers;
import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.TurnState;

import java.io.IOException;

public class UpdateMarketMsg extends AnswerMsg {
    private Market market;
    private String message="\nMarket updated";

    public UpdateMarketMsg(Market market) {
        this.market = market;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.update(market);
        /*
        lightModel.setMarket(market.getMarketTable());
        lightModel.setMarbleInExcess(market.getMarbleInExcess());
        lightModel.getMarketView().showMarbles(market.getMarketTable());
        lightModel.getMarketView().plot();

         */
    }

    @Override
    public void printMessage() { System.out.println(message);

    }
}
