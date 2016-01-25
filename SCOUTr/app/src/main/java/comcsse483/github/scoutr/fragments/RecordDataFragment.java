package comcsse483.github.scoutr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comcsse483.github.scoutr.R;


public class RecordDataFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_record_data, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
