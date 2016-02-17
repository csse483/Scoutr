package comcsse483.github.scoutr;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.plnyyanks.tba.apiv2.APIv2Helper;

import java.util.ArrayList;
import java.util.Scanner;

import comcsse483.github.scoutr.fragments.SetUpNewTournamentFragment;
import comcsse483.github.scoutr.fragments.StatusFragment;
import comcsse483.github.scoutr.fragments.SyncDataDialogFragment;
import comcsse483.github.scoutr.fragments.TestDBFragment;
import comcsse483.github.scoutr.fragments.ViewDataDetailFragment;
import comcsse483.github.scoutr.fragments.ViewDataFragment;
import comcsse483.github.scoutr.models.DataContainer;
import comcsse483.github.scoutr.models.Match;
import comcsse483.github.scoutr.models.Tournament;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewDataFragment.OnFragmentInteractionListener {
    private Fragment mCurrentFragment;
    private Tournament mTournament;
    public DBHelper mDBHelper;
    public ArrayList<Match> mMatches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load The Blue Alliance API
        APIv2Helper.setAppId(Constants.API_ID);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDBHelper = new DBHelper(this);
        loadPersistentData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!mTournament.getName().equals("")) {
            mCurrentFragment = new StatusFragment();
        } else {
            //Overwrites the empty tournament object
            mCurrentFragment = new SetUpNewTournamentFragment();
        }
        ft.replace(R.id.fragment_container, mCurrentFragment);
        ft.commit();
    }

    private void loadPersistentData(){
        //Populate mMatches from the database
        ArrayList<Match> matchList = getMatchListFromDataBase();
        if (matchList.size() != 0) {
            mMatches = matchList;
        }
        mTournament = createTournamentFromSharedPreferences();
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
            //TODO: Add option to select a new tournament and wipe database
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
        //TODO: Fix which nav item is selected when changing between fragments
        switch (item.getItemId()) {
            case R.id.nav_status:
                switchTo = StatusFragment.newInstance();
                break;
            case R.id.nav_set_up_new_tournament:
                switchTo = new SetUpNewTournamentFragment();
                break;
//            case R.id.nav_test_data:
//                switchTo = new TestDBFragment();
//                break;
            //TODO: Migrate test data view into view data fragment
            case R.id.nav_view_data:
                switchTo = new ViewDataFragment();
                break;
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
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getSharedPreferences(Constants.APP_SHARED_PREF, Constants.MODE_PRIVATE).edit();
        editor.putString(Constants.TOURNAMENT, mTournament.getName());
        editor.putString(Constants.POSITION, mTournament.getTeamPositionString());
        editor.putString(Constants.EVENT_CODE, mTournament.getmEventCode());
        editor.commit();

    }

    private Tournament createTournamentFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_SHARED_PREF, Constants.MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.TOURNAMENT, "");
        String position = sharedPreferences.getString(Constants.POSITION, "");
        String eventCode = sharedPreferences.getString(Constants.EVENT_CODE, "");
        return new Tournament(name, eventCode, position);
    }

    @Override
    public void switchToDetailFragment(Match match) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new TestDBFragment());
//        ft.addToBackStack("detail");
        ft.commit();
    }

    private ArrayList<Match> getMatchListFromDataBase() {
        SQLiteDatabase mDB = mDBHelper.getReadableDatabase();
        Cursor mCursor = mDB.query(mDBHelper.TABLE_NAME_MATCH_LIST, null, null, null, null, null, null);
        ArrayList<Match> matchList = new ArrayList<>();

        for (int i = 0; i < mCursor.getCount(); i++) {
            mCursor.moveToPosition(i);
            int[] blueTeams = {mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.MatchListEntry.COLUMN_NAME_BLUE_ONE)),
                    mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.MatchListEntry.COLUMN_NAME_BLUE_TWO)),
                    mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.MatchListEntry.COLUMN_NAME_BLUE_THREE))};

            int[] redTeams = {mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.MatchListEntry.COLUMN_NAME_RED_ONE)),
                    mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.MatchListEntry.COLUMN_NAME_RED_TWO)),
                    mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.MatchListEntry.COLUMN_NAME_RED_THREE))};

            int matchId = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.MatchListEntry.COLUMN_NAME_MATCH_NUMBER));
            Match match = new Match(matchId, blueTeams, redTeams);
            matchList.add(match);
        }
        mCursor.close();

        return matchList;
    }

    /*-------------------------------------- Getters and Setters ---------------------------------*/

    public Tournament getTournament() {
        return mTournament;
    }

    public void setTournament(Tournament tournament) {
        mTournament = tournament;
    }

    public ArrayList<Match> getMatches() {
        return mMatches;
    }

    public void setMatches(ArrayList<Match> mMatches) {
        this.mMatches = mMatches;
    }

    public DBHelper getDBHelper() {

        return mDBHelper;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    private void processIntent(Intent intent) {
        Parcelable[] ndefMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) ndefMessages[0];
        NdefRecord[] records = msg.getRecords();
        for (NdefRecord record : records) {
            String dataString = new String(record.getPayload());
            Scanner scanner = new Scanner(dataString);
            DataContainer dataContainer = new DataContainer();
            for (int i = 0; i < Constants.DATA_NAMES.length; i++) {
                if (Constants.DATA_FIELDS[i].equals("int")) {
                    dataContainer.setData(Constants.DATA_NAMES[i], scanner.nextInt());
                } else {
                    dataContainer.setData(Constants.DATA_NAMES[i], scanner.nextInt() == 1);
                }
            }
            mDBHelper.insertDataContainer(dataContainer);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }
}
