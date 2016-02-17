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

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.adapters.RecordDataAdapter;
import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.Tournament;

/**
 * A fragment that has a recycler view where match data is inputted and recorded.
 */
public class RecordDataFragment extends Fragment implements View.OnClickListener {
    private RecordDataAdapter mAdapter;
    private DataContainer mDataContainer;

    private Tournament mTournament;
    private static final String TOURNAMENT = "TOURNAMENT";

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

        //TODO: Set Team ID for data container
        mDataContainer = new DataContainer();
        mAdapter = new RecordDataAdapter(getContext());

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
        sb.append(mTournament.getName() + ": ");
        String pos = "";
        switch (mTournament.getTeamPosition()) {
            case BLUE1:
                pos = "Blue 1";
                break;
            case BLUE2:
                pos = "Blue 2";
                break;
            case BLUE3:
                pos = "Blue 3";
                break;
            case RED1:
                pos = "Red 1";
                break;
            case RED2:
                pos = "Red 2";
                break;
            case RED3:
                pos = "Red 3";
                break;
        }
        sb.append(pos);
        return sb.toString();

    }

    /**
     * Exports the datacontainer.
     *
     * @return DataContainer
     */
    public DataContainer exportData() {
        mDataContainer = mAdapter.recordData(mDataContainer);
        return mDataContainer;
    }

    private void launchSetUpTournamentFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        SetUpNewTournamentFragment fragment = SetUpNewTournamentFragment.newInstance(getString(R.string.select_new_tournament));
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();

    }

    @Override
    public void onClick(View v) {
        //TODO: Write the data container to the database
        exportData();

        launchSetUpTournamentFragment();
    }
}
