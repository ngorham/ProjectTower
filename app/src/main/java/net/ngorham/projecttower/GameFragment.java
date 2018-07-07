package net.ngorham.projecttower;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Project Tower
 * GameFragment.java
 * Detail/Edit
 * Purpose: Handles main interaction with GameActivity
 *
 * @author Neil Gorham
 * @version 1.0 06/18/2018
 *
 */
public class GameFragment extends Fragment {
    //Private constants
    private static final String TAG = "GameFragment";
    private static final int BAG_TAG = 0;
    //Private variables
    private GameFragmentListener listener;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "INSIDE onCreate: called");
        if(getArguments() != null){
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "INSIDE onCreateView: called");
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_game, container, false);
        //Set up Buttons
        Button bagButton = layout.findViewById(R.id.button_bag);
        Button playerButton = layout.findViewById(R.id.button_player);
        Button settingsButton = layout.findViewById(R.id.button_settings);
        //Set up listener
        bagButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.bagButtonClicked(BAG_TAG); }
            }
        });
        playerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.playerButtonClicked(); }
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){ listener.settingsButtonClicked(); }
            }
        });
        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "INSIDE onAttach: called");
        if (context instanceof GameFragmentListener) {
            listener = (GameFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement GameFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "INSIDE onDetach: called");
        listener = null;
    }

    public interface GameFragmentListener {
        void bagButtonClicked(int bagTag);
        void playerButtonClicked();
        void settingsButtonClicked();
    }
}
