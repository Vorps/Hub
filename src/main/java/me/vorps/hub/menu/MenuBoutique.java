package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
import me.vorps.hub.PlayerData;
import me.vorps.hub.data.SettingsHub;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuBoutique extends MenuRecursive{

	public MenuBoutique(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {11, 5}, Bukkit.createInventory(null, 45, "§6Boutique"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {4, 0}, {6, 0}, {7, 1}, {8, 0}, {18, 0}, {26, 1}, {37, 1}, {38, 0}, {40, 0}, {42, 0}, {43, 1}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
	}

    public void initMenu(UUID uuid, int page){
        super.setItem(3, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).withAction(e -> {Bukkit.getPlayer(uuid).sendMessage("§6Boutique en ligne : §b§n"+ SettingsHub.getSite());Bukkit.getPlayer(uuid).closeInventory();}).withName("§6Obtenir des étoiles").withLore(new String[] {"§7Obtenir des étoiles"}).get());
        super.setItem(5, new ItemBuilder(Material.PAPER).withAction(e -> MenuTransactions.createMenu(uuid)).withName("§6Transactions éffectuées").withLore(new String[] {"§7Historique des achats"}).get());
        super.setItem(9, new ItemBuilder(Material.BONE).withAction(e -> MenuPets.createMenu(uuid)).withName("§6Pets").withLore(new String[] {"§7Acheter un pets"}).get());
        super.setItem(17, new ItemBuilder(Material.BLAZE_ROD).withAction(e ->  MenuGadgets.createMenu(uuid)).withName("§6Gadgets").withLore(new String[] {"§7Acheter des gadgets"}).get());
        super.setItem(27, new ItemBuilder(Material.GLOWSTONE_DUST).withAction(e -> MenuParticles.createMenu(uuid)).withName("§6Particules").withLore(new String[] {"§7Acheter des effets de particules"}).get());
        super.setItem(35, new ItemBuilder(Material.DIAMOND_CHESTPLATE).withAction(e -> new MenuCostume(uuid)).withName("§6Costumes").withLore(new String[] {"§7Acheter des costumes"}).get());
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au jeu"}).get());
        super.setItem(39, new ItemBuilder(Material.ENCHANTING_TABLE).withAction(e -> MenuGrades.createMenu(uuid)).withName("§6Grades").withLore(new String[] {"§7Obtenire un grade"}).get());
        super.setItem(41, new ItemBuilder(PotionType.STRENGTH).withAction(e -> MenuBonus.createMenu(uuid)).withName("§6Bonus").withLore(new String[] {"§7Obtenire des bonus"}).get());
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        //Game.getGameList().values().forEach((Game game) -> list.add(game.getIcon().get(PlayerData.getLang(uuid))));
        new MenuBoutique(uuid, list);
    }


    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e) {

    }
}
