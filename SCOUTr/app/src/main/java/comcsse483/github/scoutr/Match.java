package comcsse483.github.scoutr;

import java.util.ArrayList;

/**
 * Created by schmitml on 1/18/16.
 */
public class Match {
    private ArrayList<Team> mBlueTeamList;
    private ArrayList<Team> mRedTeamList;

    public Match(int[] blueTeams, int[] redTeams){
        mBlueTeamList = new ArrayList<>();
        mRedTeamList = new ArrayList<>();

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


}
