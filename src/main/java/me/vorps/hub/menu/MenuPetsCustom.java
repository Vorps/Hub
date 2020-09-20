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
public class MenuPetsCustom extends Menu{

	public MenuPetsCustom (UUID uuid, ItemStack item){
        super(uuid, null, Bukkit.createInventory(null, 54, "§6Boutique > Pets > Mon Pet"), null, Hub.getInstance());
		super.setItem(4, new ItemBuilder(Material.BONE).withName("§6Mon pet").get());
		super.setItem(10, new ItemBuilder(Material.LEATHER_HELMET).withName("§6Chapeau").get());
		super.setItem(13, item);
		super.setItem(19, new ItemBuilder(Material.LEATHER_CHESTPLATE).withName("§6Plastron").get());
		super.setItem(25, new ItemBuilder(Material.BLAZE_POWDER).withName("§6Se Métamorphoser").get());
		super.setItem(28, new ItemBuilder(Material.LEATHER_CHESTPLATE).withName("§6Plastron").get());
		super.setItem(31, new ItemBuilder(Material.NAME_TAG).withName("§6Activer mon pet").get());
		super.setItem(34, new ItemBuilder(Material.SADDLE).withName("§6Chevaucher").get());
		super.setItem(37, new ItemBuilder(Material.LEATHER_BOOTS).withName("§6Bottes").get());
		super.setItem(40, new ItemBuilder(Material.ENDER_PEARL).withName("§6Amélioration de pouvoir").get());
		super.setItem(43, new ItemBuilder(Material.GOLDEN_BOOTS).withName("§6Mode Balade").get());
	}

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {

    }
}
