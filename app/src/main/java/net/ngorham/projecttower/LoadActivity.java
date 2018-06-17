package net.ngorham.projecttower;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Project Tower
 * LoadActivity.java
 * Detail
 * Purpose: Displays a list of saved files
 *
 * @author Neil Gorham
 * @version 1.0 06/15/2018
 *
 */

public class LoadActivity extends AppCompatActivity {
    //Private constants
    private final String TAG = "LoadActivity";
    //Private variables
    private Context context;
    private RecyclerView recycler;
    private SavedFilesAdapter adapter;
    //Public constants
    public static final String SF_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Log.d(TAG, "INSIDE onCreate: called");
        context = this;
        //Set up RecyclerView
        recycler = findViewById(R.id.recycler);
        //Set up Layout Manager
        recycler.setLayoutManager(new LinearLayoutManager(this));
        readFiles();
        ArrayList<SavedFile> savedFiles = new ArrayList<>();
        savedFiles.add(new SavedFile());
        savedFiles.add(new SavedFile());
        savedFiles.add(new SavedFile());
        savedFiles.add(new SavedFile());
        savedFiles.add(new SavedFile());
        savedFiles.add(new SavedFile());
        //Set up adapter
        adapter = new SavedFilesAdapter(this, savedFiles);
        recycler.setAdapter(adapter);
        adapter.setListener(new SavedFilesAdapter.Listener(){
            @Override
            public void onClick(View view, int position){
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra(SF_POSITION, position + 1);
                startActivity(intent);
            }
        });
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

    private void readFiles(){
        AssetManager assetManager = context.getAssets();
        //String fileName;
        String line = null;
        BufferedReader reader = null;
        try{
            //fileName = "test00.txt";
            String[] paths = assetManager.list("enemy");
            //InputStream in = assetManager.open(fileName, AssetManager.ACCESS_UNKNOWN);
            for(int i = 0; i < paths.length; i++){
                Log.d(TAG, "path: " + paths[i]);
                InputStream in = assetManager.open("enemy/" + paths[i], AssetManager.ACCESS_UNKNOWN);
                if(in == null){ return; }
                StringBuilder sBuilder = new StringBuilder();
                reader = new BufferedReader(
                        new InputStreamReader(in, "UTF-8"));
                while((line = reader.readLine()) != null){
                    //process line
                    String[] args = line.split(",");
                    int id = Integer.parseInt(args[0]);
                    String name = args[1];
                    String createdOn = args[2];
                    String lastModified = args[3];
                    int currentHP = Integer.parseInt(args[4]);
                    int maxHP = Integer.parseInt(args[5]);
                    int currentAP = Integer.parseInt(args[6]);
                    int maxAP = Integer.parseInt(args[7]);
                    int ATK = Integer.parseInt(args[8]);
                    int DEF = Integer.parseInt(args[9]);
                    int SPD = Integer.parseInt(args[10]);
                    int damage = Integer.parseInt(args[11]);
                    Enemy enemy = new Enemy(id, name, createdOn, lastModified, currentHP, maxHP, currentAP, maxAP, ATK, DEF, SPD, damage);
                    Log.d(TAG, enemy.toString());
                    sBuilder.append(line);
                    sBuilder.append("\n");
                }
                in.close();
                Log.d(TAG, "File" + i + ":\n" + sBuilder.toString());
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch(IOException e){
                    e.printStackTrace();

                }
            }
        }
    }
}
