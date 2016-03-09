package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuPrincipal extends Menu{

	public MenuPrincipal(Player player){
        super(new byte[] {4, 1}, Bukkit.createInventory(null, 45, "§6Menu Principal"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {4, 0}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {18, 0}, {26, 0}, {37, 1}, {38, 0}, {40, 0}, {42, 0}, {43, 1}, {44, 0}});
        menu.setItem(9, new Item(Material.BOOK).withName("§6Aide").withLore(new String[] {"§7Besoin d'aide ?"}).get());
        menu.setItem(17, new Item(Material.NETHER_STAR).withName("§6Hub").withLore(new String[] {"§7Changer de hub"}).get());
        menu.setItem(20, new Item(Material.DIAMOND_SWORD).withName("§6HitIt!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(21, new Item(Material.BED).withName("§6Rush!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(23, new Item(Material.BOW).withName("§6SkyWars!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(24, new Item(Material.EXP_BOTTLE).withName("§6LuckyFight!").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(27, new Item(Material.LEATHER_BOOTS).withName("§6Jump").withColor(Color.PURPLE).withLore(new String[] {"§7Jumps"}).get());
        menu.setItem(35, new Item(Material.DIAMOND).withName("§6Events").withLore(new String[] {"§7Events prochainement"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        menu.setItem(39, new Item(Material.BEACON).withName("§6Zone VIP").withLore(new String[] {"§7Espace réservé exclusivement aux §a§nVIP"}).get());
        menu.setItem(41, new Item(322).withData((byte) 1).withName("§6Mini Jeux en BETA").withLore(new String[] {"§7En développement"}).get());
        player.openInventory(menu);
	}
}
