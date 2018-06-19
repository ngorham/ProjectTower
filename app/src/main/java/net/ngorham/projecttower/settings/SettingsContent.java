package net.ngorham.projecttower.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsContent {
    //Public constants
    public static final List<SettingsItem> ITEMS = new ArrayList<>();
    //public static final Map<String, SettingsItem> ITEM_MAP = new HashMap<>();

    static {
        addItem(createSettingsItem(0, "Bag Settings"));
        addItem(createSettingsItem(1, "Save & Exit"));
    }
    private static void addItem(SettingsItem item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.id, item);
    }

    private static SettingsItem createSettingsItem(int position, String text){
        return new SettingsItem(position, text);
    }

    //SettingsItem
    public static class SettingsItem {
        //private variables
        private int id;
        //private String id;
        private String content;

        public SettingsItem(int id, String content){
            this.id = id;
            this.content = content;
        }

        public int getId(){ return id; }

        public String getContent(){ return content; }
    }
}
