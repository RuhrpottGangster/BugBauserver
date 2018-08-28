package de.superklug.bugcase.bugbauserver.managers;

import de.superklug.bugcase.bugbauserver.BugBauserver;
import de.superklug.bugcase.bugbauserver.entities.Category;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CategorieManager {
    
    private final BugBauserver plugin;

    public CategorieManager(BugBauserver plugin) {
        this.plugin = plugin;
    }
    
    public void reloadCategories(final boolean asynchronly) {
        if(asynchronly) {
            plugin.getExecutorService().execute(() -> {
                
                plugin.getConfigManager().getCategoriesConfiguration().getConfigurationSection("Categories").getKeys(false).stream().forEach((categories) -> {

                    final List<String> maps = plugin.getConfigManager().getCategoriesConfiguration().getStringList(categories + ".maps");

                    final ItemStack item = plugin.getItemBuilder(Material.getMaterial(easyPath(categories + ".itemID")), maps.size(), (short) easyPath2(categories + ".itemSub"))
                            .setDisplayname(easyPath(categories + ".displayname").replaceAll("&", "§")).build();

                    final Category category = new Category();
                        category.setItem(item);
                        category.setPermission(easyPath(categories + ".permission"));
                        category.setDisplayname(easyPath(categories + ".displayname").replaceAll("&", "§"));
                        category.setMaps(maps);

                    plugin.getCategories().put(categories.toUpperCase(), category);
                    Bukkit.getConsoleSender().sendMessage(plugin.sendMessage("{0} Die Kategorie: §6{1} §8(§e{2} Map{3}§8) §3wurde erfolgreich §easync-geladen§3.", plugin.getPrefix(), categories.toUpperCase(), maps.size(), (maps.size() == 1 ? "" : "s")));
                });
                
            });
        } else {
            
            plugin.getConfigManager().getCategoriesConfiguration().getConfigurationSection("Categories").getKeys(false).stream().forEach((categories) -> {
                
                final List<String> maps = plugin.getConfigManager().getCategoriesConfiguration().getStringList(categories + ".maps");
                
                final ItemStack item = plugin.getItemBuilder(Material.getMaterial(easyPath(categories + ".itemID")), maps.size(), (short) easyPath2(categories + ".itemSub"))
                        .setDisplayname(easyPath(categories + ".displayname").replaceAll("&", "§")).build();

                final Category category = new Category();
                    category.setItem(item);
                    category.setPermission(easyPath(categories + ".permission"));
                    category.setDisplayname(easyPath(categories + ".displayname").replaceAll("&", "§"));
                    category.setMaps(maps);

                plugin.getCategories().put(categories.toUpperCase(), category);
                Bukkit.getConsoleSender().sendMessage(plugin.sendMessage("{0} Die Kategorie: §6{1} §8(§e{2} Map{3}§8) §3wurde erfolgreich §egeladen§3.", plugin.getPrefix(), categories.toUpperCase(), maps.size(), (maps.size() == 1 ? "" : "s")));
            });
            
        }
    }
    
    private String easyPath(final String data) {
        return plugin.getConfigManager().getCategoriesConfiguration().getString(data);
    }
    
    private int easyPath2(final String data) {
        return plugin.getConfigManager().getCategoriesConfiguration().getInt(data);
    }

}