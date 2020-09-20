package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuEvent extends Menu{

	public MenuEvent(UUID uuid){
        super(uuid, new byte[] {3}, Bukkit.createInventory(null, 27, "§6Events"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, Hub.getInstance());
        super.setItem(18, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
	}


    @Override
    protected void back(UUID uuid) {
        MenuPrincipal.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {

    }
}
