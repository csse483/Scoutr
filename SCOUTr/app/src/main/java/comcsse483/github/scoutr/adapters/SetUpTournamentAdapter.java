package comcsse483.github.scoutr.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plnyyanks.tba.apiv2.APIv2Helper;
import com.plnyyanks.tba.apiv2.interfaces.APIv2;
import com.plnyyanks.tba.apiv2.models.Event;
import com.plnyyanks.tba.apiv2.models.Match;

import java.util.ArrayList;
import java.util.List;

import comcsse483.github.scoutr.Constants;
import comcsse483.github.scoutr.MainActivity;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.fragments.RecordDataFragment;
import comcsse483.github.scoutr.fragments.SetUpNewTournamentFragment;
import comcsse483.github.scoutr.models.Tournament;

/**
 * Adapter used by the Set Up Tournament to display upcoming matches, pulled from The Blue Alliance API.
 */
public class SetUpTournamentAdapter extends RecyclerView.Adapter<SetUpTournamentAdapter.ViewHolder> {
    private Activity mActivity;
    private ArrayList<Tournament> mItemList;
    private FragmentManager mSupportFragmentManager;


    public SetUpTournamentAdapter(Activity activity, FragmentManager man) {
        mActivity = activity;
        mItemList = new ArrayList<>();
        mSupportFragmentManager = man;

        //Grab tournament list using TBA API
        new GetEventsTask().execute(Constants.YEAR);

    }

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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

        public void onBind(Tournament tournament) {
            mTournament = tournament;
            mItemTitle.setText(mTournament.getName());
        }


    }

    /**
     * Single choice dialog to choose which team data is to be recorded.
     *
     * @param tournament
     */
    private void onTournamentSelected(Tournament tournament) {
        final Tournament localTournament = tournament;
        tournament.setTeamPosition(0);
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle b) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setSingleChoiceItems(R.array.team_array, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        localTournament.setTeamPosition(which);
                    }
                }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        launchRecordData(localTournament);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancel();
                    }
                }).setTitle(getString(R.string.select_team_fragment_title));

                return builder.create();
            }
        };
        df.show(mActivity.getFragmentManager(), mActivity.getString(R.string.select_team_fragment_title));
    }

    private void launchRecordData(Tournament tournament) {
        MainActivity.hasTournament = true;
        FragmentTransaction ft = mSupportFragmentManager.beginTransaction();
        RecordDataFragment fragment = RecordDataFragment.newInstance(tournament);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    private void cancel() {
        MainActivity.hasTournament = false;

        FragmentTransaction ft = mSupportFragmentManager.beginTransaction();
        SetUpNewTournamentFragment fragment = new SetUpNewTournamentFragment();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    class GetMatchesTask extends AsyncTask<String, Void, List<Match>> {

        @Override
        protected List<Match> doInBackground(String... params) {
            String eventID = params[0];
            APIv2 tbaAPI = APIv2Helper.getAPI();
            List<Match> matches = tbaAPI.fetchEventMatches(eventID, null);
            return matches;
        }

        @Override
        protected void onPostExecute(List<Match> matches) {
            super.onPostExecute(matches);
            ((MainActivity) mActivity).setMatches(matches);
        }
    }
    class GetEventsTask extends AsyncTask<Integer, Void, List<Event>> {

        @Override
        protected List<Event> doInBackground(Integer... params) {
            int year = params[0];
            APIv2 tbaAPI = APIv2Helper.getAPI();
            List<Event> eventsInYear = tbaAPI.fetchEventsInYear(2016, null);
            return eventsInYear;
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            super.onPostExecute(events);
            for (Event i : events) {
                mItemList.add(new Tournament(i.getName(), i.getEvent_code()));
            }
            notifyDataSetChanged();
        }
    }
}
