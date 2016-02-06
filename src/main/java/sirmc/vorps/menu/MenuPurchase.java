package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sirmc.vorps.Hub;
import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuPurchase {

    public static void HubmenuPurchase(PlayerHub playerHub, ItemStack item) {
        Inventory menuPurchase = Bukkit.createInventory(null, 27, "§6Achat");
        menuPurchase.setItem(0, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(1, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(2, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(3, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(4, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(5, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(6, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(7, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(8, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(9, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(11, new Item(Material.GOLDEN_APPLE).withName("§b☑ Valider l'achat ☑").withLore(new String[]{"§7Valider l'achat du produit §a" + item.getItemMeta().getLore().get(0).substring(2), "§aPrix : " + Hub.instance.getListProducts().get(item.getItemMeta().getLore().get(0).substring(2)).getPrice() + " §a" + Hub.instance.getListProducts().get(item.getItemMeta().getLore().get(0).substring(2)).getMoney()}).get());
        menuPurchase.setItem(13, item);
        menuPurchase.setItem(15, new Item(Material.BARRIER).withName("§c☒ Annuler ☒").withLore(new String[] {"§cAnnule l'achat du produit §a" + item.getItemMeta().getLore().get(0).substring(2) , "§eAnnuler"}).get());
        menuPurchase.setItem(17, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(18, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(19, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(20, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(21, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(22, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(23, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(24, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(25, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(26, new Item(160).withData((byte) 11).withName(" ").get());
        menuPurchase.setItem(19, new Item(160).withData((byte) 11).withName(" ").get());
        Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuPurchase);
    }
}
