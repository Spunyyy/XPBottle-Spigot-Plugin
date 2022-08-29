package me.spuny.xpbottle.utils;

import com.google.common.base.Charsets;
import me.spuny.xpbottle.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UtilityConfig {

    private String fileName;

    private File file;

    private FileConfiguration configuration;

    public UtilityConfig(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Setupne config (vytvoří file, pokud je potřeba, loadne z něj data, atd.)
     *
     * @param downloadFromResources Pokud soubor neexistuje, má se vložit soubor z "resources" složky (true), nebo jen vytvořit prázdný soubor? (false)
     */
    public void setup(boolean downloadFromResources) {
        if (!Main.getInstance().getDataFolder().exists()) {
            Main.getInstance().getDataFolder().mkdir();
        }

        file = new File(Main.getInstance().getDataFolder() + "/"+fileName+".yml");

        if (!file.exists()) {

            if (downloadFromResources) {
                Main.getInstance().saveResource(fileName+".yml", false);
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);

        reloadConfig();
    }

    private void reloadConfig() {
        configuration = YamlConfiguration.loadConfiguration(file);
        InputStream defConfigStream = Main.getInstance().getResource(fileName+".yml");
        if (defConfigStream != null) {
            configuration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
    }

    /**
     * Vrací KONFIGURACI (tedy data ze souboru). Můžete je upravovat, ale bacha!
     * To, že je upravíte v KONFIGURACI neznamená, že je upravíte i v SOUBORU!
     * Data pak musíte ULOŽIT pomocí metody #save() níže!
     *
     * @return
     */
    public FileConfiguration get() {
        return configuration;
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException ignored) {
        }
    }
}