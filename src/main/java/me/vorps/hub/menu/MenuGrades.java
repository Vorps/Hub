package me.vorps.hub.menu;

import me.vorps.hub.shop.Shop;
import me.vorps.hub.shop.ShopManager;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuGrades extends Shop {
	public MenuGrades(Player player){
        super(new byte[] {11, 4, 5}, Bukkit.createInventory(null, 45, "§6Boutique > Grades") , new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 1}, {5, 1}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 1}, {35, 1}, {37, 1}, {38, 2}, {39, 1}, {40, 0}, {41, 1}, {42, 2}, {43, 1}, {44, 0}}, player.getName(), "ce grade");
		menu.setItem(4, new Item(Material.ENCHANTMENT_TABLE).withName("§6Grades").get());
        menu.setItem(21, new Item(Material.GOLD_ORE).withName("§6Grade §aVIP").withLore(getPurchase().purchaseGrade(ShopManager.VIP)).get());
        menu.setItem(22, new Item(Material.DIAMOND_ORE).withName("§6Grade §aHéros").withLore(getPurchase().purchaseGrade(ShopManager.HEROS)).get());
        menu.setItem(23, new Item(Material.EMERALD_ORE).withName("§6Grade §aLégende").withLore(getPurchase().purchaseGrade(ShopManager.LEGENDE)).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        player.openInventory(menu);
	}
}