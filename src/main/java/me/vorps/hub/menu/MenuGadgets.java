package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.hub.utils.Purchase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuGadgets extends MenuRecursive {

	private MenuGadgets(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {4, 5, 1}, Bukkit.createInventory(null, 45, "§6Boutique > Gadgets") , new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 1}, {4, 0}, {5, 1}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 1}, {35, 1}, {37, 1}, {38, 2}, {39, 1}, {40, 0}, {41, 1}, {42, 2}, {43, 1}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
	}

    @Override
    public void initMenu(UUID uuid, int page){
        super.setItem(4, new ItemBuilder(Material.BLAZE_ROD).withName("§6Gadgets").get());
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(8).forEach((Products product) -> list.add(product.getItem(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "ce gadget").purchase(product.getName()))));
        new MenuGadgets(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        MenuBoutique.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
    }
}
