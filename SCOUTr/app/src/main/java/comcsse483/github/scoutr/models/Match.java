package comcsse483.github.scoutr.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class that represents a match, and contains all relevant information
 * about a particular match and its teams.
 */
public class Match implements Parcelable {
    private int mMatchNumber;

    private int[] mBlueTeams;
    private int[] mRedTeams;

    public Match(int matchNumber) {
        mMatchNumber = matchNumber;
    }

    public Match(int matchNumber, int[] blueTeams, int[] redTeams) {
        mMatchNumber = matchNumber;
        mBlueTeams = blueTeams;
        mRedTeams = redTeams;
    }

    protected Match(Parcel in) {
        mMatchNumber = in.readInt();
        mBlueTeams = in.createIntArray();
        mRedTeams = in.createIntArray();
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

    public int getMatchNumber() {
        return mMatchNumber;
    }

    public int getBlue(int i) {
        return mBlueTeams[i];
    }

    public int getRed(int i) {
        return mRedTeams[i];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mMatchNumber);
        dest.writeIntArray(mBlueTeams);
        dest.writeIntArray(mRedTeams);
    }
}
