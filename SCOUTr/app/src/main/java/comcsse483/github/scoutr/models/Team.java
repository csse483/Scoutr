package comcsse483.github.scoutr.models;

/**
 * Object that represents a team for the purposes of recording statistics during matches.
 */
public class Team {
    private int mTeamNumber;
    private DataContainer mData;

    public Team(int teamNumber) {
        mTeamNumber = teamNumber;
        mData = new DataContainer();
    }
}
