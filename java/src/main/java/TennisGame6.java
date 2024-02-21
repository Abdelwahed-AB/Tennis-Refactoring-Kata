public class TennisGame6 implements TennisGame {
    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    public TennisGame6(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else if (playerName.equals(player2Name))
            player2Score++;
    }

    public String getScore() {
        String result;

        if (player1Score == player2Score) {
            result = tieScore();
        } else if (player1Score >= 4 || player2Score >= 4) {
            result = endGameScore();
        } else {
            result = regularScore();
        }

        return result;
    }

    private String regularScore() {
        String score1 = getScoreRepr(player1Score);
        String score2 = getScoreRepr(player2Score);

        return score1 + "-" + score2;
    }

    private String getScoreRepr(int score) {
        return switch (score) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            default -> "Forty";
        };
    }

    private String endGameScore() {
        if (player1Score - player2Score == 1) {
            return  "Advantage " + player1Name;
        } else if (player1Score - player2Score == -1) {
            return  "Advantage " + player2Name;
        } else if (player1Score - player2Score >= 2) {
            return  "Win for " + player1Name;
        }
        return  "Win for " + player2Name;
    }

    private String tieScore() {
        if (player1Score < 3) return getScoreRepr(player1Score) +"-All";
        return "Deuce";
    }
}
