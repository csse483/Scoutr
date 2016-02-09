package comcsse483.github.scoutr.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import comcsse483.github.scoutr.R;

/**
 * A simple dialog fragment that allows users to choose which method they sync with.
 */
public class SyncDataDialogFragment extends DialogFragment {

    public SyncDataDialogFragment() {
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
                    case (R.id.btSyncButton):
                        //TODO: Sync over bluetooth
                        Toast.makeText(getContext(), "Syncing via bluetooth has not been implemented yet. Select another option.", Toast.LENGTH_SHORT).show();
                        break;
                    case (R.id.nfcSyncButton):
                        //TODO: Sync over NFC
                        Toast.makeText(getContext(), "Syncing via NFC has not been implemented yet. Select another option.", Toast.LENGTH_SHORT).show();
                        break;
                    case (R.id.wifiSyncButton):
                        //TODO: Sync over internet
                        Toast.makeText(getContext(), "Syncing via WIFI has not been implemented yet. Select another option.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }
}
