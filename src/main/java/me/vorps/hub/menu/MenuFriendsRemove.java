package me.vorps.hub.menu;

import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 03/03/2016 at 14:52.
 */
public class MenuFriendsRemove extends MenuRecursive{

    private MenuFriendsRemove(Player player, ArrayList<Item> list){
        super(new byte[] {14}, Bukkit.createInventory(null, 45, "§6[X] Supprimer"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}, {44, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
        initMenu(player, 1);
        player.openInventory(menu);
    }

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        menu.setItem(4, new Item(Material.BARRIER).withName("§6Supprimer un ami (Clic gauche)").withLore(new String[] {"§7Supprimer un joueur de votre liste d'amis"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu amis"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        ArrayList<Item> list = new ArrayList<>();
        playerData.updateFriends();
        playerData.getFriends().getFriends().keySet().forEach((String name) ->  {
            list.add(new Item(name).withName("§c"+name).withLore(new String[] {"§7Supprimer cette ami", "§eClic gauche -> §aSupprimer"}));
        });
        new MenuFriendsRemove(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        switch (itemStack.getType()) {
            case ARROW:
                MenuFriends.createMenu(player);
                break;
            case SKULL_ITEM:
                playerData.updateFriends();
                String friend = itemStack.getItemMeta().getDisplayName().substring(2);
                if (playerData.getFriends().isFriend(friend)){
                    playerData.getFriends().deleteFriend(player, friend);
                    player.closeInventory();
                } else {
                    createMenu(player);
                }
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
