package me.vorps.hub.menu;

import me.vorps.hub.Settings;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuJump extends Menu{

	public MenuJump(Player player){
        super(new byte[] {6},  Bukkit.createInventory(null, 27, "§6Jumps"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}});
		menu.setItem(10, new Item(159).withData((byte) 5).withName("§6Jump n§1 - §aFacile").get());
		menu.setItem(11, new Item(159).withData((byte) 11).withName("§6Jump n§2 - §3Moyen").get());
		menu.setItem(12, new Item(159).withData((byte) 1).withName("§6Jump n§3 - §1Compliqué").get());
		menu.setItem(13, new Item(159).withData((byte) 14).withName("§6Jump n§4 - §4Trés compliqué").get());
		menu.setItem(16, new Item(Material.NETHER_STAR).withName("§6Jumps principal").withLore(new String[] {"§7Jump §a"+ Settings.getNameServer(), "§7JumpsFortyCube"}).get());
		menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
		player.openInventory(menu);
	}
}
