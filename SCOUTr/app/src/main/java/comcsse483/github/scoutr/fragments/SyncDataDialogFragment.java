package comcsse483.github.scoutr.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import comcsse483.github.scoutr.DBHelper;
import comcsse483.github.scoutr.MainActivity;
import comcsse483.github.scoutr.R;

/**
 * A simple dialog fragment that allows users to choose which method they sync with.
 */
public class SyncDataDialogFragment extends DialogFragment {

    NfcAdapter nfcAdapter;

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
                        nfcAdapter.setNdefPushMessage(createNdefMessage(),getActivity());
                        Toast.makeText(getContext(), "Hold this device against the back of the device to be synced with, and follow the on screen instructions ", Toast.LENGTH_SHORT).show();
                        break;
                    case (R.id.wifiSyncButton):
                        //TODO: Sync over internet
                        Toast.makeText(getContext(), "Syncing via WIFI has not been implemented yet. Select another option.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        nfcAdapter = NfcAdapter.getDefaultAdapter(getContext());
        return builder.create();
    }

    private NdefMessage createNdefMessage() {
        DBHelper db = ((MainActivity) getActivity()).getDBHelper();
        String[] data = db.getDataToSync();
        NdefRecord[] records = new NdefRecord[data.length];
        for (int i = 0; i < data.length; i++) {
            records[i] = NdefRecord.createMime("application/comcsse483.github.scoutr", data[i].getBytes());
        }
        return new NdefMessage(records);

    }
}
