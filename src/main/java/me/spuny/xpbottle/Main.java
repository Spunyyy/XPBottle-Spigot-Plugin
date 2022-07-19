package me.spuny.xpbottle;

import me.spuny.xpbottle.commands.XpBottleCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private XpBottleController xpBottleController;

    public void onEnable() {
        instance = this;

        xpBottleController = new XpBottleController();

        this.getCommand("xpbottle").setExecutor(new XpBottleCommand());

        ExpListener expListener = new ExpListener();
        Bukkit.getPluginManager().registerEvents(expListener, this);
    }

    public void onDisable() {
    }

    public static Main getInstance() {
        return instance;
    }

    public XpBottleController getXpBottleController() {
        return xpBottleController;
    }

}
