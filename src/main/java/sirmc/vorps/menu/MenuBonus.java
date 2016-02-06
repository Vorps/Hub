package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionType;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;
import sirmc.vorps.utils.Purchase;

public class MenuBonus {
	public static void HubMenuBonus(PlayerHub playerHub){
        Purchase purchase = new Purchase(playerHub, "ce bonus");
        Inventory menuBonus = Bukkit.createInventory(null, 45, "§6Boutique > Bonus");
        menuBonus.setItem(0, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(1, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(2, new Item(160).withData((byte) 5).withName(" ").get());
        menuBonus.setItem(3, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(4, new Item(PotionType.STRENGTH).withName("§6Bonus").get());
        menuBonus.setItem(5, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(6, new Item(160).withData((byte) 5).withName(" ").get());
        menuBonus.setItem(7, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(8, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(9, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(17, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(18, new Item(160).withData((byte) 5).withName(" ").get());
        menuBonus.setItem(21, new Item(PotionType.INSTANT_HEAL).withName("§6Bonus 25% §a- 2 Jours").withLore(purchase.purchaseBonus("Bonus 25%")).get());
        menuBonus.setItem(22,  new Item(PotionType.FIRE_RESISTANCE).withName("§6Bonus 50% §a- 2 Jours").withLore(purchase.purchaseBonus("Bonus 50%")).get());
        menuBonus.setItem(23,  new Item(PotionType.JUMP).withName("§6Bonus 75% §a- 2 Jours").withLore(purchase.purchaseBonus("Bonus 75%")).get());
        menuBonus.setItem(26, new Item(160).withData((byte) 5).withName(" ").get());
        menuBonus.setItem(27, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(35, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        menuBonus.setItem(37, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(38, new Item(160).withData((byte) 5).withName(" ").get());
        menuBonus.setItem(39, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(40, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(41, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(42, new Item(160).withData((byte) 5).withName(" ").get());
        menuBonus.setItem(43, new Item(160).withData((byte) 14).withName(" ").get());
        menuBonus.setItem(44, new Item(160).withData((byte) 14).withName(" ").get());
        Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuBonus);
	}
}
