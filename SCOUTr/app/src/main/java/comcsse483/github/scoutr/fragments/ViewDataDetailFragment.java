package comcsse483.github.scoutr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.models.Match;


/**
 * A simple fragment that shows match information detail.
 */
public class ViewDataDetailFragment extends Fragment {
    private static final String MATCH = "mMatch";
    private Match mMatch;


    public ViewDataDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param matchToDisplay Parameter 1.
     * @return A new instance of fragment ViewDataDetailFragment.
     */
    public static ViewDataDetailFragment newInstance(Match matchToDisplay) {
        ViewDataDetailFragment fragment = new ViewDataDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(MATCH,matchToDisplay);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMatch = (Match) getArguments().get(MATCH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_data_detail, container, false);
    }

}
