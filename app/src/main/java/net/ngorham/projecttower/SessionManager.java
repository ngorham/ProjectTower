package net.ngorham.projecttower;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Inventory Tracker
 * SessionManager.java
 * Utility
 * Purpose: Manages login preferences
 *
 * @author Neil Gorham
 * @version 1.0 05/15/2018
 *
 */

public class SessionManager {
    //Private constants
    private static final String TAG = "SessionManager";
    private static final String HAS_SESSION = "has_session";

    //Private variables
    private Context context;
    private SharedPreferences sharedPreferences;

    //Constructor
    public SessionManager(Context c){
        context = c;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setSession(boolean session){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(HAS_SESSION, session);
        editor.commit();
    }

    public boolean hasSession(){
        return sharedPreferences.getBoolean(HAS_SESSION, false);
    }
}
