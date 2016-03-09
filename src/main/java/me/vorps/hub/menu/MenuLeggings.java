package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.hub.shop.Shop;
import me.vorps.hub.shop.ShopManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuLeggings extends Shop {

    public MenuLeggings(Player player){
        super(new byte[] {11, 5, 3}, Bukkit.createInventory(null, 54, "§6Boutique > Costumes > "), new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}, {5, 0}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 2}, {35, 2}, {36, 1}, {44, 1}, {46, 1}, {47, 2}, {48, 0}, {49, 1}, {50, 0}, {51, 2}, {52, 1}, {53, 0}}, player.getName(), "ce pantalon sur vos jambes.");
        menu.setItem(4, new Item(Material.GOLD_LEGGINGS).withName("§6Pantalons").withEnchant(Enchantment.ARROW_DAMAGE, 1).hideEnchant(true).get());
        menu.setItem(10, new Item(Material.CHAINMAIL_LEGGINGS).withName("§6Cotte de maille").withLore(getPurchase().purchase(ShopManager.CHAINMAIL_LEGGINGS)).get());
        menu.setItem(11, new Item(Material.GOLD_LEGGINGS).withName("§6Or").withLore(getPurchase().purchase(ShopManager.GOLD_LEGGINGS)).get());
        menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer mon pantalon").withLore(new String[] {"§7Retire votre pantalon actuel"}).get());
        menu.setItem(15, new Item(Material.IRON_LEGGINGS).withName("§6Fer").withLore(getPurchase().purchase(ShopManager.IRON_LEGGINGS)).get());
        menu.setItem(16, new Item(Material.DIAMOND_LEGGINGS).withName("§6Diamond").withLore(getPurchase().purchase(ShopManager.DIAMOND_LEGGINGS)).get());
        menu.setItem(21, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir coloré").withColor(Color.AQUA).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_AQUA)).get());
        menu.setItem(22, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.BLACK).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_BLACK)).get());
        menu.setItem(23, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.BLUE).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_BLUE)).get());
        menu.setItem(28, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.FUCHSIA).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_FUCHSIA)).get());
        menu.setItem(29, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.GRAY).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_GRAY)).get());
        menu.setItem(30, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.GREEN).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_GREEN)).get());
        menu.setItem(31, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.LIME).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_LIME)).get());
        menu.setItem(32, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.MAROON).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_MAROON)).get());
        menu.setItem(33, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.NAVY).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_NAVY)).get());
        menu.setItem(34, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.OLIVE).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_OLIVE)).get());
        menu.setItem(37, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.ORANGE).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_ORANGE)).get());
        menu.setItem(38, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.PURPLE).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_PURPLE)).get());
        menu.setItem(39, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.RED).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_RED)).get());
        menu.setItem(40, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.SILVER).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_SILVER)).get());
        menu.setItem(41, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.TEAL).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_TEAL)).get());
        menu.setItem(42, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.WHITE).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_WHITE)).get());
        menu.setItem(43, new Item(Material.LEATHER_LEGGINGS).withName("§6Cuir color§").withColor(Color.YELLOW).withLore(getPurchase().purchase(ShopManager.LEATHER_LEGGINGS_YELLOW)).get());
        menu.setItem(45, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Costumes"}).get());
        player.openInventory(menu);
	}
}
