package de.superklug.bugcase.bugbauserver;

import com.google.gson.Gson;
import de.superklug.bugcase.bugbauserver.builders.InventoryBuilder;
import de.superklug.bugcase.bugbauserver.builders.ItemBuilder;
import de.superklug.bugcase.bugbauserver.entities.Category;
import de.superklug.bugcase.bugbauserver.entities.mojang.ReflectionUtil;
import de.superklug.bugcase.bugbauserver.enums.InventoryType;
import de.superklug.bugcase.bugbauserver.managers.CategorieManager;
import de.superklug.bugcase.bugbauserver.managers.ConfigManager;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class BugBauserver extends JavaPlugin {
    
    private Map<String, Category> categories;
    private Map<InventoryType, Inventory> inventories;
    
    private ExecutorService executorService;
    private Gson gson;
    
    private ReflectionUtil reflectionUtil;
    
    private ConfigManager configManager;
    private CategorieManager categorieManager;
    private InventoryManager inventoryManager;
    
    private final String prefix = "§8▍ §6§lB§eug§6§lB§eauserver §8┃§3";
    private final String prefixTeam = "§8▍ §4✚ §8┃§3";
    private final String prefixBlocked = "§8▍ §c✘ §8┃§3";
    
    private final String noPermissions = "§cDu hast nicht die benötigen Rechte.";
    private final String unknownCommand = "§cDieser Befehl existiert nicht.";

    @Override
    public void onEnable() {
        init();
        
        Bukkit.getConsoleSender().sendMessage(sendMessage("{0} Das Plugin von §e{1} §3wurde erfolgreich §aaktiviert§3!", this.prefix, Bukkit.getOfflinePlayer(UUID.fromString("a1d03d2d-6637-4fcd-881e-fe1c3b22fd6c")).getName()));
    }

    @Override
    public void onDisable() {
        
        Bukkit.getServer().getWorlds().forEach((worlds) -> {
            worlds.save();
            Bukkit.getConsoleSender().sendMessage(sendMessage("{0} Die Welt: §6{1} §3wurde erfolgreich §egespeichert§3.", this.prefix, worlds.getName()));
        });
        
        Bukkit.getConsoleSender().sendMessage(sendMessage("{0} Das Plugin von §e{1} §3wurde erfolgreich §cbeendet§3!", this.prefix, Bukkit.getOfflinePlayer(UUID.fromString("a1d03d2d-6637-4fcd-881e-fe1c3b22fd6c")).getName()));
    }
    
    //<editor-fold defaultstate="collapsed" desc="init">
    private void init() {
        this.categories = new LinkedHashMap<>();
        this.inventories = new HashMap<>();
        
        this.executorService = Executors.newCachedThreadPool();
        
        this.gson = new Gson();
        
        this.reflectionUtil = new ReflectionUtil(this);
        
        
        this.configManager = new ConfigManager(this);
        
        this.categorieManager = new CategorieManager(this);
        this.categorieManager.reloadCategories(true);
        
        this.inventoryManager = new InventoryManager(this);
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getConfigManager">
    public ConfigManager getConfigManager() {
        return configManager;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getCategorieManager">
    public CategorieManager getCategorieManager() {
        return categorieManager;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getInventoryManager">
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getReflectionUtil">
    public ReflectionUtil getReflectionUtil() {
        return reflectionUtil;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getExecutorService">
    public ExecutorService getExecutorService() {
        return executorService;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getGson">
    public Gson getGson() {
        return gson;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="getItemBuilder">
    public ItemBuilder getItemBuilder(final Material type, final int amount, final short damage) {
        return new ItemBuilder(this, type, amount, damage);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getInventoryBuilder">
    public InventoryBuilder getInventoryBuilder() {
        return new InventoryBuilder(this);
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="getPrefix">
    public String getPrefix() {
        return prefix;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPrefixTeam">
    public String getPrefixTeam() {
        return prefixTeam;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPrefixBlocked">
    public String getPrefixBlocked() {
        return prefixBlocked;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getNoPermissions">
    public String getNoPermissions() {
        return noPermissions;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getUnknownCommand">
    public String getUnknownCommand() {
        return unknownCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getCategories">
    public Map<String, Category> getCategories() {
        return categories;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getInventories">
    public Map<InventoryType, Inventory> getInventories() {
        return inventories;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="sendMessage">
    public String sendMessage(final String pattern, final Object... objects) {
        return MessageFormat.format(pattern, objects).replaceAll("&", "§");
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="sendPlayerMessage">
    public void sendPlayerMessage(final Player player, final String pattern, final Object... objects) {
        player.sendMessage(MessageFormat.format(pattern, objects).replaceAll("&", "§"));
    }
    //</editor-fold>

}
