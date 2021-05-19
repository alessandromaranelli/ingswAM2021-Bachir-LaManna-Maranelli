package it.polimi.ingsw.client.CLI;

public class FaithTrackVisualizer {
    public void plot(int position){
        for(int i=0;i<26;i++){
            if(i!=position)
                System.out.print("["+i+"]");
            else
                System.out.print("[✙]");
        }
        System.out.println();
        for(int i=0;i<26;i++) {
            System.out.print(i);
        }
    }

}
