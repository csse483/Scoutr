package comcsse483.github.scoutr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import comcsse483.github.scoutr.Constants;
import comcsse483.github.scoutr.MainActivity;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.fragments.ViewDataFragment;
import comcsse483.github.scoutr.models.Match;

/**
 * Recycler view adapter for the View Data fragment. Displays a list of all mMatches and their results. Pulls match information from the database.
 */
public class ViewDataAdapter extends RecyclerView.Adapter<ViewDataAdapter.ViewHolder> {

    private ArrayList<Match> matches;
    private ViewDataFragment.OnFragmentInteractionListener mListener;

    public ViewDataAdapter(ViewDataFragment.OnFragmentInteractionListener listener, Context mActivity) {
        matches = ((MainActivity) mActivity).getMatches();

        Collections.sort(matches, new Comparator<Match>() {
            @Override
            public int compare(Match lhs, Match rhs) {
                if (lhs.getMatchNumber() > rhs.getMatchNumber()) {
                    return 1;
                }
                else if (lhs.getMatchNumber() < rhs.getMatchNumber()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
        mListener = listener;
        Log.d(Constants.TAG, "Match list adapter created");
    }

    @Override
    public ViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewDataAdapter.ViewHolder holder, int position) {
        final Match match = getMatch(position);
        holder.mMatchNumberView.setText("Match\n" + match.getMatchNumber());
        holder.mBlue1.setText(match.getBlue(0) + "");
        holder.mBlue2.setText(match.getBlue(1) + "");
        holder.mBlue3.setText(match.getBlue(2) + "");
        holder.mRed1.setText(match.getRed(0) + "");
        holder.mRed2.setText(match.getRed(1) + "");
        holder.mRed3.setText(match.getRed(2) + "");
        holder.mBlueScore.setText(getAlliancePredictedScore(match.getBlueTeams()) + "");
        holder.mRedScore.setText(getAlliancePredictedScore(match.getRedTeams()) + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.switchToDetailFragment(match);
            }
        });


    }

    private int getAlliancePredictedScore(int[] teams) {
        //TODO: Get alliance predicted score from database
        Random rnd = new Random();
        return rnd.nextInt(300);
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public Match getMatch(int matchNumber) {
        return matches.get(matchNumber);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mMatchNumberView, mRed1, mRed2, mRed3, mBlue1, mBlue2, mBlue3, mBlueScore, mRedScore;

        public ViewHolder(View itemView) {
            super(itemView);
            mMatchNumberView = (TextView) itemView.findViewById(R.id.match_title);
            mBlue1 = (TextView) itemView.findViewById(R.id.blue1);
            mBlue2 = (TextView) itemView.findViewById(R.id.blue2);
            mBlue3 = (TextView) itemView.findViewById(R.id.blue3);
            mRed1 = (TextView) itemView.findViewById(R.id.red1);
            mRed2 = (TextView) itemView.findViewById(R.id.red2);
            mRed3 = (TextView) itemView.findViewById(R.id.red3);
            mBlueScore = (TextView) itemView.findViewById(R.id.blue_score);
            mRedScore = (TextView) itemView.findViewById(R.id.red_score);

        }

    }
}


