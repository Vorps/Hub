package sirmc.vorps.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

import java.util.ArrayList;
import java.util.List;

public class MenuFriends {
	private static List<String> des = new ArrayList<>();

	public static void HubMenuFriends(PlayerHub playerHub, int page){
		playerHub.UpdateFriends();
		Inventory menuFriends;
		if(playerHub.getFriends().getFriendsOnLineList().size() <= 21){
			menuFriends = Bukkit.createInventory(null, 45, "§6Profil > Amis");
		} else {
			menuFriends = Bukkit.createInventory(null, 45, "§6Profil>Amis|§4"+page);
		}
		menuFriends.setItem(0, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(1, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(2, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(3, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(4, new Item(175).withName("§6Amis en ligne").withLore(new String[] {"§7Affiche vos amis en ligne", "§7En ligne : §a"+playerHub.getFriends().getFriendsOnLineList().size(), "§7Hors ligne : §7"+(playerHub.getFriends().getFriendsOnLineList().size()-playerHub.getFriends().getFriendsOnLineList().size())}).get());
		menuFriends.setItem(5, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(6, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(7, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(8, new Item(160).withData((byte) 5).withName(" ").get());
		 if(playerHub.getFriends().getFriendsOnLineList().size() <= 21){
			 listFriends(menuFriends, playerHub.getFriends().getFriendsOnLineList().size(), 0, 0, playerHub.getFriends().getFriendsOnLineList());
		 } else {
			 for(int i = page-1*21; i < (page-1*21)+21 ; i++) {
				 menuFriends.setItem(i + 10, new Item(playerHub.getFriends().getFriendsOnLineList().get(i)).withName("§6" + playerHub.getFriends().getFriendsOnLineList().get(i)).withLore(new String[]{"§7Vous pouvez inviter ou rejoindre cet ami", "§eClic gauche -> §aRejoindre", "§eClic droit -> §aInviter"}).get());
			 }
		 }
		menuFriends.setItem(9, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(17, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(18, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(26, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(27, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(35, new Item(160).withData((byte) 5).withName(" ").get());
		if(page > 1){
			menuFriends.setItem(36, new Item(Material.PAPER).withName("§6<- page "+(page-1)).withLore(new String[] {"§7Page pr§c§dente", ""+(page-1)}).get());
		} else {
			menuFriends.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
		}
		menuFriends.setItem(37, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(38, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(39, new Item(Material.ITEM_FRAME).withName("§6Ajouter un ami").withLore(new String[] {"§7Ajouter un joueur de votre liste d'amis"}).get());
		menuFriends.setItem(40, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(41, new Item(Material.BARRIER).withName("§6Supprimer un ami").withLore(new String[] {"§7Supprimer un joueur de votre liste d'amis"}).get());
		menuFriends.setItem(42, new Item(160).withData((byte) 5).withName(" ").get());
		menuFriends.setItem(43, new Item(160).withData((byte) 5).withName(" ").get());
		if(playerHub.getFriends().getFriendsOnLineList().size() <= 21){
			menuFriends.setItem(44, new Item(160).withData((byte) 5).withName(" ").get());
		} else {
			menuFriends.setItem(44, new Item(Material.PAPER).withName("§6Page "+(page+1)+" ->").withLore(new String[] {"§7Page suivante", ""+(page+1)}).get());
		}
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuFriends);
	}
	
	public static void HubMenuRemoveAmis(PlayerHub playerHub, int page){

		playerHub.UpdateFriends();
		Inventory menuFriendsRemove;
		if(playerHub.getFriends().getFriends().size() <= 21){
			menuFriendsRemove= Bukkit.createInventory(null, 45, "§6[X] Supprimer");
		} else {
			menuFriendsRemove= Bukkit.createInventory(null, 45, "§6[X] Supprimer | Page §4"+page);
		}
		menuFriendsRemove.setItem(0, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(1, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(2, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(3, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(4, new Item(Material.BARRIER).withName("§6Supprimer un ami (Clic gauche)").withLore(new String[] {"§7Supprime un joueur § votre liste d'amis"}).get());
		menuFriendsRemove.setItem(5, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(6, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(7, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(8, new Item(160).withData((byte) 14).withName(" ").get());
		des.clear();
		des.add("§7Suppression d'un joueur § votre liste d'amis");
		des.add("§eClic gauche -> §aSupprimer");
		 if(playerHub.getFriends().getFriends().size() <= 21){
			 listFriends(menuFriendsRemove, playerHub.getFriends().getFriends().size(), 0, 0, playerHub.getFriends().getFriends());
		 } else {
			 for(int i = page-1*21; i < (page-1*21)+21 ; i++){
				 menuFriendsRemove.setItem(i+10, new Item(playerHub.getFriends().getFriends().get(i)).withName(playerHub.getFriends().getFriends().get(i)).withLore(des.toArray(new String[des.size()])).get());
			 }
		 }
		des.clear();
		menuFriendsRemove.setItem(9, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(17, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(18, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(26, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(27, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(35, new Item(160).withData((byte) 14).withName(" ").get());
		if(page > 1){
			menuFriendsRemove.setItem(36, new Item(Material.PAPER).withName("§6<- page "+(page-1)).withLore(new String[] {"§7Page remove précédente", ""+(page-1)}).get());
		} else {
			menuFriendsRemove.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu amis"}).get());
		}
		menuFriendsRemove.setItem(37, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(38, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(39, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(40, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(41, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(42, new Item(160).withData((byte) 14).withName(" ").get());
		menuFriendsRemove.setItem(43, new Item(160).withData((byte) 14).withName(" ").get());
		if(playerHub.getFriends().getFriends().size() <= 21){
			menuFriendsRemove.setItem(44, new Item(160).withData((byte) 14).withName(" ").get());
		} else {
			menuFriendsRemove.setItem(44, new Item(Material.PAPER).withName("§6Page "+(page+1)+" ->").withLore(new String[] {"§7Page de suppression suivante", ""+(page+1)}).get());
		}
		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuFriendsRemove);
	}

	private static void listFriends(Inventory menuFriendsRemove, int nbr, int i, int coef, ArrayList<String> listFriends){
		 switch (nbr) {
		 	case 0:
			 break;
			case 1:
				menuFriendsRemove.setItem(22+coef, new Item(listFriends.get(i)).withName(listFriends.get(i)).withLore(des.toArray(new String[des.size()])).get());
				break;
			case 2:
				 menuFriendsRemove.setItem(21+coef, new Item(listFriends.get(i)).withName(listFriends.get(i)).withLore(des.toArray(new String[des.size()])).get());
				 menuFriendsRemove.setItem(23+coef, new Item(listFriends.get(i+1)).withName(listFriends.get(i+1)).withLore(des.toArray(new String[des.size()])).get());
				break;
			case 3:
				 menuFriendsRemove.setItem(21+coef, new Item(listFriends.get(i)).withName(listFriends.get(i)).withLore(des.toArray(new String[des.size()])).get());
				 menuFriendsRemove.setItem(22+coef, new Item(listFriends.get(i+1)).withName(listFriends.get(i+1)).withLore(des.toArray(new String[des.size()])).get());
				 menuFriendsRemove.setItem(23+coef, new Item(listFriends.get(i+2)).withName(listFriends.get(i+2)).withLore(des.toArray(new String[des.size()])).get());
				 break;
			case 4:
				 menuFriendsRemove.setItem(20+coef, new Item(listFriends.get(i)).withName(listFriends.get(i)).withLore(des.toArray(new String[des.size()])).get());
				 menuFriendsRemove.setItem(21+coef, new Item(listFriends.get(i+1)).withName(listFriends.get(i+1)).withLore(des.toArray(new String[des.size()])).get());
				 menuFriendsRemove.setItem(23+coef, new Item(listFriends.get(i+2)).withName(listFriends.get(i+2)).withLore(des.toArray(new String[des.size()])).get());
				 menuFriendsRemove.setItem(24+coef, new Item(listFriends.get(i+3)).withName(listFriends.get(i+3)).withLore(des.toArray(new String[des.size()])).get());
				 break;
			case 5:
				menuFriendsRemove.setItem(20+coef, new Item(listFriends.get(i)).withName(listFriends.get(i)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(21+coef, new Item(listFriends.get(i+1)).withName(listFriends.get(i+1)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(22+coef, new Item(listFriends.get(i+2)).withName(listFriends.get(i+2)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(23+coef, new Item(listFriends.get(i+3)).withName(listFriends.get(i+3)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(24+coef, new Item(listFriends.get(i+4)).withName(listFriends.get(i+4)).withLore(des.toArray(new String[des.size()])).get());
				break;
			case 6:
				menuFriendsRemove.setItem(19+coef, new Item(listFriends.get(i)).withName(listFriends.get(i)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(20+coef, new Item(listFriends.get(i+1)).withName(listFriends.get(i+1)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(21+coef, new Item(listFriends.get(i+2)).withName(listFriends.get(i+2)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(23+coef, new Item(listFriends.get(i+3)).withName(listFriends.get(i+3)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(24+coef, new Item(listFriends.get(i+4)).withName(listFriends.get(i+4)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(25+coef, new Item(listFriends.get(i+5)).withName(listFriends.get(i+5)).withLore(des.toArray(new String[des.size()])).get());
				break;
			case 7:
				menuFriendsRemove.setItem(19+coef, new Item(listFriends.get(i)).withName(listFriends.get(i)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(20+coef, new Item(listFriends.get(i+1)).withName(listFriends.get(i+1)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(21+coef, new Item(listFriends.get(i+2)).withName(listFriends.get(i+2)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(22+coef, new Item(listFriends.get(i+3)).withName(listFriends.get(i+3)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(23+coef, new Item(listFriends.get(i+4)).withName(listFriends.get(i+4)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(24+coef, new Item(listFriends.get(i+5)).withName(listFriends.get(i+5)).withLore(des.toArray(new String[des.size()])).get());
				menuFriendsRemove.setItem(25+coef, new Item(listFriends.get(i+6)).withName(listFriends.get(i+6)).withLore(des.toArray(new String[des.size()])).get());
				break;
			default:
				if(nbr <= 14){
					listFriends(menuFriendsRemove, 7, i, 0, listFriends);
					listFriends(menuFriendsRemove, nbr-7, i+7, -9, listFriends);
				} else {
					listFriends(menuFriendsRemove, 7, i, 0, listFriends);
					listFriends(menuFriendsRemove, 7, i+7, -9, listFriends);
					listFriends(menuFriendsRemove, nbr-14, i+14, 9, listFriends);
				}
				break;
			}
	}
}
