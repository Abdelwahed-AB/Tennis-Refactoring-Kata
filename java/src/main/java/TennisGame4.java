public class TennisGame4 implements TennisGame {

    int serverScore;
    int receiverScore;
    private String server;
    private String receiver;

    public TennisGame4(String player1, String player2) {
        this.server = player1;
        this.receiver = player2;
    }

    @java.lang.Override
    public void wonPoint(String playerName) {
        if (server.equals(playerName))
            this.serverScore += 1;
        else
            this.receiverScore += 1;
    }

    @java.lang.Override
    public String getScore() {
        DefaultResult defaultResult = new DefaultResult(this);
        AdvantageReceiver advantageReceiver = new AdvantageReceiver(this, defaultResult);
        AdvantageServer advantageServer = new AdvantageServer(this, advantageReceiver);
        GameReceiver gameReceiver = new GameReceiver(this, advantageServer);
        GameServer gameServer = new GameServer(this, gameReceiver);

        Deuce deuce = new Deuce(this, gameServer);
        TennisResult result = deuce.getResult();
        return result.format();
    }

    boolean receiverHasAdvantage() {
        return receiverScore >= 4 && (receiverScore - serverScore) == 1;
    }

    boolean serverHasAdvantage() {
        return serverScore >= 4 && (serverScore - receiverScore) == 1;
    }

    boolean receiverHasWon() {
        return receiverScore >= 4 && (receiverScore - serverScore) >= 2;
    }

    boolean serverHasWon() {
        return serverScore >= 4 && (serverScore - receiverScore) >= 2;
    }

    boolean isDeuce() {
        return serverScore >= 3 && receiverScore >= 3 && (serverScore == receiverScore);
    }

    public int getServerScore() {
        return serverScore;
    }

    public void setServerScore(int serverScore) {
        this.serverScore = serverScore;
    }

    public int getReceiverScore() {
        return receiverScore;
    }

    public void setReceiverScore(int receiverScore) {
        this.receiverScore = receiverScore;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}

class TennisResult {
    String serverScore;
    String receiverScore;

    TennisResult(String serverScore, String receiverScore) {
        this.serverScore = serverScore;
        this.receiverScore = receiverScore;
    }

    String format() {
        if (this.receiverScore.isEmpty())
            return this.serverScore;
        if (serverScore.equals(receiverScore))
            return serverScore + "-All";
        return this.serverScore + "-" + this.receiverScore;
    }
}

interface ResultProvider {
    TennisResult getResult();
}

class Deuce implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public Deuce(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.isDeuce())
            return new TennisResult("Deuce", "");
        return this.nextResult.getResult();
    }
}

class GameServer implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public GameServer(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.serverHasWon())
            return new TennisResult("Win for " + game.getServer(), "");
        return this.nextResult.getResult();
    }
}

class GameReceiver implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public GameReceiver(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.receiverHasWon())
            return new TennisResult("Win for " + game.getReceiver(), "");
        return this.nextResult.getResult();
    }
}

class AdvantageServer implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public AdvantageServer(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.serverHasAdvantage())
            return new TennisResult("Advantage " + game.getServer(), "");
        return this.nextResult.getResult();
    }
}

class AdvantageReceiver implements ResultProvider {

    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public AdvantageReceiver(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.receiverHasAdvantage())
            return new TennisResult("Advantage " + game.getReceiver(), "");
        return this.nextResult.getResult();
    }
}

class DefaultResult implements ResultProvider {

    private static final String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    private final TennisGame4 game;

    public DefaultResult(TennisGame4 game) {
        this.game = game;
    }

    @Override
    public TennisResult getResult() {
        return new TennisResult(scores[game.serverScore], scores[game.receiverScore]);
    }
}
