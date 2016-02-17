package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.adapters.SetUpTournamentAdapter;

/**
 * A simple fragment class that has a recyclerview that displays upcoming matches,
 * pulled from The Blue Alliance. Team positions are selected when a match is selected.
 */
public class SetUpNewTournamentFragment extends Fragment {
    private static String MESSAGE = "MESSAGE";

    public SetUpNewTournamentFragment() {
    }

    public static SetUpNewTournamentFragment newInstance(String message) {
        Bundle args = new Bundle();
        args.putString(MESSAGE, message);
        SetUpNewTournamentFragment fragment = new SetUpNewTournamentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            String message = getArguments().getString(MESSAGE);
            if (!message.equals("")) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_set_up_new_tournament, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        SetUpTournamentAdapter adapter = new SetUpTournamentAdapter(getActivity(), getFragmentManager());
        view.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }


}
