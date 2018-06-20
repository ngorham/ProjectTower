package net.ngorham.projecttower;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    //Private variables
    private GameFragmentListener listener;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_game, container, false);
        //Set up buttons
        Button bagButton = layout.findViewById(R.id.button_bag);
        Button playerButton = layout.findViewById(R.id.button_player);
        Button settingsButton = layout.findViewById(R.id.button_settings);
        //Set up listener
        bagButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.bagButtonClicked(); }
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
        if (context instanceof GameFragmentListener) {
            listener = (GameFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface GameFragmentListener {
        void bagButtonClicked();
        void playerButtonClicked();
        void settingsButtonClicked();
    }
}
