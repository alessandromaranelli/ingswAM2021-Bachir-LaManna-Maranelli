package it.polimi.ingsw.model;

public class RedMarble implements Marble {
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getFaithTrack().movePositionForward();
    }

}