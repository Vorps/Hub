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
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuFriends extends MenuRecursive {

	private MenuFriends(Player player, ArrayList<Item> list){
        super(new byte[] {5}, Bukkit.createInventory(null, 45, "§6Profil > Amis") , new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}, {44, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
        initMenu(player, 1);
		player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.clear();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        menu.setItem(4, new Item(175).withName("§6Amis en ligne").withLore(new String[] {"§7Affiche vos amis en ligne", "§7En ligne : §a"+ playerData.getFriends().getFriendsOnline().size(), "§7Hors ligne : §7"+(playerData.getFriends().getFriendsOnline().size()- playerData.getFriends().getFriendsOnline().size())}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
        if(!playerData.getFriends().getFriends().isEmpty()){
            menu.setItem(39, new Item(Material.ITEM_FRAME).withName("§6Ajouter un ami").withLore(new String[] {"§7Ajouter un joueur de votre liste d'amis"}).get());
            menu.setItem(41, new Item(Material.BARRIER).withName("§6Supprimer un ami").withLore(new String[] {"§7Supprimer un joueur de votre liste d'amis"}).get());
        } else {
            menu.setItem(40, new Item(Material.ITEM_FRAME).withName("§6Ajouter un ami").withLore(new String[] {"§7Ajouter un joueur de votre liste d'amis"}).get());
        }
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        playerData.updateFriends();
        ArrayList<Item> list = new ArrayList<>();
        playerData.getFriends().getFriendsOnline().forEach((String name) ->  list.add(new Item(name).withName("§6"+name).withLore(new String[] {"§7Vous pouvez inviter ou rejoindre cet ami", "§eClic gauche -> §aRejoindre", "§eClic droit -> §aInviter"})));
        new MenuFriends(player, list);
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        switch (itemStack.getType()) {
            case ARROW:
                new MenuProfil(player);
                break;
            case BARRIER:
                MenuFriendsRemove.createMenu(player);
                break;
            case SKULL_ITEM:
                playerData.updateFriends();
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
                }
                break;
            case ITEM_FRAME:
                playerData.getFriends().addFriend(player);
                player.closeInventory();
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
