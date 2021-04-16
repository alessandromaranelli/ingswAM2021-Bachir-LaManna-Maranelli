package it.polimi.ingsw.model;

import Exceptions.ModelException;

public class GreyMarble implements Marble {
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.STONE,1);

    }

}