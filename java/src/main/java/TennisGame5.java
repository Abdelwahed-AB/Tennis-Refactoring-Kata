import java.util.HashMap;
import java.util.Map;

public class TennisGame5 implements TennisGame {

    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    private final float MATCH_END_DELTA = 2;

    public TennisGame5(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else if (playerName.equals(player2Name))
            player2Score++;
        else
            throw new IllegalArgumentException("Invalid player name.");
    }

    @Override
    public String getScore() {
        if (player1Score > 4 || player2Score > 4) {
            player1Score--;
            player2Score--;
        }

        return getScoreStr();
    }

    private String getScoreStr(){
        if(player1Score < 0 || player2Score < 0){
            throw new IllegalArgumentException("Invalid score.");
        }

        int scoreDelta = player1Score - player2Score;
        if(player1Score <= 3 && player2Score <= 3){
            if(scoreDelta == 0) {
                if (player1Score == 3)
                    return "Deuce";
                return getScoreRepresentation(player2Score) + "-All";
            }

            return getScoreRepresentation(player1Score) + "-" + getScoreRepresentation(player2Score);
        }else{
            if(scoreDelta == 0)
                return "Deuce";
            if (scoreDelta >= MATCH_END_DELTA)
                return "Win for " + player1Name;
            if (scoreDelta <= -MATCH_END_DELTA)
                return "Win for " + player2Name;
            if (scoreDelta == 1)
                return "Advantage " + player1Name;
            if (scoreDelta == -1)
                return "Advantage " + player2Name;
        }

        return "";
    }

    public String getScoreRepresentation(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            default:
                return "Forty";
        }
    }
}
