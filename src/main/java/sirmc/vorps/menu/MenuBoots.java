package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuBoots {
	public static void HubMenuBoots(PlayerHub playerHub){
		Purchase purchase = new Purchase(playerHub, "ces bottes a vos pieds");
		Inventory menuBoots = Bukkit.createInventory(null, 54, "§6Boutique > Costumes > ");
		menuBoots.setItem(0, new Item(160).withData((byte) 1).withName(" ").get());
		menuBoots.setItem(1, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(2, new Item(160).withData((byte) 5).withName(" ").get());
		menuBoots.setItem(3, new Item(160).withData((byte) 1).withName(" ").get());
		menuBoots.setItem(4,new Item(Material.GOLD_BOOTS).withName("§6Bottes").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menuBoots.setItem(5, new Item(160).withData((byte) 1).withName(" ").get());
		menuBoots.setItem(6, new Item(160).withData((byte) 5).withName(" ").get());
		menuBoots.setItem(7, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(8, new Item(160).withData((byte) 1).withName(" ").get());
		menuBoots.setItem(9, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(10, new Item(Material.CHAINMAIL_BOOTS).withName("§6Cotte de maille").withLore(purchase.purchase("Chainmail_boots")).get());
		menuBoots.setItem(11, new Item(Material.GOLD_BOOTS).withName("§6Or").withLore(purchase.purchase("Gold_boots")).get());
		menuBoots.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mes bottes").withLore(new String[] {"§7Retire vos bottes actuel"}).get());
		menuBoots.setItem(15, new Item(Material.IRON_BOOTS).withName("§6Fer").withLore(purchase.purchase("Iron_boots")).get());
		menuBoots.setItem(16, new Item(Material.DIAMOND_BOOTS).withName("§6Diamond").withLore(purchase.purchase("Diamond_boots")).get());
		menuBoots.setItem(17, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(18, new Item(160).withData((byte) 5).withName(" ").get());
		menuBoots.setItem(21, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.AQUA).withLore(purchase.purchase("Leather_boots_aqua")).get());
		menuBoots.setItem(22, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.BLACK).withLore(purchase.purchase("Leather_boots_black")).get());
		menuBoots.setItem(23, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.BLUE).withLore(purchase.purchase("Leather_boots_blue")).get());
		menuBoots.setItem(26, new Item(160).withData((byte) 5).withName(" ").get());
		menuBoots.setItem(27, new Item(160).withData((byte) 1).withName(" ").get());
		menuBoots.setItem(28, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.FUCHSIA).withLore(purchase.purchase("Leather_boots_fuchsia")).get());
		menuBoots.setItem(29, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.GRAY).withLore(purchase.purchase("Leather_boots_gray")).get());
		menuBoots.setItem(30, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.GREEN).withLore(purchase.purchase("Leather_boots_green")).get());
		menuBoots.setItem(31, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.LIME).withLore(purchase.purchase("Leather_boots_lime")).get());
		menuBoots.setItem(32, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.MAROON).withLore(purchase.purchase("Leather_boots_maroon")).get());
		menuBoots.setItem(33, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.NAVY).withLore(purchase.purchase("Leather_boots_navy")).get());
		menuBoots.setItem(34, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.OLIVE).withLore(purchase.purchase("Leather_boots_olive")).get());
		menuBoots.setItem(35, new Item(160).withData((byte) 1).withName(" ").get());
		menuBoots.setItem(36, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(37, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.ORANGE).withLore(purchase.purchase("Leather_boots_orange")).get());
		menuBoots.setItem(38, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.PURPLE).withLore(purchase.purchase("Leather_boots_purple")).get());
		menuBoots.setItem(39, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.RED).withLore(purchase.purchase("Leather_boots_red")).get());
		menuBoots.setItem(40, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.SILVER).withLore(purchase.purchase("Leather_boots_silver")).get());
		menuBoots.setItem(41, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.TEAL).withLore(purchase.purchase("Leather_boots_teal")).get());
		menuBoots.setItem(42, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.WHITE).withLore(purchase.purchase("Leather_boots_white")).get());
		menuBoots.setItem(43, new Item(Material.LEATHER_BOOTS).withName("§6Cuir color§").withColor(Color.YELLOW).withLore(purchase.purchase("Leather_boots_yellow")).get());
		menuBoots.setItem(44, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
		menuBoots.setItem(46, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(47, new Item(160).withData((byte) 5).withName(" ").get());
		menuBoots.setItem(48, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(49, new Item(160).withData((byte) 1).withName(" ").get());
		menuBoots.setItem(50, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(51, new Item(160).withData((byte) 5).withName(" ").get());
		menuBoots.setItem(52, new Item(160).withData((byte) 11).withName(" ").get());
		menuBoots.setItem(53, new Item(160).withData((byte) 1).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuBoots);
	}
}
