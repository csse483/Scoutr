package comcsse483.github.scoutr.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class that represents a match, and contains all relevant information
 * about a particular match and its teams.
 */
public class Match implements Parcelable {
    private ArrayList<Team> mBlueTeamList;
    private ArrayList<Team> mRedTeamList;
    private int[] blueTeamNumbers;
    private int[] redTeamNumbers;
    private int matchNumber;

    public Match(int matchNumber, int[] blueTeams, int[] redTeams){
        mBlueTeamList = new ArrayList<>();
        mRedTeamList = new ArrayList<>();
        blueTeamNumbers = blueTeams;
        redTeamNumbers = redTeams;
        this.matchNumber = matchNumber;

        //Create Team objects and add to their respective lists
        for(int i = 0; i < 3; i++){
            mBlueTeamList.add(new Team(blueTeams[i]));
            mRedTeamList.add(new Team(redTeams[i]));
        }
    }

    protected Match(Parcel in) {
        blueTeamNumbers = in.createIntArray();
        redTeamNumbers = in.createIntArray();
        matchNumber = in.readInt();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(matchNumber);
        dest.writeIntArray(blueTeamNumbers);
        dest.writeIntArray(redTeamNumbers);
    }
}
