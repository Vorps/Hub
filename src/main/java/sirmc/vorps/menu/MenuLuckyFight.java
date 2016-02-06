package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuLuckyFight {

	public static void HubmenuLuckyFight(PlayerHub playerHub){
		Inventory menuLuckyFight = Bukkit.createInventory(null, 36, "§6Menu Principal > LuckyFight");
		menuLuckyFight.setItem(0, new Item(160).withData((byte) 4).withName(" ").get());
		menuLuckyFight.setItem(1, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(2, new Item(160).withData((byte) 4).withName(" ").get());
		menuLuckyFight.setItem(3, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(4, new Item(Material.EXP_BOTTLE).withName("§6LuckyFight").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menuLuckyFight.setItem(5, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(6, new Item(160).withData((byte) 4).withName(" ").get());
		menuLuckyFight.setItem(7, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(8, new Item(160).withData((byte) 4).withName(" ").get());
		
		menuLuckyFight.setItem(9, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(10, new Item(Material.SIGN).withName("§6Panneaux").get());
		menuLuckyFight.setItem(13, new Item(Material.ENDER_PEARL).withName("§6Partie aléatoire").get());
		menuLuckyFight.setItem(16, new Item(Material.ENDER_CHEST).withName("§6Boutique du jeu").get());
		menuLuckyFight.setItem(17, new Item(160).withData((byte) 1).withName(" ").get());
		
		menuLuckyFight.setItem(18, new Item(160).withData((byte) 4).withName(" ").get());
		
		menuLuckyFight.setItem(20, new Item(Material.PAPER).withName("§6{Nom} - Team autorisées").get());
		menuLuckyFight.setItem(21, new Item(Material.PAPER).withName("§6{Nom} - Team interdites").get());
		
		menuLuckyFight.setItem(23, new Item(Material.PAPER).withName("§6{Nom} - Team autorisées").get());
		menuLuckyFight.setItem(24, new Item(Material.PAPER).withName("§6{Nom} - Team interdites").get());
		
		menuLuckyFight.setItem(26, new Item(160).withData((byte) 4).withName(" ").get());
		
		
		
		menuLuckyFight.setItem(27,  new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());

		menuLuckyFight.setItem(28, new Item(160).withData((byte) 4).withName(" ").get());
		menuLuckyFight.setItem(29, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(30, new Item(160).withData((byte) 4).withName(" ").get());
		menuLuckyFight.setItem(31, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(32, new Item(160).withData((byte) 4).withName(" ").get());
		menuLuckyFight.setItem(33, new Item(160).withData((byte) 1).withName(" ").get());
		menuLuckyFight.setItem(34, new Item(160).withData((byte) 4).withName(" ").get());
		menuLuckyFight.setItem(35, new Item(160).withData((byte) 1).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuLuckyFight);
	}
}
