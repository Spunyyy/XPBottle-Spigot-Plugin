package me.spuny.xpbottle.utils;

import me.spuny.xpbottle.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemUtils {

    public static Integer getInteger(ItemMeta itemMeta, String key) {
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), key), PersistentDataType.INTEGER);
    }

    public static boolean hasKey(ItemMeta itemMeta, String key, PersistentDataType type) {
        return itemMeta.getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), key), type);
    }

}
