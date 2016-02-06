package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionType;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuBoutique {
	
	public static void HubMenuBoutique(PlayerHub playerHub){
		 Inventory menuBoutique = Bukkit.createInventory(null, 45, "§6Boutique");
		 menuBoutique.setItem(0, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(1, new Item(160).withData((byte) 5).withName(" ").get());
		 menuBoutique.setItem(2, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(3, new Item(322).withData((byte) 1).withName("§6Obtenir des étoiles").withLore(new String[] {"§6Obtenir des étoiles"}).get());
		 menuBoutique.setItem(4, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(5, new Item(Material.PAPER).withName("§6Transactions éffectuées").withLore(new String[] {"§7Historique des achats"}).get());
		 menuBoutique.setItem(6, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(7, new Item(160).withData((byte) 5).withName(" ").get());
		 menuBoutique.setItem(8, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(9, new Item(Material.BONE).withName("§6Pets").withLore(new String[] {"§7Acheter un pets"}).get());
		 menuBoutique.setItem(17, new Item(Material.BLAZE_ROD).withName("§6Gadgets").withLore(new String[] {"§7Acheter des gadgets"}).get());
		 menuBoutique.setItem(18, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(20, new Item(Material.DIAMOND_SWORD).withName("§6Boutique HitIt!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		 menuBoutique.setItem(21, new Item(Material.BED).withName("§6Boutique Rush").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		 menuBoutique.setItem(23, new Item(Material.BOW).withName("§6Boutique SkyWars").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		 menuBoutique.setItem(24, new Item(Material.EXP_BOTTLE).withName("§6Boutique LuckyFight").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
		 menuBoutique.setItem(26, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(27, new Item(Material.GLOWSTONE_DUST).withName("§6Particules").withLore(new String[] {"§7Acheter des effets de particules"}).get());
		 menuBoutique.setItem(35, new Item(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").withLore(new String[] {"§7Acheter des costumes"}).get());
		 menuBoutique.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
		 menuBoutique.setItem(37, new Item(160).withData((byte) 5).withName(" ").get());
		 menuBoutique.setItem(38, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(39, new Item(Material.ENCHANTMENT_TABLE).withName("§6Grades").withLore(new String[] {"§7Obtenire un grade"}).get());
		 menuBoutique.setItem(40, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(41, new Item(PotionType.STRENGTH).withName("§6Bonus").withLore(new String[] {"§7Obtenire des bonus"}).get());
		 menuBoutique.setItem(42, new Item(160).withData((byte) 11).withName(" ").get());
		 menuBoutique.setItem(43, new Item(160).withData((byte) 5).withName(" ").get());
		 menuBoutique.setItem(44, new Item(160).withData((byte) 11).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuBoutique);
	}
}
