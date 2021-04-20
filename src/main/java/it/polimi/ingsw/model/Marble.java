package it.polimi.ingsw.model;

/**
 * Marble is an interface that declares the method whenDrawn, called whenever a marble is taken from the market.
 */

public interface Marble {

    /**
     * method whenDrawn specifies an action for every color of the marbles
     * that has an effect on the personal board of the player who has taken the marble.
     * @param personalBoard - the player's personal board
     */
    public void whenDrawn(PersonalBoard personalBoard);

}
