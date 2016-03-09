package me.vorps.hub.menu;

import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 03/03/2016 at 14:52.
 */
public class MenuFriendsRemove extends Menu{

    public MenuFriendsRemove(Player player, int page){
        super(new byte[] {14}, null, new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {39, 0}, {40, 0}, {41, 0}, {42, 0}, {43, 0}});
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        playerData.updateFriends();
        if(playerData.getFriends().getFriends().size() <= 21){
            menu = Bukkit.createInventory(null, 45, "§6[X] Supprimer");
        } else {
            menu = Bukkit.createInventory(null, 45, "§6[X] Supprimer | Page §4"+page);
        }
        constructModel();
        menu.setItem(4, new Item(Material.BARRIER).withName("§6Supprimer un ami (Clic gauche)").withLore(new String[] {"§7Supprime un joueur § votre liste d'amis"}).get());
        String[] des = {"§7Suppression d'un joueur § votre liste d'amis", "§eClic gauche -> §aSupprimer"};
        if(playerData.getFriends().getFriends().size() <= 21){
            list(19, playerData.getFriends().getFriends().size(), 0, 0, playerData.getFriends().getFriends(), des);
        } else {
            for(int i = page*21; i < page*21+21 ; i++){
                menu.setItem(i+10, new Item(playerData.getFriends().getFriends().get(i)).withName(playerData.getFriends().getFriends().get(i)).withLore(des).get());
            }
        }
        if(page > 1){
            menu.setItem(36, new Item(Material.PAPER).withName("§6<- page "+(page-1)).withLore(new String[] {"§7Page remove précédente", ""+(page-1)}).get());
        } else {
            menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu amis"}).get());
        }
        if(playerData.getFriends().getFriends().size() <= 21){
            menu.setItem(44, new Item(160).withData((byte) 14).withName(" ").get());
        } else {
            menu.setItem(44, new Item(Material.PAPER).withName("§6Page "+(page+1)+" ->").withLore(new String[] {"§7Page de suppression suivante", ""+(page+1)}).get());
        }
        player.openInventory(menu);
    }
}
