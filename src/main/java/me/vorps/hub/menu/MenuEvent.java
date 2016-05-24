package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuEvent extends Menu{

	public MenuEvent(Player player){
        super(new byte[] {3}, Bukkit.createInventory(null, 27, "§6Events"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}});
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
		player.openInventory(menu);
	}


    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuPrincipal.createMenu(player);
                break;
            default:
                break;
        }
    }
}
