package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.Settings;

public class MenuHelp {
	public static void HubMenuHelp(PlayerHub playerHub){
		Inventory menuHelp = Bukkit.createInventory(null, 27, "§6Aide");
		
		menuHelp.setItem(0, new Item(Material.CHAINMAIL_CHESTPLATE).withName("§6Pour bien débuter").withLore(new String[] {"§7Aide §aPour bien débuter sur le serveur §6§n"+Settings.getNameServer()}).get());
		menuHelp.setItem(9, new Item(Material.BOOK_AND_QUILL).withName("§6Pour bien débuter").withLore(new String[] {"§7Aide §aPour bien débuter sur le serveur §6§n"+Settings.getNameServer()}).get());
		menuHelp.setItem(1, new Item(175).withData((byte) 0).withName("§6Amis & Party").withLore(new String[] {"§7Comment bien gérer ses §a§namis§7 et ses §a§nparty"}).get());
		menuHelp.setItem(10, new Item(Material.BOOK_AND_QUILL).withName("§6Amis & Party").withLore(new String[] {"§7Comment bien gérer ses §a§namis§7 et ses §a§nparty"}).get());
		menuHelp.setItem(2, new Item(Material.DIAMOND).withName("§6Grades").withLore(new String[] {"§7Informations concernants sur les grades du serveur §6§n"+Settings.getNameServer()}).get());
		menuHelp.setItem(11, new Item(Material.BOOK_AND_QUILL).withName("§6Grades").withLore(new String[] {"§7Informations concernants sur les grades du serveur §6§n"+Settings.getNameServer()}).get());
		menuHelp.setItem(3, new Item(Material.GOLD_NUGGET).withName("§6Economie du serveur").withLore(new String[] {"§7L'économie pour les nuls"}).get());
		menuHelp.setItem(12, new Item(Material.BOOK_AND_QUILL).withName("§6Economie du serveur").withLore(new String[] {"§7L'économie pour les nuls"}).get());
		menuHelp.setItem(4, new Item(160).withData((byte) 12).withName(" ").get());
		menuHelp.setItem(5, new Item(Material.DIAMOND_SWORD).withName("§6HitIt !").withLore(new String[] {"§7Informations sur le mode de jeu §a§nHitIt"}).get());
		menuHelp.setItem(14, new Item(Material.BOOK_AND_QUILL).withName("§6HitIt !").withLore(new String[] {"§7Informations sur le mode de jeu §a§nHitIt"}).get());
		menuHelp.setItem(6, new Item(Material.BED).withName("§6Rush").withLore(new String[] {"§7Informations sur le mode de jeux §a§nRush"}).get());
		menuHelp.setItem(15, new Item(Material.BOOK_AND_QUILL).withName("§6Rush").withLore(new String[] {"§7Informations sur le mode de jeux §a§nRush"}).get());
		menuHelp.setItem(7, new Item(Material.BOW).withName("§6SkyWars").withLore(new String[] {"§7Informations sur le mode de jeux §a§nSkyWars"}).get());
		menuHelp.setItem(16, new Item(Material.BOOK_AND_QUILL).withName("§6Grades").withLore(new String[] {"§7Informations sur le mode de jeux §a§nSkyWars"}).get());
		menuHelp.setItem(8, new Item(Material.EXP_BOTTLE).withName("§6LuckyFight").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).withLore(new String[] {"§7Informations sur le mode de jeux §a§nLuckyFight"}).get());
		menuHelp.setItem(17, new Item(Material.BOOK_AND_QUILL).withName("§6LuckyFight").withLore(new String[] {"§7Informations sur le mode de jeux §a§nLuckyFight"}).get());
		menuHelp.setItem(13, new Item(160).withData((byte) 15).withName(" ").get());
		menuHelp.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
		menuHelp.setItem(19, new Item(160).withData((byte) 15).withName(" ").get());
		menuHelp.setItem(20, new Item(160).withData((byte) 12).withName(" ").get());
		menuHelp.setItem(21, new Item(160).withData((byte) 15).withName(" ").get());
		menuHelp.setItem(22, new Item(160).withData((byte) 12).withName(" ").get());
		menuHelp.setItem(23, new Item(160).withData((byte) 15).withName(" ").get());
		menuHelp.setItem(24, new Item(160).withData((byte) 12).withName(" ").get());
		menuHelp.setItem(25, new Item(160).withData((byte) 15).withName(" ").get());
		menuHelp.setItem(26, new Item(160).withData((byte) 12).withName(" ").get());
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuHelp);
	}
}
