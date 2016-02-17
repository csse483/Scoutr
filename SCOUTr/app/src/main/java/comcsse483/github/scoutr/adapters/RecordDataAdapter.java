package comcsse483.github.scoutr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comcsse483.github.scoutr.Constants;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.Utils;
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
        populateRecyclerViewWithDataEntryFields();
    }

    public DataContainer recordData(DataContainer dataContainer) {
        DataContainer localDataContainer = dataContainer;
        //Takes all of the values in the data entry containers and writes them to the data container
        for (int i = 0; i < mItemList.size(); i++) {
            DataEntry dataEntry = mItemList.get(i);
            if(!dataEntry.getName().equals(Constants.DATA_NAMES[0]) || !dataEntry.getName().equals(Constants.DATA_NAMES[1])){
                localDataContainer.setData(dataEntry.getName(), dataEntry.getData());
            }
        }

        Log.e("SCOUTr", "Successfully captured data in form.");
        return localDataContainer;
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


    /*----------------------------------------Utils-----------------------------------------------*/

    private void populateRecyclerViewWithDataEntryFields() {
        for (int pos = 0; pos < Constants.DATA_FIELDS.length; pos++) {
            if (Constants.DATA_FIELDS[pos].equals("int")) {
                mItemList.add(new DataEntry(Constants.DATA_NAMES[pos], Integer.class));
            } else if (Constants.DATA_FIELDS[pos].equals("boolean")) {
                mItemList.add(new DataEntry(Constants.DATA_NAMES[pos], Boolean.class));

            }
        }
    }


    /*-----------------------------------------ViewHolder-----------------------------------------*/

    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher, CompoundButton.OnCheckedChangeListener {
        TextView dataEntryTextView;
        EditText dataEntryEditText;
        CheckBox dataEntryCheckBox;
        DataEntry mDataEntry;

        public ViewHolder(View itemView) {
            super(itemView);

            dataEntryTextView = (TextView) itemView.findViewById(R.id.data_entry_text_view);
            dataEntryEditText = (EditText) itemView.findViewById(R.id.data_entry_edit_text);
            dataEntryEditText.addTextChangedListener(this);
            dataEntryCheckBox = (CheckBox) itemView.findViewById(R.id.data_entry_check_box);
            dataEntryCheckBox.setOnCheckedChangeListener(this);
        }

        public void onBind(DataEntry dataEntry) {
            mDataEntry = dataEntry;

            // Change dataEntry layout according to type of data in the container
            if (mDataEntry.getType().equals(Integer.class)) {
                dataEntryEditText.setVisibility(View.VISIBLE);
                dataEntryCheckBox.setVisibility(View.GONE);

                dataEntryEditText.setText(String.valueOf(dataEntry.getData()));

            } else if (mDataEntry.getType().equals(Boolean.class)) {
                dataEntryEditText.setVisibility(View.GONE);
                dataEntryCheckBox.setVisibility(View.VISIBLE);

                dataEntryCheckBox.setChecked((Boolean) dataEntry.getData());
            }

            dataEntryTextView.setText(dataEntry.getName());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (Utils.isNumber(editable.toString())) {
                mDataEntry.setData(Integer.parseInt(editable.toString()));
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Don't Care
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Don't Care
        }


        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            mDataEntry.setData(isChecked);
        }
    }
}
