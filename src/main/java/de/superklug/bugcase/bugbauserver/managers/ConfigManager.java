package de.superklug.bugcase.bugbauserver.managers;

import de.superklug.bugcase.bugbauserver.BugBauserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    
    private final BugBauserver plugin;
    
    private File folder;
    private File categoriesFile;
    private File locationsFile;
    private File configFile;
    
    private YamlConfiguration categoriesConfiguration;
    private YamlConfiguration locationsConfiguration;
    private YamlConfiguration configConfiguration;

    public ConfigManager(BugBauserver plugin) {
        this.plugin = plugin;
        configurateConfigFiles();
    }
    
    private void configurateConfigFiles() {
        this.folder = new File("plugins/BugBauserver");
        this.categoriesFile = new File(this.folder, "categories.yml");
        this.locationsFile = new File(this.folder, "locations.yml");
        this.configFile = new File(this.folder, "config.yml");
        
        this.categoriesConfiguration = YamlConfiguration.loadConfiguration(this.categoriesFile);
        this.locationsConfiguration = YamlConfiguration.loadConfiguration(this.locationsFile);
        this.configConfiguration = YamlConfiguration.loadConfiguration(this.configFile);
        
        if(!this.folder.exists()) {
            this.folder.mkdir();
        }
        
        try {
            
            if(!this.categoriesFile.exists()) {
                this.categoriesFile.createNewFile();
                this.categoriesConfiguration.options().copyDefaults(true);
                
                this.categoriesConfiguration.addDefault("Categories.QSG.itemID", "IRON_SWORD");
                this.categoriesConfiguration.addDefault("Categories.QSG.itemSub", 0);
                this.categoriesConfiguration.addDefault("Categories.QSG.permission", "bugcase.bauserver.category.qsg.use");
                this.categoriesConfiguration.addDefault("Categories.QSG.displayname", "&aQuick Survival Games");
                this.categoriesConfiguration.addDefault("Categories.QSG.maps", new ArrayList<>());
                
                this.categoriesConfiguration.addDefault("Categories.BedWars.itemID", "BED");
                this.categoriesConfiguration.addDefault("Categories.BedWars.itemSub", 0);
                this.categoriesConfiguration.addDefault("Categories.BedWars.permission", "bugcase.bauserver.category.bedwars.use");
                this.categoriesConfiguration.addDefault("Categories.BedWars.displayname", "&cBed Wars");
                this.categoriesConfiguration.addDefault("Categories.BedWars.maps", new ArrayList<>());
                
                this.categoriesConfiguration.addDefault("Categories.SkyWars.itemID", "GRASS");
                this.categoriesConfiguration.addDefault("Categories.SkyWars.itemSub", 0);
                this.categoriesConfiguration.addDefault("Categories.SkyWars.permission", "bugcase.bauserver.category.skywars.use");
                this.categoriesConfiguration.addDefault("Categories.SkyWars.displayname", "&2Sky Wars");
                this.categoriesConfiguration.addDefault("Categories.SkyWars.maps", new ArrayList<>());
                
                this.categoriesConfiguration.addDefault("Categories.IslandClash.itemID", "GOLD_PICKAXE");
                this.categoriesConfiguration.addDefault("Categories.IslandClash.itemSub", 0);
                this.categoriesConfiguration.addDefault("Categories.IslandClash.permission", "bugcase.bauserver.category.islandclash.use");
                this.categoriesConfiguration.addDefault("Categories.IslandClash.displayname", "&eIsland Clash");
                this.categoriesConfiguration.addDefault("Categories.IslandClash.maps", new ArrayList<>());
            }
            
            if (!this.locationsFile.exists()) {
                this.locationsFile.createNewFile();
                this.locationsConfiguration.options().copyDefaults(true);
            }
            
            if (!this.configFile.exists()) {
                this.configFile.createNewFile();
                this.configConfiguration.options().copyDefaults(true);
                
                this.configConfiguration.addDefault("Settings.Worlds-To-Load", Arrays.asList("world"));
            }
            
            saveAllFiles();
            
        } catch (IOException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveAllFiles() {
        try {
            this.categoriesConfiguration.save(this.categoriesFile);
            this.locationsConfiguration.save(this.locationsFile);
            this.configConfiguration.save(this.configFile);
        } catch (IOException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveCategoriesFile() {
        try {
            this.categoriesConfiguration.save(this.categoriesFile);
        } catch (IOException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveLocationsFile() {
        try {
            this.locationsConfiguration.save(this.locationsFile);
        } catch (IOException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveConfigFile() {
        try {
            this.configConfiguration.save(this.configFile);
        } catch (IOException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File getCategoriesFile() {
        return categoriesFile;
    }

    public File getLocationsFile() {
        return locationsFile;
    }

    public File getConfigFile() {
        return configFile;
    }

    public YamlConfiguration getCategoriesConfiguration() {
        return categoriesConfiguration;
    }

    public YamlConfiguration getLocationsConfiguration() {
        return locationsConfiguration;
    }

    public YamlConfiguration getConfigConfiguration() {
        return configConfiguration;
    }

}
