package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuLuckyFight extends Menu{

	public MenuLuckyFight(Player player){
        super(new byte[] {4, 1}, Bukkit.createInventory(null, 36, "§6Menu Principal > LuckyFight"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 0}, {26, 0}, {28, 0}, {29, 1}, {30, 0}, {31, 1}, {32, 0}, {33, 1}, {34, 0}, {35, 1}});
        menu.setItem(4, new Item(Material.EXP_BOTTLE).withName("§6LuckyFight").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		menu.setItem(10, new Item(Material.SIGN).withName("§6Panneaux").get());
		menu.setItem(13, new Item(Material.ENDER_PEARL).withName("§6Partie aléatoire").get());
		menu.setItem(16, new Item(Material.ENDER_CHEST).withName("§6Boutique du jeu").get());
		menu.setItem(20, new Item(Material.PAPER).withName("§6{Nom} - Team autorisées").get());
		menu.setItem(21, new Item(Material.PAPER).withName("§6{Nom} - Team interdites").get());
		menu.setItem(23, new Item(Material.PAPER).withName("§6{Nom} - Team autorisées").get());
		menu.setItem(24, new Item(Material.PAPER).withName("§6{Nom} - Team interdites").get());
		menu.setItem(27,  new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        player.openInventory(menu);
	}
}