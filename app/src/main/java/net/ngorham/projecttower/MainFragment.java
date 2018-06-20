package net.ngorham.projecttower;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Project Tower
 * MainFragment.java
 * Main
 * Purpose: Displays a list of menu options
 *
 * @author Neil Gorham
 * @version 1.0 06/20/2018
 *
 */

public class MainFragment extends Fragment {
    //Private constants
    private static final String TAG ="MainFragment";
    //Private variables
    private MainFragmentListener listener;

    public MainFragment() {
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
                             Bundle savedInstanceState){
        Log.d(TAG, "INSIDE onCreateView: called");
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        //Set up TextViews
        TextView newGameTextView = layout.findViewById(R.id.text_new_game);
        TextView exitTextView = layout.findViewById(R.id.text_exit);
        //Set up listener
        newGameTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.newGameClicked(); }
            }
        });
        exitTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.exitClicked(); }
            }
        });
        return layout;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof MainFragmentListener){
            listener = (MainFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface MainFragmentListener {
        void newGameClicked();
        void exitClicked();
    }
}
