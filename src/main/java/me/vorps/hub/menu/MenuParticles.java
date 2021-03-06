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
public class MenuParticles extends MenuRecursive {

	public MenuParticles(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {4}, Bukkit.createInventory(null, 45, "§6Boutique > Particules") , new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {12, 0}, {14, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, new int[] {12, 13, 14}, Type.DYNAMIQUE, Hub.getInstance());
	}

    @Override
    public void initMenu(UUID uuid, int page){
        super.setItem(4, new ItemBuilder(Material.GLOWSTONE_DUST).withName("§6Particule").get());
        super.setItem(13, new ItemBuilder(Material.BARRIER).withName("§6Retirer ma particules").withLore(new String[] {"§7Retirer ma particule actuel"}).get());
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Products.getProduct(7).forEach((Products product) -> list.add(product.getItem(PlayerData.getLang(uuid)).withLore(new Purchase(uuid, "cette particule").purchase(product.getName()))));
        new MenuParticles(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        MenuBoutique.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        switch (type) {
            case BARRIER:
                //PlayerData.getPlayerData(uuid).setParticle(null);
                Bukkit.getPlayer(uuid).sendMessage("§7Votre particule a été retiré");
                break;
            default:
                break;
        }
    }
}
