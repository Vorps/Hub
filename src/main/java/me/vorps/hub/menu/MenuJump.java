package me.vorps.hub.menu;

import me.vorps.fortycube.menu.MenuRecursive;
import me.vorps.hub.Object.Jumps;
import me.vorps.hub.PlayerData;
import me.vorps.hub.Settings;
import me.vorps.fortycube.menu.Item;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Project Hub Created by Vorps on 01/02/2016 at 01:41.
 */
public class MenuJump extends MenuRecursive{

	private MenuJump(Player player, ArrayList<Item> list){
        super(new byte[] {6},  Bukkit.createInventory(null, 27, "§6Jumps"), new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {17, 0}, {19, 0}, {20, 0}, {21, 0}, {22, 0}, {23, 0}, {24, 0}, {25, 0}, {26, 0}}, list, PlayerData.getPlayerData(player.getName()).getLang(), 7, 9);
		initMenu(player, 1);
        player.openInventory(menu);
	}

    @Override
    public void initMenu(Player player, int page){
        menu.setItem(4, new Item(Material.LEATHER_BOOTS).withName("§6Jump").withColor(Color.PURPLE).get());
        menu.setItem(18, new Item(Material.ARROW).withName("§6<-Retour").withLore(new String[] {"§7Retour au menu Principal"}).get());
        getPage(page);
        player.updateInventory();
    }

    public static void createMenu(Player player){
        ArrayList<Item> list = new ArrayList<>();
        if(Jumps.nbrJump() == 1){
            Jumps.getListJumps().forEach((Jumps jump) -> MenuJumpDifficulty.createMenu(player, jump));
        } else {
            Jumps.getListJumps().forEach((Jumps jump) -> {
                list.add(jump.getIcon().get(PlayerData.getPlayerData(player.getName()).getLang()));
            });
            new MenuJump(player, list);
        }
    }

    @Override
    public void interractInventory(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (itemStack.getType()) {
            case ARROW:
                MenuPrincipal.createMenu(player);
                break;
            case PAPER:
                initMenu(player, page+1);
                break;
            case EMPTY_MAP:
                initMenu(player, page-1);
                break;
            default:
                break;
        }
        Jumps jumps = Jumps.getJump(itemStack.getItemMeta().getDisplayName(), PlayerData.getPlayerData(player.getName()).getLang());
        if(jumps != null){
            MenuJumpDifficulty.createMenu(player, jumps);
        }
    }
}
