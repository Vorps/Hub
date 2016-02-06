package sirmc.vorps.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuHitIt {
	private static List<String> des = new ArrayList<String>();
	
	public static void HubmenuHitIt(PlayerHub playerHub){
		Inventory menuHitIt = Bukkit.createInventory(null, 36, "§6Menu Principal > HitIt!");
		menuHitIt.setItem(0, new Item(160).withData((byte) 11).withName(" ").get());
		menuHitIt.setItem(1, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(2, new Item(160).withData((byte) 11).withName(" ").get());
		menuHitIt.setItem(3, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(4, new Item(Material.DIAMOND_SWORD).withName("§6HitIt!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menuHitIt.setItem(5, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(6, new Item(160).withData((byte) 11).withName(" ").get());
		menuHitIt.setItem(7, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(8, new Item(160).withData((byte) 11).withName(" ").get());
		
		menuHitIt.setItem(9, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(10, new Item(Material.SIGN).withName("§6Panneaux").get());
		menuHitIt.setItem(13, new Item(Material.ENDER_PEARL).withName("§6Partie al�atoire").get());
		menuHitIt.setItem(16, new Item(Material.ENDER_CHEST).withName("§6Boutique du jeu").get());
		menuHitIt.setItem(17, new Item(160).withData((byte) 9).withName(" ").get());
		
		menuHitIt.setItem(18, new Item(160).withData((byte) 11).withName(" ").get());
		
		menuHitIt.setItem(20, new Item(Material.PAPER).withName("§6Organic - Sans Team").get());
		menuHitIt.setItem(21, new Item(Material.PAPER).withName("§6Organic - Avec Team").get());
		
		menuHitIt.setItem(23, new Item(Material.PAPER).withName("§6Map 2 - Sans Team").get());
		menuHitIt.setItem(24, new Item(Material.PAPER).withName("§6Map 2 - Avec Team").get());
		
		menuHitIt.setItem(26, new Item(160).withData((byte) 11).withName(" ").get());

		menuHitIt.setItem(27, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());

		menuHitIt.setItem(28, new Item(160).withData((byte) 11).withName(" ").get());
		menuHitIt.setItem(29, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(30, new Item(160).withData((byte) 11).withName(" ").get());
		menuHitIt.setItem(31, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(32, new Item(160).withData((byte) 11).withName(" ").get());
		menuHitIt.setItem(33, new Item(160).withData((byte) 9).withName(" ").get());
		menuHitIt.setItem(34, new Item(160).withData((byte) 11).withName(" ").get());
		menuHitIt.setItem(35, new Item(160).withData((byte) 9).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuHitIt);
	}
}
