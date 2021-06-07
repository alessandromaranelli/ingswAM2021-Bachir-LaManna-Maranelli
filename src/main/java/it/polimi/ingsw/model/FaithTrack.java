package it.polimi.ingsw.model;

import java.util.ArrayList;


/**
 * FaithTrack is the class that describes the fatih path. It is composed by an arraylist of 25 Boxes, of which
 * 22 are normal Boxes and 3 are PopeSpaces. It also has an arraylist of three PopeFavours and an attribute
 * playerPosition that contains the Box in which the FaithMarker is in.
 */
public class FaithTrack {
    private ArrayList<Box> track;
    private ArrayList<PopeFavour> popeFavours;
    private Box playerPosition;

    /**
     * Constructor FaithTrack creates a new FaithTrack instance
     * @param vaticanReportSections - ArrayList<VaticanReportSection>, a list the contain the vaticanReportSections
     * of the game
     */
    public FaithTrack(ArrayList<VaticanReportSection> vaticanReportSections) {
        track=new ArrayList<>();
        Box box= new Box(0); //0
        track.add(box);
        playerPosition=box;
        box = new Box(0); //1
        track.add(box);
        box = new Box(0); //2
        track.add(box);
        box = new Box(1); //3
        track.add(box);
        box = new Box(0);  //4
        track.add(box);
        box = new Box(0);  //5
        track.add(box);
        box = new Box(2);  //6
        track.add(box);
        box = new Box(0);  //7
        track.add(box);
        box = new PopeSpace(0, vaticanReportSections.get(0)); //8
        track.add(box);
        box = new Box(4);  //9
        track.add(box);
        box = new Box(0);  //10
        track.add(box);
        box = new Box(0);  //11
        track.add(box);
        box = new Box(6);  //12
        track.add(box);
        box = new Box(0);  //13
        track.add(box);
        box = new Box(0);  //14
        track.add(box);
        box = new Box(9);  //15
        track.add(box);
        box = new PopeSpace(0, vaticanReportSections.get(1));  //16
        track.add(box);
        box = new Box(0);  //17
        track.add(box);
        box = new Box(12);  //18
        track.add(box);
        box = new Box(0);  //19
        track.add(box);
        box = new Box(0);  //20
        track.add(box);
        box = new Box(16);  //21
        track.add(box);
        box = new Box(0);  //22
        track.add(box);
        box = new Box(0);  //23
        track.add(box);
        box = new PopeSpace(20,vaticanReportSections.get(2));  //24
        track.add(box);

        popeFavours = new ArrayList<>();
        PopeFavour popeFavour= new PopeFavour(2);
        popeFavours.add(popeFavour);
        popeFavour = new PopeFavour(3);
        popeFavours.add(popeFavour);
        popeFavour = new PopeFavour(4);
        popeFavours.add(popeFavour);
    }

    /**
     * Getter method that returns the actual playerPosition
     * @return Box playerPosition
     */
    public Box checkPlayerPosition(){
        return playerPosition;
    }

    /**
     * Getter method that returns the track
     * @return ArrayList<Box> track
     */
    public ArrayList<Box> getTrack() {
        return track;
    }

    /**
     * Getter method that returns the popeFavours
     * @return ArrayList<PopeFavour> popeFavours
     */
    public ArrayList<PopeFavour> getPopeFavours() {
        return popeFavours;
    }

    /**
     * Method that increases by 1 box the playerPosition on the track.
     * At the end, method report is called
     */
    public void movePositionForward(){
        int playerPos= track.indexOf(playerPosition);
        if(playerPos<24) {
            playerPosition = track.get(playerPos + 1);
            playerPosition.report();
        }
    }
}