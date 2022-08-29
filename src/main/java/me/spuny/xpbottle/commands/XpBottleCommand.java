package me.spuny.xpbottle.commands;

import me.spuny.xpbottle.Main;
import me.spuny.xpbottle.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XpBottleCommand implements CommandExecutor {

    private Main plugin;

    public XpBottleCommand() {
        plugin = Main.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getLogger().warning("Only player can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(plugin.getExpManager().getUsePermissions())) {
            Messages.sendMsg(player, plugin.getExpManager().getNoPermMessage());
            return true;
        }


        if (args.length == 0) {
            Messages.sendMsg(player, plugin.getExpManager().getPlayerCurrentXpMessage()
                    .replace("%exp%", String.valueOf(player.getTotalExperience()))
                    .replace("%level%", String.valueOf(player.getLevel())));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.getXpBottleController().reloadPluginData(player);
            return true;
        }

        if (!plugin.getXpBottleController().checkXpIsNumber(args)) {
            Messages.sendMsg(player, plugin.getExpManager().getTutorialCmdMessage());
            return true;
        }

        int xp = Integer.parseInt(args[0]);
        int currentXp = player.getTotalExperience();

        if (xp > currentXp) {
            Messages.sendMsg(player, plugin.getExpManager().getOutOfXPMessage());
            return true;
        }

        if (xp < plugin.getExpManager().getLimitMin() || xp > plugin.getExpManager().getLimitMax()) {
            Messages.sendMsg(player, plugin.getExpManager().getXpLimitMessage()
                    .replace("%limitMin%", String.valueOf(plugin.getExpManager().getLimitMin()))
                    .replace("%limitMax%", String.valueOf(plugin.getExpManager().getLimitMax())));
            return true;
        }

        if (plugin.getExpManager().getCooldowns().containsKey(player.getName().toLowerCase())) {
            if (plugin.getExpManager().getCooldowns().get(player.getName().toLowerCase()) > System.currentTimeMillis()) {
                long timeLeft = (plugin.getExpManager().getCooldowns().get(player.getName().toLowerCase()) - System.currentTimeMillis()) / 1000;
                Messages.sendMsg(player, plugin.getExpManager().getCoolDownMessage().replace("%timeLeft%", String.valueOf(timeLeft)));
                return true;
            }
        }

        plugin.getXpBottleController().putPlayerIntoCooldown(player);
        plugin.getXpBottleController().editPlayerXP(player, currentXp, xp);
        plugin.getExpManager().createNewBottle(player, xp);

        Messages.sendMsg(player, plugin.getExpManager().getPlayerCurrentXpMessage()
                .replace("%exp%", String.valueOf(player.getTotalExperience()))
                .replace("%level%", String.valueOf(player.getLevel())));
        return true;
    }
}
