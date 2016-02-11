package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuRush {
	public static void HubmenuRush(PlayerHub playerHub){
		Inventory menuRush = Bukkit.createInventory(null, 36, "§6Menu Principal > Rush");
		menuRush.setItem(0, new Item(160).withData((byte) 14).withName(" ").get());
		menuRush.setItem(1, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(2, new Item(160).withData((byte) 14).withName(" ").get());
		menuRush.setItem(3, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(4, new Item(Material.BED).withName("§6Rush").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menuRush.setItem(5, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(6, new Item(160).withData((byte) 14).withName(" ").get());
		menuRush.setItem(7, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(8, new Item(160).withData((byte) 14).withName(" ").get());
		
		menuRush.setItem(9, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(10, new Item(Material.SIGN).withName("§6Panneaux").get());
		menuRush.setItem(13, new Item(Material.ENDER_PEARL).withName("§6Partie aléatoire").get());
		menuRush.setItem(16, new Item(Material.ENDER_CHEST).withName("§6Boutique du jeu").get());
		menuRush.setItem(17, new Item(160).withData((byte) 0).withName(" ").get());
		
		menuRush.setItem(18, new Item(160).withData((byte) 14).withName(" ").get());
		
		menuRush.setItem(20, new Item(Material.PAPER).withName("§6{Nom} - 2v2").get());
		menuRush.setItem(21, new Item(Material.PAPER).withName("§6Benza - 4v4").get());
		
		menuRush.setItem(23, new Item(Material.PAPER).withName("§6FairyWorld - 2v2").get());
		menuRush.setItem(24, new Item(Material.PAPER).withName("§6Orc - 4v4").get());
		
		menuRush.setItem(26, new Item(160).withData((byte) 14).withName(" ").get());
		
		menuRush.setItem(27, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());

		menuRush.setItem(28, new Item(160).withData((byte) 14).withName(" ").get());
		menuRush.setItem(29, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(30, new Item(160).withData((byte) 14).withName(" ").get());
		menuRush.setItem(31, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(32, new Item(160).withData((byte) 14).withName(" ").get());
		menuRush.setItem(33, new Item(160).withData((byte) 0).withName(" ").get());
		menuRush.setItem(34, new Item(160).withData((byte) 14).withName(" ").get());
		menuRush.setItem(35, new Item(160).withData((byte) 0).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuRush);
	}
}
