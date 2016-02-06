package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuSettings {

	public static void HubMenuSettings(PlayerHub playerHub){
		Inventory HubMenuSettings = Bukkit.createInventory(null, 36, "§6Paramètre");
		HubMenuSettings.setItem(0, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(1, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(2, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(3, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(4, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(5, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(6, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(7, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(8, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(9, new Item(160).withData((byte) 11).withName(" ").get());
		if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.joueur.me")){
			if(playerHub.isVisiblePlayer()){
				HubMenuSettings.setItem(10, new Item(351).withData((byte) 10).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
			} else {
				HubMenuSettings.setItem(10, new Item(351).withData((byte) 8).withName("§6Visiblité des joueurs").withLore(new String[] {"", "§7Afficher ou cacher les joueurs dans le hub", "", "§aCommande §a> §b/joueur"}).get());
			}
		}
		if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.build.me")){
			if(playerHub.isBuild()){
				HubMenuSettings.setItem(11, new Item(351).withData((byte) 10).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			} else {
				HubMenuSettings.setItem(11, new Item(351).withData((byte) 8).withName("§6Mode build dans le hub").withLore(new String[] {"", "§7Mode build dans le hub", "", "§aCommande §a> §b/build"}).get());
			}
		}
		if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.fly.me")){
			if(playerHub.isFly()){
				HubMenuSettings.setItem(20, new Item(351).withData((byte) 10).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			} else {
				HubMenuSettings.setItem(20, new Item(351).withData((byte) 8).withName("§6Mode fly dans le hub").withLore(new String[] {"", "§7Mode fly dans le hub", "", "§aCommande §a> §b/fly"}).get());
			}
		}
		if(Bukkit.getPlayer(playerHub.getPlayerName()).hasPermission("hub.jump.me")){
			if(playerHub.isDoubleJumps()){
				HubMenuSettings.setItem(13, new Item(351).withData((byte) 10).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			} else {
				HubMenuSettings.setItem(13, new Item(351).withData((byte) 8).withName("§6Mode jump dans le hub").withLore(new String[] {"", "§7Mode jump dans le hub", "", "§aCommande §a> §b/jump"}).get());
			}
		}
		HubMenuSettings.setItem(17, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(18, new Item(160).withData((byte) 11).withName(" ").get());
		
		HubMenuSettings.setItem(26, new Item(160).withData((byte) 11).withName(" ").get());
		
		HubMenuSettings.setItem(27, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(28, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(29, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(30, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(31, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(32, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(33, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(34, new Item(160).withData((byte) 11).withName(" ").get());
		HubMenuSettings.setItem(35, new Item(160).withData((byte) 11).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(HubMenuSettings);
	}
}
