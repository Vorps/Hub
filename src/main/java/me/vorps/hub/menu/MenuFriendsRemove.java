package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 03/03/2016 at 14:52.
 */
public class MenuFriendsRemove extends MenuRecursive{

    private MenuFriendsRemove(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {14}, Bukkit.createInventory(null, 45, "§6[X] Supprimer"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
    }

    @Override
    public void initMenu(UUID uuid, int page){
        super.setItem(4, new ItemBuilder(Material.BARRIER).withName("§6Supprimer un ami (Clic gauche)").withLore(new String[] {"§7Supprimer un joueur de votre liste d'amis"}).get());
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu amis"}).get());
    }

    public static void createMenu(UUID uuid){
        PlayerData playerData = PlayerData.getPlayerData(uuid);
        ArrayList<ItemBuilder> list = new ArrayList<>();
        /*playerData.updateFriends();
        playerData.getFriends().getFriends().keySet().forEach((String name) ->  {
            list.add(new ItemBuilder(name).withName("§c"+name).withLore(new String[] {"§7Supprimer cette ami", "§eClic gauche -> §aSupprimer"}));
        })*/;
        new MenuFriendsRemove(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        MenuFriends.createMenu(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        switch (type) {
            case SKELETON_SKULL:
                /*playerData.updateFriends();
                String friend = itemStack.getItemMeta().getDisplayName().substring(2);
                if (playerData.getFriends().isFriend(friend)){
                    playerData.getFriends().deleteFriend(player, friend);
                    player.closeInventory();
                } else {
                    createMenu(player);
                }*/
                break;
            default:
                break;
        }
    }
}
