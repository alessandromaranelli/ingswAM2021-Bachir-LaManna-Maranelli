package it.polimi.ingsw.model;

import Exceptions.ModelException;

public class BlueMarble implements Marble {
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.SHIELD,1);

    }

}