package me.spuny.xpbottle.manager;

import me.spuny.xpbottle.Main;
import me.spuny.xpbottle.utils.Utils;
import me.spuny.xpbottle.utils.colors.ColorAPI;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpManager {

    private Main plugin;

    HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    private int limitMin;
    private int limitMax;
    private int cooldown;

    private String adminPermissions;
    private String usePermissions;

    private String itemName;
    private Material itemType;
    private List<String> itemLore;

    private String configReloadMessage;
    private String noPermMessage;
    private String playerCurrentXpMessage;
    private String outOfXPMessage;
    private String xpLimitMessage;
    private String coolDownMessage;
    private String addXpMessage;
    private String tutorialCmdMessage;

    public ExpManager() {
        this.plugin = Main.getInstance();
        loadData();
        loadMessages();
    }

    public void loadData() {
        cooldowns = new HashMap<>();
        itemName = plugin.getConfig().getString("item.name");
        itemType = Material.valueOf(plugin.getConfig().getString("item.type"));
        itemLore = plugin.getConfig().getStringList("item.lore");

        adminPermissions = plugin.getConfig().getString("permissions.player.admin");
        usePermissions = plugin.getConfig().getString("permissions.player.use");


        cooldown = plugin.getConfig().getInt("limits.cooldownTimer");
        limitMin = plugin.getConfig().getInt("limits.min");
        limitMax = plugin.getConfig().getInt("limits.max");
    }

    public void loadMessages() {
        playerCurrentXpMessage = plugin.getConfig().getString("messages.player.currentXp");
        outOfXPMessage = plugin.getConfig().getString("messages.player.outOfXp");
        xpLimitMessage = plugin.getConfig().getString("messages.player.xpLimit");
        coolDownMessage = plugin.getConfig().getString("messages.player.cooldown");
        addXpMessage = plugin.getConfig().getString("messages.player.addXp");
        tutorialCmdMessage = plugin.getConfig().getString("messages.player.tutorial");
        configReloadMessage = plugin.getConfig().getString("messages.player.configReload");
        noPermMessage = plugin.getConfig().getString("messages.player.noPerm");
    }

    public void setupPlayerData(Player player) {
        player.setExp(0.0F);
        player.setLevel(0);
        player.setTotalExperience(0);
    }

    public void createNewBottle(Player player, int xp) {
        String pattern = Utils.convertNumber("###,###", xp);

        ItemStack itemBottle = new ItemStack(plugin.getExpManager().getItemType());
        ItemMeta meta = itemBottle.getItemMeta();
        if (meta == null) {
            return;
        }

        meta.setDisplayName(ColorAPI.colorize(plugin.getExpManager().getItemName().replace("%expNumber%", pattern)));
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "exp"), PersistentDataType.INTEGER, xp);

        ArrayList<String> lore = new ArrayList<>();
        for (String lores : plugin.getExpManager().getItemLore()) {
            lore.add(ColorAPI.colorize(lores.replace("%expNumber%", pattern).replace("%playerName%", player.getName())));
        }

        meta.setLore(lore);
        itemBottle.setItemMeta(meta);
        player.getInventory().addItem(itemBottle);


    }


    public String getConfigReloadMessage() {
        return configReloadMessage;
    }

    public HashMap<String, Long> getCooldowns() {
        return cooldowns;
    }

    public String getPlayerCurrentXpMessage() {
        return playerCurrentXpMessage;
    }

    public String getOutOfXPMessage() {
        return outOfXPMessage;
    }

    public String getXpLimitMessage() {
        return xpLimitMessage;
    }

    public String getAddXpMessage() {
        return addXpMessage;
    }

    public String getCoolDownMessage() {
        return coolDownMessage;
    }

    public int getLimitMin() {
        return limitMin;
    }

    public int getLimitMax() {
        return limitMax;
    }

    public String getItemName() {
        return itemName;
    }

    public Material getItemType() {
        return itemType;
    }

    public List<String> getItemLore() {
        return itemLore;
    }

    public String getTutorialCmdMessage() {
        return tutorialCmdMessage;
    }

    public String getAdminPermissions() {
        return adminPermissions;
    }

    public String getUsePermissions() {
        return usePermissions;
    }

    public String getNoPermMessage() {
        return noPermMessage;
    }

    public int getCooldown() {
        return cooldown;
    }
}
