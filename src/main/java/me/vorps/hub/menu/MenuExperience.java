package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuExperience extends Menu {
	
	public MenuExperience(Player player){
        super(new byte[] {4}, Bukkit.createInventory(null, 27, "§6Profil > Experience"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}});
        menu.setItem(10, new Item(386).withData((byte) 0).withName("§6Succés").get());
        menu.setItem(13, new Item(Material.EXP_BOTTLE).withName("§6Experience").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(16, new Item(Material.NETHER_STAR).withName("§6Récompense Journalière").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
        player.openInventory(menu);
	}
}
