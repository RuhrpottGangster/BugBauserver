package de.superklug.bugcase.bugbauserver;

import de.superklug.bugcase.bugbauserver.entities.Category;
import de.superklug.bugcase.bugbauserver.enums.InventoryType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {
    
    private final BugBauserver plugin;
    
    private final ItemStack pattern;

    public InventoryManager(BugBauserver plugin) {
        this.pattern = plugin.getItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7).setNoName().build();
        this.plugin = plugin;
        createInventories();
    }
    
    private void createInventories() {
        
        {
            Inventory inventory = Bukkit.createInventory(null, 27, "§8► BugBauserver System");
            
            for(int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, pattern);
            }
            
            inventory.setItem(9+3, plugin.getItemBuilder(Material.PAPER, 1, (short) 0).setDisplayname("§aKategorien").setLore("§8§m----------------------", "§8► §3Klicke um alle §6Kategorien", " §3zu §esehen §3und §everwalten§3.").build());
            inventory.setItem(9+5, plugin.getItemBuilder(Material.NETHER_STAR, 1, (short) 0).setDisplayname("§cExtras").setLore("§8§m----------------------", "§8► §3Klicke um alle §6Extras", " §3zu §esehen §3und §ebenutzen§3.").build());
            
            plugin.getInventories().put(InventoryType.MENU, inventory);
        }
        
        {
            final int categories = plugin.getCategories().size();
            
            Inventory inventory = Bukkit.createInventory(null, plugin.getInventoryBuilder().generateInventorySize(categories), plugin.sendMessage("§8► §e{0} §6{1}", categories, (categories == 1 ? "Kategorie" : "Kategorien")));

            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, pattern);
            }

            int i = 0;
            for (Category next : plugin.getCategories().values()) {
                inventory.setItem(i, next.getItem());
                i++;
            }

            plugin.getInventories().put(InventoryType.CATEGORIES, inventory);
        }
        
        {
            Inventory inventory = Bukkit.createInventory(null, 27, "§8► Map erstellen");

            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, pattern);
            }

            inventory.setItem(9 + 2, plugin.getItemBuilder(Material.BARRIER, 1, (short) 0).setDisplayname("§cLeere Welt").setLore("§8§m----------------------", plugin.sendMessage("§8► §3Erstelle eine {0}", "§cleere Welt")).build());
            inventory.setItem(9 + 4, plugin.getItemBuilder(Material.BEDROCK, 1, (short) 0).setDisplayname("§bFlache Welt").setLore("§8§m----------------------", plugin.sendMessage("§8► §3Erstelle eine {0}", "§bflache Welt")).build());
            inventory.setItem(9 + 6, plugin.getItemBuilder(Material.GRASS, 1, (short) 0).setDisplayname("§aNormale Welt").setLore("§8§m----------------------", plugin.sendMessage("§8► §3Erstelle eine {0}", "§anormale Welt")).build());

            plugin.getInventories().put(InventoryType.CREATEMAP, inventory);
        }
        
    }

}
