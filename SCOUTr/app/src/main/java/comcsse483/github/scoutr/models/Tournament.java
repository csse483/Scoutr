package comcsse483.github.scoutr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.okhttp.OkHttpClient;

import comcsse483.github.scoutr.TeamPosition;

/**
 * Created by schmitml on 1/23/16.
 */
public class Tournament implements Parcelable{
    private String mName;
    private String mEventCode;
    private TeamPosition mTeamPosition;

    public Tournament(String name, String eventCode){
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

    public void setTeamPosition(int i){
        switch (i){
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

    public String getName(){
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
