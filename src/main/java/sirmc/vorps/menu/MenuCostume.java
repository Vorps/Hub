package sirmc.vorps.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuCostume {
	private static List<String> des = new ArrayList<String>();
	
	public static void HubMenuCostume(PlayerHub playerHub){
		 Inventory menuCostume = Bukkit.createInventory(null, 45, "§6Boutique > Costumes");
		 menuCostume.setItem(0, new Item(160).withData((byte) 3).withName(" ").get());
		 menuCostume.setItem(1, new Item(160).withData((byte) 14).withName(" ").get());
		 menuCostume.setItem(2, new Item(160).withData((byte) 3).withName(" ").get());
		 menuCostume.setItem(3, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(4, new Item(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").get());
		 menuCostume.setItem(5, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(6, new Item(160).withData((byte) 3).withName(" ").get());
		 menuCostume.setItem(7, new Item(160).withData((byte) 14).withName(" ").get());
		 menuCostume.setItem(8, new Item(160).withData((byte) 3).withName(" ").get());
		 menuCostume.setItem(9, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(17, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(18, new Item(160).withData((byte) 14).withName(" ").get());
		 menuCostume.setItem(19, new Item(Material.LEATHER_HELMET).withName("§6Chapeau").withLore(new String[] {"§7Chapeaux"}).get());
		 menuCostume.setItem(21, new Item(Material.LEATHER_CHESTPLATE).withName("§6Plastron").withLore(new String[] {"§7Plastron"}).get());
		 menuCostume.setItem(13, new Item(Material.BARRIER).withName("§6Retire votre costume").withLore(new String[] {"§7Retire votre costume"}).get());
		 menuCostume.setItem(23, new Item(Material.LEATHER_LEGGINGS).withName("§6Pantalon").withLore(new String[] {"§7Pantalon"}).get());
		 menuCostume.setItem(25, new Item(Material.LEATHER_BOOTS).withName("§6Bottes").withLore(new String[] {"§7Bottes"}).get());
		 menuCostume.setItem(26, new Item(160).withData((byte) 14).withName(" ").get());
		 menuCostume.setItem(27, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(35, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		 menuCostume.setItem(37, new Item(160).withData((byte) 14).withName(" ").get());
		 menuCostume.setItem(38, new Item(160).withData((byte) 3).withName(" ").get());
		 menuCostume.setItem(39, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(40, new Item(160).withData((byte) 14).withName(" ").get());
		 menuCostume.setItem(41, new Item(160).withData((byte) 1).withName(" ").get());
		 menuCostume.setItem(42, new Item(160).withData((byte) 3).withName(" ").get());
		 menuCostume.setItem(43, new Item(160).withData((byte) 14).withName(" ").get());
		 menuCostume.setItem(44, new Item(160).withData((byte) 3).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuCostume);
	}
}
