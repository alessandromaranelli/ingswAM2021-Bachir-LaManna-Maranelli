package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Resource;

import java.util.Map;

public class ProductionVisualizer {
    public void plot(Map<Resource, Integer> totalCost, Map<Resource, Integer> totalGain, int faithPoints) {
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        System.out.print( whiteEscape);
        System.out.println("Total cost of production:");
        for(Resource s: totalCost.keySet()){
            String res = getResource(s);
            System.out.println("--" + res + ":" + totalCost.get(s));
        }

        System.out.println("Total gain of production:");
        for(Resource s: totalCost.keySet()){
            String res = getResource(s);
            System.out.println("--" + res + ":" + totalGain.get(s));
        }

        System.out.println("Faithpoints gained: " + faithPoints);
        System.out.print( whiteEscape);
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
