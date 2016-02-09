package comcsse483.github.scoutr.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import comcsse483.github.scoutr.Constants;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.Utils;
import comcsse483.github.scoutr.fragments.ViewDataFragment;
import comcsse483.github.scoutr.models.Match;
import comcsse483.github.scoutr.models.Team;

/**
 * Recycler view adapter for the View Data fragment. Displays a list of all matches and their results. Pulls match information from the database.
 */
public class ViewDataAdapter extends RecyclerView.Adapter<ViewDataAdapter.ViewHolder> {

    private ArrayList<Match> matches;
    private ViewDataFragment.OnFragmentInteractionListener mListener;

    public ViewDataAdapter(ViewDataFragment.OnFragmentInteractionListener listener) {
        //TODO: Populate Arraylist of matches
        matches = Utils.generateSampleTeamList();
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
        holder.mMatchNumberView.setText("Match\n" + match.getNumber());
        holder.mBlue1.setText(match.getmBlueTeamList().get(0).getTeamNumber() + "");
        holder.mBlue2.setText(match.getmBlueTeamList().get(1).getTeamNumber() + "");
        holder.mBlue3.setText(match.getmBlueTeamList().get(2).getTeamNumber() + "");
        holder.mRed1.setText(match.getmRedTeamList().get(0).getTeamNumber() + "");
        holder.mRed2.setText(match.getmRedTeamList().get(1).getTeamNumber() + "");
        holder.mRed3.setText(match.getmRedTeamList().get(2).getTeamNumber() + "");
        holder.mBlueScore.setText(getAlliancePredictedScore(match.getmBlueTeamList()) + "");
        holder.mRedScore.setText(getAlliancePredictedScore(match.getmRedTeamList()) + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.switchToDetailFragment(match);
            }
        });


    }

    private int getAlliancePredictedScore(ArrayList<Team> teams) {
        //TODO: Get alliance predicted score from database
        Random rnd = new Random();
        return rnd.nextInt(100);
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


