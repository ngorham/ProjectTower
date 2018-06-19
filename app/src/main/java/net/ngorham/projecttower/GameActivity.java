package net.ngorham.projecttower;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import net.ngorham.projecttower.BagContent.BagItem;
import net.ngorham.projecttower.settings.SettingsContent.SettingsItem;

/**
 * Project Tower
 * GameActivity.java
 * Edit
 * Purpose: Main loop
 *
 * @author Neil Gorham
 * @version 1.0 06/15/2018
 *
 */

public class GameActivity extends AppCompatActivity
        implements BagFragment.BagFragmentListener,
        SettingsFragment.SettingsFragmentListener{
    //Private constants
    private final String TAG = "GameActivity";
    //Private variables
    private Context context;
    private SessionManager sessionManager;
    //private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "INSIDE onCreate: called");
        context = this;
        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        sessionManager = new SessionManager(getApplication());
        //gameManager = new GameManager();
        if(sessionManager.hasSession()){
            //loadSavedFile
            //Continue progress
            Toast.makeText(getApplicationContext(), "session is true", Toast.LENGTH_SHORT).show();
            sessionManager.setSession(false);
        } else {
            //New Game
            sessionManager.setSession(true);
            Toast.makeText(getApplicationContext(), "session set to true", Toast.LENGTH_SHORT).show();
            //session = true
            //Create Player
            //gameManager.setPlayer(player);
            //create/write save file
            //start run
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "INSIDE onPostCreate: called");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "INSIDE onStart: called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "INSIDE onResume: called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "INSIDE onPause: called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "INSIDE onStop: called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "INSIDE onDestroy: called");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "INSIDE onRestart: called");
    }

    //BagFragmentListener on item clicked
    @Override
    public void bagItemClicked(BagItem item){
        Toast.makeText(getApplicationContext(), "item id: " + item.getId(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "item id: " + item.getId());
    }

    //SettingsFragmentListener on item clicked
    @Override
    public void settingsItemClicked(SettingsItem item){
        Toast.makeText(getApplicationContext(), "item content: " + item.getContent(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "item content: " + item.getContent());
        switch(item.getId()){
            case 0: //Bag settings
                break;
            case 1: //Save & Exit
                finish();
                break;
        }
    }

    //Set up ViewPager
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GameFragment(), "Game");
        adapter.addFragment(new BagFragment(), "Bag");
        adapter.addFragment(new PlayerFragment(), "Player");
        adapter.addFragment(new SettingsFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }
}
