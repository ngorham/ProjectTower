package net.ngorham.projecttower;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Project Tower
 * SavedFilesAdapter.java
 * Utility
 * Purpose: Displays list of tally counters in a RecyclerView
 *
 * @author Neil Gorham
 * @version 1.0 06/15/2018
 *
 */

public class SavedFilesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //Private constants
    private final String TAG = "TallyCounterAdapter";
    private final int SAVED_FILES_TYPE = 0;
    //Private variables
    private Context context;
    private Listener listener;
    private ArrayList<SavedFile> savedFiles;

    public interface Listener {
        void onClick(View view, int position);
    }

    //SavedFiles Constructor
    public SavedFilesAdapter(Context context, ArrayList<SavedFile> savedFiles){
        this.context = context;
        setSavedFiles(savedFiles);
    }

    //Set onClick Listener
    public void setListener(Listener listener){ this.listener = listener; }

    private void configureSavedFile(SavedFileViewHolder holder, final int position){
        SavedFile savedFile = savedFiles.get(position);
        if(savedFile != null){
            holder.getParent().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener != null){ listener.onClick(v, position); }
                }
            });
        }
    }

    //Return the item view type
    @Override
    public int getItemViewType(int position){
        if(savedFiles != null) {
            return SAVED_FILES_TYPE;
        } else {
            return -1;
        }
    }

    //Create viewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == SAVED_FILES_TYPE){
            view = inflater.inflate(R.layout.view_saved_file, parent, false);
            return new SavedFileViewHolder(view);
        }
        return null;
    }

    //Set data inside viewHolder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position){
        switch(holder.getItemViewType()){
            case SAVED_FILES_TYPE:
                SavedFileViewHolder sfvh = (SavedFileViewHolder)holder;
                configureSavedFile(sfvh, position);
                break;
        }
    }

    //Return number of items in the data set
    @Override
    public int getItemCount(){
        if(savedFiles != null) {
            return savedFiles.size();
        } else {
            return 0;
        }
    }

    public void setSavedFiles(ArrayList<SavedFile> savedFiles){ this.savedFiles = savedFiles; }
}
