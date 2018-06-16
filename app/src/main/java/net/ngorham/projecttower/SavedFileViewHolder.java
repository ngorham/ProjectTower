package net.ngorham.projecttower;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Project Tower
 * SavedFileViewHolder.java
 * Utility
 * Purpose: Provides access to a RecyclerView.ViewHolder of Saved File type item
 *
 * @author Neil Gorham
 * @version 1.0 06/15/2018
 *
 */

public class SavedFileViewHolder extends RecyclerView.ViewHolder {
    //Private variables
    private View parent;

    //Constructor
    public SavedFileViewHolder(View v){
        super(v);
        this.parent = v;
    }

    public View getParent(){ return parent; }
}
