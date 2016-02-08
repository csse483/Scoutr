package comcsse483.github.scoutr.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import comcsse483.github.scoutr.models.TestDataContainer;

/**
 * Created by yarlagrt on 2/8/2016.
 */
public class TestDBAdapter extends RecyclerView.Adapter<TestDBAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<TestDataContainer> mItemList = new ArrayList<>();

    public TestDBAdapter(Context context) {
        mContext = context;
    }

    @Override
    public TestDBAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TestDBAdapter.ViewHolder holder, int position) {
            holder.onBind(mItemList.get(position))
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void onBind(TestDataContainer testDataContainer) {

        }
    }
}
