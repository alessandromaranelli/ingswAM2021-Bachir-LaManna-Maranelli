package it.polimi.ingsw.client.CLI;

/**
 * The view of the faithrack
 */
public class FaithTrackVisualizer {
    /**
     * Prints on screen the faith track
     *
     * @param position    faith marker pose
     * @param popeFavours pope favours state
     */
    public void plot(int position, Boolean[] popeFavours){
        String whiteEscape = ColorVisualizer.ANSI_WHITE.escape();
        System.out.print( whiteEscape);
        System.out.println("This the faith track!");
        for(int i=0;i<25;i++){
            if(i!=position)
                System.out.print("["+i+"]");
            else
                if (position>=10)
                    System.out.print("[+ ]");
                else System.out.print("[+]");
        }
        System.out.println("  FaithTrack");
        for(int i=0;i<25;i++) {
            if(i==8)
                System.out.print("["+"P"+"]");
            if(i==16||i==24)
                System.out.print("["+"P "+"]");
            if(i>=10 && i!=16 && i!=24)
                System.out.print("    ");
            if(i<10 && i!=8)
                System.out.print("   ");
        }
        System.out.println("  PopeSpaces");

        for(int i=0;i<25;i++) {
            if(i==5)
                System.out.print("["+"S"+"]");
            if(i==12||i==19)
                System.out.print("["+"S "+"]");
            if(i>=10 && i!=12 && i!=19)
                System.out.print("    ");
            if(i<10 && i!=5)
                System.out.print("   ");
        }
        System.out.println("  Start of PopeFavour's zones");

        for (int i=0;i<3;i++){
            if (popeFavours[i]==false) System.out.print("["+"F"+"]");
            else System.out.print("["+"T"+"]");
        }
        System.out.println("  Actual state of your PopeFavours");
        System.out.print( whiteEscape);
        System.out.print(ColorVisualizer.RESET);
    }

}
