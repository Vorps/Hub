package me.vorps.hub.menu;

import me.vorps.hub.shop.Shop;
import me.vorps.hub.shop.ShopManager;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionType;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuParticles extends Shop {

	public MenuParticles(Player player){
        super(new byte[] {4}, Bukkit.createInventory(null, 45, "§6Boutique > Particules") , new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {12, 0}, {14, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}, {44, 0}}, player.getName(), "cette particule");
		menu.setItem(4, new Item(Material.GLOWSTONE_DUST).withName("§6Pets").get());
		menu.setItem(10, new Item(PotionType.WATER).withName("§6Bulles").withLore(getPurchase().purchase(ShopManager.PARTICULE_BULLE)).get());
		menu.setItem(11, new Item(Material.FIREBALL).withName("§6Colère").withLore(getPurchase().purchase(ShopManager.PARTICULE_COLERE)).get());
		menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer ma particules").withLore(new String[] {"§7Retirer ma particule actuel"}).get());
		menu.setItem(15, new Item(38).withData((byte) 0).withName("§6Coeurs").withLore(getPurchase().purchase(ShopManager.PARTICULE_COEUR)).get());
		menu.setItem(16, new Item(388).withData((byte) 0).withName("§6Particules vertes").withLore(getPurchase().purchase(ShopManager.PARTICULE_VERTE)).get());
		menu.setItem(19, new Item(Material.TORCH).withName("§6Fummèe").withLore(getPurchase().purchase(ShopManager.PARTICULE_FUMEE)).get());
		menu.setItem(21, new Item(PotionType.SPEED).withName("§6Particules de potion").withLore(getPurchase().purchase(ShopManager.PARTICULE_POTION)).get());
		menu.setItem(22,  new Item(PotionType.STRENGTH).withName("§6Dégâts Magiques").withLore(getPurchase().purchase(ShopManager.PARTICULE_DEGAT_MAGIQUE)).get());
		menu.setItem(23,  new Item(PotionType.SPEED).withName("§6Particules de spash potion").withLore(getPurchase().purchase(ShopManager.PARTICULE_SPASH_POTION)).get());
		menu.setItem(25, new Item(Material.CAULDRON_ITEM).withName("§6Sorcière").withLore(getPurchase().purchase(ShopManager.PARTICULE_SORCIERE)).get());
		menu.setItem(28, new Item(Material.NOTE_BLOCK).withName("§6Note de musique").withLore(getPurchase().purchase(ShopManager.PARTICULE_NOTE_MUSIQUE)).get());
		menu.setItem(29, new Item(351).withData((byte) 15).withName("§6Nuage Blanc").withLore(getPurchase().purchase(ShopManager.PARTICULE_NUAGE_BLANC)).get());
		menu.setItem(30, new Item(Material.LAVA_BUCKET).withName("§6Lave").withLore(getPurchase().purchase(ShopManager.PARTICULE_LAVE)).get());
		menu.setItem(31, new Item(Material.SLIME_BALL).withName("§6Slime").withLore(getPurchase().purchase(ShopManager.PARTICULE_SLIME)).get());
		menu.setItem(32, new Item(Material.BLAZE_POWDER).withName("§6Flame").withLore(getPurchase().purchase(ShopManager.PARTICULE_FLAME)).get());
		menu.setItem(33, new Item(Material.IRON_SWORD).withName("§6Dégàts").withLore(getPurchase().purchase(ShopManager.PARTICULE_DEGATS)).get());
		menu.setItem(34, new Item(Material.ENCHANTMENT_TABLE).withName("§6Table d'enchantement").withLore(getPurchase().purchase(ShopManager.PARTICULE_ENCHANTEMENT)).get());
		menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        player.openInventory(menu);
	}
}
