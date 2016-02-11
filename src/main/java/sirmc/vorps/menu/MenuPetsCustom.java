package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuPetsCustom {

	public static void HubPetsCustom (PlayerHub playerHub, ItemStack item){
		Inventory menuPetsCustom = Bukkit.createInventory(null, 54, "§6Boutique > Pets > Mon Pet");
		menuPetsCustom.setItem(4, new Item(Material.BONE).withName("§6Mon pet").get());
		menuPetsCustom.setItem(10, new Item(Material.LEATHER_HELMET).withName("§6Chapeau").get());
		menuPetsCustom.setItem(13, item);
		menuPetsCustom.setItem(19, new Item(Material.LEATHER_CHESTPLATE).withName("§6Plastron").get());
		menuPetsCustom.setItem(25, new Item(Material.BLAZE_POWDER).withName("§6Se Métamorphoser").get());
		menuPetsCustom.setItem(28, new Item(Material.LEATHER_CHESTPLATE).withName("§6Plastron").get());
		menuPetsCustom.setItem(31, new Item(Material.NAME_TAG).withName("§6Activer mon pet").get());
		menuPetsCustom.setItem(34, new Item(Material.SADDLE).withName("§6Chevaucher").get());
		menuPetsCustom.setItem(37, new Item(Material.LEATHER_BOOTS).withName("§6Bottes").get());
		menuPetsCustom.setItem(40, new Item(Material.ENDER_PEARL).withName("§6Amélioration de pouvoir").get());
		menuPetsCustom.setItem(43, new Item(Material.GOLD_BOOTS).withName("§6Mode Balade").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuPetsCustom);
	}
}
