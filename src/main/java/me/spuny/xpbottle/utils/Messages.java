package me.spuny.xpbottle.utils;

import org.bukkit.entity.Player;

public class Messages {

    public static void sendMsg(Player player, String message) {
        player.sendMessage(Utils.colorize(message));
    }


}
