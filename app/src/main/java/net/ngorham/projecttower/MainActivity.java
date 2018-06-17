package net.ngorham.projecttower;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Project Tower
 * MainActivity.java
 * Main
 * Purpose: Displays a list of menu options
 *
 * @author Neil Gorham
 * @version 1.0 06/15/2018
 *
 */

public class MainActivity extends AppCompatActivity {
    //Private constants
    private final String TAG = "MainActivity";
    //Private variables
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "INSIDE onCreate: called");
        sessionManager = new SessionManager(getApplication());
        if(sessionManager.hasSession()){
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
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

    //On click start new game
    public void newGame(View view){
        Toast.makeText(getApplicationContext(), "New Game clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    //On click exit game
    public void exit(View view){
        Toast.makeText(getApplicationContext(), "Exit Game clicked", Toast.LENGTH_SHORT).show();
        finish();
    }
}
