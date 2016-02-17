package comcsse483.github.scoutr.models;

/**
 * Object that represents a team for the purposes of recording statistics during mMatches.
 */
public class Team {
    private int mMatchNumber;

    private int[] mBlueTeams;
    private int[] mRedTeams;

    public Team(int teamNumber) {
        mMatchNumber = teamNumber;
    }

    public Team(int teamNumber, int[] blueTeams, int[] redTeams) {
        mMatchNumber = teamNumber;
        mBlueTeams = blueTeams;
        mRedTeams = redTeams;
    }

    public int getTeamNumber() {
        return mMatchNumber;
    }

    public int[] getBlueTeams() {
        return mBlueTeams;
    }

    public void setBlueTeams(int[] mBlueTeams) {
        this.mBlueTeams = mBlueTeams;
    }

    public int[] getRedTeams() {
        return mRedTeams;
    }

    public void setRedTeams(int[] mRedTeams) {
        this.mRedTeams = mRedTeams;
    }

}
