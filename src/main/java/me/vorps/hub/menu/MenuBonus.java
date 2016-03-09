package me.vorps.hub.menu;

import me.vorps.hub.Object.Bonus;
import me.vorps.hub.PlayerData;
import me.vorps.hub.shop.Shop;
import me.vorps.hub.shop.ShopManager;
import org.bukkit.Bukkit;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionType;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuBonus extends Shop {
	public MenuBonus(Player player){
        super(new byte[] {14, 5}, Bukkit.createInventory(null, 45, "§6Boutique > Bonus"), new int[][] {{0, 0}, {1, 0}, {2, 1}, {3, 0}, {5, 0}, {6, 1}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 1}, {26, 1}, {27, 0}, {35, 0}, {37, 0}, {38, 1}, {39, 0}, {40, 0}, {41, 0}, {42, 1}, {43, 0}, {44, 0}}, player.getName(), "ce bonus");
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        Bonus bonus = playerData.getBonus();
        if(bonus.getBonus().equals("default")){
            menu.setItem(4, new Item(PotionType.STRENGTH).withName("§6Bonus").get());
        } else {
            menu.setItem(4, new Item(PotionType.STRENGTH).withName("§6Bonus").withLore(new String[] {"§6"+bonus.getBonus()}).get());
        }
        menu.setItem(21, new Item(PotionType.INSTANT_HEAL).withName("§6Bonus 25% §a- 2 Jours").withLore(getPurchase().purchaseBonus(ShopManager.BONUS_25)).get());
        menu.setItem(22, new Item(PotionType.FIRE_RESISTANCE).withName("§6Bonus 10% §a- 2 Jours").withLore(getPurchase().purchaseBonus(ShopManager.BONUS_50)).get());
        menu.setItem(23, new Item(PotionType.JUMP).withName("§6Bonus 75% §a- 2 Jours").withLore(getPurchase().purchaseBonus(ShopManager.BONUS_75)).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        player.openInventory(menu);
	}
}
