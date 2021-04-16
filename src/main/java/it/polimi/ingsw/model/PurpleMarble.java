package it.polimi.ingsw.model;

import Exceptions.ModelException;

public class PurpleMarble implements Marble {
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.SERVANT,1);
    }

}