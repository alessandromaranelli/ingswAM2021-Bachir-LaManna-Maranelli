package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * CpuActionDiscard defines a token that discards two cards from the developmentCardDecks of
 * a specific color.
 */
public class CpuActionDiscard implements CpuAction {
    private Color color;

    /**
     * Constructor CpuActionDiscard creates a new CpuActionDiscard instance
     * @param color - the color of the DevelopmentCards to discard
     */
    public CpuActionDiscard(Color color) {
        this.color = color;
    }

    /**
     * This method discards two card of the color defined by the attribute color from the developmentCardDecks
     * It looks first for level 1 cards, if there are not it discards level 2 cards and then level 3 cards
     * @param table - the gameboard
     * @param faithTrack - the Cpu's FaithTrack
     * @param cpuActions - a list of all the action tokens
     */
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