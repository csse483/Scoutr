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

import java.util.ArrayList;

import comcsse483.github.scoutr.Constants;
import comcsse483.github.scoutr.DBHelper;
import comcsse483.github.scoutr.MainActivity;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.adapters.RecordDataAdapter;
import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.Match;
import comcsse483.github.scoutr.models.Tournament;

/**
 * A fragment that has a recycler view where match data is inputted and recorded.
 */
public class RecordDataFragment extends Fragment implements View.OnClickListener {
    private RecordDataAdapter mAdapter;
    private int mTeamNumber;
    private int mMatchNumber;

    private Tournament mTournament;

    public RecordDataFragment() {
        // Required empty public constructor
    }

    public static RecordDataFragment newInstance(int matchNumber) {
        RecordDataFragment fragment = new RecordDataFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.MATCH_NUMBER, matchNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMatchNumber = getArguments().getInt(Constants.MATCH_NUMBER);
        }

        //TODO: Set Team ID for data container
        mAdapter = new RecordDataAdapter(getContext());

        //Set tournament
        MainActivity mainActivity = (MainActivity) getActivity();
        mTournament = mainActivity.getTournament();

        View view = inflater.inflate(R.layout.fragment_record_data, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.record_data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        Button recordDataButton = (Button) view.findViewById(R.id.record_data_button);
        recordDataButton.setOnClickListener(this);

        TextView teamTextView = (TextView) view.findViewById(R.id.record_data_team_text_view);
        teamTextView.setText(getTeamAndPositionString());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    private String getTeamAndPositionString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Match " + mMatchNumber + ": ");
        String pos = "";
        ArrayList<Match> matches = ((MainActivity) getActivity()).getMatches();
        for (Match match : matches) {
            if (match.getMatchNumber() == mMatchNumber) {
                switch (mTournament.getTeamPosition()) {
                    case BLUE1:
                        pos = match.getBlue(0) + "";
                        break;
                    case BLUE2:
                        pos = match.getBlue(1) + "";
                        break;
                    case BLUE3:
                        pos = match.getBlue(2) + "";
                        break;
                    case RED1:
                        pos = match.getRed(0) + "";
                        break;
                    case RED2:
                        pos = match.getRed(1) + "";
                        break;
                    case RED3:
                        pos = match.getRed(2) + "";
                        break;
                }
            }
        }
//        List<Match> mMatches = ((MainActivity) getActivity()).getmMatches();
//        for (Match i : mMatches) {
//            if (i.getComp_level().equals("qm") && i.getMatch_number() == mMatchNumber) {
//                //DONE: Grab team number from alliances JsonObject
//                switch (mTournament.getTeamPosition()) {
//                    case BLUE1:
//                        pos = i.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(0).getAsString();
//                        break;
//                    case BLUE2:
//                        pos = i.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(1).getAsString();
//                        break;
//                    case BLUE3:
//                        pos = i.getAlliances().get("blue").getAsJsonObject().get("teams").getAsJsonArray().get(2).getAsString();
//                        break;
//                    case RED1:
//                        pos = i.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(0).getAsString();
//                        break;
//                    case RED2:
//                        pos = i.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(1).getAsString();
//                        break;
//                    case RED3:
//                        pos = i.getAlliances().get("red").getAsJsonObject().get("teams").getAsJsonArray().get(2).getAsString();
//                        break;
//                }
//            }
//        }
        mTeamNumber = Integer.parseInt(pos);
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
        dataContainer.setmTeamId(mTeamNumber);
        return dataContainer;
    }

    private void launchStatusFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        StatusFragment fragment = StatusFragment.newInstance();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();

    }

    @Override
    public void onClick(View v) {
        //DONE: Write the data container to the database
        MainActivity activity = (MainActivity) getActivity();
        DBHelper dbHelper = activity.getDBHelper();
        dbHelper.insertDataContainer(exportData());
        launchStatusFragment();
    }
}
