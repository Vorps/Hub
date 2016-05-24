package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Item;
import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
import me.vorps.hub.Object.Stats;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 09/05/2016 at 17:23.
 */
public class MenuGameStats extends MenuRecursive {

    public MenuGameStats(Player player, ArrayList<Item> list){
        super(new byte[] {1, 2}, Bukkit.createInventory(null, 45, "§6Profil -> Statistique"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 0}, {26, 0}, {27, 1}, {35, 1}, {37, 1}, {38, 0}, {39, 1}, {40, 0}, {41, 1}, {42, 0}, {43, 1}, {44, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
        initMenu(player, 1);
        player.openInventory(menu);
    }

    public void initMenu(Player player, int page){
        menu.setItem(4, new Item(386).withData((byte) 0).withName("§6Statistiques").withLore(new String[] {"§7Statistique du joueur"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        Game.getGameList().values().forEach((Game game) -> list.add(new Item(game.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang())).withLore(new Stats(player.getName(), game.getDatabase()).getStats())));
        new MenuGameStats(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                new MenuProfil(player);
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
