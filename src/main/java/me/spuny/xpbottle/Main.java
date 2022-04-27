package me.spuny.xpbottle;

import me.spuny.xpbottle.commands.XpBottleCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public void onEnable() {
        instance = this;

        this.getCommand("xpbottle").setExecutor(new XpBottleCommand());
    }

    public void onDisable() {
    }

    public static Main getInstance() {
        return instance;
    }

}
