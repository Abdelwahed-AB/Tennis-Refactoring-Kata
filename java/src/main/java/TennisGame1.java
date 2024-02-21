import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            m_score1 += 1;
        } else if (playerName.equals(player2Name)) {
            m_score2 += 1;
        }
    }

    public String getScore() {

        if (m_score1 == m_score2) {
            return getScoreTie();
        }

        if (m_score1 >= 4 || m_score2 >= 4) {
            return getScoreUnbalanced();
        }

        String scoreStr = Stream.of(m_score1, m_score2)
                .map(item -> switch (item) {
                    case 0 -> "Love";
                    case 1 -> "Fifteen";
                    case 2 -> "Thirty";
                    default -> "Forty";
                }).collect(Collectors.joining("-"));

        return scoreStr;
    }

    public String getScoreTie(){
        String score = switch (m_score1) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };

        return score;
    }

    public String getScoreUnbalanced(){
        String score;
        int scoreDelta = m_score1 - m_score2;

        if (scoreDelta == 1) score = "Advantage " + player1Name ;
        else if (scoreDelta == -1) score = "Advantage " + player2Name;
        else if (scoreDelta >= 2) score = "Win for " + player1Name;
        else score = "Win for " + player2Name;

        return score;
    }
}
