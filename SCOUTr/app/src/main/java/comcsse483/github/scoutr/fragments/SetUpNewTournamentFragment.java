package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.TournamentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetUpNewTournamentFragment extends Fragment {

    private TournamentAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_set_up_new_tournament, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        TournamentAdapter adapter = new TournamentAdapter(getActivity());
        view.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }


}
