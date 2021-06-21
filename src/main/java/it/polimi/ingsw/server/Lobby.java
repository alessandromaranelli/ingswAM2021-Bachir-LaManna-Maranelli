package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UnicodeMsg;

import java.io.FileNotFoundException;
import java.util.*;

public class Lobby {
    private Server server;
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
    private final Map<String,Match> unicodeList= new HashMap<>();
    private Set<Match> matches;

    //TODO handle end game with the connections
    public Lobby(Server server,Set<Match> matches) throws FileNotFoundException {
        this.server=server;
        this.matches=matches;
    }

    public void add(ClientHandler clientHandler){
        clientConnectionThreads.add(clientHandler);

        clientHandler.sendAnswerMessage(new StringMsg("\n======  [NEW GAME] | [QUICKSTART] | [REJOIN] ======\n"));
    }

    public synchronized void createMatch(ClientHandler clientHandler) throws FileNotFoundException {
        String code=nextString();
        clientHandler.sendAnswerMessage(new UnicodeMsg(code));
        clientHandler.setUnicode(code);

        Match match=new Match(server);
        matches.add(match);
        match.createMatch();
        match.getClientConnectionThreads().add(clientHandler);
        clientHandler.setController(match.getController());
        clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a game\n"));
    }

    public synchronized void joinMatch(ClientHandler clientHandler) throws FileNotFoundException {
        String code=nextString();
        clientHandler.sendAnswerMessage(new UnicodeMsg(code));
        clientHandler.setUnicode(code);
        for (Match m:matches){
            if(!m.isFull()){
                m.getClientConnectionThreads().add(clientHandler);
                clientHandler.setController(m.getController());
                clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a game\n"));
                return;
            }
        }
        Match match=new Match(server);
        matches.add(match);
        match.createMatch();
        match.getClientConnectionThreads().add(clientHandler);
        clientHandler.setController(match.getController());
        clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a game\n"));
    }

    public synchronized void reJoinMatch(ClientHandler clientHandler, String code){
        for (String s : unicodeList.keySet()){
            if(s.equals(code)) {
                clientHandler.setUnicode(code);
                unicodeList.get(s).reAdd(clientHandler);
                unicodeList.remove(s);
                return;
            }
        }
        clientHandler.sendAnswerMessage(new ErrorMsg("You cannot rejoin an existing game!!!"));

    }

    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }

    public Map<String, Match> getUnicodeList() {
        return unicodeList;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    /**
     * Generate a random string.
     */
     public String nextString() {
        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        final String lower = upper.toLowerCase(Locale.ROOT);

        final String digits = "0123456789";

        final String alphanum = upper + lower + digits;

        final char []symbols =alphanum.toCharArray();
        final char[] buf=new char[10];
        final Random random=new Random();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }




}
