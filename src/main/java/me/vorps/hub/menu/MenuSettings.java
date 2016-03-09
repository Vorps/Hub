package me.vorps.hub.menu;

import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuSettings extends Menu{

	public MenuSettings(Player player){
        super(new byte[] {11}, Bukkit.createInventory(null, 36, "§6Paramètre"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {28, 0}, {29, 0}, {30, 0}, {31, 0}, {32, 0}, {33, 0}, {34, 0}, {35, 0}});
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        if(player.hasPermission("hub.joueur.me")){
			if(playerData.isVisiblePlayer()){
				menu.setItem(10, new Item(351).withData((byte) 10).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
			} else {
				menu.setItem(10, new Item(351).withData((byte) 8).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
			}
		}
		if(player.hasPermission("hub.build.me")){
			if(playerData.isBuild()){
				menu.setItem(11, new Item(351).withData((byte) 10).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			} else {
				menu.setItem(11, new Item(351).withData((byte) 8).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			}
		}
		if(player.hasPermission("hub.fly.me")){
			if(playerData.isFly()){
				menu.setItem(20, new Item(351).withData((byte) 10).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			} else {
				menu.setItem(20, new Item(351).withData((byte) 8).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			}
		}
		if(player.hasPermission("hub.jump.me")){
			if(playerData.isDoubleJumps()){
				menu.setItem(13, new Item(351).withData((byte) 10).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			} else {
				menu.setItem(13, new Item(351).withData((byte) 8).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			}
		}
        menu.setItem(27, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
		player.openInventory(menu);
	}
}
