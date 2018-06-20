package net.ngorham.projecttower;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.ngorham.projecttower.BagContent.BagItem;
import net.ngorham.projecttower.settings.SettingsContent;
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
        implements MainFragment.MainFragmentListener,
        GameFragment.GameFragmentListener {
    //Private constants
    private static final String TAG = "GameActivity";
    private final String VISIBLE_FRAGMENT_TAG = "visible_fragment";
    //Private variables
    private Context context;
    private SessionManager sessionManager;
    private FragmentManager fragmentManager;
    //private Toolbar toolbar;
    //private TabLayout tabLayout;
    //private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "INSIDE onCreate: called");
        context = this;
        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //viewPager = (ViewPager)findViewById(R.id.viewpager);
        //setupViewPager(viewPager);
        //tabLayout = (TabLayout)findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);
        //Set up fragment
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        sessionManager = new SessionManager(getApplication());
        //gameManager = new GameManager();
        if(sessionManager.hasSession()){
            //loadSavedFile
            //Continue progress
            Toast.makeText(getApplicationContext(), "session is true", Toast.LENGTH_SHORT).show();
            sessionManager.setSession(false);
            //Set up GameFragment
            fragment = new GameFragment();
        } else {
            //New Game
            //Set up MainFragment
            fragment = new MainFragment();
            //Create Player
            //gameManager.setPlayer(player);
            //create/write save file
            //start run
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content_frame, fragment, VISIBLE_FRAGMENT_TAG);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
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

    //MainFragmentListener on textView clicked
    @Override
    public void newGameClicked(){
        Toast.makeText(getApplicationContext(), "newGame clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "newGame clicked");
        //Set session to true
        sessionManager.setSession(true);
        //Replace fragment with GameFragment
        Fragment fragment = new GameFragment();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment, VISIBLE_FRAGMENT_TAG);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public void exitClicked(){
        Toast.makeText(getApplicationContext(), "exit clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "exit clicked");
        finish();
    }

    //GameFragmentListener on button clicked
    @Override
    public void bagButtonClicked(){
        Toast.makeText(getApplicationContext(), "Bag button clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Bag button clicked");
        bagDialog();
    }

    @Override
    public void playerButtonClicked(){
        Toast.makeText(getApplicationContext(), "Player button clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Player button clicked");
        playerDialog();
    }

    @Override
    public void settingsButtonClicked(){
        Toast.makeText(getApplicationContext(), "Settings button clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Settings button clicked");
        settingsDialog();
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

    //Display Bag AlertDialog
    private void bagDialog(){
        final BagArrayAdapter bagArrayAdapter =
                new BagArrayAdapter(context, BagContent.ITEMS);
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(R.string.button_bag);
        builder.setAdapter(bagArrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BagItem item = bagArrayAdapter.getItem(which);
                Toast.makeText(getApplicationContext(), "item id: " + item.getId() + "\ncontent: " + item.getContent(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "item id: " + item.getId() + "\ncontent: " + item.getContent());
                //Microtransactions?
            }
        });
        builder.setNegativeButton(R.string.dialog_close, null);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Display Player AlertDialog
    private void playerDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(R.string.button_player);
        View view = getLayoutInflater().inflate(R.layout.dialog_player, null);
        builder.setView(view);
        builder.setNegativeButton(R.string.dialog_close, null);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Display Settings AlertDialog
    private void settingsDialog(){
        final SettingsArrayAdapter settingsArrayAdapter =
                new SettingsArrayAdapter(context, SettingsContent.ITEMS);
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(R.string.button_settings);
        builder.setAdapter(settingsArrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SettingsItem item = settingsArrayAdapter.getItem(which);
                Toast.makeText(getApplicationContext(), "item id: " + item.getId() + "\ncontent: " + item.getContent(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "item id: " + item.getId() + "\ncontent: " + item.getContent());
                switch(item.getId()){
                    case 0: //Bag settings
                        break;
                    case 1: //Save & Exit
                        finish();
                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.dialog_close, null);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
