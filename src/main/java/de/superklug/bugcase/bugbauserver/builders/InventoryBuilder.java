package de.superklug.bugcase.bugbauserver.builders;

import de.superklug.bugcase.bugbauserver.BugBauserver;

public class InventoryBuilder {
    
    private final BugBauserver plugin;

    public InventoryBuilder(BugBauserver plugin) {
        this.plugin = plugin;
    }
    
    public int generateInventorySize(final int size) {
        
        if(size <= 9) {
            return 9;
        } else if(size > 9 && size <= 18) {
            return 18;
        } else if(size > 18 && size <= 27) {
            return 27;
        } else if(size > 27 && size <= 36) {
            return 36;
        } else if(size > 36 && size <= 45) {
            return 45;
        } else if(size > 45 && size <= 54) {
            return 54;
        }
        
        return 9;
    }

}
