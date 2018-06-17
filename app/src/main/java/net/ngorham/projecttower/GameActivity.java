package net.ngorham.projecttower;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class GameActivity extends AppCompatActivity {
    //Private constants
    private final String TAG = "GameActivity";
    //Private variables
    private Context context;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "INSIDE onCreate: called");
        context = this;
        sessionManager = new SessionManager(getApplication());
        if(sessionManager.hasSession()){
            //loadSavedFile
            Toast.makeText(getApplicationContext(), "session true", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
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
}
