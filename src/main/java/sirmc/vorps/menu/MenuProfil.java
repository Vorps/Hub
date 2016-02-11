package sirmc.vorps.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import sirmc.vorps.PlayerHub;
import sirmc.vorps.utils.Item;

public class MenuProfil {
	private static List<String> des = new ArrayList<String>();
	
	public static void HubMenuProfil(PlayerHub playerHub){
		Inventory menuProfil = Bukkit.createInventory(null, 45, "§6Profil");
		menuProfil.setItem(0, new Item(160).withData((byte) 2).withName(" ").get());
		menuProfil.setItem(1, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(2, new Item(160).withData((byte) 2).withName(" ").get());
		menuProfil.setItem(3, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(4, new Item(160).withData((byte) 2).withName(" ").get());
		menuProfil.setItem(5, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(6, new Item(160).withData((byte) 2).withName(" ").get());
		menuProfil.setItem(7, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(8, new Item(160).withData((byte) 2).withName(" ").get());

		menuProfil.setItem(9, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(17, new Item(160).withData((byte) 6).withName(" ").get());

		menuProfil.setItem(18, new Item(175).withData((byte) 0).withName("§6Amis").withLore(new String[] {"§a- §7Affiche les amis en ligne", "§a- §7Rejoindre ses amis", "§a- §7Inviter des amis", "§a- §7Ajouter ou suprimer des joueurs dans votre liste d'amis"}).get());
		menuProfil.setItem(20, new Item(386).withData((byte) 0).withName("§6Statistiques").withLore(new String[] {"§7Stats"}).get());
		Navigator.Profil(playerHub, menuProfil);
		menuProfil.setItem(24, new Item(Material.REDSTONE_COMPARATOR).withName("§6Paramètres").withLore(new String[] {"§7Préférences"}).get());
		menuProfil.setItem(26, new Item(Material.MAGMA_CREAM).withName("§6Party").withLore(new String[] {"§a- §7Affiche les membres du groupe en ligne", "§a- §7Rejoindre une party", "§a- §7Ajouter ou suprimer des membre de votre party"}).get());
		menuProfil.setItem(27, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(35, new Item(160).withData((byte) 6).withName(" ").get());

		menuProfil.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
		menuProfil.setItem(37, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(38,  new Item(160).withData((byte) 2).withName(" ").get());
		menuProfil.setItem(39, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(40,  new Item(160).withData((byte) 2).withName(" ").get());
		menuProfil.setItem(41, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(42,  new Item(160).withData((byte) 2).withName(" ").get());
		menuProfil.setItem(43, new Item(160).withData((byte) 6).withName(" ").get());
		menuProfil.setItem(44,  new Item(160).withData((byte) 2).withName(" ").get());

		Bukkit.getPlayer(playerHub.getPlayerName()).openInventory(menuProfil);
	}
}
