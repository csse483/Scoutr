package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.plnyyanks.tba.apiv2.models.Match;

import org.json.JSONObject;

import java.util.List;

import comcsse483.github.scoutr.DBHelper;
import comcsse483.github.scoutr.MainActivity;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.adapters.RecordDataAdapter;
import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.TeamPosition;
import comcsse483.github.scoutr.models.Tournament;

/**
 * A fragment that has a recycler view where match data is inputted and recorded.
 */
public class RecordDataFragment extends Fragment implements View.OnClickListener {
    private RecordDataAdapter mAdapter;


    private Tournament mTournament;
    private static final String TOURNAMENT = "TOURNAMENT";
    private int teamNumber;

    public RecordDataFragment() {
        // Required empty public constructor
    }

    public static RecordDataFragment newInstance(Tournament tournamentToDisplay) {
        RecordDataFragment fragment = new RecordDataFragment();
        Bundle args = new Bundle();
        args.putParcelable(TOURNAMENT, tournamentToDisplay);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTournament = (Tournament) getArguments().get(TOURNAMENT);
        }
        if(mTournament == null){
            launchSetUpTournamentFragment(getString(R.string.no_available_tournament));
            return inflater.inflate(R.layout.fragment_record_data, container, false);
        }
        //TODO: Set Team ID for data container
        mAdapter = new RecordDataAdapter(getContext());

        View view = inflater.inflate(R.layout.fragment_record_data, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.record_data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        Button recordDataButton = (Button) view.findViewById(R.id.record_data_button);
        recordDataButton.setOnClickListener(this);

        TextView teamTextView = (TextView) view.findViewById(R.id.record_data_team_text_view);
        //TODO: Change argument to incrementing match number
        teamTextView.setText(getTeamAndPositionString(1));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    private String getTeamAndPositionString(int matchNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Match " + matchNumber + ": ");
        String pos = "";
        List<Match> matches = ((MainActivity) getActivity()).getMatches();
        for (Match i : matches) {
            if (i.getComp_level().equals("qm") && i.getMatch_number() == matchNumber) {
                //DONE: Grab team number from alliances JsonObject
                switch (mTournament.getTeamPosition()) {
                    case BLUE1:
                        pos = i.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(0).getAsString();
                        break;
                    case BLUE2:
                        pos = i.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(1).getAsString();
                        break;
                    case BLUE3:
                        pos = i.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(2).getAsString();
                        break;
                    case RED1:
                        pos = i.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(0).getAsString();
                        break;
                    case RED2:
                        pos = i.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(1).getAsString();
                        break;
                    case RED3:
                        pos = i.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(2).getAsString();
                        break;
                }
            }
        }
        teamNumber = Integer.parseInt(pos.substring(3));
        sb.append(pos);
        return sb.toString();

    }

    /**
     * Exports a datacontainer with the recorded data.
     *
     * @return DataContainer
     */
    public DataContainer exportData() {
        DataContainer dataContainer = new DataContainer();
        dataContainer = mAdapter.recordData(dataContainer);
        dataContainer.setmTeamId(teamNumber);
        return dataContainer;
    }

    private void launchSetUpTournamentFragment(String message) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        SetUpNewTournamentFragment fragment = SetUpNewTournamentFragment.newInstance(message);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();

    }

    @Override
    public void onClick(View v) {
        //DONE: Write the data container to the database
        MainActivity activity = (MainActivity) getActivity();
        DBHelper dbHelper = activity.getDBHelper();
        dbHelper.insertData(exportData());
        launchSetUpTournamentFragment(getString(R.string.select_new_tournament));
    }
}
