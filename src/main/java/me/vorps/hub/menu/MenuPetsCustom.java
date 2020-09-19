package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuPetsCustom extends Menu{

	public MenuPetsCustom (Player player, ItemStack item){
        super(null, Bukkit.createInventory(null, 54, "§6Boutique > Pets > Mon Pet"), null, Hub.getInstance());
		menu.setItem(4, new ItemBuilder(Material.BONE).withName("§6Mon pet").get());
		menu.setItem(10, new ItemBuilder(Material.LEATHER_HELMET).withName("§6Chapeau").get());
		menu.setItem(13, item);
		menu.setItem(19, new ItemBuilder(Material.LEATHER_CHESTPLATE).withName("§6Plastron").get());
		menu.setItem(25, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Se Métamorphoser").get());
		menu.setItem(28, new ItemBuilder(Material.LEATHER_CHESTPLATE).withName("§6Plastron").get());
		menu.setItem(31, new ItemBuilder(Material.NAME_TAG).withName("§6Activer mon pet").get());
		menu.setItem(34, new ItemBuilder(Material.SADDLE).withName("§6Chevaucher").get());
		menu.setItem(37, new ItemBuilder(Material.LEATHER_BOOTS).withName("§6Bottes").get());
		menu.setItem(40, new ItemBuilder(Material.ENDER_PEARL).withName("§6Amélioration de pouvoir").get());
		menu.setItem(43, new ItemBuilder(Material.GOLDEN_BOOTS).withName("§6Mode Balade").get());
        player.openInventory(menu);
	}

    @Override
    public void interractInventory(InventoryClickEvent e) {

    }
}
