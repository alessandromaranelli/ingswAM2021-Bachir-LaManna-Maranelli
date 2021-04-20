package it.polimi.ingsw.model;

/**
 * YellowMarble is a class that implements Marble. When a YellowMarble is taken from the market,
 * a resource of the type 'coin' can be added to the player's storages.
 */

public class YellowMarble implements Marble {

    /**
     * method whenDrawn adds a 'coin' to the resources that can be put in the storages.
     * @param personalBoard - the player's personal board
     */
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.COIN,1);

    }

}
