package me.vorps.hub.menu;

import me.vorps.hub.shop.Shop;
import me.vorps.hub.shop.ShopManager;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuChestplate extends Shop {

	public MenuChestplate(Player player) {
        super(new byte[] {14, 5}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > "), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {36, 1}, {44, 1}, {46, 1}, {47, 0}, {48, 1}, {49, 0}, {50, 1}, {51, 0}, {52, 1}, {53, 0}}, player.getName(), "ce plastron sur votre torse");
		menu.setItem(4, new Item(Material.GOLD_CHESTPLATE).withName("§6Plastrons").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menu.setItem(10, new Item(Material.CHAINMAIL_CHESTPLATE).withName("§6Cotte de maille").withLore(getPurchase().purchase(ShopManager.CHAINMAIL_CHESTPLATE)).get());
		menu.setItem(11, new Item(Material.GOLD_CHESTPLATE).withName("§6Or").withLore(getPurchase().purchase(ShopManager.GOLD_CHESTPLATE)).get());
		menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon plastron").withLore(new String[]{"§7Retire votre plastron actuel"}).get());
		menu.setItem(15, new Item(Material.IRON_CHESTPLATE).withName("§6Fer").withLore(getPurchase().purchase(ShopManager.IRON_CHESTPLATE)).get());
		menu.setItem(16, new Item(Material.DIAMOND_CHESTPLATE).withName("§6Diamond").withLore(getPurchase().purchase(ShopManager.DIAMOND_CHESTPLATE)).get());
		menu.setItem(21, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.AQUA).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_AQUA)).get());
		menu.setItem(22, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.BLACK).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_BLACK)).get());
		menu.setItem(23, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.BLUE).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_BLUE)).get());
		menu.setItem(28, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.FUCHSIA).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_FUCHSIA)).get());
		menu.setItem(29, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.GRAY).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_GRAY)).get());
		menu.setItem(30, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.GREEN).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_GREEN)).get());
		menu.setItem(31, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.LIME).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_LIME)).get());
		menu.setItem(32, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.MAROON).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_MAROON)).get());
		menu.setItem(33, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.NAVY).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_NAVY)).get());
		menu.setItem(34, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.OLIVE).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_OLIVE)).get());
		menu.setItem(37, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.ORANGE).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_ORANGE)).get());
		menu.setItem(38, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.PURPLE).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_PURPLE)).get());
		menu.setItem(39, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.RED).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_RED)).get());
		menu.setItem(40, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.SILVER).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_SILVER)).get());
		menu.setItem(41, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.TEAL).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_TEAL)).get());
		menu.setItem(42, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.WHITE).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_WHITE)).get());
		menu.setItem(43, new Item(Material.LEATHER_CHESTPLATE).withName("§6Cuir coloré").withColor(Color.YELLOW).withLore(getPurchase().purchase(ShopManager.LEATHER_CHESTPLATE_YELLOW)).get());
		menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
        player.openInventory(menu);
	}
}
