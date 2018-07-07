package net.ngorham.projecttower;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Tower
 * GameManager.java
 * Utility
 * Purpose: Manages battle operations
 *
 * @author Neil Gorham
 * @version 1.0 06/22/2018
 *
 */

public class GameManager {
    //Private constants
    private static final String TAG = "GameManager";
    private static final int PLAYER_LOSE = 0;
    private static final int PLAYER_WIN = 1;
    //Private variables
    private Context context;
    //private Player player;
    private Actor player;
    private Actor enemy;
    //private Enemy enemy;
    private int turnCount;
    private List<String> battleLog = new ArrayList<>();
    //View variables
    private TextView enemyHP;
    private TextView playerHP;
    private TextView playerAP;
    private ListView battleLogListView;

    //Default Constructor
    public GameManager(Context c, Actor p){
        context = c;
        //player = new Player();
        //enemy = new Enemy();
        player = p;
    }

    //=== Player Methods ===
    //Return reference to Player
    public Actor getPlayer(){ return player; }

    public String getStringPlayerHP(){
        return player.getCurrentHP() + "/" + player.getMaxHP() + " HP";
    }

    public String getStringPlayerAP(){
        return player.getCurrentAP() + "/" + player.getMaxAP() + " AP";
    }

    public void setPlayerFullHP(){
        player.setCurrentHP(player.getMaxHP());
    }

    public int getPlayerSPD(){ return player.getSPD(); }

    //Player Actions
    //Player attack action
    private void playerAttack(){
        //Calculate player damage
        player.setDamage(calculateDamage());
        //Subtract player damage from Enemy HP
        enemy.setCurrentHP(enemy.getCurrentHP() - player.getDamage());
        //Update Enemy HP TextView
        enemyHP.setText(getStringEnemyHP());
        //Update battle log
        battleLog.add("Player hit Enemy for " + player.getDamage());
    }

    //Player defend action
    private void playerDefend(){
        Log.d(TAG, "player damage reduction: " + player.getDamageReduction());
        //Increase player damage reduction
        player.setDamageReduction(0.8);
        battleLog.add("Player took a defensive stance");
        Log.d(TAG, "player damage reduction: " + player.getDamageReduction());
    }

    //Player skill action
    private void playerSkill(){

    }

    //Player bag action
    private void playerBag(int position){
        BagItem item = player.getBag().get(position);
        //update player status
        switch(item.getType()){
            case 0: //HP type
                //compare currentHP to maxHP
                if(player.getCurrentHP() < player.getMaxHP()){
                    incrementTurnCount();
                    playerChangeHP(item.getPercentHP());
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    Toast.makeText(context, "Player HP is full", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case 1: //AP type
                break;
            case 2: //State type
                break;
        }
    }

    //Player change HP action
    private void playerChangeHP(double percent){
        Log.d(TAG, "playerHeal action called");
        double h = player.getMaxHP() * percent;
        int heal = (int)h;
        Log.d(TAG, "heal amount: " + heal + "\nplayer HP: " + player.getCurrentHP());
        if((heal + player.getCurrentHP()) > player.getMaxHP()){
            player.setCurrentHP(player.getMaxHP());
        } else {
            player.setCurrentHP(heal + player.getCurrentHP());
        }
        Log.d(TAG, "player HP: " + player.getCurrentHP());
        playerHP.setText(getStringPlayerHP());
        battleLog.add("Player restored " + heal + " HP");
    }

    //=== Enemy Methods ===
    //Return reference to Enemy
    public Actor getEnemy(){ return enemy; }

    public String getEnemyName(){ return enemy.getName(); }

    public String getStringEnemyHP(){
        return enemy.getCurrentHP() + "/" + enemy.getMaxHP() + " HP";
    }

    public int getEnemySPD(){ return enemy.getSPD(); }

    public void setEnemy(Actor e){ enemy = e; }

    //Enemy Actions
    //Enemy attack action
    private void enemyAttack(){
        //Calculate enemy damage
        enemy.setDamage(calculateDamage());
        //subtract enemy damage from Player HP
        player.setCurrentHP(player.getCurrentHP() - enemy.getDamage());
        //update Player HP textView
        playerHP.setText(getStringPlayerHP());
        //update Battle log
        battleLog.add("Enemy hit Player for " + enemy.getDamage());
    }

    //Enemy defend action
    private void enemyDefend(){
        Log.d(TAG, "enemy damage reduction: " + enemy.getDamageReduction());
        //Increase enemy damage reduction
        enemy.setDamageReduction(0.8);
        battleLog.add("Enemy took a defensive stance");
        Log.d(TAG, "enemy damage reduction: " + enemy.getDamageReduction());
    }

    //Enemy skill action
    private void enemySkill(){

    }

    //Enemy bag action
    private void enemyBag(){

    }

    //Turn Count Methods
    //Return reference to turnCount
    public int getTurnCount(){ return turnCount; }

    //Increase turnCount value by one
    public void incrementTurnCount(){
        turnCount++;
        Log.d(TAG, "Turn count: " + turnCount);
        //Update battle log
        battleLog.add("Turn " + turnCount + ":");
    }

    //Set turnCount value to zero
    public void resetTurnCount(){ turnCount = 0; }

    //Attack action
    public void attackAction(View view){
        Toast.makeText(context, "attackAction called", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "attackAction called");
        enemyHP = view.findViewById(R.id.text_enemy_hp);
        playerHP = view.findViewById(R.id.text_player_hp);
        battleLogListView = view.findViewById(R.id.list_battle_log);
        //Increment turn counter
        incrementTurnCount();
        //Compare player and enemy SPD values
        if(player.getSPD() >= enemy.getSPD()){
            //Player attack action
            playerAttack();
            //Check winState
            checkWinState();
            //Enemy action
            //AI picks enemy action to execute
            computerAction();
            //Check winState
            checkWinState();
        } else {
            //Enemy Action
            //AI picks enemy action to execute
            computerAction();
            //Check winState
            checkWinState();
            //Player attack action
            playerAttack();
            //Check winState
            checkWinState();
        }
        ArrayAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, battleLog);
        battleLogListView.setAdapter(adapter);
    }

    //Defend action
    public void defendAction(View view){
        Toast.makeText(context, "defendAction called", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "defendAction called");
        enemyHP = view.findViewById(R.id.text_enemy_hp);
        playerHP = view.findViewById(R.id.text_player_hp);
        battleLogListView = view.findViewById(R.id.list_battle_log);
        //Increment turn counter
        incrementTurnCount();
        if(player.getSPD() >= enemy.getSPD()){
            //Player defend action
            playerDefend();
            //Check winState
            checkWinState();
            //Enemy action
            computerAction();
            //Check winState
            checkWinState();
        } else {
            computerAction();
            checkWinState();
            playerDefend();
            checkWinState();
        }
        ArrayAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, battleLog);
        battleLogListView.setAdapter(adapter);
        //Reset Damage reduction
        player.setDamageReduction(1.0);
        enemy.setDamageReduction(1.0);
    }

    //Skill action
    public void skillAction(){

    }

    //Bag action
    public void bagAction(View view, int position){
        Toast.makeText(context, "bagAction called", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "bagAction called");
        enemyHP = view.findViewById(R.id.text_enemy_hp);
        playerHP = view.findViewById(R.id.text_player_hp);
        battleLogListView = view.findViewById(R.id.list_battle_log);
        if(player.getSPD() >= enemy.getSPD()){
            playerBag(position);
            checkWinState();
            computerAction();
            checkWinState();
        } else {
            computerAction();
            checkWinState();
            playerBag(position);
            checkWinState();

        }
        ArrayAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, battleLog);
        battleLogListView.setAdapter(adapter);
    }

    //AI Enemy action
    private void computerAction(){
        Toast.makeText(context, "computerAction called", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "computerAction called");
        //Randomly pick Enemy action to execute
        //enemyAttack();
        enemyDefend();
    }

    //Calculate damage
    public int calculateDamage(){
        return 0;
    }

    //Check if Player has won or lost the current battle
    private void checkWinState(){
        switch(checkWinLoseState()){
            case PLAYER_LOSE: //player lose
                //gameManager.setEnemy(null);
                //gameManager.setPlayer(null);
                //gameManager.resetTurnCounter();
                //sessionManager.setSession(false);
                //replace fragment with MainFragment
                break;
            case PLAYER_WIN: //player win
                //gameManager.setEnemy(null);
                //gameManager.setPlayerFullHP();
                //reward choice, execute choice
                //update and replace fragment with GameFragment
                break;
        }
    }

    public int checkWinLoseState(){
        if(player.getCurrentHP() <= 0){
            Toast.makeText(context, "You lose", Toast.LENGTH_SHORT).show();
            return PLAYER_LOSE;
        } else if(enemy.getCurrentHP() <= 0){
            Toast.makeText(context, "You win", Toast.LENGTH_SHORT).show();
            return PLAYER_WIN;
        } else {
            return -1;
        }
    }

    //toString
    public String toString(){
        return "Player\n" + player.toString()
                + "\nEnemy\n" + enemy.toString()
                + "\nturnCounter: " + turnCount;
    }
}
