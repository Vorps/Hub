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
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuFriends extends MenuRecursive {

	private MenuFriends(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {5}, Bukkit.createInventory(null, 45, "§6Profil > Amis") , new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
	}

    @Override
    public void initMenu(UUID uuid, int page){
        //menu.setItem(4, new ItemBuilder(Material.SUNFLOWER).withName("§6Amis en ligne").withLore(new String[] {"§7Affiche vos amis en ligne", "§7En ligne : §a"+ playerData.getFriends().getFriendsOnline().size(), "§7Hors ligne : §7"+(playerData.getFriends().getFriendsOnline().size()- playerData.getFriends().getFriendsOnline().size())}).get());
        //menu.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
        /*if(!playerData.getFriends().getFriends().isEmpty()){
            menu.setItem(39, new ItemBuilder(Material.ITEM_FRAME).withName("§6Ajouter un ami").withLore(new String[] {"§7Ajouter un joueur de votre liste d'amis"}).get());
            menu.setItem(41, new ItemBuilder(Material.BARRIER).withName("§6Supprimer un ami").withLore(new String[] {"§7Supprimer un joueur de votre liste d'amis"}).get());
        } else {
            menu.setItem(40, new ItemBuilder(Material.ITEM_FRAME).withName("§6Ajouter un ami").withLore(new String[] {"§7Ajouter un joueur de votre liste d'amis"}).get());
        }*/
    }

    public static void createMenu(UUID uuid){
        PlayerData playerData = PlayerData.getPlayerData(uuid);
        //playerData.updateFriends();
        ArrayList<ItemBuilder> list = new ArrayList<>();
        //playerData.getFriends().getFriendsOnline().forEach((String name) ->  list.add(new ItemBuilder(name).withName("§6"+name).withLore(new String[] {"§7Vous pouvez inviter ou rejoindre cet ami", "§eClic gauche -> §aRejoindre", "§eClic droit -> §aInviter"})));
        new MenuFriends(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        new MenuProfil(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {
        PlayerData playerData = PlayerData.getPlayerData(uuid);
        switch (type) {
            case BARRIER:
                MenuFriendsRemove.createMenu(uuid);
                break;
            case SKELETON_SKULL:
                /*playerData.updateFriends();
                String friend = itemStack.getItemMeta().getDisplayName().substring(2);
                if (playerData.getFriends().isFriend(friend)){
                    if(e.isLeftClick()){
                        playerData.getFriends().joinFriend(player);
                        player.closeInventory();
                    } else if(e.isRightClick()){
                        playerData.getFriends().inviteFriend(player, friend);
                        player.closeInventory();
                    }
                } else {
                    createMenu(player);
                }*/
                break;
            case ITEM_FRAME:
                //playerData.getFriends().addFriend(player);
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            default:
                break;
        }
    }

}
