public class TennisGame2 implements TennisGame {
    public int p1Point = 0;
    public int p2Point = 0;

    private final String player1Name;
    private final String player2Name;

    private final int FOUR = 4;
    private final int THREE = 3;
    private final int MATCH_END_DELTA = 2;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScoreRepresentation(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }

    public String getScore() {

        if (p1Point == p2Point) { //Tie
            if (p1Point < THREE)
                return getScoreRepresentation(p1Point) + "-All";
            return "Deuce";
        }

        // midgame
        if (p2Point < FOUR && p1Point < FOUR) {
            return getScoreRepresentation(p1Point) + "-" + getScoreRepresentation(p2Point);
        }

        //endgame (delta >= 2)
        int scoreDelta = p1Point - p2Point;

        if(Math.abs(scoreDelta) >= MATCH_END_DELTA){
            return "Win for " + getLeaderName();
        }

        return "Advantage " + getLeaderName();
    }



    public String getLeaderName(){
        return p1Point > p2Point ? player1Name : player2Name;
    }

    //renamed from set to add
    public void addP1Score(int number) {
        p1Point += number;
    }
    public void addP2Score(int number) {
        p2Point += number;
    }
    public void P1Score() {
        p1Point++;
    }
    public void P2Score() {
        p2Point++;
    }
    public void wonPoint(String player) {
        if (player.equals(player1Name)) {
            P1Score();
        } else if (player.equals(player2Name)) {
            P2Score();
        }
    }
}