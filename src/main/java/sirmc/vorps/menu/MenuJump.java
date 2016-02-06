package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.Settings;

public class MenuJump {

	public static void HubmenuJump(PlayerHub playerHub){
		Inventory menuJump = Bukkit.createInventory(null, 27, "§6Jumps");
		menuJump.setItem(0, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(1, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(2, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(3, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(4, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(5, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(6, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(7, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(8, new Item(160).withData((byte) 6).withName(" ").get());
		
		menuJump.setItem(9, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(10, new Item(159).withData((byte) 5).withName("§6Jump n§1 - §aFacile").get());
		menuJump.setItem(11, new Item(159).withData((byte) 11).withName("§6Jump n§2 - §3Moyen").get());
		menuJump.setItem(12, new Item(159).withData((byte) 1).withName("§6Jump n§3 - §1Compliqué").get());
		menuJump.setItem(13, new Item(159).withData((byte) 14).withName("§6Jump n§4 - §4Trés compliqué").get());
		menuJump.setItem(16, new Item(Material.NETHER_STAR).withName("§6Jumps principal").withLore(new String[] {"§7Jump §a"+Settings.getNameServer(), "§7JumpsSirelMC"}).get());
		menuJump.setItem(17, new Item(160).withData((byte) 6).withName(" ").get());
		
		menuJump.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());

		menuJump.setItem(19, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(20, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(21, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(22, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(23, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(24, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(25, new Item(160).withData((byte) 6).withName(" ").get());
		menuJump.setItem(26, new Item(160).withData((byte) 6).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuJump);
	}
}
