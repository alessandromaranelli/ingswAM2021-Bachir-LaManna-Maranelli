package it.polimi.ingsw.client.CLI;

public enum ColorVisualizer {
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_GREY("\u001B[37m"),
    ANSI_WHITE("\u001B[38m");

    static final String RESET = "\u001B[0m";

    private String escape;

    ColorVisualizer(String escape) {
        this.escape = escape;
    }

    public String escape(){
        return escape;
    }

}