package comcsse483.github.scoutr;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.plnyyanks.tba.apiv2.APIv2Helper;

import comcsse483.github.scoutr.fragments.RecordDataFragment;
import comcsse483.github.scoutr.fragments.SetUpNewTournamentFragment;
import comcsse483.github.scoutr.fragments.SyncDataDialogFragment;
import comcsse483.github.scoutr.fragments.TestDBFragment;
import comcsse483.github.scoutr.fragments.ViewDataFragment;
import comcsse483.github.scoutr.models.Match;
import comcsse483.github.scoutr.models.Tournament;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewDataFragment.OnFragmentInteractionListener {

    public static boolean hasTournament = false;
    private Fragment mCurrentFragment;

    public DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APIv2Helper.setAppId(Constants.API_ID);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDBHelper = new DBHelper(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            mCurrentFragment = new SetUpNewTournamentFragment();
            ft.replace(R.id.fragment_container, mCurrentFragment);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_sync_data:
                DialogFragment df = new SyncDataDialogFragment();
                df.show(getSupportFragmentManager(), "");
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment switchTo = null;

        switch (item.getItemId()) {
            case R.id.nav_set_up_new_tournament:
                switchTo = new SetUpNewTournamentFragment();
                break;
            case R.id.nav_record_data:
                if (mCurrentFragment instanceof RecordDataFragment) {
                    //TODO: Find way of storing the current tournament
                    switchTo = RecordDataFragment.newInstance(getTournament());
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.select_tournament), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_test_data:
                switchTo = new TestDBFragment();
                break;
            //TODO: Migrate test data view into view data fragment
//            case R.id.nav_view_data:
//                switchTo = new ViewDataFragment();
//                break;
        }

        if (switchTo != null) {
            mCurrentFragment = switchTo;
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, mCurrentFragment);

            //Manage back stack
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                getSupportFragmentManager().popBackStackImmediate();
            }

            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void switchToDetailFragment(Match match) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new ViewDataFragment());
        ft.addToBackStack("detail");
        ft.commit();
    }

    private Tournament getTournament() {
        //TODO: Fix temporary storage of current tournament object
        return null;
    }

    public DBHelper getDBHelper(){
        return mDBHelper;
    }
}
