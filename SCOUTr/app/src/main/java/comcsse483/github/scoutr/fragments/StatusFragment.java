package comcsse483.github.scoutr.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.plnyyanks.tba.apiv2.models.Match;

import java.util.ArrayList;

import comcsse483.github.scoutr.MainActivity;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.models.Tournament;

/**
 * Fragment that displays state information about the app, tournament, and matches. Serves as the main page of the app.
 */
public class StatusFragment extends Fragment {
    private static String STATUS_FRAGMENT_PREF = "STATUS_FRAGMENT_PREF";
    private static String MATCH_NUMBER = "MATCH_NUMBER";
    private static String TOURNAMENT_NAME = "TOURNAMENT_NAME";
    private static int MODE_PRIVATE = 0;

    private Tournament mTournament;

    private ArrayList<Match> mMatches;
    private int matchCounter;

    private TextView mMatchNumberTextView;

    public StatusFragment() {
        // Required empty public constructor
    }

    public static StatusFragment newInstance() {
        Bundle args = new Bundle();
        StatusFragment fragment = new StatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mTournament = mainActivity.getTournament();
        if (mTournament == null) {
            //TODO: Start set up new tournament fragment
            launchSetUpNewTournament();
        }

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(STATUS_FRAGMENT_PREF, MODE_PRIVATE);
        int num = sharedPreferences.getInt(MATCH_NUMBER, -1);
        String name = sharedPreferences.getString(TOURNAMENT_NAME, "");

        //Make sure we are looking at the same tournament
        if (!name.equals("") && name.equals(mTournament.getName())) {
            if (num != -1) {
                matchCounter = num;
            } else {
                matchCounter = 1;
            }
        }else{
            matchCounter = 1;
        }


        View view = inflater.inflate(R.layout.fragment_status, container, false);

        //Tournament Name
        TextView tournamentName = (TextView) view.findViewById(R.id.tournament_name_text_view);
        tournamentName.setText(mTournament.getName());

        //Match Number
        mMatchNumberTextView = (TextView) view.findViewById(R.id.match_number_text_view);
        mMatchNumberTextView.setText(String.format(getString(R.string.match_number), matchCounter));

        //Position
        TextView position = (TextView) view.findViewById(R.id.position_text_view);
        position.setText(String.format(getString(R.string.position), mTournament.getTeamPositionString()));

        //Set Background Color Based on Station
//        view.findViewById(R.id.status_layout).setBackgroundColor(setBackgroundColor());
        //TODO: Change status page color based on position

        Button recordDataButton = (Button) view.findViewById(R.id.begin_record_data_button);
        recordDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordNewMatch();
            }
        });

        return view;
    }

    private void launchSetUpNewTournament() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        SetUpNewTournamentFragment fragment = SetUpNewTournamentFragment.newInstance();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    /**
     * Called when recording the next match's data
     */
    private void recordNewMatch() {
        incrementMatchCounter();
        launchRecordDataFragment();
    }

    private void incrementMatchCounter() {
        matchCounter++;
        mMatchNumberTextView.setText(String.format(getString(R.string.match_number), matchCounter));
    }

    private void launchRecordDataFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        RecordDataFragment fragment = RecordDataFragment.newInstance();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    private int setBackgroundColor() {
        int color = R.color.background_red;
        if (mTournament.getColor()) {
            color = R.color.background_blue;
        }

        return color;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(STATUS_FRAGMENT_PREF, MODE_PRIVATE).edit();
        editor.putInt(MATCH_NUMBER, matchCounter);
        editor.putString(TOURNAMENT_NAME, mTournament.getName());
        editor.commit();
    }
}
