package comcsse483.github.scoutr.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import comcsse483.github.scoutr.R;

public class SyncDataFragment extends DialogFragment {

    public SyncDataFragment()  {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.sync_dialog_title);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_sync_data, null, false);
        builder.setView(view);
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.syncRadioGroup);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case(R.id.btSyncButton):
                        //TODO: Sync over bluetooth
                        break;
                    case(R.id.nfcSyncButton):
                        //TODO: Sync over NFC
                        break;
                    case(R.id.wifiSyncButton):
                        //TODO: Sync over internet
                        break;
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }
}
