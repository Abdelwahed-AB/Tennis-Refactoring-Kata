
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
        } else if(playerName.equals(player2Name)) {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String score = "";

        if (m_score1 == m_score2) {
            score = switch (m_score1) {
                case 0 -> "Love-All";
                case 1 -> "Fifteen-All";
                case 2 -> "Thirty-All";
                default -> "Deuce";
            };

            return score;
        }

        if (m_score1 >= 4 || m_score2 >= 4) {
            int scoreDelta = m_score1 - m_score2;

            if (scoreDelta == 1) score = "Advantage player1";
            else if (scoreDelta == -1) score = "Advantage player2";
            else if (scoreDelta >= 2) score = "Win for player1";
            else score = "Win for player2";

            return score;
        }

        int tempScore = m_score1;
        for (int i = 1; i < 3; i++) {
            if(i > 1) {
                score += "-";
            }

            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }

            tempScore = m_score2;
        }
        return score;
    }
}
