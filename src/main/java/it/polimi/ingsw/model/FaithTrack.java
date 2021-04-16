package it.polimi.ingsw.model;

import java.util.ArrayList;

public class FaithTrack {
    private ArrayList<Box> track;
    private ArrayList<PopeFavour> popeFavours;
    private Box playerPosition;

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

    public Box checkPlayerPosition(){
        return playerPosition;
    }

    public ArrayList<Box> getTrack() {
        return track;
    }

    public ArrayList<PopeFavour> getPopeFavours() {
        return popeFavours;
    }

    public void movePositionForward(){
        int playerPos= track.indexOf(playerPosition);
        playerPosition= track.get(playerPos+1);
        playerPosition.report();
    }
}