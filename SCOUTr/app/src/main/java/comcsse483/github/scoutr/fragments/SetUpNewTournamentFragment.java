package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.SlidingTabLayout;
import comcsse483.github.scoutr.TabsPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetUpNewTournamentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetUpNewTournamentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetUpNewTournamentFragment extends Fragment {
    private SlidingTabLayout mSlidingTabLayout;

    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set_up_new_tournament, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setUpPager(view);
    }

    void setUpPager(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TabsPagerAdapter(getActivity()));
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }


}
