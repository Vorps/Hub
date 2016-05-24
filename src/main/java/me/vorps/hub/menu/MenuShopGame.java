package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.MenuRecursive;
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

/**
 * Project Hub Created by Vorps on 18/05/2016 at 00:41.
 */
public class MenuShopGame extends MenuRecursive {

    private Game game;

    public MenuShopGame(Player player, ArrayList<Item> list, Game game){
        super(game.getShopMenu().getIds(), Bukkit.createInventory(null, game.getShopMenu().getSize(), game.getShopMenu().getLabel().get(PlayerData.getPlayerData(player.getName()).getLang())), game.getShopMenu().getModel(), list, PlayerData.getPlayerData(player.getName()).getLang(), game.getShopMenu().getLineSize(), game.getShopMenu().getStart(), game.getShopMenu().getExclude());
        this.game = game;
        initMenu(player, 1);
        player.openInventory(menu);
    }

    @Override
    public void initMenu(Player player, int page){
        menu.setItem(4, game.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang()).get());
        menu.setItem(game.getShopMenu().getSize()-9, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Boutique"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player, Game game){
        ArrayList<Products> products = Products.getProduct(game);
        ArrayList<Item> list = new ArrayList<>();
        for(Products product : products){
            Purchase purchase = new Purchase(player.getName(), " ce kit");
            list.add(product.getItem().get(PlayerData.getPlayerData(player.getName()).getLang()).withLore(purchase.purchase(product.getName())));
        }
        new MenuShopGame(player, list, game);
    }

    @Override
    public void interractInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()){
            case PAPER:
                initMenu(player, ++page);
                break;
            case ARROW:
                MenuBoutique.createMenu(player);
                break;
            case EMPTY_MAP:
                initMenu(player, --page);
                break;
            default:
                break;
        }
    }

}
