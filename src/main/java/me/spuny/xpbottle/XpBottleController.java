package me.spuny.xpbottle;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XpBottleController {

    private Main plugin;

    public XpBottleController(){
plugin = Main.getInstance();
    }

    public void editPlayerXP(Player player, int currentXp, int xp){
        player.setExp(0.0F);
        player.setLevel(0);
        player.setTotalExperience(0);

        player.giveExp(currentXp - xp);
    }

    public void createItem(Player player, int xp){
        DecimalFormat formatter = new DecimalFormat("###,###");
        ItemStack xpBottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = xpBottle.getItemMeta();
        meta.setDisplayName("§e" + formatter.format(xp) + " exp");
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "exp"), PersistentDataType.INTEGER, xp);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§eTato lahvička obsahuje " + formatter.format(xp) + " expů");
        lore.add("§eVytvořeno hráčem: §9" + player.getName());
        meta.setLore(lore);
        xpBottle.setItemMeta(meta);
        player.getInventory().addItem(xpBottle);
    }
}
