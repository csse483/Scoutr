package comcsse483.github.scoutr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import comcsse483.github.scoutr.Constants;
import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.DataEntry;


/**
 * Created by schmitml on 1/26/16.
 */
public class RecordDataAdapter extends RecyclerView.Adapter<RecordDataAdapter.ViewHolder> {
    private ArrayList<DataEntry> mItemList;
    private Context mContext;
    private int mMatchNumber;
    private int mTeamNumber;

    public RecordDataAdapter(Context context, int matchNumber, int teamNumber) {
        mContext = context;
        this.mMatchNumber = matchNumber;
        this.mTeamNumber = teamNumber;
        mItemList = new ArrayList<>();
        populateRecyclerViewWithDataEntryFields();
    }

    public DataContainer recordData(DataContainer dataContainer) {
        DataContainer localDataContainer = dataContainer;
        //Takes all of the values in the data entry containers and writes them to the data container
        for (int i = 0; i < mItemList.size(); i++) {
            DataEntry dataEntry = mItemList.get(i);
            if (!dataEntry.getName().equals(Constants.DATA_NAMES[0]) || !dataEntry.getName().equals(Constants.DATA_NAMES[1])) {
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

            if (pos == 0) {
                mItemList.get(0).setData(mTeamNumber);
            } else if (pos == 1) {
                mItemList.get(1).setData(mMatchNumber);
            }
        }
    }


    /*-----------------------------------------ViewHolder-----------------------------------------*/

    public class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        TextView dataEntryTextView;
        //New
        ImageButton dataEntrySubtractButton;
        ImageButton dataEntryAddButton;
        TextView dataEntryValueTextView;

        CheckBox dataEntryCheckBox;
        DataEntry mDataEntry;

//        TableRow dataEntryButtonRow;
//        TableRow dataEntryCheckBoxRow;

        public ViewHolder(View itemView) {
            super(itemView);


            dataEntryTextView = (TextView) itemView.findViewById(R.id.data_entry_text_view);
//
//            dataEntryCheckBoxRow = (TableRow) itemView.findViewById(R.id.data_entry_check_box_row);
//            dataEntryButtonRow = (TableRow) itemView.findViewById(R.id.data_entry_button_row);

            //Integer
            dataEntryValueTextView = (TextView) itemView.findViewById(R.id.data_entry_value_text_view);
            dataEntryAddButton = (ImageButton) itemView.findViewById(R.id.data_entry_add_button);
            dataEntrySubtractButton = (ImageButton) itemView.findViewById(R.id.data_entry_subtract_button);
            //TODO: Create button listeners for add and subtract buttons

            //Listener
            dataEntryAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataEntry.setData((int) mDataEntry.getData() + 1);
                    dataEntryValueTextView.setText(String.valueOf(mDataEntry.getData()));
                }
            });
            dataEntrySubtractButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((int) mDataEntry.getData() > 0){
                        mDataEntry.setData((int) mDataEntry.getData() - 1);
                        dataEntryValueTextView.setText(String.valueOf(mDataEntry.getData()));
                    }
                }
            });

            //Boolean
            dataEntryCheckBox = (CheckBox) itemView.findViewById(R.id.data_entry_check_box);
            dataEntryCheckBox.setOnCheckedChangeListener(this);
        }

        public void onBind(DataEntry dataEntry) {
            mDataEntry = dataEntry;

            // Change dataEntry layout according to type of data in the container
            if (mDataEntry.getType().equals(Integer.class)) {
                // Integer Input
//                dataEntryButtonRow.setVisibility(View.VISIBLE);
//                dataEntryCheckBoxRow.setVisibility(View.GONE);
                dataEntryAddButton.setVisibility(View.VISIBLE);
                dataEntrySubtractButton.setVisibility(View.VISIBLE);
                dataEntryValueTextView.setVisibility(View.VISIBLE);

                dataEntryCheckBox.setVisibility(View.GONE);

                dataEntryValueTextView.setText(String.valueOf(dataEntry.getData()));

            } else if (mDataEntry.getType().equals(Boolean.class)) {
                // Boolean Input
//                dataEntryButtonRow.setVisibility(View.GONE);
//                dataEntryCheckBoxRow.setVisibility(View.VISIBLE);

                dataEntryAddButton.setVisibility(View.GONE);
                dataEntrySubtractButton.setVisibility(View.GONE);
                dataEntryValueTextView.setVisibility(View.GONE);

                dataEntryCheckBox.setVisibility(View.VISIBLE);

                dataEntryCheckBox.setChecked((Boolean) dataEntry.getData());
            }

            dataEntryTextView.setText(dataEntry.getName());
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            mDataEntry.setData(isChecked);
        }

    }


}
