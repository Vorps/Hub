package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuChestplate {

	public static void HubMenuChestplate(PlayerHub playerHub) {
		Purchase purchase = new Purchase(playerHub, "ce plastron sur votre torse");
		Inventory menuChestplate = Bukkit.createInventory(null, 54, "§6Boutique > Costumes > ");
		menuChestplate.setItem(0, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(1, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(2, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(3, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(4, new Item(Material.GOLD_CHESTPLATE).withName("§6Plastrons").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menuChestplate.setItem(5, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(6, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(7, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(8, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(9, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(10, new Item(Material.CHAINMAIL_CHESTPLATE).withName("§6Cotte de maille").withLore(purchase.purchase("Chainmail_chestplate")).get());
		menuChestplate.setItem(11, new Item(Material.GOLD_CHESTPLATE).withName("§6Or").withLore(purchase.purchase("Gold_chestplate")).get());
		menuChestplate.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon plastron").withLore(new String[]{"§7Retire votre plastron actuel"}).get());
		menuChestplate.setItem(15, new Item(Material.IRON_CHESTPLATE).withName("§6Fer").withLore(purchase.purchase("Iron_chestplate")).get());
		menuChestplate.setItem(16, new Item(Material.DIAMOND_CHESTPLATE).withName("§6Diamond").withLore(purchase.purchase("Diamond_chestplate")).get());
		menuChestplate.setItem(17, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(18, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(21, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.AQUA).withLore(purchase.purchase("Leather_chestplate_aqua")).get());
		menuChestplate.setItem(22, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.BLACK).withLore(purchase.purchase("Leather_chestplate_black")).get());
		menuChestplate.setItem(23, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.BLUE).withLore(purchase.purchase("Leather_chestplate_blue")).get());
		menuChestplate.setItem(26, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(27, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(28, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.FUCHSIA).withLore(purchase.purchase("Leather_chestplate_fuchsia")).get());
		menuChestplate.setItem(29, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.GRAY).withLore(purchase.purchase("Leather_chestplate_gray")).get());
		menuChestplate.setItem(30, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.GREEN).withLore(purchase.purchase("Leather_chestplate_green")).get());
		menuChestplate.setItem(31, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.LIME).withLore(purchase.purchase("Leather_chestplate_lime")).get());
		menuChestplate.setItem(32, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.MAROON).withLore(purchase.purchase("Leather_chestplate_maroon")).get());
		menuChestplate.setItem(33, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.NAVY).withLore(purchase.purchase("Leather_chestplate_navy")).get());
		menuChestplate.setItem(34, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.OLIVE).withLore(purchase.purchase("Leather_chestplate_olive")).get());
		menuChestplate.setItem(35, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(36, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(37, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.ORANGE).withLore(purchase.purchase("Leather_chestplate_orange")).get());
		menuChestplate.setItem(38, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.PURPLE).withLore(purchase.purchase("Leather_chestplate_purple")).get());
		menuChestplate.setItem(39, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.RED).withLore(purchase.purchase("Leather_chestplate_red")).get());
		menuChestplate.setItem(40, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.SILVER).withLore(purchase.purchase("Leather_chestplate_silver")).get());
		menuChestplate.setItem(41, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.TEAL).withLore(purchase.purchase("Leather_chestplate_teal")).get());
		menuChestplate.setItem(42, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.WHITE).withLore(purchase.purchase("Leather_chestplate_white")).get());
		menuChestplate.setItem(43, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.YELLOW).withLore(purchase.purchase("Leather_chestplate_yellow")).get());
		menuChestplate.setItem(44, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
		menuChestplate.setItem(46, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(47, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(48, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(49, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(50, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(51, new Item(160).withData((byte) 5).withName(" ").get());
		menuChestplate.setItem(52, new Item(160).withData((byte) 14).withName(" ").get());
		menuChestplate.setItem(53, new Item(160).withData((byte) 5).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuChestplate);
	}
}
