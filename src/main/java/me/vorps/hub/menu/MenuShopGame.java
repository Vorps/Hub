package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
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
 * Project Hub Created by Vorps on 18/05/2016 at 00:41.
 */
public class MenuShopGame extends MenuRecursive {

    private Game game;

    public MenuShopGame(UUID uuid, ArrayList<ItemBuilder> list, Game game){
        super(game.getShopMenu().getIds(), Bukkit.createInventory(null, game.getShopMenu().getSize(), game.getShopMenu().getLabel().get(PlayerData.getLang(uuid))), game.getShopMenu().getModel(), list, PlayerData.getLang(uuid), game.getShopMenu().getLineSize(), game.getShopMenu().getStart(), game.getShopMenu().getExclude(), Type.STATIC, Hub.getInstance());
        this.game = game;
        initMenu(uuid, 1);
        Bukkit.getPlayer(uuid).openInventory(menu);
    }

    @Override
    public void initMenu(UUID uuid, int page){
        menu.setItem(4, game.getIcon().get(PlayerData.getLang(uuid)).get());
        menu.setItem(game.getShopMenu().getSize()-9, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid, Game game){
        ArrayList<Products> products = Products.getProduct(game);
        ArrayList<ItemBuilder> list = new ArrayList<>();
        for(Products product : products){
            Purchase purchase = new Purchase(uuid, " ce kit");
            list.add(product.getItem().get(PlayerData.getLang(uuid)).withLore(purchase.purchase(product.getName())));
        }
        new MenuShopGame(uuid, list, game);
    }

    @Override
    public void interractInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCurrentItem();
        UUID uuid =e.getWhoClicked().getUniqueId();
        switch (itemStack.getType()){
            case PAPER:
                initMenu(uuid, ++page);
                break;
            case ARROW:
                MenuBoutique.createMenu(uuid);
                break;
            case MAP:
                initMenu(uuid, --page);
                break;
            default:
                break;
        }
    }

}
