package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import me.vorps.hub.Settings;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuHelp extends Menu{
	public MenuHelp(Player player){
        super(new byte[] {12, 15}, Bukkit.createInventory(null, 27, "§6Aide"), new int[][] {{4, 0}, {13, 1}, {19, 1}, {20, 0}, {21, 1}, {22, 0}, {23, 1}, {24, 0}, {25, 1}, {26, 0}});
		menu.setItem(0, new Item(Material.CHAINMAIL_CHESTPLATE).withName("§6Pour bien débuter").withLore(new String[] {"§7Aide §aPour bien débuter sur le serveur §6§n"+Settings.getNameServer()}).get());
		menu.setItem(9, new Item(Material.BOOK_AND_QUILL).withName("§6Pour bien débuter").withLore(new String[] {"§7Aide §aPour bien débuter sur le serveur §6§n"+Settings.getNameServer()}).get());
		menu.setItem(1, new Item(175).withData((byte) 0).withName("§6Amis & Party").withLore(new String[] {"§7Comment bien gérer ses §a§namis§7 et ses §a§nparty"}).get());
		menu.setItem(10, new Item(Material.BOOK_AND_QUILL).withName("§6Amis & Party").withLore(new String[] {"§7Comment bien gérer ses §a§namis§7 et ses §a§nparty"}).get());
		menu.setItem(2, new Item(Material.DIAMOND).withName("§6Grades").withLore(new String[] {"§7Informations concernants sur les grades du serveur §6§n"+Settings.getNameServer()}).get());
		menu.setItem(11, new Item(Material.BOOK_AND_QUILL).withName("§6Grades").withLore(new String[] {"§7Informations concernants sur les grades du serveur §6§n"+Settings.getNameServer()}).get());
		menu.setItem(3, new Item(Material.GOLD_NUGGET).withName("§6Economie du serveur").withLore(new String[] {"§7L'économie pour les nuls"}).get());
		menu.setItem(12, new Item(Material.BOOK_AND_QUILL).withName("§6Economie du serveur").withLore(new String[] {"§7L'économie pour les nuls"}).get());
		menu.setItem(5, new Item(Material.DIAMOND_SWORD).withName("§6HitIt !").withLore(new String[] {"§7Informations sur le mode de jeu §a§nHitIt"}).get());
		menu.setItem(14, new Item(Material.BOOK_AND_QUILL).withName("§6HitIt !").withLore(new String[] {"§7Informations sur le mode de jeu §a§nHitIt"}).get());
		menu.setItem(6, new Item(Material.BED).withName("§6Rush").withLore(new String[] {"§7Informations sur le mode de jeux §a§nRush"}).get());
		menu.setItem(15, new Item(Material.BOOK_AND_QUILL).withName("§6Rush").withLore(new String[] {"§7Informations sur le mode de jeux §a§nRush"}).get());
		menu.setItem(7, new Item(Material.BOW).withName("§6SkyWars").withLore(new String[] {"§7Informations sur le mode de jeux §a§nSkyWars"}).get());
		menu.setItem(16, new Item(Material.BOOK_AND_QUILL).withName("§6Grades").withLore(new String[] {"§7Informations sur le mode de jeux §a§nSkyWars"}).get());
		menu.setItem(8, new Item(Material.EXP_BOTTLE).withName("§6LuckyFight").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).withLore(new String[] {"§7Informations sur le mode de jeux §a§nLuckyFight"}).get());
		menu.setItem(17, new Item(Material.BOOK_AND_QUILL).withName("§6LuckyFight").withLore(new String[] {"§7Informations sur le mode de jeux §a§nLuckyFight"}).get());
		menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        player.openInventory(menu);
	}
}
