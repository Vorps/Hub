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
public class MenuPets extends MenuRecursive {

	public MenuPets(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {0, 8}, Bukkit.createInventory(null, 54, "§6Boutique > Pets"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {46, 1}, {47, 0}, {48, 1}, {49, 0}, {50, 1}, {51, 0}, {52, 1}, {53, 0}}, list, PlayerData.getLang(uuid), 9, 9, new int[] {40}, Type.STATIC, Hub.getInstance());
		initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
	}

    @Override
    public void initMenu(UUID uuid, int page){
        menu.clear();
        menu.setItem(4, new ItemBuilder(Material.BONE).withName("§6Pets").get());
        menu.setItem(45, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(9).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "ce pets").purchase(product.getName()))));
        new MenuPets(uuid, list);
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
