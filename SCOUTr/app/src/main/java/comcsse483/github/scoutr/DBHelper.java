package comcsse483.github.scoutr;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.Tournament;
import comcsse483.github.scoutr.DatabaseContract.TeamMatchEntry;

/**
 * Created by yarlagrt on 2/1/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ScouterDB.db";
    private static final String TYPE_INT = " INT, ";
    private final String TABLE_NAME;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, Tournament tournament) {
        super(context, DB_NAME, null, 1);
        this.TABLE_NAME = tournament.getName();
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
                TeamMatchEntry.COLUMN_NAME_TOWER_SCALED + TYPE_INT +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (DataContainer data) {
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
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_A2, data.getCrossedA1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_B1, data.getCrossedA1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_B2, data.getCrossedA1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_C1, data.getCrossedA1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_C2, data.getCrossedA1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_D1, data.getCrossedA1());
        contentValues.put(TeamMatchEntry.COLUMN_NAME_CROSSED_D2, data.getCrossedA1());

        int towerChallenged = data.isTowerChallenged() ? 1 : 0;
        int towerScaled = data.isTowerScaled() ? 1 : 0;

        contentValues.put(TeamMatchEntry.COLUMN_NAME_TOWER_CHALLENGED, towerChallenged);
        contentValues.put(TeamMatchEntry.COLUMN_NAME_TOWER_SCALED, towerScaled);

        return db.insert(TABLE_NAME, null, contentValues) != -1;

    }
}
