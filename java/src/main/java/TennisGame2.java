public class TennisGame2 implements TennisGame {
    public int p1Point = 0;
    public int p2Point = 0;

    public String p1Res = "";
    public String p2Res = "";
    private final String player1Name;
    private final String player2Name;

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
            if (p1Point < 3)
                return getScoreRepresentation(p1Point) + "-All";
            return "Deuce";
        }

        // midgame
        if (p2Point < 4 && p1Point < 4) {
            return getScoreRepresentation(p1Point) + "-" + getScoreRepresentation(p2Point);
        }

        //endgame (delta >= 2)
        int scoreDelta = p1Point - p2Point;

        if(Math.abs(scoreDelta) >= 2){
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