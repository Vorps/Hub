package me.vorps.hub.menu;

import me.vorps.fortycube.menu.Menu;
import me.vorps.hub.Object.Game;
import me.vorps.hub.PlayerData;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuProfil extends Menu {

	public MenuProfil(Player player){
        super(new byte[] {2, 6}, Bukkit.createInventory(null, 45, "§6Profil"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {4, 0}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {27, 1}, {35, 1}, {37, 1}, {38, 0}, {39, 1}, {40, 0}, {41, 1}, {42, 0}, {43, 1}, {44, 0}});
		menu.setItem(18, new Item(175).withData((byte) 0).withName("§6Amis").withLore(new String[] {"§a- §7Affiche les amis en ligne", "§a- §7Rejoindre ses amis", "§a- §7Inviter des amis", "§a- §7Ajouter ou suprimer des joueurs dans votre liste d'amis"}).get());
        menu.setItem(20, new Item(386).withData((byte) 0).withName("§6Statistiques").withLore(new String[] {"§7Statistique du joueur"}).get());
		Navigator.profil(PlayerData.getPlayerData(player.getName()), player, menu);
        menu.setItem(24, new Item(Material.REDSTONE_COMPARATOR).withName("§6Paramètres").withLore(new String[] {"§7Préférences"}).get());
        menu.setItem(26, new Item(Material.MAGMA_CREAM).withName("§6Party").withLore(new String[] {"§a- §7Affiche les membres du groupe en ligne", "§a- §7Rejoindre une party", "§a- §7Ajouter ou suprimer des membre de votre party"}).get());
        menu.setItem(36, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        player.openInventory(menu);
	}

    @Override
    public void interractInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (e.getCurrentItem().getType()){
            case REDSTONE_COMPARATOR:
                new MenuSettings(player);
                break;
            case MAGMA_CREAM:
                MenuParty.createMenu(player);
                break;
            case ARROW:
                player.closeInventory();
                break;
            default:
                break;
        }
        switch (itemStack.getData().getItemTypeId()){
            case 175:
                MenuFriends.createMenu(player);
                break;
            case 386:
                MenuGameStats.createMenu(player);
            default:
                break;
        }
    }
}
