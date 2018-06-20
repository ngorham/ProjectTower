package net.ngorham.projecttower;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.ngorham.projecttower.settings.SettingsContent.SettingsItem;

import java.util.List;

public class SettingsArrayAdapter extends ArrayAdapter<SettingsItem> {
    //Private constants
    private final Context CONTEXT;
    private final List<SettingsItem> ITEMS;

    private static class ViewHolder {
        TextView content;
    }

    public SettingsArrayAdapter(Context context, List<SettingsItem> items){
        super(context, R.layout.view_settings_item, items);
        CONTEXT = context;
        ITEMS = items;
    }

    @Override
    public SettingsItem getItem(int position){ return ITEMS.get(position); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        SettingsItem currentItem = ITEMS.get(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(CONTEXT);
            convertView = inflater.inflate(R.layout.view_settings_item, parent, false);
            viewHolder.content = convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.content.setText(currentItem.getContent());
        viewHolder.content.setTag(position);
        return convertView;
    }
}
