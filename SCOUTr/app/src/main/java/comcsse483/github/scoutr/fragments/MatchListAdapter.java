package comcsse483.github.scoutr.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import comcsse483.github.scoutr.Match;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.Team;

/**
 * Created by yarlagrt on 1/25/2016.
 */
public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {

    private ArrayList<Match> matches;
    private ViewDataFragment.OnFragmentInteractionListener mListener;

    public MatchListAdapter (ViewDataFragment.OnFragmentInteractionListener listener) {
        //TODO: Populate Arraylist of matches
        mListener = listener;
    }

    @Override
    public MatchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchListAdapter.ViewHolder holder, int position) {
        final Match match = getMatch(position);
        holder.mMatchNumberView.setText(match.getNumber() + "");
        holder.mBlue1.setText(match.getmBlueTeamList().get(1) + "");
        holder.mBlue2.setText(match.getmBlueTeamList().get(2) + "");
        holder.mBlue3.setText(match.getmBlueTeamList().get(3) + "");
        holder.mRed1.setText(match.getmRedTeamList().get(1) + "");
        holder.mRed2.setText(match.getmRedTeamList().get(2) + "");
        holder.mRed3.setText(match.getmRedTeamList().get(3) + "");
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
        //TODO
        return 0;
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
            mBlueScore = (TextView)itemView.findViewById(R.id.blue_score);
            mRedScore = (TextView) itemView.findViewById(R.id.red_score);

        }

    }
}

