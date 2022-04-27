package me.spuny.xpbottle.commands;

import me.spuny.xpbottle.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class XpBottleCommand implements CommandExecutor {

    private Main plugin;

    public XpBottleCommand() {
        plugin = Main.getInstance();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage("§2§lXP §8§l>> §aAktuálně máš: §e" + player.getTotalExperience() + " xp (" + player.getLevel() + " levelů)");
            return true;
        }

        if(args.length > 0){
            int currentXp = player.getTotalExperience();
            int xp = Integer.parseInt(args[0]);

            player.setExp(0.0F);
            player.setLevel(0);
            player.setTotalExperience(0);

            player.giveExp(currentXp - xp);

            item(player, xp);



            player.sendMessage("§2§lXP §8§l>> §aAktuálně máš: §e" + player.getTotalExperience() + " xp (" + player.getLevel() + " levelů)");
            return true;
        }

        return true;
    }

    public void item(Player player, int xp){
        ItemStack xpBottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = xpBottle.getItemMeta();
        meta.setDisplayName("§e" + xp + " exp");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§eTato lahvička obsahuje " + xp + " expů");
        meta.setLore(lore);
        xpBottle.setItemMeta(meta);
        player.getInventory().addItem(xpBottle);
    }
}
