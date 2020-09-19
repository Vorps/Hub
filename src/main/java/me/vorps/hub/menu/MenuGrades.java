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
public class MenuGrades extends MenuRecursive {

	private MenuGrades(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {11, 4, 5}, Bukkit.createInventory(null, 45, "§6Boutique > Grades") , new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 1}, {5, 1}, {6, 2}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 2}, {26, 2}, {27, 1}, {35, 1}, {37, 1}, {38, 2}, {39, 1}, {40, 0}, {41, 1}, {42, 2}, {43, 1}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.STATIC, Hub.getInstance());
		initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
	}

    @Override
    public void initMenu(UUID uuid, int page){
        menu.clear();
        menu.setItem(4, new ItemBuilder(Material.ENCHANTING_TABLE).withName("§6Grades").get());
        menu.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(6).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "ce grade").purchaseGrade(product.getName()))));
        new MenuGrades(uuid, list);
    }


    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid = e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()) {
            case ARROW:
                MenuBoutique.createMenu(uuid);
                break;
            case PAPER:
                initMenu(uuid, page+1);
                break;
            case MAP:
                initMenu(uuid, page-1);
                break;
            default:
                break;
        }
    }
}