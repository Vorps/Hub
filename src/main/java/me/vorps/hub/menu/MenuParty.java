package me.vorps.hub.menu;


import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuParty extends Menu{

	public MenuParty(Player player, int page){
        super(new byte[] {1}, null, new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {21, 0}, {23, 0}});
        PlayerData playerData = PlayerData.getPlayerData(player.getName());
        playerData.updateParty();
		if(playerData.getParty().getMembers().size() <= 7){
			menu = Bukkit.createInventory(null, 27, "§6Party");
		} else {
            menu = Bukkit.createInventory(null, 27, "§6Party | page : §4"+page);
		}
        constructModel();
		menu.setItem(4, new Item(Material.MAGMA_CREAM).withName("§6Membres en ligne").get());
        String[] des = {};
		if(playerData.getParty().getMembersOnLineList().size() <= 7*page){
            list(10, playerData.getParty().getMembersOnLineList().size()-(page-1)*7, (page-1)*7, 0, playerData.getParty().getMembersOnLineList(), des);
		} else {
            int index = (page-1)*7;
			for(int i = 10; i < 10 + playerData.getParty().getMembersOnLineList().size() - (page - 1) * 7 ; i++){
				menu.setItem(i, new Item(playerData.getParty().getMembersOnLineList().get(index)).withName("§6"+ playerData.getParty().getMembersOnLineList().get(index)).get());
                index++;
			}
		}
		if(page > 1){
            menu.setItem(18, new Item(Material.PAPER).withName("§6<- page "+(page-1)).withLore(new String[] {"Party back", ""+(page-1)}).get());
		} else {
            menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
		}
		if(playerData.getParty().isState()){
			if(playerData.getParty().getOwner().equals(player.getName())){
				menu.setItem(20, new Item(Material.ITEM_FRAME).withName("§6Ajouter un joueur").withLore(new String[] {"§7Ajouter un membre au groupe"}).get());
				menu.setItem(22, new Item(Material.PAPER).withName("§6"+ playerData.getParty().getName()).withLore(new String[] {"§7Changer le nom du groupe"}).get());
				menu.setItem(24, new Item(Material.BARRIER).withName("§6Supprimer un membre").withLore(new String[] {"§7Supprimer un membre du groupe"}).get());
				menu.setItem(25, new Item(Material.ACACIA_DOOR_ITEM).withName("§6Dissoudre le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
			} else {
				menu.setItem(20, new Item(160).withData((byte) 1).withName(" ").get());
				menu.setItem(22, new Item(160).withData((byte) 1).withName(" ").get());
				menu.setItem(24, new Item(160).withData((byte) 1).withName(" ").get());
				menu.setItem(25, new Item(Material.ACACIA_DOOR_ITEM).withName("§6Quitter le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
			}
		} else {
			menu.setItem(22, new Item(Material.ITEM_FRAME).withName("§6Crée une party").withLore(new String[] {"§7Inviter un joueur"}).get());
			menu.setItem(20, new Item(160).withData((byte) 1).withName(" ").get());
			menu.setItem(24, new Item(160).withData((byte) 1).withName(" ").get());
			menu.setItem(25, new Item(160).withData((byte) 1).withName(" ").get());
		}
		if(playerData.getParty().getMembers().size() <= page*7){
			menu.setItem(26, new Item(160).withData((byte) 1).withName(" ").get());
		} else {
			menu.setItem(26, new Item(Material.PAPER).withName("§6Page "+page+1+" ->").withLore(new String[] {"Party next",""+page+1}).get());
		}
		player.openInventory(menu);
	}
}
