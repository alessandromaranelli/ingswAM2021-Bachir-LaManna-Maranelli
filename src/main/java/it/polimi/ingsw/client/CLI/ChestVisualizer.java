package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Chest;
import it.polimi.ingsw.model.Resource;

import java.util.Map;

public class ChestVisualizer {
    public void plot(Map<Resource, Integer> chest) {
        for(Resource s: chest.keySet()){
            String res = getResource(s);
            System.out.println("--" + res + ":" + chest.get(s));
        }
    }

    private String getResource(Resource res) {
        String greenEscape = ColorVisualizer.ANSI_GREEN.escape();
        String s;
        switch (res) {
            case SERVANT:
                return ColorVisualizer.ANSI_PURPLE.escape() + "⚒" + greenEscape;
            case SHIELD:
                return ColorVisualizer.ANSI_BLUE.escape() + "⛊" + greenEscape;
            case COIN:
                return ColorVisualizer.ANSI_YELLOW.escape() + "⛀" + greenEscape;
            default:
                return ColorVisualizer.ANSI_GREY.escape() + "⛰" + greenEscape;
        }
    }
}