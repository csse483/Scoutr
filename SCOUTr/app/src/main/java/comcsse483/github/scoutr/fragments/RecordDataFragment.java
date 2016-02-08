package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.adapters.RecordDataAdapter;
import comcsse483.github.scoutr.models.DataContainer;


public class RecordDataFragment extends Fragment {
    private RecordDataAdapter mAdapter;
    private DataContainer mDataContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataContainer = new DataContainer();
        mAdapter = new RecordDataAdapter(getContext(), mDataContainer);

        View view = inflater.inflate(R.layout.fragment_record_data, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.record_data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        Button recordDataButton = (Button) view.findViewById(R.id.record_data_button);
        recordDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportData();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    /**
     * Exports the datacontainer.
     * @return
     */
    public DataContainer exportData(){
        mAdapter.recordData();
        return mDataContainer;
    }
}
