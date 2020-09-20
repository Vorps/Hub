package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Object.PlayerJump;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 17/05/2016 at 22:28.
 */
public class MenuGame extends MenuRecursive{

    private Game game;

    public MenuGame(UUID uuid, Game game){
        super(uuid, game.getGameMenu().getIds(), Bukkit.createInventory(null, game.getGameMenu().getSize(), game.getGameMenu().getLabel(PlayerData.getLang(uuid))), game.getGameMenu().getModel(), game.getListMap(), PlayerData.getLang(uuid), game.getGameMenu().getLineSize(), game.getGameMenu().getStart(), game.getShopMenu().getExclude(), Type.DYNAMIQUE, Hub.getInstance());
        this.game = game;
    }

    @Override
    public void initMenu(UUID uuid, int page){
        super.setItem(4, game.getIcon().get(PlayerData.getLang(uuid)).get());
        PlayerJump playerJump = PlayerData.getPlayerData(uuid).getJump();
        /*if(playerJump.isInJump()){
            menu.setItem(10, new ItemBuilder(Material.SPRUCE_SIGN).withName("§6Panneaux").withLore(new String[] {playerJump.toString()}).get());
        } else {
            menu.setItem(10, new ItemBuilder(Material.SPRUCE_SIGN).withName("§6Panneaux").get());
        }*/
        super.setItem(13, new ItemBuilder(Material.ENDER_PEARL).withAction(e -> MenuShopGame.createMenu(uuid, game)).withName("§6Partie aléatoire").withLore(new String[] {"§7Rush Volcano"}).get());
        super.setItem(16, new ItemBuilder(Material.ENDER_CHEST).withName("§6Boutique du jeu").get());
        super.setItem(27, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        getPage(page);
        Bukkit.getPlayer(uuid).updateInventory();
    }

    public static void createMenu(UUID uuid, Game game){
        new MenuGame(uuid, game);
    }

    @Override
    protected void back(UUID uuid) {
        MenuPrincipal.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e){
        PlayerJump playerJump = PlayerData.getPlayerData(uuid).getJump();
        switch (type){
            case SPRUCE_SIGN:
                /*if(playerJump.isInJump()){
                    playerJump.stopJump(Bukkit.getPlayer(uuid), true);
                }*/
                Bukkit.getPlayer(uuid).teleport(game.getSignLoc());
                break;
            case ENDER_PEARL:
                //Dispatcher.connectServer(game.getType(), player, true);
                break;
            /*case MAP:
                Dispatcher.connectServer(game.getType(), itemStack.getItemMeta().getDisplayName().substring(2), player, true);
                break;*/
            default:
                break;
        }
    }

}
