package it.polimi.ingsw.model;

import Exceptions.ModelException;

public class YellowMarble implements Marble {
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.COIN,1);

    }

}
