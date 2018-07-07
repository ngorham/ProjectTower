package net.ngorham.projecttower;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BagArrayAdapter extends ArrayAdapter<BagItem> {
    //Private constants
    private final Context CONTEXT;
    private final List<BagItem> ITEMS;

    private static class ViewHolder {
        TextView content;
        TextView name;
        TextView quanity;
        ImageView icon;
    }

    public BagArrayAdapter(Context context, List<BagItem> items){
        super(context, R.layout.view_bag_item, items);
        CONTEXT = context;
        ITEMS = items;
    }

    @Override
    public BagItem getItem(int position){ return ITEMS.get(position); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BagItem currentItem = ITEMS.get(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(CONTEXT);
            convertView = inflater.inflate(R.layout.view_bag_item, parent, false);
            viewHolder.content = convertView.findViewById(R.id.text_bag_item_content);
            viewHolder.name = convertView.findViewById(R.id.text_bag_item_name);
            viewHolder.quanity = convertView.findViewById(R.id.text_bag_item_quantity);
            viewHolder.icon = convertView.findViewById(R.id.image_bag_item_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.content.setText(currentItem.getContent());
        viewHolder.name.setText(currentItem.getName());
        viewHolder.quanity.setText(String.valueOf(currentItem.getQuantity()));
        viewHolder.icon.setImageResource(currentItem.getIcon());
        viewHolder.icon.setTag(position);
        return convertView;
    }
}
