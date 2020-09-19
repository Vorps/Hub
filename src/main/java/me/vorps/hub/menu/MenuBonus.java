package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import me.vorps.hub.Object.Bonus;
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
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuBonus extends MenuRecursive {

	public MenuBonus(UUID uuid, ArrayList<ItemBuilder> list){
        super(new byte[] {14, 5}, Bukkit.createInventory(null, 45, "§6Boutique > Bonus"), new int[][] {{0, 0}, {1, 0}, {2, 1}, {3, 0}, {5, 0}, {6, 1}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 1}, {26, 1}, {27, 0}, {35, 0}, {37, 0}, {38, 1}, {39, 0}, {40, 0}, {41, 0}, {42, 1}, {43, 0}, {44, 0}}, list, PlayerData.getLang(uuid), 7 , 9, Type.STATIC, Hub.getInstance());
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(this.menu);
	}

    @Override
    public void initMenu(UUID uuid, int page){
        this.menu.clear();
        Bonus bonus = PlayerData.getBonus(uuid);
        if(bonus.getName().equals("default")){
            this.menu.setItem(4, new ItemBuilder(PotionType.STRENGTH).withName("§6Bonus").get());
        } else {
            this.menu.setItem(4, new ItemBuilder(PotionType.STRENGTH).withName("§6Bonus").withLore(new String[] {"§6"+bonus.getName()}).get());
        }
        this.menu.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(5).forEach((Products product) -> list.add(product.getItem().get(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "ce bonus").purchaseBonus(product.getName()))));
        new MenuBonus(uuid, list);
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
