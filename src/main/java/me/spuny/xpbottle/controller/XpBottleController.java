package me.spuny.xpbottle.controller;

import me.spuny.xpbottle.Main;
import me.spuny.xpbottle.utils.Messages;
import org.bukkit.entity.Player;

public class XpBottleController {

    private Main plugin;

    public XpBottleController(){
        plugin = Main.getInstance();
    }

    public void putPlayerIntoCooldown(Player player) {
        long timer = plugin.getExpManager().getCooldown();
        plugin.getExpManager().getCooldowns().put(player.getName().toLowerCase(), System.currentTimeMillis() + (timer * 1000));
    }

    public void reloadPluginData(Player player) {
        if (!player.hasPermission(plugin.getExpManager().getAdminPermissions())) {
            Messages.sendMsg(player, plugin.getExpManager().getNoPermMessage());
            return;
        }

        plugin.getUtilityConfig().save();
        plugin.getExpManager().loadData();
        plugin.getExpManager().loadMessages();
        Messages.sendMsg(player, plugin.getExpManager().getConfigReloadMessage());
    }

    public void editPlayerXP(Player player, int currentXp, int xp){
        plugin.getExpManager().setupPlayerData(player);

        player.giveExp(currentXp - xp);
    }

    public boolean checkXpIsNumber(String[] args) {
        int xp;
        try {
            xp = Integer.parseInt(args[0]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
