package me.vorps.hub.menu;

import me.vorps.hub.Object.Products;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuPurchase extends Menu{

    public MenuPurchase(Player player, ItemStack item){
        super(new byte[] {11}, Bukkit.createInventory(null, 27, "§6Achat"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}});
        Products products = Products.getProduct(item.getItemMeta().getLore().get(0).substring(2));
        menu.setItem(11, new Item(Material.GOLDEN_APPLE).withName("§b☑ Valider l'achat ☑").withLore(new String[]{"§7Valider l'achat du produit §a" + item.getItemMeta().getLore().get(0).substring(2), "§aPrix : " + products.getPrice() + " §a" + products.getMoney()}).get());
        menu.setItem(13, item);
        menu.setItem(15, new Item(Material.BARRIER).withName("§c☒ Annuler ☒").withLore(new String[] {"§cAnnule l'achat du produit §a" + item.getItemMeta().getLore().get(0).substring(2) , "§eAnnuler"}).get());
        player.openInventory(menu);
    }
}
