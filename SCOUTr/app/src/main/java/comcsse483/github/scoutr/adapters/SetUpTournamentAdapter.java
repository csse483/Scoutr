package comcsse483.github.scoutr.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
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
import comcsse483.github.scoutr.fragments.StatusFragment;
import comcsse483.github.scoutr.models.Tournament;

/**
 * Adapter used by the Set Up Tournament to display upcoming mMatches, pulled from The Blue Alliance API.
 */
public class SetUpTournamentAdapter extends RecyclerView.Adapter<SetUpTournamentAdapter.ViewHolder> {
    private Activity mActivity;
    private ArrayList<Tournament> mItemList;
    private FragmentManager mSupportFragmentManager;
    private Tournament selectedTournament;


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

                        getMatchList(localTournament);
                    }
                }).setNegativeButton(R.string.cancel, null).setTitle(getString(R.string.select_team_fragment_title));

                return builder.create();
            }
        };
        df.show(mActivity.getFragmentManager(), mActivity.getString(R.string.select_team_fragment_title));
    }

    private void launchStatusFragment() {

        FragmentTransaction ft = mSupportFragmentManager.beginTransaction();
        StatusFragment fragment = StatusFragment.newInstance();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    private void getMatchList(Tournament localTournament) {
        selectedTournament = localTournament;
        new GetMatchesTask().execute(localTournament.getmEventCode());
        //launchRecordData(localTournament);
    }


    private void okay(Tournament tournament) {
        //Set the tournament
        ((MainActivity) mActivity).setTournament(tournament);
        launchStatusFragment();
    }

    class GetMatchesTask extends AsyncTask<String, Void, ArrayList<comcsse483.github.scoutr.models.Match>> {
        @Override
        protected ArrayList<comcsse483.github.scoutr.models.Match> doInBackground(String... params) {
            String eventID = params[0];
            APIv2 tbaAPI = APIv2Helper.getAPI();
            List<Match> matchList = tbaAPI.fetchEventMatches(eventID, null);
            ArrayList<comcsse483.github.scoutr.models.Match> matches = new ArrayList<>();
            for (Match match : matchList) {
                int[] blueTeams = {getTeamId(match.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(0).getAsString()),
                        getTeamId(match.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(1).getAsString()),
                                getTeamId(match.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(2).getAsString())};

                int[] redTeams = {getTeamId(match.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(0).getAsString()),
                        getTeamId(match.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(1).getAsString()),
                        getTeamId(match.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(2).getAsString())};
                if (match.getComp_level().equals("qm")) {
                    matches.add(new comcsse483.github.scoutr.models.Match(match.getMatch_number(), blueTeams, redTeams));
                }
            }
            //Store Match Data in database
            ((MainActivity) mActivity).getDBHelper().insertMatchList(matches);
            return matches;
        }

        @Override
        protected void onPostExecute(ArrayList<comcsse483.github.scoutr.models.Match> matches) {
            super.onPostExecute(matches);
            ((MainActivity) mActivity).setMatches(matches);
            okay(selectedTournament);
        }

        private int getTeamId(String str) {
            return Integer.parseInt(str.substring(3));
        }
    }

    class GetEventsTask extends AsyncTask<Integer, Void, List<Event>> {

        @Override
        protected List<Event> doInBackground(Integer... params) {
            int year = params[0];
            APIv2 tbaAPI = APIv2Helper.getAPI();
            List<Event> eventsInYear = tbaAPI.fetchEventsInYear(Constants.YEAR, null);
            return eventsInYear;
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            super.onPostExecute(events);
            for (Event i : events) {
                mItemList.add(new Tournament(i.getName(), i.getKey()));
            }
            notifyDataSetChanged();
        }
    }
}
