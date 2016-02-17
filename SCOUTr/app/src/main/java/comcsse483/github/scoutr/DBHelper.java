package comcsse483.github.scoutr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import comcsse483.github.scoutr.DatabaseContract.MatchListEntry;
import comcsse483.github.scoutr.DatabaseContract.TeamMatchEntry;
import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.Match;


/**
 * A helper class for the database.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "ScouterDB.db";
    public static final String TYPE_INT = " INT, ";
    public static final String TABLE_NAME_DATA_CONTAINER = "DATA_TABLE_DATA_CONTAINER";
    public static final String TABLE_NAME_MATCH_LIST = "DATA_TABLE_MATCH_LIST";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_DATA_CONTAINER + " (" +
                        TeamMatchEntry._ID + " INTEGER PRIMARY KEY, " +
                        TeamMatchEntry.COLUMN_NAME_MATCH_NUMBER + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_TEAM_NUMBER + TYPE_INT +

                        TeamMatchEntry.COLUMN_NAME_AUTO_LOW_ATTEMPTED + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_AUTO_LOW_SCORED + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_LOW_ATTEMPTED + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_LOW_SCORED + TYPE_INT +

                        TeamMatchEntry.COLUMN_NAME_AUTO_HIGH_ATTEMPTED + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_AUTO_HIGH_SCORED + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_HIGH_ATTEMPTED + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_HIGH_SCORED + TYPE_INT +

                        TeamMatchEntry.COLUMN_NAME_CROSSED_A1 + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_CROSSED_A2 + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_CROSSED_B1 + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_CROSSED_B2 + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_CROSSED_C1 + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_CROSSED_C2 + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_CROSSED_D1 + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_CROSSED_D2 + TYPE_INT +

                        TeamMatchEntry.COLUMN_NAME_TOWER_CHALLENGED + TYPE_INT +
                        TeamMatchEntry.COLUMN_NAME_TOWER_SCALED + " INT" +
                        ")"
        );

        db.execSQL("CREATE TABLE " + TABLE_NAME_MATCH_LIST + " (" +
                        MatchListEntry._ID + " INTEGER PRIMARY KEY, " +
                        MatchListEntry.COLUMN_NAME_MATCH_NUMBER + TYPE_INT +
                        MatchListEntry.COLUMN_NAME_BLUE_ONE + TYPE_INT +
                        MatchListEntry.COLUMN_NAME_BLUE_TWO + TYPE_INT +
                        MatchListEntry.COLUMN_NAME_BLUE_THREE + TYPE_INT +
                        MatchListEntry.COLUMN_NAME_RED_ONE + TYPE_INT +
                        MatchListEntry.COLUMN_NAME_RED_TWO + TYPE_INT +
                        MatchListEntry.COLUMN_NAME_RED_THREE + " INT" + ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DATA_CONTAINER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MATCH_LIST);
        onCreate(db);
    }

    public String[] getDataToSync() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_DATA_CONTAINER, null, null, null, null, null, null);
        String[] output = new String[]{};
        for (int row = 0; row < cursor.getCount(); row++) {
            String rowString = "";
            for (String column : TeamMatchEntry.getListOfColumns()) {
                rowString = rowString + cursor.getInt(cursor.getColumnIndex(column)) + " ";
            }
            output[row] = rowString;
        }
        return output;
    }

    public boolean insertDataContainer(DataContainer data) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TeamMatchEntry.COLUMN_NAME_AUTO_LOW_ATTEMPTED, data.getAutoLowGoalAttempted());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_AUTO_LOW_SCORED, data.getAutoLowGoalScored());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_LOW_ATTEMPTED, data.getLowGoalAttempted());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_LOW_SCORED, data.getLowGoalScored());

        contentValues.put(TeamMatchEntry.COLUMN_NAME_AUTO_HIGH_ATTEMPTED, data.getAutoHighGoalAttempted());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_AUTO_HIGH_SCORED, data.getAutoHighGoalScored());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_HIGH_ATTEMPTED, data.getHighGoalAttempted());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_HIGH_SCORED, data.getHighGoalScored());

        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_A1, data.getCrossedA1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_A2, data.getCrossedA2());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_B1, data.getCrossedB1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_B2, data.getCrossedB2());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_C1, data.getCrossedC1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_C2, data.getCrossedC2());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_D1, data.getCrossedD1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_D2, data.getCrossedD2());

        int towerChallenged = data.isTowerChallenged() ? 1 : 0;
        int towerScaled = data.isTowerScaled() ? 1 : 0;

        contentValues.put(TeamMatchEntry.COLUMN_NAME_TOWER_CHALLENGED, towerChallenged);
        contentValues.put(TeamMatchEntry.COLUMN_NAME_TOWER_SCALED, towerScaled);
        boolean isSuccessful = db.insert(TABLE_NAME_DATA_CONTAINER, null, contentValues) != -1;
        db.close();
        Log.d(Constants.TAG, "Wrote the following to db: " + data.toString());
        return isSuccessful;

    }

    public boolean insertMatchList(ArrayList<Match> list) {
        SQLiteDatabase db = getWritableDatabase();

        boolean isSuccessful = false;

        for (Match match : list) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(MatchListEntry.COLUMN_NAME_MATCH_NUMBER, match.getMatchNumber());
            contentValues.put(MatchListEntry.COLUMN_NAME_BLUE_ONE, match.getBlue(0));
            contentValues.put(MatchListEntry.COLUMN_NAME_BLUE_TWO, match.getBlue(1));
            contentValues.put(MatchListEntry.COLUMN_NAME_BLUE_THREE, match.getBlue(2));
            contentValues.put(MatchListEntry.COLUMN_NAME_RED_ONE, match.getRed(0));
            contentValues.put(MatchListEntry.COLUMN_NAME_RED_TWO, match.getRed(1));
            contentValues.put(MatchListEntry.COLUMN_NAME_RED_THREE, match.getRed(2));

            isSuccessful = db.insert(TABLE_NAME_MATCH_LIST, null, contentValues) != -1;
            if (isSuccessful) {
                Log.d(Constants.TAG, "Wrote the following to db: " + match.toString());
            } else {
                Log.e(Constants.TAG, "Failed to write match to database");
            }
        }
        db.close();
        return isSuccessful;
    }
}
