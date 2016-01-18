package comcsse483.github.scoutr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by schmitml on 1/18/16.
 */
public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {
    private ArrayList<MenuItem> mMenuList;
    private Context mContext;

    public MainMenuAdapter(Context context) {
        mContext = context;
        mMenuList = new ArrayList<>();

        mMenuList.add(new MenuItem(mContext.getString(R.string.set_up_new_tournament), 0));
        mMenuList.add(new MenuItem(mContext.getString(R.string.record_data), 1));
        mMenuList.add(new MenuItem(mContext.getString(R.string.view_data), 2));
        mMenuList.add(new MenuItem(mContext.getString(R.string.sync_data), 3));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.menu_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuItem menuItem = mMenuList.get(position);
        holder.mMenuItemTextView.setText(menuItem.getTitle());

    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    private void launchActivity(int menuOptionNumber){
        Intent intent = new Intent();
        boolean isSet = true;
        switch (menuOptionNumber){
            case 0: //Set Up New Tournament
            intent = new Intent();
            break;
            case 1: //Record Data
            intent = new Intent();

            break;
            case 2: //View Data
            intent = new Intent();

            break;
            case 3: //Sync Data

                isSet = false;
            break;
            default:
                Toast.makeText(mContext, "Activity does not exist.", Toast.LENGTH_SHORT);
                isSet = false;
                break;
        }

        if(isSet){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mMenuItemTextView;
        MenuItem mMenuItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mMenuItemTextView = (TextView) itemView.findViewById(R.id.menu_item_text_view);
        }

        public void onBind(MenuItem menuItem) {
            mMenuItem = menuItem;
            mMenuItemTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            launchActivity(mMenuItem.getMenuNumber());
        }

    }
}
