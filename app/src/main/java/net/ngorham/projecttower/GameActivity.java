package net.ngorham.projecttower;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.ngorham.projecttower.SettingsContent.SettingsItem;

import java.util.ArrayList;
import java.util.List;

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
        GameFragment.GameFragmentListener,
        BattleFragment.BattleFragmentListener {
    //Private constants
    private static final String TAG = "GameActivity";
    private final String VISIBLE_FRAGMENT_TAG = "visible_fragment";
    //Private variables
    private Context context;
    private SessionManager sessionManager;
    private GameManager gameManager;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private List<String> battleLog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "INSIDE onCreate: called");
        context = this;
        //Set up GameManager
        Actor player = new Actor(0, "Player", "createdOn", "lastModified",
                10, 150, 10, 10, 10, 10, 10, 0, BagContent.PLAYER_ITEMS);
        Actor enemy = new Actor(1, "Enemy", "createdOn", "lastModifed",
        10, 100, 10, 10, 1, 1, 1, 0, null);
        gameManager = new GameManager(context, player);
        gameManager.setEnemy(enemy);
        Log.d(TAG, gameManager.toString());
        //Set up SessionManager
        sessionManager = new SessionManager(getApplication());
        //Set up fragment
        fragmentManager = getSupportFragmentManager();
        fragment = new BattleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("enemy_name", gameManager.getEnemyName());
        bundle.putString("enemy_hp", gameManager.getStringEnemyHP());
        bundle.putString("player_hp", gameManager.getStringPlayerHP());
        bundle.putString("player_ap", gameManager.getStringPlayerAP());
        fragment.setArguments(bundle);
        /*if(sessionManager.hasSession()){
            //loadSavedFile
            //Continue progress
            Toast.makeText(getApplicationContext(), "session is true", Toast.LENGTH_SHORT).show();
            //sessionManager.setSession(false);
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
        }*/
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

    //MainFragmentListener on textViews clicked
    @Override
    public void newGameClicked(){
        Toast.makeText(getApplicationContext(), "newGame clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "newGame clicked");
        //Set session to true
        sessionManager.setSession(true);
        //Replace fragment with GameFragment
        fragment = new GameFragment();
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

    //GameFragmentListener on buttons clicked
    @Override
    public void bagButtonClicked(int bagTag){
        Toast.makeText(getApplicationContext(), "Bag button clicked, Bag Tag: " + bagTag, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Bag button clicked, Bag Tag: " + bagTag);
        bagDialog(bagTag);
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

    //BattleFragmentListener on buttons clicked
    @Override
    public void attackButtonClicked() {
        Toast.makeText(getApplicationContext(), "attack button clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "attack button clicked");
        Fragment frag = fragmentManager.findFragmentByTag(VISIBLE_FRAGMENT_TAG);
        View layout = frag.getView();
        if(layout != null){ gameManager.attackAction(layout); }
    }

    @Override
    public void defendButtonClicked(){
        Toast.makeText(getApplicationContext(), "defend button clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "defend button clicked");
        Fragment frag = fragmentManager.findFragmentByTag(VISIBLE_FRAGMENT_TAG);
        View layout = frag.getView();
        if(layout != null){ gameManager.defendAction(layout); }
    }

    @Override
    public void skillsButtonClicked(){
        Toast.makeText(getApplicationContext(), "skills button clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "skills button clicked");
    }

    //Display Bag AlertDialog
    private void bagDialog(final int bagTag){
        final BagArrayAdapter bagArrayAdapter =
                new BagArrayAdapter(context, gameManager.getPlayer().getBag());
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(R.string.button_bag);
        builder.setAdapter(bagArrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BagItem item = bagArrayAdapter.getItem(which);
                switch(bagTag){
                    case 0: //GameFragment Bag
                        //Microtransactions?
                        break;
                    case 1: //BattleFragment Bag
                        int curQty = item.getQuantity();
                        if(curQty > 0){
                            Fragment frag = fragmentManager.findFragmentByTag(VISIBLE_FRAGMENT_TAG);
                            View layout = frag.getView();
                            if(layout != null) {
                                gameManager.bagAction(layout, which);
                            }
                        } else {
                            //Print quantity is zero
                        }
                        break;
                }
                Toast.makeText(getApplicationContext(), "item id: " + item.getId() + "\ncontent: " + item.getContent(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "item id: " + item.getId() + "\ncontent: " + item.getContent());
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
