package comcsse483.github.scoutr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.plnyyanks.tba.apiv2.models.Team;

import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.Tournament;
import comcsse483.github.scoutr.DatabaseContract.TeamMatchEntry;

/**
 * A helper class for the database.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "ScouterDB.db";
    public static final String TYPE_INT = " INT, ";
    public static final String TABLE_NAME = "DATA_TABLE";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String[] getDataToSync() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        String[] output = new String[cursor.getCount()];
        for (int row = 0; row < cursor.getCount(); row++){
            cursor.moveToPosition(row);
            String rowString = "";
            for (String column : TeamMatchEntry.getListOfColumns()){
                int columnIndex = cursor.getColumnIndex(column);
                int data = cursor.getInt(columnIndex);
                rowString = rowString + data + " ";
            }
            output[row] = rowString;
        }
        cursor.close();
        db.close();
        Log.d("NFC", output[1]);
        return output;
    }

    public boolean insertData (DataContainer data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TeamMatchEntry.COLUMN_NAME_TEAM_NUMBER, data.getmTeamId());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_MATCH_NUMBER, data.getMatchNumber());

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
        boolean isSuccessful = db.insert(TABLE_NAME, null, contentValues) != -1;
        db.close();
        Log.d("DTB", "Wrote the following to db: " + data.toString());
        return isSuccessful;
    }
}
