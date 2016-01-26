package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.adapters.RecordDataAdapter;


public class RecordDataFragment extends Fragment {
    private RecordDataAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        mAdapter = new RecordDataAdapter();
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_record_data, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        view.setHasFixedSize(true);
        view.setAdapter(mAdapter);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
