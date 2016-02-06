package sirmc.vorps.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuEvent {
	private static List<String> des = new ArrayList<String>();
	
	public static void HubmenuEvent(PlayerHub playerHub){
		 Inventory menuEvent = Bukkit.createInventory(null, 27, "§6Events");
		 menuEvent.setItem(0, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(1, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(2, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(3, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(4, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(5, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(6, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(7, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(8, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(9, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(17, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
		 menuEvent.setItem(19, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(20, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(21, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(22, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(23, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(24, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(25, new Item(160).withData((byte) 3).withName(" ").get());
		 menuEvent.setItem(26, new Item(160).withData((byte) 3).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuEvent);
		 
	}
}
