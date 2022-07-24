package me.spuny.xpbottle.commands;

import me.spuny.xpbottle.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class XpBottleCommand implements CommandExecutor {

    private Main plugin;

    public XpBottleCommand() {
        plugin = Main.getInstance();
    }

    HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // Nejdříve checknout sender instanceof Player, jinak return
        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage("§2§lXP §8§l>> §aAktuálně máš: §e" + player.getTotalExperience() + " xp (" + player.getLevel() + " levelů)");
            return true;
        }

        // Zbytečný jelikož args.length v tento moment musí být > 0
        if(args.length > 0){

            // int xp = 0
            try {
                int xp = Integer.parseInt(args[0]);
            } catch (Throwable e) {
                // Exception místo Throwable - optional
                player.sendMessage("§2§lXP §8§l>> §c/xpbottle <číslo>");
                // Return true
                return false;
            }

            int currentXp = player.getTotalExperience();
            int xp = Integer.parseInt(args[0]);

            if(xp > currentXp){
                player.sendMessage("§2§lXP §8§l>> §cNemáš dostatek XP");
                return true;
            }

            // Možná do configu?
            if(xp < 100 || xp > 100000) {
                player.sendMessage("§2§lXP §8§l>> §cXP lze vybrat od 100 do 100 000");
                return true;
            }

            if(cooldowns.containsKey(player.getName())){
                if(cooldowns.get(player.getName()) > System.currentTimeMillis()){
                    long timeLeft = (cooldowns.get(player.getName()) - System.currentTimeMillis()) / 1000;
                    player.sendMessage("§2§lXP §8§l>> §cPříkaz nelze znovu použít po dobu §e" + timeLeft + " §csekund");
                    return true;
                }
            }

            cooldowns.put(player.getName(), System.currentTimeMillis() + (5 * 1000));

            plugin.getXpBottleController().editPlayerXP(player, currentXp, xp);
            plugin.getXpBottleController().createItem(player, xp);

            player.sendMessage("§2§lXP §8§l>> §aAktuálně máš: §e" + player.getTotalExperience() + " xp (" + player.getLevel() + " levelů)");
            return true;
        }

        return true;
    }
}
