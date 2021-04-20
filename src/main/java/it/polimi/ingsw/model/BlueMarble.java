package it.polimi.ingsw.model;

/**
 * BlueMarble is a class that implements Marble. When a BlueMarble is taken from the market,
 * a resource of the type 'shield' can be added to the player's storages.
 */

public class BlueMarble implements Marble {

    /**
     * method whenDrawn adds a 'shield' to the resources that can be put in the storages
     * @param personalBoard - the player's personal board.
     */
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.SHIELD,1);

    }

}