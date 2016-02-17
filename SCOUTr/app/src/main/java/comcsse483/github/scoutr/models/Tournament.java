package comcsse483.github.scoutr.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A model for a tournament.
 */
public class Tournament implements Parcelable {
    private String mName;

    public String getmEventCode() {
        return mEventCode;
    }

    private String mEventCode;
    private TeamPosition mTeamPosition;

    public Tournament(String name, String eventCode) {
        mName = name;
        mEventCode = eventCode;

    }

    protected Tournament(Parcel in) {
        mName = in.readString();
    }

    public static final Creator<Tournament> CREATOR = new Creator<Tournament>() {
        @Override
        public Tournament createFromParcel(Parcel in) {
            return new Tournament(in);
        }

        @Override
        public Tournament[] newArray(int size) {
            return new Tournament[size];
        }
    };

    public void setTeamPosition(int i) {
        switch (i) {
            case 0:
                mTeamPosition = TeamPosition.BLUE1;
                break;
            case 1:
                mTeamPosition = TeamPosition.BLUE2;
                break;
            case 2:
                mTeamPosition = TeamPosition.BLUE3;
                break;
            case 3:
                mTeamPosition = TeamPosition.RED1;
                break;
            case 4:
                mTeamPosition = TeamPosition.RED2;
                break;
            case 5:
                mTeamPosition = TeamPosition.RED3;
                break;
        }
    }

    public TeamPosition getTeamPosition() {
        return mTeamPosition;
    }

    public String getTeamPositionString() {
        String pos = "";
        switch (mTeamPosition) {
            case BLUE1:
                pos = "Blue 1";
                break;
            case BLUE2:
                pos = "Blue 2";
                break;
            case BLUE3:
                pos = "Blue 3";
                break;
            case RED1:
                pos = "Red 1";
                break;
            case RED2:
                pos = "Red 2";
                break;
            case RED3:
                pos = "Red 3";
                break;
        }

        return pos;
    }

    public boolean getColor() {
        switch (mTeamPosition) {
            case BLUE1:
            case BLUE2:
            case BLUE3:
                return true;
            case RED1:
            case RED2:
            case RED3:
                return false;
        }

        return false;
    }

    public String getName() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
    }
}
