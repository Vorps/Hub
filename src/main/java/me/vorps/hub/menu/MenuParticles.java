package me.vorps.hub.menu;

import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import me.vorps.hub.utils.Purchase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuParticles extends MenuRecursive {

	public MenuParticles(Player player, ArrayList<Item> list){
        super(new byte[] {4}, Bukkit.createInventory(null, 45, "§6Boutique > Particules") , new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {12, 0}, {14, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}, {44, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9, new int[] {12, 13, 14});
        initMenu(player, 1);
        player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        menu.setItem(4, new Item(Material.GLOWSTONE_DUST).withName("§6Pets").get());
        menu.setItem(13, new Item(Material.BARRIER).withName("§6Retirer ma particules").withLore(new String[] {"§7Retirer ma particule actuel"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Products.getProduct(7).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(new Purchase(player.getName(), "cette particule").purchase(product.getName()))));
        new MenuParticles(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuBoutique.createMenu(player);
                break;
            case BARRIER:
                player.sendMessage("§6En développement");
                break;
            case PAPER:
                initMenu(player, page+1);
                break;
            case EMPTY_MAP:
                initMenu(player, page-1);
                break;
            default:
                break;
        }
    }
}
