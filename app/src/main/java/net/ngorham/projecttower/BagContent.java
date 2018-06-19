package net.ngorham.projecttower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagContent {
    //Public constants
    public static final List<BagItem> ITEMS = new ArrayList<>();
    //public static final Map<String, SettingsItem> ITEM_MAP = new HashMap<>();

    static {
        addItem(createBagItem(0, "One"));
        addItem(createBagItem(1, "Two"));
        addItem(createBagItem(2, "Three"));
        addItem(createBagItem(3, "Four"));
    }
    private static void addItem(BagItem item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.id, item);
    }

    private static BagItem createBagItem(int position, String text){
        return new BagItem(position, text);
    }

    //SettingsItem
    public static class BagItem {
        //private variables
        private int id;
        //private String id;
        private String content;

        public BagItem(int id, String content){
            this.id = id;
            this.content = content;
        }

        public int getId(){ return id; }

        public String getContent(){ return content; }
    }
}
