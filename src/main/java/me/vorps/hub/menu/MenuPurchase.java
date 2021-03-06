package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.Menu;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.hub.utils.GiveProductPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuPurchase extends Menu{

    private Products products;

    public MenuPurchase(UUID uuid, Products products){
        super(uuid, new byte[] {11}, Bukkit.createInventory(null, 27, "§6Achat"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, Hub.getInstance());
        this.products = products;
        super.setItem(11, new ItemBuilder(Material.GOLDEN_APPLE).withAction(e -> GiveProductPlayer.giveItemPlayer(uuid, products, true)).withName("§b☑ Valider l'achat ☑").withLore(new String[]{"§7Valider l'achat du produit §a" + products.getName(), "§aPrix : " + products.getPrice() + " §a" + products.getMoney()}).get());
        super.setItem(13, products.getItem(PlayerData.getLang(uuid)).get());
        super.setItem(15, new ItemBuilder(Material.BARRIER).withAction(e -> Bukkit.getPlayer(uuid).closeInventory()).withName("§c☒ Annuler ☒").withLore(new String[] {"§cAnnule l'achat du produit §a" + products.getName() , "§eAnnuler"}).get());
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
    }
}
