package de.superklug.bugcase.bugbauserver.entities;

import java.util.List;
import org.bukkit.inventory.ItemStack;

public class Category {
    
    private ItemStack item;
    private String permission;
    private String displayname;
    private List<String> maps;

    public Category() {
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public List<String> getMaps() {
        return maps;
    }

    public void setMaps(List<String> maps) {
        this.maps = maps;
    }

}
