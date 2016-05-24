package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.PlayerJump;
import me.vorps.hub.dispatcher.Dispatcher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 17/05/2016 at 22:28.
 */
public class MenuGame extends MenuRecursive{

    private Game game;

    public MenuGame(Player player, Game game){
        super(game.getGameMenu().getIds(), Bukkit.createInventory(null, game.getGameMenu().getSize(), game.getGameMenu().getLabel().get(PlayerData.getPlayerData(player.getName()).getLang())), game.getGameMenu().getModel(), game.getListMap(), PlayerData.getPlayerData(player.getName()).getLang(), game.getGameMenu().getLineSize(), game.getGameMenu().getStart(), game.getShopMenu().getExclude());
        this.game = game;
        initMenu(player, 1);
        player.openInventory(menu);
    }

    @Override
    public void initMenu(Player player, int page){
        menu.setItem(4, game.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang()).get());
        PlayerJump playerJump = PlayerData.getPlayerData(player.getName()).getJump();
        if(playerJump.isInJump()){
            menu.setItem(10, new Item(Material.SIGN).withName("§6Panneaux").withLore(new String[] {playerJump.toString()}).get());
        } else {
            menu.setItem(10, new Item(Material.SIGN).withName("§6Panneaux").get());
        }
        menu.setItem(13, new Item(Material.ENDER_PEARL).withName("§6Partie aléatoire").withLore(new String[] {"§7Rush Volcano"}).get());
        menu.setItem(16, new Item(Material.ENDER_CHEST).withName("§6Boutique du jeu").get());
        menu.setItem(27, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player, Game game){
        new MenuGame(player, game);
    }

    @Override
    public void interractInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        PlayerJump playerJump = PlayerData.getPlayerData(player.getName()).getJump();
        switch (itemStack.getType()){
            case SIGN:
                if(playerJump.isInJump()){
                    playerJump.stopJump(player, true);
                }
                player.teleport(game.getSignLoc());
                break;
            case ENDER_PEARL:
                Dispatcher.connectServer(game.getType(), player, true);
                break;
            case ENDER_CHEST:
                MenuShopGame.createMenu(player, game);
                break;
            case PAPER:
                if(e.getSlot() != 35){
                    Dispatcher.connectServer(game.getType(), itemStack.getItemMeta().getDisplayName().substring(2), player, true);
                } else {
                    initMenu(player, ++page);
                }
                break;
            case ARROW:
                MenuPrincipal.createMenu(player);
                break;
            case EMPTY_MAP:
                initMenu(player, --page);
                break;
            default:
                break;
        }
    }

}
