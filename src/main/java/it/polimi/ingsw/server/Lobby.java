package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.StringMsg;

import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Lobby {
    private Server server;
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
    private Set<Match> matches;


    public Lobby(Server server,Set<Match> matches) throws FileNotFoundException {
        this.server=server;
        this.matches=matches;
    }

    public void add(ClientHandler clientHandler){
        clientConnectionThreads.add(clientHandler);
        clientHandler.sendAnswerMessage(new StringMsg("\n======  [NEW GAME] | [QUICKSTART]  ======\n"));
    }

    public synchronized void createMatch(ClientHandler clientHandler) throws FileNotFoundException {
        Match match=new Match();
        matches.add(match);
        match.createMatch();
        match.getClientConnectionThreads().add(clientHandler);
        clientHandler.setController(match.getController());
        clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a game\n"));
    }

    public synchronized void joinMatch(ClientHandler clientHandler) throws FileNotFoundException {
        for (Match m:matches){
            if(!m.isFull()){
                m.getClientConnectionThreads().add(clientHandler);
                clientHandler.setController(m.getController());
                clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a game\n"));
                return;
            }
        }
        Match match=new Match();
        matches.add(match);
        match.createMatch();
        match.getClientConnectionThreads().add(clientHandler);
        clientHandler.setController(match.getController());
        clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a game\n"));
    }

    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }



}
