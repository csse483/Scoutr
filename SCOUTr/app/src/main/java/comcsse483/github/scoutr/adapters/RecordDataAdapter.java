package comcsse483.github.scoutr.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import comcsse483.github.scoutr.R;
import comcsse483.github.scoutr.models.DataEntry;

/**
 * Created by schmitml on 1/26/16.
 */
public class RecordDataAdapter extends RecyclerView.Adapter<RecordDataAdapter.ViewHolder> {
    private ArrayList<DataEntry> mItemList;

    public RecordDataAdapter(){
        mItemList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataEntryTextView;
        EditText dataEntryEditText;
        DataEntry mDataEntry;

        public ViewHolder(View itemView) {
            super(itemView);

            dataEntryTextView = (TextView) itemView.findViewById(R.id.data_entry_text_view);
            dataEntryEditText = (EditText) itemView.findViewById(R.id.data_entry_edit_text);

        }

        public void onBind(DataEntry dataEntry){
            mDataEntry = dataEntry;

            dataEntryTextView.setText(dataEntry.getName());
        }
    }
}
