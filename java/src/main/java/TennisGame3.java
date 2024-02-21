
public class TennisGame3 implements TennisGame {

    private int p2Score;
    private int p1Score;
    private String p1Name;
    private String p2Name;

    private final int FOUR = 4;
    private final int THREE = 3;
    private final int MATCH_END_DELTA = 2;


    public TennisGame3(String p1Name, String p2Name) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }

    public String getScore() {
        if (p1Score < FOUR && p2Score < FOUR && p1Score + p2Score != THREE*2) {
            String[] scoreRepresentations = new String[]{"Love", "Fifteen", "Thirty", "Forty"};

            if (p1Score == p2Score) {
                return scoreRepresentations[p1Score] + "-All";
            } else {
                return scoreRepresentations[p1Score] + "-" + scoreRepresentations[p2Score];
            }
        } else {
            if (p1Score == p2Score) {
                return "Deuce";
            }
            String leaderName = p1Score > p2Score ? p1Name : p2Name;

            int scoreDelta = p1Score - p2Score;
            if(Math.abs(scoreDelta) >= MATCH_END_DELTA){
                return "Win for " + leaderName;
            }else{
                return "Advantage " + leaderName;
            }
        }
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(p1Name)) {
            this.p1Score += 1;
        } else if (playerName.equals(p2Name)) {
            this.p2Score += 1;
        }
    }
}
