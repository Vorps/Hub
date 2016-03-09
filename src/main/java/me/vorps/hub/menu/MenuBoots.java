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
public class MenuBoots extends Shop {
	public MenuBoots(Player player){
        super(new byte[] {1, 11, 5}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > "), new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}, {53, 0}}, player.getName(), "ces bottes a vos pieds");
		menu.setItem(4,new Item(Material.GOLD_BOOTS).withName("§6Bottes").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menu.setItem(10, new Item(Material.CHAINMAIL_BOOTS).withName("§6Cotte de maille").withLore(getPurchase().purchase(ShopManager.CHAINMAIL_BOOTS)).get());
		menu.setItem(11, new Item(Material.GOLD_BOOTS).withName("§6Or").withLore(getPurchase().purchase(ShopManager.GOLD_BOOTS)).get());
		menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mes bottes").withLore(new String[] {"§7Retire vos bottes actuel"}).get());
		menu.setItem(15, new Item(Material.IRON_BOOTS).withName("§6Fer").withLore(getPurchase().purchase(ShopManager.IRON_BOOTS)).get());
		menu.setItem(16, new Item(Material.DIAMOND_BOOTS).withName("§6Diamond").withLore(getPurchase().purchase(ShopManager.DIAMOND_BOOTS)).get());
		menu.setItem(21, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.AQUA).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_AQUA)).get());
		menu.setItem(22, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.BLACK).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_BLACK)).get());
		menu.setItem(23, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.BLUE).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_BLUE)).get());
		menu.setItem(28, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.FUCHSIA).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_FUCHSIA)).get());
		menu.setItem(29, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.GRAY).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_GRAY)).get());
		menu.setItem(30, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.GREEN).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_GREEN)).get());
		menu.setItem(31, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.LIME).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_LIME)).get());
		menu.setItem(32, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.MAROON).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_MAROON)).get());
		menu.setItem(33, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.NAVY).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_NAVY)).get());
		menu.setItem(34, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.OLIVE).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_OLIVE)).get());
		menu.setItem(37, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.ORANGE).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_ORANGE)).get());
		menu.setItem(38, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.PURPLE).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_PURPLE)).get());
		menu.setItem(39, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.RED).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_RED)).get());
		menu.setItem(40, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.SILVER).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_SILVER)).get());
		menu.setItem(41, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.TEAL).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_TEAL)).get());
		menu.setItem(42, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.WHITE).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_WHITE)).get());
		menu.setItem(43, new Item(Material.LEATHER_BOOTS).withName("§6Cuir coloré").withColor(Color.YELLOW).withLore(getPurchase().purchase(ShopManager.LEATHER_BOOTS_YELLOW)).get());
		menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
        player.openInventory(menu);
	}
}
