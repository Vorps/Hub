package me.vorps.hub.menu;

import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.Bonus;
import me.vorps.hub.Object.Products;
import me.vorps.hub.PlayerData;
import me.vorps.hub.utils.Purchase;
import org.bukkit.Bukkit;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuBonus extends MenuRecursive {

	public MenuBonus(Player player, ArrayList<Item> list){
        super(new byte[] {14, 5}, Bukkit.createInventory(null, 45, "§6Boutique > Bonus"), new int[][] {{0, 0}, {1, 0}, {2, 1}, {3, 0}, {5, 0}, {6, 1}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 1}, {26, 1}, {27, 0}, {35, 0}, {37, 0}, {38, 1}, {39, 0}, {40, 0}, {41, 0}, {42, 1}, {43, 0}, {44, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7 , 9);
        initMenu(player, 1);
        player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        Bonus bonus = PlayerData.getPlayerData(player.getName()).getBonus();
        if(bonus.getBonus().equals("default")){
            menu.setItem(4, new Item(PotionType.STRENGTH).withName("§6Bonus").get());
        } else {
            menu.setItem(4, new Item(PotionType.STRENGTH).withName("§6Bonus").withLore(new String[] {"§6"+bonus.getBonus()}).get());
        }
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Products.getProduct(5).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(new Purchase(player.getName(), "ce bonus").purchaseBonus(product.getName()))));
        new MenuBonus(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuBoutique.createMenu(player);
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
