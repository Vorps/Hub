package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuPrincipal {

	public static void HubMenuPricipal(PlayerHub playerHub){
	        Inventory menuPrincipal = Bukkit.createInventory(null, 45, "§6Menu Principal");
	        menuPrincipal.setItem(0, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(1, new Item(160).withData((byte) 1).withName(" ").get());
	        menuPrincipal.setItem(2, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(3, new Item(160).withData((byte) 1).withName(" ").get());
	        menuPrincipal.setItem(4, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(5, new Item(160).withData((byte) 1).withName(" ").get());
	        menuPrincipal.setItem(6, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(7, new Item(160).withData((byte) 1).withName(" ").get());
	        menuPrincipal.setItem(8, new Item(160).withData((byte) 4).withName(" ").get());
	        
	        menuPrincipal.setItem(9, new Item(Material.BOOK).withName("§6Aide").withLore(new String[] {"§7Besoin d'aide ?"}).get());
	        menuPrincipal.setItem(17, new Item(Material.NETHER_STAR).withName("§6Hub").withLore(new String[] {"§7Changer de hub"}).get());
	        menuPrincipal.setItem(18, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(20, new Item(Material.DIAMOND_SWORD).withName("§6HitIt!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).withLore(new String[] {"HitIt"}).get());
			menuPrincipal.setItem(21, new Item(Material.BED).withName("§6Rush!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).withLore(new String[] {"Rush"}).get());
			menuPrincipal.setItem(23, new Item(Material.BOW).withName("§6SkyWars!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).withLore(new String[] {"SkyWars"}).get());
			menuPrincipal.setItem(24, new Item(Material.EXP_BOTTLE).withName("§6LuckyFight!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).withLore(new String[] {"LuckyFight"}).get());
	        menuPrincipal.setItem(26, new Item(160).withData((byte) 4).withName(" ").get());
	        
	        menuPrincipal.setItem(27, new Item(Material.LEATHER_BOOTS).withName("§6Jump").withColor(Color.PURPLE).withLore(new String[] {"§7Jumps"}).get());
	        menuPrincipal.setItem(35, new Item(Material.DIAMOND).withName("§6Events").withLore(new String[] {"§7Events prochainement"}).get());
	        menuPrincipal.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
	        menuPrincipal.setItem(37, new Item(160).withData((byte) 1).withName(" ").get());
	        menuPrincipal.setItem(38, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(39, new Item(Material.BEACON).withName("§6Zone VIP").withLore(new String[] {"§7Espace réservé exclusivement aux §a§nVIP"}).get());
	        menuPrincipal.setItem(40, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(41, new  Item(322).withData((byte) 1).withName("§6Mini Jeux en BETA").withLore(new String[] {"§7En développement"}).get());
	        menuPrincipal.setItem(42, new Item(160).withData((byte) 4).withName(" ").get());
	        menuPrincipal.setItem(43, new Item(160).withData((byte) 1).withName(" ").get());
	        menuPrincipal.setItem(44, new Item(160).withData((byte) 4).withName(" ").get());
	       Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuPrincipal);
	}
}
