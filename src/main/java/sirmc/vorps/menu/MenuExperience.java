package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuExperience {
	
	public static void HubmenuExperience(PlayerHub playerHub){
		 Inventory menuExperience = Bukkit.createInventory(null, 27, "�6Profil > Experience");
		 menuExperience.setItem(0, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(1, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(2, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(3, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(4, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(5, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(6, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(7, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(8, new Item(160).withData((byte) 4).withName(" ").get());
		 
		 menuExperience.setItem(9, new Item(160).withData((byte) 4).withName(" ").get());
		 
		 menuExperience.setItem(10, new Item(386).withData((byte) 0).withName("§6Succés").get());
		 menuExperience.setItem(13, new Item(Material.EXP_BOTTLE).withName("§6Experience").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		 menuExperience.setItem(16, new Item(Material.NETHER_STAR).withName("§6Récompense Journalière").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
	       
		 menuExperience.setItem(17, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
		 menuExperience.setItem(19, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(20, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(21, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(22, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(23, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(24, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(25, new Item(160).withData((byte) 4).withName(" ").get());
		 menuExperience.setItem(26, new Item(160).withData((byte) 4).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuExperience);
		 
	}
}
