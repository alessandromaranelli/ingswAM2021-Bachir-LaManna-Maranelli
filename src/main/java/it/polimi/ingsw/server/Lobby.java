package it.polimi.ingsw.server;

import Exceptions.ModelException;
import it.polimi.ingsw.messages.answers.ErrorMsg;
import it.polimi.ingsw.messages.answers.GameJoinedMsg;
import it.polimi.ingsw.messages.answers.StringMsg;
import it.polimi.ingsw.messages.answers.UnicodeMsg;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * The type Lobby accepts clients when they first enter the game. It then moves the clients to the matches, according to their
 * choices
 */
public class Lobby {
    private Server server;
    private final Set<ClientHandler> clientConnectionThreads = new LinkedHashSet<>();
    private final Map<String,Match> unicodeList= new HashMap<>();
    private Set<Match> matches;

    /**
     * Instantiates a new Lobby.
     *
     * @param server  the server
     * @param matches the matches
     * @throws FileNotFoundException the file not found exception
     */
    public Lobby(Server server,Set<Match> matches) throws FileNotFoundException {
        this.server=server;
        this.matches=matches;
    }

    /**
     * Method called when a new client connects to the server. An AnswerMessage is sent to the client
     *
     * @param clientHandler the client handler
     */
    public void add(ClientHandler clientHandler){
        clientConnectionThreads.add(clientHandler);

        clientHandler.sendAnswerMessage(new StringMsg("\n======  [NEW GAME] | [QUICKSTART] | [REJOIN] ======\n"));
    }

    /**
     * CreateMatch method instantiates a new Match in response to a client request.
     *
     * @param clientHandler the client handler
     * @throws FileNotFoundException the file not found exception
     */
    public synchronized void createMatch(ClientHandler clientHandler) throws FileNotFoundException {
        String code=nextString();
        clientHandler.sendAnswerMessage(new UnicodeMsg(code));
        clientHandler.setUnicode(code);

        Match match=new Match(server);
        matches.add(match);
        match.createMatch();
        match.getClientConnectionThreads().add(clientHandler);
        clientHandler.setController(match.getController());
        clientHandler.setMatch(match);
        clientHandler.sendAnswerMessage(new GameJoinedMsg());
        clientHandler.sendAnswerMessage(new StringMsg("\nYou created a game\n"));
    }

    /**
     * JoinMatch inserts the client in an existing match. If there are not, a new match is instantiated.
     *
     * @param clientHandler the client handler
     * @throws FileNotFoundException the file not found exception
     */
    public synchronized void joinMatch(ClientHandler clientHandler) throws FileNotFoundException {
        String code=nextString();
        clientHandler.sendAnswerMessage(new UnicodeMsg(code));
        clientHandler.setUnicode(code);
        for (Match m:matches){
            if(!m.isFull()){
                m.getClientConnectionThreads().add(clientHandler);
                if (m.getClientConnectionThreads().size()==m.getController().getNumberOfPlayers()) m.setFull(true);
                clientHandler.setController(m.getController());
                clientHandler.setMatch(m);
                clientHandler.sendAnswerMessage(new GameJoinedMsg());
                clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a game\n"));
                return;
            }
        }
        Match match=new Match(server);
        matches.add(match);
        match.createMatch();
        match.getClientConnectionThreads().add(clientHandler);
        clientHandler.setController(match.getController());
        clientHandler.setMatch(match);
        clientHandler.sendAnswerMessage(new GameJoinedMsg());
        clientHandler.sendAnswerMessage(new StringMsg("\nYou joined a brand new game\n"));
    }

    /**
     * ReJoinMatch is a method called when a client wants to rejoin a game from which he had disconnected in the past.
     * If the game does not exist, an ErrorMessage is sent to the client.
     *
     * @param clientHandler the client handler
     * @param code          the code
     * @throws ModelException the model exception
     */
    public synchronized void reJoinMatch(ClientHandler clientHandler, String code) throws ModelException {
        for (String s : unicodeList.keySet()){
            if(s.equals(code)) {
                clientHandler.setUnicode(code);
                clientHandler.setMatch(unicodeList.get(s));
                unicodeList.get(s).reAdd(clientHandler);
                unicodeList.remove(s);
                return;
            }
        }
        clientHandler.sendAnswerMessage(new ErrorMsg("You cannot rejoin an existing game!!!"));

    }

    /**
     * Gets client connection threads.
     *
     * @return the client connection threads
     */
    public Set<ClientHandler> getClientConnectionThreads() {
        return clientConnectionThreads;
    }

    /**
     * Gets unicode list.
     *
     * @return the unicode list
     */
    public Map<String, Match> getUnicodeList() {
        return unicodeList;
    }

    /**
     * Gets matches.
     *
     * @return the matches
     */
    public Set<Match> getMatches() {
        return matches;
    }

    /**
     * Generates a random string of 10 characters. It will characterize the client in an unique way.
     *
     * @return the string
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
