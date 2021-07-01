package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Resource;

import java.util.Map;

/**
 * Visualizes the cli version of what the client is paying and what are the available resources in the storages and chest
 */
public class ProductionVisualizer {
    /**
     * Prints on screen the state of production
     *
     * @param totalCost   the total cost updated on every payment
     * @param totalGain   the total gain
     * @param faithPoints the faith points earned
     */
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
        System.out.print(ColorVisualizer.RESET);
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
