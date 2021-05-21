package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;

import java.util.List;

public class StoragesVisualizer {
    public void plot(List<Resource> storageType, List<Integer> storageQuantity){
        for(int i=0;i<storageType.size(); i++){
            String res  = getResource(storageType.get(i));
            for(int j=0; j<storageQuantity.get(i); j++)
                System.out.print(" " + res + " ");
            System.out.println();
        }
    }

    private String getResource(Resource res){
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
