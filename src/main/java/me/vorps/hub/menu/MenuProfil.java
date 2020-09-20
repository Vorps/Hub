package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.Menu;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuProfil extends Menu {

	public MenuProfil(UUID uuid){
        super(uuid, new byte[] {2, 6}, Bukkit.createInventory(null, 45, "§6Profil"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {4, 0}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {27, 1}, {35, 1}, {37, 1}, {38, 0}, {39, 1}, {40, 0}, {41, 1}, {42, 0}, {43, 1}, {44, 0}}, Hub.getInstance());
        super.setItem(18, new ItemBuilder(Material.SUNFLOWER).withName("§6Amis").withLore(new String[] {"§a- §7Affiche les amis en ligne", "§a- §7Rejoindre ses amis", "§a- §7Inviter des amis", "§a- §7Ajouter ou suprimer des joueurs dans votre liste d'amis"}).get());
        super.setItem(20, new ItemBuilder(Material.WRITABLE_BOOK).withName("§6Statistiques").withLore(new String[] {"§7Statistique du joueur"}).get());
		Navigator.profil(uuid, menu);
        super.setItem(24, new ItemBuilder(Material.COMPARATOR).withName("§6Paramètres").withLore(new String[] {"§7Préférences"}).get());
        super.setItem(26, new ItemBuilder(Material.MAGMA_CREAM).withName("§6Party").withLore(new String[] {"§a- §7Affiche les membres du groupe en ligne", "§a- §7Rejoindre une party", "§a- §7Ajouter ou suprimer des membre de votre party"}).get());
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
	}

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e){
        switch (e.getCurrentItem().getType()){
            case COMPARATOR:
                new MenuSettings(uuid, false);
                break;
            case MAGMA_CREAM:
                MenuParty.createMenu(uuid);
                break;
            case ARROW:
                Bukkit.getPlayer(uuid).closeInventory();
                break;
            case SUNFLOWER:
                MenuFriends.createMenu(uuid);
                break;
            case WRITABLE_BOOK:
                MenuGameStats.createMenu(uuid);
            default:
                break;
        }
    }
}
