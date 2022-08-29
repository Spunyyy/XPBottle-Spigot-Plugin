package me.spuny.xpbottle.listener;

import me.spuny.xpbottle.Main;
import me.spuny.xpbottle.utils.ItemUtils;
import me.spuny.xpbottle.utils.Messages;
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

    private Main plugin;

    public ExpListener() {
        this.plugin = Main.getInstance();
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
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

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK && e.getItem().getType() == Main.getInstance().getExpManager().getItemType()) {
            if (!ItemUtils.hasKey(itemMeta, "exp", PersistentDataType.INTEGER)) {
                return;
            }
            int exp = ItemUtils.getInteger(itemMeta, "exp");
            item.setAmount(item.getAmount() - 1);
            player.giveExp(exp);
            Messages.sendMsg(player, plugin.getExpManager().getAddXpMessage().replace("%recievedExp%", String.valueOf(exp)));
            e.setCancelled(true);

        }
    }


}
