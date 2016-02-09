package comcsse483.github.scoutr.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plnyyanks.tba.apiv2.APIv2Helper;
import com.plnyyanks.tba.apiv2.interfaces.APIv2;
import com.plnyyanks.tba.apiv2.models.Event;

import java.util.ArrayList;
import java.util.List;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.models.Tournament;

/**
 * Created by schmitml on 1/23/16.
 */
public class TournamentAdapter extends RecyclerView.Adapter<TournamentAdapter.ViewHolder> {
    private Activity mActivity;
    private ArrayList<Tournament> mItemList;
    private int mSelectedPosition;



    public TournamentAdapter(Activity activity){
        mActivity = activity;
        mItemList = new ArrayList<>();

        //Grab tournament list using TBA API
        APIv2 tbaAPI = APIv2Helper.getAPI();
        List<Event> eventsInYear = tbaAPI.fetchEventsInYear(2016, null);
        for (Event i : eventsInYear) {
            mItemList.add(new Tournament(i.getName(), i.getEvent_code()));
        }
        notifyDataSetChanged();
    }

//    private void test(){
//        for(int i = 1; i < 11; i++)
//            mItemList.add(new Tournament("Tournament: " + i));
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mItemTitle;
        Tournament mTournament;

        public ViewHolder(View itemView) {
            super(itemView);

            mItemTitle = (TextView) itemView.findViewById(R.id.item_title);
            mItemTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTournamentSelected(mTournament);
        }

        public void onBind(Tournament tournament){
            mTournament = tournament;
            mItemTitle.setText(mTournament.getName());
        }


    }

    private void onTournamentSelected(final Tournament tournament){
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle b) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setSingleChoiceItems(R.array.team_array, 0, null).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        tournament.setTeamPosition(position);
                        launchRecordData(tournament);
                    }
                }).setNegativeButton(R.string.cancel, null).setTitle(getString(R.string.select_team_fragment_title));

                return builder.create();
            }
        };
        df.show(mActivity.getFragmentManager(), mActivity.getString(R.string.select_team_fragment_title));
    }

    private void launchRecordData(Tournament tournament){
//        FragmentManager fragmentManager = mActivity.getFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.fragment_container, RecordDataFragment.newInstance(), null);
//        ft.commit();


//        Intent recordDataIntent = new Intent(mActivity.getApplicationContext(), RecordDataFragment.class);
//        recordDataIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        recordDataIntent.putExtra(Constants.KEY_TOURNAMENT, tournament);
//        mActivity.startActivity(recordDataIntent);
    }
}
