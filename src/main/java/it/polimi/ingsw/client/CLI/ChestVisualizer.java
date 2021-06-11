package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Chest;
import it.polimi.ingsw.model.Resource;

import java.util.Map;

public class ChestVisualizer {
    public void plot(Map<Resource, Integer> chest, String m) {
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        System.out.print( whiteEscape);
        System.out.println(m);
        for(Resource s: chest.keySet()){
            String res = getResource(s);
            System.out.println("--" + res + ":" + chest.get(s));
        }
        System.out.print( whiteEscape);
    }

    private String getResource(Resource res) {
        String greenEscape = ColorVisualizer.ANSI_GREEN.escape();
        String s;
        switch (res) {
            case SERVANT:
                return ColorVisualizer.ANSI_PURPLE.escape() + "⚒" ;
            case SHIELD:
                return ColorVisualizer.ANSI_BLUE.escape() + "⛊" ;
            case COIN:
                return ColorVisualizer.ANSI_YELLOW.escape() + "⛀" ;
            default:
                return ColorVisualizer.ANSI_GREY.escape() + "⛰" ;
        }
    }
}