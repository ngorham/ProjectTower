package net.ngorham.projecttower;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.ngorham.projecttower.SettingsFragment.SettingsFragmentListener;
import net.ngorham.projecttower.settings.SettingsContent.SettingsItem;

import java.util.List;

public class SettingsRecyclerViewAdapter extends RecyclerView.Adapter<SettingsRecyclerViewAdapter.ViewHolder> {
    //Private constants
    private final List<SettingsItem> VALUES;
    private final SettingsFragmentListener LISTENER;
    //Private variables

    //Constructor
    public SettingsRecyclerViewAdapter(List<SettingsItem> items, SettingsFragmentListener listener){
        VALUES = items;
        LISTENER = listener;
    }

    @Override
    public SettingsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_settings_item, parent, false);
        return new SettingsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SettingsRecyclerViewAdapter.ViewHolder holder, int position){
        holder.item = VALUES.get(position);
        holder.contentView.setText(holder.item.getContent());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != LISTENER) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    LISTENER.settingsItemClicked(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return VALUES.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View parent;
        public final TextView contentView;
        public SettingsItem item;

        public ViewHolder(View view) {
            super(view);
            parent = view;
            contentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
