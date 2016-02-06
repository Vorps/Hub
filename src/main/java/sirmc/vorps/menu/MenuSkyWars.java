package sirmc.vorps.menu;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuSkyWars {
	public static void HubmenuSkyWars(PlayerHub playerHub){
		Inventory menuSkyWars = Bukkit.createInventory(null, 36, "§6Menu Principal > §6SkyWars");
		menuSkyWars.setItem(0, new Item(160).withData((byte) 5).withName(" ").get());
		menuSkyWars.setItem(1, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(2, new Item(160).withData((byte) 5).withName(" ").get());
		menuSkyWars.setItem(3, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(4, new Item(Material.BOW).withName("§6SkyWars").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menuSkyWars.setItem(5, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(6, new Item(160).withData((byte) 5).withName(" ").get());
		menuSkyWars.setItem(7, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(8, new Item(160).withData((byte) 5).withName(" ").get());
		
		menuSkyWars.setItem(9, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(10, new Item(Material.SIGN).withName("§6Panneaux").get());
		menuSkyWars.setItem(13, new Item(Material.ENDER_PEARL).withName("§6Partie al§atoire").get());
		menuSkyWars.setItem(16, new Item(Material.ENDER_CHEST).withName("§6Boutique du jeu").get());
		menuSkyWars.setItem(17, new Item(160).withData((byte) 13).withName(" ").get());
		
		menuSkyWars.setItem(18, new Item(160).withData((byte) 5).withName(" ").get());
		
		menuSkyWars.setItem(19, new Item(Material.PAPER).withName("§6Frozen - Solo").get());
		menuSkyWars.setItem(20, new Item(Material.PAPER).withName("§6{Nom} - Solo").get());
		
		menuSkyWars.setItem(22, new Item(Material.PAPER).withName("§6Nemo - Solo").get());
		
		menuSkyWars.setItem(24, new Item(Material.PAPER).withName("§6{Nom} - Team").get());
		menuSkyWars.setItem(25, new Item(Material.PAPER).withName("§6{Nom} - Team").get());
		
		menuSkyWars.setItem(26, new Item(160).withData((byte) 5).withName(" ").get());
		
		menuSkyWars.setItem(27, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());

		menuSkyWars.setItem(28, new Item(160).withData((byte) 5).withName(" ").get());
		menuSkyWars.setItem(29, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(30, new Item(160).withData((byte) 5).withName(" ").get());
		menuSkyWars.setItem(31, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(32, new Item(160).withData((byte) 5).withName(" ").get());
		menuSkyWars.setItem(33, new Item(160).withData((byte) 13).withName(" ").get());
		menuSkyWars.setItem(34, new Item(160).withData((byte) 5).withName(" ").get());
		menuSkyWars.setItem(35, new Item(160).withData((byte) 13).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuSkyWars);
	}
}
