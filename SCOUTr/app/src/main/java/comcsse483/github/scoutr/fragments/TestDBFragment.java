package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.adapters.TestDBAdapter;
import comcsse483.github.scoutr.models.Match;

/**
 * A simple fragment that shows match data pulled from the database.
 */
public class TestDBFragment extends Fragment {
    private static final String MATCH = "MATCH";
    private TestDBAdapter mAdapter;

    public static TestDBFragment newInstance(Match match){
        Bundle args = new Bundle();
        args.putParcelable(MATCH, match);
        TestDBFragment fragment = new TestDBFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_test_db, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        TestDBAdapter adapter = new TestDBAdapter(getActivity());
        view.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }


}
