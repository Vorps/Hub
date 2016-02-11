package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuGrades {
	public static void HubMenuGrades(PlayerHub playerHub){
		Purchase purchase = new Purchase(playerHub, "ce grade");
		Inventory menuGrades = Bukkit.createInventory(null, 45, "§6Boutique > Grades");
		menuGrades.setItem(0, new Item(160).withData((byte) 11).withName(" ").get());
		menuGrades.setItem(1, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(2, new Item(160).withData((byte) 5).withName(" ").get());
		menuGrades.setItem(3, new Item(160).withData((byte) 11).withName(" ").get());
		menuGrades.setItem(4, new Item(Material.ENCHANTMENT_TABLE).withName("§6Grades").get());
		menuGrades.setItem(5, new Item(160).withData((byte) 11).withName(" ").get());
		menuGrades.setItem(6, new Item(160).withData((byte) 5).withName(" ").get());
		menuGrades.setItem(7, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(8, new Item(160).withData((byte) 11).withName(" ").get());
		menuGrades.setItem(9, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(17, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(18, new Item(160).withData((byte) 5).withName(" ").get());
		menuGrades.setItem(20, new Item(38).withData((byte) 1).withName(" ").get());
		menuGrades.setItem(21, new Item(Material.GOLD_ORE).withName("§6Grade §aVIP").withLore(purchase.purchaseGrade("VIP")).get());
		menuGrades.setItem(22, new Item(Material.DIAMOND_ORE).withName("§6Grade §aHéros").withLore(purchase.purchaseGrade("Héros")).get());
		menuGrades.setItem(23, new Item(Material.EMERALD_ORE).withName("§6Grade §aLégende").withLore(purchase.purchaseGrade("Légende")).get());
		menuGrades.setItem(24, new Item(38).withData((byte) 1).withName(" ").get());
		menuGrades.setItem(26, new Item(160).withData((byte) 5).withName(" ").get());
		menuGrades.setItem(27, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(35, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
		menuGrades.setItem(37, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(38, new Item(160).withData((byte) 5).withName(" ").get());
		menuGrades.setItem(39, new Item(160).withData((byte) 11).withName(" ").get());
		menuGrades.setItem(40, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(41, new Item(160).withData((byte) 11).withName(" ").get());
		menuGrades.setItem(42, new Item(160).withData((byte) 5).withName(" ").get());
		menuGrades.setItem(43, new Item(160).withData((byte) 4).withName(" ").get());
		menuGrades.setItem(44, new Item(160).withData((byte) 11).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuGrades);
	}
}
