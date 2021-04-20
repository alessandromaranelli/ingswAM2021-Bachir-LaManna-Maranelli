package it.polimi.ingsw.model;

/**
 * RedMarble is a class that implements Marble. When a RedMarble is taken from the market,
 * the player's faith marker has to be moved one box forward in the faith track.
 */

public class RedMarble implements Marble {

    /**
     * method whenDrawn moves the player's faith marker one box forward.
     * @param personalBoard - the player's personal board
     */
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getFaithTrack().movePositionForward();
    }

}