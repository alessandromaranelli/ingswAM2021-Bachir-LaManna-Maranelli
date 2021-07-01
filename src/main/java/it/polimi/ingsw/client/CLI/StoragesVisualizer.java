package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Storage;

import java.util.List;

/**
 * The type Storages visualizer takes care of visualising all the storages state
 */
public class StoragesVisualizer {
    /**
     * Prints on screen the details
     *
     * @param storageType     the storage type
     * @param storageQuantity amount of resources
     */
    public void plot(List<Resource> storageType, List<Integer> storageQuantity){
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        System.out.print( whiteEscape);
        System.out.println("These are the storages");
        for(int i=0;i<storageType.size(); i++){
            String res  = getResource(storageType.get(i));
            for(int j=0; j<storageQuantity.get(i); j++)
                System.out.print(" " + res + " ");
            if(storageQuantity.get(i)==0) System.out.print(" " + 0 + " ");
            System.out.println();
        }
        System.out.print( whiteEscape);
        System.out.print(ColorVisualizer.RESET);
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
