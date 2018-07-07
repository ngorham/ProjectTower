package net.ngorham.projecttower;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BattleFragment extends Fragment {
    //Private constants
    private static final String TAG ="BattleFragment";
    private static final int BAG_TAG = 1;
    private final String ENEMY_NAME = "enemy_name";
    private final String ENEMY_HP = "enemy_hp";
    private final String PLAYER_HP = "player_hp";
    private final String PLAYER_AP = "player_ap";
    //Private variables
    private BattleFragmentListener listener;
    private String enemyName;
    private String enemyHP;
    private String playerHP;
    private String playerAP;

    public BattleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "INSIDE onCreate: called");
        if(getArguments() != null){
            enemyName = getArguments().getString(ENEMY_NAME);
            enemyHP = getArguments().getString(ENEMY_HP);
            playerHP = getArguments().getString(PLAYER_HP);
            playerAP = getArguments().getString(PLAYER_AP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "INSIDE onCreateView: called");
        //Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_battle, container, false);
        //Set up TextViews
        TextView enemyNameTextView = layout.findViewById(R.id.text_enemy_name);
        TextView enemyHPTextView = layout.findViewById(R.id.text_enemy_hp);
        TextView playerHPTextView = layout.findViewById(R.id.text_player_hp);
        TextView playerAPTextView = layout.findViewById(R.id.text_player_ap);
        enemyNameTextView.setText(enemyName);
        enemyHPTextView.setText(enemyHP);
        playerHPTextView.setText(playerHP);
        playerAPTextView.setText(playerAP);
        //Set up Buttons
        Button attackButton = layout.findViewById(R.id.button_attack);
        Button defendButton = layout.findViewById(R.id.button_defend);
        Button skillsButton = layout.findViewById(R.id.button_skills);
        Button bagButton = layout.findViewById(R.id.button_bag);
        //Set up listener
        attackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.attackButtonClicked(); }
            }
        });
        defendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.defendButtonClicked(); }
            }
        });
        skillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){ listener.skillsButtonClicked(); }
            }
        });
        bagButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){ listener.bagButtonClicked(BAG_TAG); }
            }
        });
        return layout;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof BattleFragmentListener){
            listener = (BattleFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BattleFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface BattleFragmentListener{
        void attackButtonClicked();
        void defendButtonClicked();
        void skillsButtonClicked();
        void bagButtonClicked(int bagTag);
    }
}
