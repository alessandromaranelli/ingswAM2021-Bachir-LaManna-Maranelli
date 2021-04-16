package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CpuActionDiscard implements CpuAction {
    private Color color;

    public CpuActionDiscard(Color color) {
        this.color = color;
    }

    @Override
    public void activateAction(Table table, FaithTrack faithTrack, ArrayList<CpuAction> cpuActions) {
        int i=0;
        if (!table.getDevelopmentCardDeck(color,1).getDevelopmentCards().isEmpty()) {
            table.getDevelopmentCardDeck(color,1).removeFromTop();
            i++;
            if (!table.getDevelopmentCardDeck(color,1).getDevelopmentCards().isEmpty()){
                table.getDevelopmentCardDeck(color,1).removeFromTop();
                i++;
            }
        }
        if (i==2) return;
        if (!table.getDevelopmentCardDeck(color,2).getDevelopmentCards().isEmpty()) {
            table.getDevelopmentCardDeck(color,2).removeFromTop();
            i++;
            if (i<2 && !table.getDevelopmentCardDeck(color,2).getDevelopmentCards().isEmpty()){
                table.getDevelopmentCardDeck(color,2).removeFromTop();
                i++;
            }
        }
        if (i==2) return;
        if (!table.getDevelopmentCardDeck(color,3).getDevelopmentCards().isEmpty()) {
            table.getDevelopmentCardDeck(color,3).removeFromTop();
            i++;
            if (i<2 && !table.getDevelopmentCardDeck(color,3).getDevelopmentCards().isEmpty()){
                table.getDevelopmentCardDeck(color,3).removeFromTop();
                i++;
            }
        }

    }
}