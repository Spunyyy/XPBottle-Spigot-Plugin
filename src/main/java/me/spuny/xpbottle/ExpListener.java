package me.spuny.xpbottle;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ExpListener implements Listener {

    @EventHandler
    public void addXp(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();

        ItemStack item = e.getItem();
        if (item == null) {
            return;
        }
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return;
        }

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK && e.getItem().getType() == Material.EXPERIENCE_BOTTLE) {
            if (!hasKey(itemMeta, "exp", PersistentDataType.INTEGER)) {
                return;
            }
            int exp = getInteger(itemMeta, "exp");
            player.giveExp(exp);
            item.setAmount(item.getAmount() - 1);
            player.sendMessage("§2§lXP §8§l>> §aBylo ti přidáno §e" + exp + " §axp");
            e.setCancelled(true);
        }
    }

    public static Integer getInteger(ItemMeta itemMeta, String key) {
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getInstance(), key), PersistentDataType.INTEGER);
    }

    public static boolean hasKey(ItemMeta itemMeta, String key, PersistentDataType type) {
        return itemMeta.getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), key), type);
    }
}
