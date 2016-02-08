package comcsse483.github.scoutr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.DataEntry;

/**
 * Created by schmitml on 1/26/16.
 */
public class RecordDataAdapter extends RecyclerView.Adapter<RecordDataAdapter.ViewHolder> {
    private ArrayList<DataEntry> mItemList;
    private Context mContext;

    public RecordDataAdapter(Context context) {
        mContext = context;
        mItemList = new ArrayList<>();
        setUpDataEntries();
    }

    private void setUpDataEntries() {
        for (int pos = 0; pos < DataContainer.DATA_FIELDS.length; pos++) {
            if (DataContainer.DATA_FIELDS[pos].equals("int")) {
                mItemList.add(new DataEntry<Integer>(DataContainer.DATA_NAMES[pos], Integer.class));
            } else if (DataContainer.DATA_FIELDS[pos].equals("boolean")) {
                mItemList.add(new DataEntry<Boolean>(DataContainer.DATA_NAMES[pos], Boolean.class));

            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.data_entry_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataEntryTextView;
        EditText dataEntryEditText;
        CheckBox dataEntryCheckBox;
        DataEntry mDataEntry;

        public ViewHolder(View itemView) {
            super(itemView);

            dataEntryTextView = (TextView) itemView.findViewById(R.id.data_entry_text_view);
            dataEntryEditText = (EditText) itemView.findViewById(R.id.data_entry_edit_text);
            dataEntryCheckBox = (CheckBox) itemView.findViewById(R.id.data_entry_check_box);
        }

        public void onBind(DataEntry dataEntry) {
            mDataEntry = dataEntry;

            // Change dataEntry layout according to type of data in the container
            if (mDataEntry.getType().equals(Integer.class)) {
                dataEntryEditText.setVisibility(View.VISIBLE);
                dataEntryCheckBox.setVisibility(View.GONE);
            } else if (mDataEntry.getType().equals(Boolean.class)) {
                dataEntryEditText.setVisibility(View.GONE);
                dataEntryCheckBox.setVisibility(View.VISIBLE);
            }

            dataEntryTextView.setText(dataEntry.getName());
        }
    }
}
