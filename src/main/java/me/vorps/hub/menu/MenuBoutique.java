package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionType;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuBoutique extends Menu{

	public MenuBoutique(Player player){
        super(new byte[] {11, 5}, Bukkit.createInventory(null, 45, "§6Boutique"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {4, 0}, {6, 0}, {7, 1}, {8, 0}, {18, 0}, {26, 1}, {37, 1}, {38, 0}, {40, 0}, {42, 0}, {43, 1}, {44, 0}});
        menu.setItem(3, new Item(322).withData((byte) 1).withName("§6Obtenir des étoiles").withLore(new String[] {"§7Obtenir des étoiles"}).get());
        menu.setItem(5, new Item(Material.PAPER).withName("§6Transactions éffectuées").withLore(new String[] {"§7Historique des achats"}).get());
        menu.setItem(9, new Item(Material.BONE).withName("§6Pets").withLore(new String[] {"§7Acheter un pets"}).get());
        menu.setItem(17, new Item(Material.BLAZE_ROD).withName("§6Gadgets").withLore(new String[] {"§7Acheter des gadgets"}).get());
        menu.setItem(20, new Item(Material.DIAMOND_SWORD).withName("§6Boutique HitIt!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(21, new Item(Material.BED).withName("§6Boutique Rush").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(23, new Item(Material.BOW).withName("§6Boutique SkyWars").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(24, new Item(Material.EXP_BOTTLE).withName("§6Boutique LuckyFight").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(27, new Item(Material.GLOWSTONE_DUST).withName("§6Particules").withLore(new String[] {"§7Acheter des effets de particules"}).get());
        menu.setItem(35, new Item(Material.DIAMOND_CHESTPLATE).withName("§6Costumes").withLore(new String[] {"§7Acheter des costumes"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(39, new Item(Material.ENCHANTMENT_TABLE).withName("§6Grades").withLore(new String[] {"§7Obtenire un grade"}).get());
        menu.setItem(41, new Item(PotionType.STRENGTH).withName("§6Bonus").withLore(new String[] {"§7Obtenire des bonus"}).get());
        player.openInventory(menu);
	}
}
