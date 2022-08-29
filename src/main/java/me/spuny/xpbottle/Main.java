package me.spuny.xpbottle;

import me.spuny.xpbottle.commands.XpBottleCommand;
import me.spuny.xpbottle.controller.XpBottleController;
import me.spuny.xpbottle.listener.ExpListener;
import me.spuny.xpbottle.manager.ExpManager;
import me.spuny.xpbottle.utils.UtilityConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private XpBottleController xpBottleController;
    private ExpManager expManager;
    private UtilityConfig utilityConfig;

    @Override
    public void onEnable() {
        instance = this;

        utilityConfig = new UtilityConfig("config");
        utilityConfig.setup(true);
        expManager = new ExpManager();
        xpBottleController = new XpBottleController();

        this.getCommand("xpbottle").setExecutor(new XpBottleCommand());

        Bukkit.getPluginManager().registerEvents(new ExpListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public ExpManager getExpManager() {
        return expManager;
    }

    public XpBottleController getXpBottleController() {
        return xpBottleController;
    }

    public UtilityConfig getUtilityConfig() {
        return utilityConfig;
    }
}
