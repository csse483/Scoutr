package comcsse483.github.scoutr.models;

import android.os.Parcel;
import android.os.Parcelable;

import comcsse483.github.scoutr.Constants;

/**
 * A model for a tournament.
 */
public class Tournament implements Parcelable {
    private String mName;
    private String mEventCode;
    private TeamPosition mTeamPosition;

    //Teams
    private static final String blueOne = "Blue 1";
    private static final String blueTwo = "Blue 2";
    private static final String blueThree = "Blue 3";
    private static final String redOne = "Red 1";
    private static final String redTwo = "Red 2";
    private static final String redThree = "Red 3";

    public Tournament(String name, String eventCode) {
        mName = name;
        mEventCode = eventCode;
    }

    public Tournament(String name, String eventCode, String position){
        mName = name;
        mEventCode = eventCode;
        setTeamPosition(position);

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

    public void setTeamPosition(String str){
        if(str.equals(blueOne)){
            setTeamPosition(0);
        }else if(str.equals(blueTwo)){
            setTeamPosition(1);
        }else if(str.equals(blueThree)){
            setTeamPosition(2);
        }else if(str.equals(redOne)){
            setTeamPosition(3);
        }else if(str.equals(redTwo)){
            setTeamPosition(4);
        }else if(str.equals(redThree)){
            setTeamPosition(5);
        }
    }

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
                pos = blueOne;
                break;
            case BLUE2:
                pos = blueTwo;
                break;
            case BLUE3:
                pos = blueThree;
                break;
            case RED1:
                pos = redOne;
                break;
            case RED2:
                pos = redTwo;
                break;
            case RED3:
                pos = redThree;
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

    public String getmEventCode() {
        return mEventCode;
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
