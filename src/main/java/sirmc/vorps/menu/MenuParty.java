package sirmc.vorps.menu;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

import java.util.ArrayList;

public class MenuParty {

	public static void HubMenuParty(PlayerHub playerHub, int page){
		playerHub.UpdateParty();
		Inventory menuParty;
		if(playerHub.getParty().getMembers().size() <= 7){
			menuParty = Bukkit.createInventory(null, 27, "§6Party");
		} else {
			menuParty = Bukkit.createInventory(null, 27, "§6Party | page : §4"+page);
		}
		menuParty.setItem(0, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(1, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(2, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(3, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(4, new Item(Material.MAGMA_CREAM).withName("§6Membres en ligne").get());
		menuParty.setItem(5, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(6, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(7, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(8, new Item(160).withData((byte) 1).withName(" ").get());

		menuParty.setItem(9, new Item(160).withData((byte) 1).withName(" ").get());
		if(playerHub.getParty().getMembersOnLineList().size() <= 7*page){
            listMembers(menuParty, playerHub.getParty().getMembersOnLineList().size() - page-1*7, page-1*7, playerHub.getParty().getMembersOnLineList());
		} else {
			for(int i = page-(1*7); i < (page-(1*7))+7 ; i++){
				menuParty.setItem((i-(page-(1*7)))+10, new Item(playerHub.getParty().getMembersOnLineList().get(i)).withName("§6"+playerHub.getParty().getMembersOnLineList().get(i)).get());
			}
		}
		menuParty.setItem(17, new Item(160).withData((byte) 1).withName(" ").get());

		if(page > 1){
            menuParty.setItem(18, new Item(Material.PAPER).withName("§6<- page "+(page-1)).withLore(new String[] {"Party back", ""+(page-1)}).get());
		} else {
            menuParty.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
		}
		menuParty.setItem(19, new Item(160).withData((byte) 1).withName(" ").get());
		if(playerHub.getParty().isState()){
			if(playerHub.getParty().getOwner().equals(playerHub.getPlayerName())){
				menuParty.setItem(20, new Item(Material.ITEM_FRAME).withName("§6Ajouter un joueur").withLore(new String[] {"§7Ajouter un membre au groupe"}).get());
				menuParty.setItem(22, new Item(Material.PAPER).withName("§6"+playerHub.getParty().getName()).withLore(new String[] {"§7Changer le nom du groupe"}).get());
				menuParty.setItem(24, new Item(Material.BARRIER).withName("§6Supprimer un membre").withLore(new String[] {"§7Supprimer un membre du groupe"}).get());
				menuParty.setItem(25, new Item(Material.ACACIA_DOOR_ITEM).withName("§6Dissoudre le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
			} else {
				menuParty.setItem(20, new Item(160).withData((byte) 1).withName(" ").get());
				menuParty.setItem(22, new Item(160).withData((byte) 1).withName(" ").get());
				menuParty.setItem(24, new Item(160).withData((byte) 1).withName(" ").get());
				menuParty.setItem(25, new Item(Material.ACACIA_DOOR_ITEM).withName("§6Quitter le groupe").withLore(new String[] {"§7Quitter le groupe"}).get());
			}
		} else {
			menuParty.setItem(22, new Item(Material.ITEM_FRAME).withName("§6Crée une party").withLore(new String[] {"§7Inviter un joueur"}).get());
			menuParty.setItem(20, new Item(160).withData((byte) 1).withName(" ").get());
			menuParty.setItem(24, new Item(160).withData((byte) 1).withName(" ").get());
			menuParty.setItem(25, new Item(160).withData((byte) 1).withName(" ").get());
		}
		menuParty.setItem(21, new Item(160).withData((byte) 1).withName(" ").get());
		menuParty.setItem(23, new Item(160).withData((byte) 1).withName(" ").get());

		if(playerHub.getParty().getMembers().size() <= 7){
			menuParty.setItem(26, new Item(160).withData((byte) 1).withName(" ").get());
		} else {
			menuParty.setItem(26, new Item(Material.PAPER).withName("§6Page "+page+" ->").withLore(new String[] {"Party next",""+page+1}).get());
		}
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuParty);
	}

	public static void HubMenuRemoveParty(PlayerHub playerHub, int page){
		playerHub.UpdateParty();
		Inventory menuParty;
		if(playerHub.getParty().getMembers().size() <= 7){
			menuParty = Bukkit.createInventory(null, 27, "§6Supprimer un membre");
		} else {
			menuParty = Bukkit.createInventory(null, 27, "§6Del un membre|page : §4"+page);
		}
		 menuParty.setItem(0, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(1, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(2, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(3, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(4, new Item(Material.BARRIER).withName("§6Supprimer un membre (Clique)").get());
		 menuParty.setItem(5, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(6, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(7, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(8, new Item(160).withData((byte) 14).withName(" ").get());

		 menuParty.setItem(9, new Item(160).withData((byte) 14).withName(" ").get());
		 if(playerHub.getParty().getMembers().size() <= 7){
             listMembers(menuParty, playerHub.getParty().getMembers().size(), 0, playerHub.getParty().getMembers());
		 } else {
             for(int i = page-1*7; i < (page-1*7)+7; i++){
				 menuParty.setItem((i-(page-1*7))+10, new Item(playerHub.getParty().getMembers().get(i)).withName("§6"+playerHub.getParty().getMembers().get(i)).withLore(new String[] {"Remove Membre"}).get());
			 }
		 }
		 menuParty.setItem(17, new Item(160).withData((byte) 14).withName(" ").get());

		if(page > 1){
            menuParty.setItem(18, new Item(Material.PAPER).withName("§6<- page "+page--).withLore(new String[] {"Party remove back", ""+page--}).get());
		} else {
            menuParty.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu party"}).get());
		}
		 menuParty.setItem(19, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(20, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(21, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(22, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(23, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(24, new Item(160).withData((byte) 14).withName(" ").get());
		 menuParty.setItem(25, new Item(160).withData((byte) 14).withName(" ").get());
		 if(playerHub.getParty().getMembers().size() <= 7){
			 menuParty.setItem(26, new Item(160).withData((byte) 14).withName(" ").get());
		 } else {
			 menuParty.setItem(26, new Item(Material.PAPER).withName("§6Page "+page+1+" ->").withLore(new String[] {"Party remove", ""+page+1}).get());
		 }
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuParty);
	}


	private static void listMembers(Inventory menuParty, int nbr, int i, ArrayList<String> membersOnLineList){
		 switch (nbr) {
		 case 0:
			 break;
			case 1:
				menuParty.setItem(13, new Item(membersOnLineList.get(i)).withName("§6"+membersOnLineList.get(i)).withLore(new String[] {}).get());
				break;
			case 2:
				 menuParty.setItem(12, new Item(membersOnLineList.get(i)).withName("§6"+membersOnLineList.get(i)).withLore(new String[] {}).get());
				 menuParty.setItem(14, new Item(membersOnLineList.get(i+1)).withName("§6"+membersOnLineList.get(i+1)).withLore(new String[] {}).get());
				break;
			case 3:
				 menuParty.setItem(12, new Item(membersOnLineList.get(i)).withName("§6"+membersOnLineList.get(i)).withLore(new String[] {}).get());
				 menuParty.setItem(13, new Item(membersOnLineList.get(i+1)).withName("§6"+membersOnLineList.get(i+1)).withLore(new String[] {}).get());
				 menuParty.setItem(14, new Item(membersOnLineList.get(i+2)).withName("§6"+membersOnLineList.get(i+2)).withLore(new String[] {}).get());
				 break;
			case 4:
				 menuParty.setItem(11, new Item(membersOnLineList.get(i)).withName("§6"+membersOnLineList.get(i)).withLore(new String[] {}).get());
				 menuParty.setItem(12, new Item(membersOnLineList.get(i+1)).withName("§6"+membersOnLineList.get(i+1)).withLore(new String[] {}).get());
				 menuParty.setItem(14, new Item(membersOnLineList.get(i+2)).withName("§6"+membersOnLineList.get(i+2)).withLore(new String[] {}).get());
				 menuParty.setItem(15, new Item(membersOnLineList.get(i+3)).withName("§6"+membersOnLineList.get(i+3)).withLore(new String[] {}).get());
				 break;
			case 5:
				menuParty.setItem(11, new Item(membersOnLineList.get(i)).withName("§6"+membersOnLineList.get(i)).withLore(new String[] {}).get());
				menuParty.setItem(12, new Item(membersOnLineList.get(i+1)).withName("§6"+membersOnLineList.get(i+1)).withLore(new String[] {}).get());
				menuParty.setItem(13, new Item(membersOnLineList.get(i+2)).withName("§6"+membersOnLineList.get(i+2)).withLore(new String[] {}).get());
				menuParty.setItem(14, new Item(membersOnLineList.get(i+3)).withName("§6"+membersOnLineList.get(i+3)).withLore(new String[] {}).get());
				menuParty.setItem(15, new Item(membersOnLineList.get(i+4)).withName("§6"+membersOnLineList.get(i+4)).withLore(new String[] {}).get());
				break;
			case 6:
				menuParty.setItem(10, new Item(membersOnLineList.get(i)).withName("§6"+membersOnLineList.get(i)).withLore(new String[] {}).get());
				menuParty.setItem(11, new Item(membersOnLineList.get(i+1)).withName("§6"+membersOnLineList.get(i+1)).withLore(new String[] {}).get());
				menuParty.setItem(12, new Item(membersOnLineList.get(i+2)).withName("§6"+membersOnLineList.get(i+2)).withLore(new String[] {}).get());
				menuParty.setItem(14, new Item(membersOnLineList.get(i+3)).withName("§6"+membersOnLineList.get(i+3)).withLore(new String[] {}).get());
				menuParty.setItem(15, new Item(membersOnLineList.get(i+4)).withName("§6"+membersOnLineList.get(i+4)).withLore(new String[] {}).get());
				menuParty.setItem(16, new Item(membersOnLineList.get(i+5)).withName("§6"+membersOnLineList.get(i+5)).withLore(new String[] {}).get());
				break;
			case 7:
				menuParty.setItem(10, new Item(membersOnLineList.get(i)).withName("§6"+membersOnLineList.get(i)).withLore(new String[] {}).get());
				menuParty.setItem(11, new Item(membersOnLineList.get(i+1)).withName("§6"+membersOnLineList.get(i+1)).withLore(new String[] {}).get());
				menuParty.setItem(12, new Item(membersOnLineList.get(i+2)).withName("§6"+membersOnLineList.get(i+2)).withLore(new String[] {}).get());
				menuParty.setItem(13, new Item(membersOnLineList.get(i+3)).withName("§6"+membersOnLineList.get(i+3)).withLore(new String[] {}).get());
				menuParty.setItem(14, new Item(membersOnLineList.get(i+4)).withName("§6"+membersOnLineList.get(i+4)).withLore(new String[] {}).get());
				menuParty.setItem(15, new Item(membersOnLineList.get(i+5)).withName("§6"+membersOnLineList.get(i+5)).withLore(new String[] {}).get());
				menuParty.setItem(16, new Item(membersOnLineList.get(i+6)).withName("§6"+membersOnLineList.get(i+6)).withLore(new String[] {}).get());
				break;
			default:
				break;
		}
	}
}
