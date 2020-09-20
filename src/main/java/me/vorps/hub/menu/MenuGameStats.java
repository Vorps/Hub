package me.vorps.hub.menu;

import me.vorps.hub.Hub;
import net.vorps.api.menu.ItemBuilder;
import net.vorps.api.menu.MenuRecursive;
import me.vorps.hub.Object.Game;
import me.vorps.hub.Object.Stats;
import me.vorps.hub.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project Hub Created by Vorps on 09/05/2016 at 17:23.
 */
public class MenuGameStats extends MenuRecursive {

    public MenuGameStats(UUID uuid, ArrayList<ItemBuilder> list){
        super(uuid, new byte[] {1, 2}, Bukkit.createInventory(null, 45, "§6Profil -> Statistique"), new int[][] {{0, 0}, {1, 1}, {2, 0}, {3, 1}, {5, 1}, {6, 0}, {7, 1}, {8, 0}, {9, 1}, {17, 1}, {18, 0}, {26, 0}, {27, 1}, {35, 1}, {37, 1}, {38, 0}, {39, 1}, {40, 0}, {41, 1}, {42, 0}, {43, 1}, {44, 0}}, list, PlayerData.getLang(uuid), 7, 9, Type.DYNAMIQUE, Hub.getInstance());
    }

    public void initMenu(UUID uuid, int page){
        super.setItem(4, new ItemBuilder(Material.WRITABLE_BOOK).withName("§6Statistiques").withLore(new String[] {"§7Statistique du joueur"}).get());
        super.setItem(36, new ItemBuilder(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu profil"}).get());
    }

    public static void createMenu(UUID uuid){
        ArrayList<ItemBuilder> list = new ArrayList<>();
        Game.getGameList().values().forEach((Game game) -> list.add(new ItemBuilder(game.getIcon().get(PlayerData.getLang(uuid))).withLore(new Stats(uuid, game.getDatabase()).getStats())));
        new MenuGameStats(uuid, list);
    }

    @Override
    protected void back(UUID uuid) {
        new MenuProfil(uuid);
    }

    @Override
    public void interactInventory(UUID uuid, Material type, InventoryClickEvent e){

    }
}
