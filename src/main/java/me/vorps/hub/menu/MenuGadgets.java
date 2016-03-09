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
public class MenuGadgets extends Shop {
	public MenuGadgets(Player player){
        super(new byte[] {4, 5, 1}, Bukkit.createInventory(null, 45, "§6Boutique > Gadgets") , new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 1}, {4, 0}, {5, 1}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 1}, {35, 1}, {37, 1}, {38, 2}, {39, 1}, {40, 0}, {41, 1}, {42, 2}, {43, 1}, {44, 0}}, player.getName(), "ce gadget");
		menu.setItem(4, new Item(Material.BLAZE_ROD).withName("§6Gadgets").get());
        menu.setItem(21, new Item(383).withData((byte) 65).withName("§6Lance Bat").withLore(getPurchase().purchase(ShopManager.GADGETS_BAT)).get());
        menu.setItem(23, new Item(Material.SNOW_BALL).withName("§6Lance Couleur").withLore(getPurchase().purchase(ShopManager.GADGETS_COLOR)).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        player.openInventory(menu);
	}
}
