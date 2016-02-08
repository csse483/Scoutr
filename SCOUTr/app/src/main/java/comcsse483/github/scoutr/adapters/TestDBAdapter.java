package comcsse483.github.scoutr.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import comcsse483.github.scoutr.DBHelper;
import comcsse483.github.scoutr.DatabaseContract;
import comcsse483.github.scoutr.MainActivity;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.models.TestDataContainer;
import comcsse483.github.scoutr.DatabaseContract.TeamMatchEntry;

/**
 * Created by yarlagrt on 2/8/2016.
 */
public class TestDBAdapter extends RecyclerView.Adapter<TestDBAdapter.ViewHolder>{
    private ArrayList<TestDataContainer> mItemList = new ArrayList<>();

    public TestDBAdapter(Context context) {
        //Pull data from database and add to arrayList
        DBHelper mDBHelper = ((MainActivity)context).mDBHelper;
        SQLiteDatabase mDB = mDBHelper.getReadableDatabase();

        Cursor mCursor = mDB.query(mDBHelper.TABLE_NAME, null, null, null, null, null, null);

        for (int i = 0; i < mCursor.getCount(); i++) {
            mCursor.moveToPosition(i);
            TestDataContainer newContainer = new TestDataContainer();

            //Shot percentage
            int shotsAttempted = mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_AUTO_HIGH_ATTEMPTED)) +
                    mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_AUTO_LOW_ATTEMPTED)) +
                    mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_LOW_ATTEMPTED)) +
                    mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_HIGH_ATTEMPTED));

            int shotsMade = mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_AUTO_HIGH_SCORED)) +
                    mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_AUTO_LOW_SCORED)) +
                    mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_LOW_SCORED)) +
                    mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_HIGH_SCORED));

            newContainer.setShotPercetage(shotsMade/shotsAttempted);

            //Number of Crossings
            int numCrossings = 0;
            for (String defense : TeamMatchEntry.getListOfDefenses()) {
                numCrossings += mCursor.getInt(mCursor.getColumnIndex(defense));
            }
            newContainer.setNumCrossings(numCrossings);

            //Tower data
            newContainer.setTowerChallenged(mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_TOWER_CHALLENGED)) == 1);
            newContainer.setTowerScaled(mCursor.getInt(mCursor.getColumnIndex(TeamMatchEntry.COLUMN_NAME_TOWER_SCALED)) == 1);

            mItemList.add(newContainer);
        }
        mCursor.close();
    }

    @Override
    public TestDBAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestDBAdapter.ViewHolder holder, int position) {
            holder.onBind(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView shotPercentageTextView;
        TextView crossingsTextView;
        TextView towerScaledTextView;
        TextView towerChallengedTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            shotPercentageTextView = (TextView) itemView.findViewById(R.id.shot_percetage_text_view);
            crossingsTextView = (TextView) itemView.findViewById(R.id.defenses_crossed_text_view);
            towerScaledTextView = (TextView) itemView.findViewById(R.id.tower_scaled_text_view);
            towerChallengedTextView = (TextView) itemView.findViewById(R.id.tower_challenged_text_view);
        }

        public void onBind(TestDataContainer testDataContainer) {
            shotPercentageTextView.setText(Double.toString(testDataContainer.getShotPercetage()));
            crossingsTextView.setText(Integer.toString(testDataContainer.getNumCrossings()));
            towerChallengedTextView.setText(Boolean.toString(testDataContainer.isTowerChallenged()));
            towerScaledTextView.setText(Boolean.toString(testDataContainer.isTowerScaled()));
        }
    }
}
