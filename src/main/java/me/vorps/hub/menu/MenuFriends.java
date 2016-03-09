package me.vorps.hub.menu;

import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuFriends extends Menu {

	public MenuFriends(Player player, int page){
        super(new byte[] {5}, null, new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {18, 0}, {26, 0}, {27, 0}, {35, 0}, {37, 0}, {38, 0}, {40, 0}, {42, 0}, {43, 0}});
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        playerData.updateFriends();
		if(playerData.getFriends().getFriendsOnLineList().size() <= 21){
            menu = Bukkit.createInventory(null, 45, "§6Profil > Amis");
		} else {
            menu = Bukkit.createInventory(null, 45, "§6Profil>Amis|§4"+page);
		}
        constructModel();
		menu.setItem(4, new Item(175).withName("§6Amis en ligne").withLore(new String[] {"§7Affiche vos amis en ligne", "§7En ligne : §a"+ playerData.getFriends().getFriendsOnLineList().size(), "§7Hors ligne : §7"+(playerData.getFriends().getFriendsOnLineList().size()- playerData.getFriends().getFriendsOnLineList().size())}).get());
		String[] des = {"§7Vous pouvez inviter ou rejoindre cet ami", "§eClic gauche -> §aRejoindre", "§eClic droit -> §aInviter"};
        if(playerData.getFriends().getFriendsOnLineList().size() <= 21){
			 list(19 ,playerData.getFriends().getFriendsOnLineList().size(), 0, 0, playerData.getFriends().getFriendsOnLineList(), des);
        } else {
			 for(int i = page*21; i < page*21+21 ; i++) {
				 menu.setItem(i + 10, new Item(playerData.getFriends().getFriendsOnLineList().get(i)).withName("§6" + playerData.getFriends().getFriendsOnLineList().get(i)).withLore(des).get());
			 }
        }
		if(page > 1){
			menu.setItem(36, new Item(Material.PAPER).withName("§6<- page "+(page-1)).withLore(new String[] {"§7Page pr§c§dente", ""+(page-1)}).get());
		} else {
			menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
		}
		menu.setItem(39, new Item(Material.ITEM_FRAME).withName("§6Ajouter un ami").withLore(new String[] {"§7Ajouter un joueur de votre liste d'amis"}).get());
		menu.setItem(41, new Item(Material.BARRIER).withName("§6Supprimer un ami").withLore(new String[] {"§7Supprimer un joueur de votre liste d'amis"}).get());
		if(playerData.getFriends().getFriendsOnLineList().size() <= 21){
			menu.setItem(44, new Item(160).withData((byte) 5).withName(" ").get());
		} else {
			menu.setItem(44, new Item(Material.PAPER).withName("§6Page "+(page+1)+" ->").withLore(new String[] {"§7Page suivante", ""+(page+1)}).get());
		}
		player.openInventory(menu);
	}

}
