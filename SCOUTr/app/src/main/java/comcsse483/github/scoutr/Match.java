package comcsse483.github.scoutr;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class that represents a match, and contains all relevant information
 * about a particular match and its teams.
 */
public class Match {
    private ArrayList<Team> mBlueTeamList;
    private ArrayList<Team> mRedTeamList;
    private int matchNumber;

    public Match(int matchNumber, int[] blueTeams, int[] redTeams){
        mBlueTeamList = new ArrayList<>();
        mRedTeamList = new ArrayList<>();
        this.matchNumber = matchNumber;

        //Create Team objects and add to their respective lists
        for(int i = 0; i < 3; i++){
            mBlueTeamList.add(new Team(blueTeams[i]));
            mRedTeamList.add(new Team(redTeams[i]));
        }
    }

    /**
     * TODO: Implement once database is set up.
     */
    public void onMatchFinished(){

    }

    public ArrayList<Team> getmBlueTeamList() {
        return mBlueTeamList;
    }

    public ArrayList<Team> getmRedTeamList() {
        return mRedTeamList;
    }

    public int getNumber() {
        return matchNumber;
    }
}
