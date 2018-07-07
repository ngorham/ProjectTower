package net.ngorham.projecttower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagContent {
    //Public constants
    public static final List<BagItem> PLAYER_ITEMS = new ArrayList<>();
    //public static final Map<String, SettingsItem> ITEM_MAP = new HashMap<>();

    static {
        //Player starting bag
        addPlayerItem(createBagItem(0, "Heal player for 20% of their HP", "Potion", 5, R.drawable.ic_plus_circle_outline_grey600_48dp , 0, 0.2, 0.0));
        addPlayerItem(createBagItem(1, "Heal player for 40% of their HP", "Mid Potion", 5, R.drawable.ic_plus_circle_outline_grey600_48dp,0, 0.4, 0.0));
        addPlayerItem(createBagItem(2, "Heal player for 60% of their HP", "High Potion", 5, R.drawable.ic_plus_circle_outline_grey600_48dp, 0, 0.6, 0.0));
    }

    private static void addPlayerItem(BagItem item) {
        PLAYER_ITEMS.add(item);
        //ITEM_MAP.put(item.id, item);
    }

    private static BagItem createBagItem(int position, String text, String name, int qty, int icon, int type, double HP, double AP){
        return new BagItem(position, text, name, qty, icon, type, HP, AP);
    }
}
